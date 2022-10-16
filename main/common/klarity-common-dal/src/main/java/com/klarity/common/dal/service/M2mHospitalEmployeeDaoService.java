package com.klarity.common.dal.service;

import com.klarity.common.dal.entity.M2mHospitalEmployeeEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 医院与员工之间的关系表 服务类
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
public interface M2mHospitalEmployeeDaoService extends IService<M2mHospitalEmployeeEntity> {
    /**
     * 查询医院和员工之间的雇佣关系
     *
     * @param employeeId
     * @param hospitalId
     * @param employmentState
     * @return
     */
    M2mHospitalEmployeeEntity getM2mHospitalEmployee(String employeeId, String hospitalId, String employmentState);

    /**
     * 查询员工服务的医院的数量
     *
     * @param employeeId
     * @param employmentState
     * @return
     */
    List<String> getM2mHospitalEmployeeList(String employeeId, String employmentState);
}
