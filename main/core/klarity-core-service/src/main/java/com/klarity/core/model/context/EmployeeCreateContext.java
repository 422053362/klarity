package com.klarity.core.model.context;

import com.klarity.common.dal.entity.EmployeeEntity;
import com.klarity.common.dal.entity.HospitalEntity;
import com.klarity.common.dal.entity.M2mHospitalEmployeeEntity;
import com.klarity.common.facade.enums.EmploymentStateEnums;
import com.klarity.common.facade.request.EmployeeCreateRequest;
import com.klarity.common.util.IdUtils;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 16:39
 */
@Data
public class EmployeeCreateContext {

    /**
     * 本次业务请求参数
     */
    EmployeeCreateRequest request;

    /**
     * 已经注册的员工信息
     */
    EmployeeEntity employeeEntity;

    /**
     * 本次业务涉及的医院
     */
    HospitalEntity hospitalEntity;

    /**
     * 员工已经服务的医院Id
     */
    List<String> employmentHospitalIdList;


    /**
     * 本次需要注册员工的信息
     */
    EmployeeEntity newEmployeeEntity;
    /**
     * 本次需要保存的雇佣关系
     */
    M2mHospitalEmployeeEntity newM2mHospitalEmployeeEntity;

    /**
     * 构建一个新的员工信息
     *
     * @return
     */
    public EmployeeEntity buildNewEmployeeEntity() {
        EmployeeEntity entity = new EmployeeEntity();
        String id = IdUtils.nextVal();
        entity.setEmployeeId(id);
        entity.setEmployeeName(request.getEmployeeName());
        entity.setEmail(request.getEmail());
        return entity;
    }

    /**
     * 构建一个雇佣关系
     *
     * @param entity
     * @return
     */
    public M2mHospitalEmployeeEntity buildM2mHospitalEmployeeEntity(EmployeeEntity entity) {
        M2mHospitalEmployeeEntity m2mHospitalEmployeeEntity = new M2mHospitalEmployeeEntity();
        m2mHospitalEmployeeEntity.setEmployeeId(entity.getEmployeeId());
        m2mHospitalEmployeeEntity.setHospitalId(hospitalEntity.getHospitalId());
        m2mHospitalEmployeeEntity.setStatus(EmploymentStateEnums.WORK.getCode());
        return m2mHospitalEmployeeEntity;
    }

    /**
     * 存在雇佣关系
     *
     * @return
     */
    public Boolean hasEmployment() {
        return CollectionUtils.isNotEmpty(employmentHospitalIdList) && employmentHospitalIdList.contains(hospitalEntity.getHospitalId());
    }
}
