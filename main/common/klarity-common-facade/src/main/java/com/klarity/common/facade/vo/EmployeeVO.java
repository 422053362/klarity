package com.klarity.common.facade.vo;

import lombok.Data;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 16:05
 */
@Data
public class EmployeeVO {
    /**
     * 员工Id
     */
    String employeeId;
    /**
     * 员工名称
     */
    String employeeName;
    /**
     * 员工邮箱
     */
    String email;
}
