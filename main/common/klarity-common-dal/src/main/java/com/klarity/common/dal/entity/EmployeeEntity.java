package com.klarity.common.dal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 员工信息表
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tbl_klarity_employee")
public class EmployeeEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 员工Id
     */
    @TableField("employee_id")
    private String employeeId;

    /**
     * 员工名称
     */
    @TableField("employee_name")
    private String employeeName;

    /**
     * 员工邮箱
     */
    @TableField("email")
    private String email;


}
