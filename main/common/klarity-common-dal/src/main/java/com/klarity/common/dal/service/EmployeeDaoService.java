package com.klarity.common.dal.service;

import com.klarity.common.dal.entity.EmployeeEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 员工信息表 服务类
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
public interface EmployeeDaoService extends IService<EmployeeEntity> {
    /**
     * @param email
     * @return
     */
    EmployeeEntity selectEmployeeByEmail(String email);

    /**
     *
     * @param employeeId
     * @return
     */
    EmployeeEntity getEmployeeByEmployeeId(String employeeId);
}
