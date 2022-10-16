package com.klarity.core.repository.impl;

import com.klarity.common.dal.entity.EmployeeEntity;
import com.klarity.common.dal.entity.HospitalEntity;
import com.klarity.common.dal.entity.M2mHospitalEmployeeEntity;
import com.klarity.common.dal.service.EmployeeDaoService;
import com.klarity.common.dal.service.HospitalDaoService;
import com.klarity.common.dal.service.M2mHospitalEmployeeDaoService;
import com.klarity.common.facade.enums.EmploymentStateEnums;
import com.klarity.common.facade.request.EmployeeCreateRequest;
import com.klarity.core.model.context.EmployeeCreateContext;
import com.klarity.core.repository.EmployeeContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 16:46
 */
@Repository
public class EmployeeContextRepositoryImpl implements EmployeeContextRepository {
    @Autowired
    EmployeeDaoService employeeDaoService;
    @Autowired
    M2mHospitalEmployeeDaoService m2mHospitalEmployeeDaoService;
    @Autowired
    HospitalDaoService hospitalDaoService;

    @Override
    public EmployeeCreateContext buildEmployeeCreateContext(EmployeeCreateRequest request) {
        String email = request.getEmail();
        String hospitalId = request.getHospitalId();
        //开始构建上下文
        EmployeeCreateContext context = new EmployeeCreateContext();
        context.setRequest(request);
        //医院信息放入上下文
        HospitalEntity hospitalEntity = hospitalDaoService.getHospitalByHospitalId(hospitalId);
        context.setHospitalEntity(hospitalEntity);
        //如果用户存在，放入上下文
        EmployeeEntity employeeEntity = employeeDaoService.selectEmployeeByEmail(email);
        context.setEmployeeEntity(employeeEntity);
        //员工已经服务的医院的Id
        if (employeeEntity != null) {
            String employeeId = employeeEntity.getEmployeeId();
            List<String> employmentHospitalIdList = this.m2mHospitalEmployeeDaoService.getM2mHospitalEmployeeList(employeeId, EmploymentStateEnums.WORK.getCode());
            context.setEmploymentHospitalIdList(employmentHospitalIdList);
        }
        return context;
    }

    @Override
    public void persistEmployeeCreateContext(EmployeeCreateContext context) {
        EmployeeEntity employeeEntity = context.getNewEmployeeEntity();
        M2mHospitalEmployeeEntity m2mHospitalEmployeeEntity = context.getNewM2mHospitalEmployeeEntity();
        if (employeeEntity != null) {
            employeeDaoService.save(employeeEntity);
        }
        if (m2mHospitalEmployeeEntity != null) {
            m2mHospitalEmployeeDaoService.save(m2mHospitalEmployeeEntity);
        }
    }
}
