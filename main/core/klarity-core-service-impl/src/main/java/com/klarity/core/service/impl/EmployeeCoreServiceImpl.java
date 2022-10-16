package com.klarity.core.service.impl;

import com.klarity.common.dal.entity.EmployeeEntity;
import com.klarity.common.dal.entity.M2mHospitalEmployeeEntity;
import com.klarity.core.model.context.EmployeeCreateContext;
import com.klarity.core.service.EmployeeCoreService;
import org.springframework.stereotype.Service;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 17:01
 */
@Service
public class EmployeeCoreServiceImpl implements EmployeeCoreService {
    @Override
    public EmployeeCreateContext createEmployee(EmployeeCreateContext createEmployeeContext) {
        EmployeeEntity existOne = createEmployeeContext.getEmployeeEntity();
        if (existOne == null) {
            existOne = createEmployeeContext.buildNewEmployeeEntity();
            createEmployeeContext.setNewEmployeeEntity(existOne);
            createEmployeeContext.setEmployeeEntity(existOne);
        }
        if (!createEmployeeContext.hasEmployment()) {
            //如果不存在雇佣关系，构建一个雇佣关系
            M2mHospitalEmployeeEntity m2mHospitalEmployeeEntity = createEmployeeContext.buildM2mHospitalEmployeeEntity(existOne);
            createEmployeeContext.setNewM2mHospitalEmployeeEntity(m2mHospitalEmployeeEntity);
        }
        return createEmployeeContext;
    }
}
