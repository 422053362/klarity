package com.klarity.common.dal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 医院与员工之间的关系表
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tbl_klarity_m2m_hospital_employee")
public class M2mHospitalEmployeeEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 医院Id
     */
    @TableField("hospital_id")
    private String hospitalId;

    /**
     * 员工Id
     */
    @TableField("employee_id")
    private String employeeId;

    /**
     * 雇佣关系状态
     */
    @TableField("status")
    private String status;


}
