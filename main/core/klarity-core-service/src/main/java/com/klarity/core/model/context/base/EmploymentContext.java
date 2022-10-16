package com.klarity.core.model.context.base;

import com.klarity.common.dal.entity.EmployeeEntity;
import com.klarity.common.dal.entity.HospitalEntity;
import com.klarity.common.dal.entity.M2mHospitalEmployeeEntity;
import lombok.Data;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 19:23
 */
@Data
public abstract class EmploymentContext {
    //雇佣关系上下文
    EmployeeEntity employeeEntity;
    HospitalEntity hospitalEntity;
    M2mHospitalEmployeeEntity m2mHospitalEmployeeEntity;

    /**
     * 是否存在雇佣关系
     *
     * @return
     */
    public Boolean hasEmployment() {
        return employeeEntity != null && hospitalEntity != null && m2mHospitalEmployeeEntity != null;
    }
}
