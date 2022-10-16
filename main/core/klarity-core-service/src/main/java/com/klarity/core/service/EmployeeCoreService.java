package com.klarity.core.service;

import com.klarity.core.model.context.EmployeeCreateContext;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 16:44
 */
public interface EmployeeCoreService {
    /**
     * 注册一个员工到指定的医院
     *
     * @param createEmployeeContext
     * @return
     */
    EmployeeCreateContext createEmployee(EmployeeCreateContext createEmployeeContext);
}
