package com.klarity.common.dal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.klarity.common.dal.entity.BaseEntity;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 任务操作记录表
 * </p>
 *
 * @author pengcheng091
 * @since 2022-10-16
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tbl_klarity_task_assignment")
public class TaskAssignmentEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 任务Id
     */
    @TableField("task_id")
    private String taskId;

    /**
     * 员工Id
     */
    @TableField("employee_id")
    private String employeeId;

    /**
     * 分配记录状态
     */
    @TableField("state")
    private String state;


}
