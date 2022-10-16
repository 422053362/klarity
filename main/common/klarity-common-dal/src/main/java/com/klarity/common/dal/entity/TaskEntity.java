package com.klarity.common.dal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 任务记录表
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tbl_klarity_task")
public class TaskEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 任务Id
     */
    @TableField("task_id")
    private String taskId;

    /**
     * 医院Id
     */
    @TableField("hospital_id")
    private String hospitalId;

    /**
     * 员工Id
     */
    @TableField("owner_id")
    private String ownerId;

    /**
     * 任务标题
     */
    @TableField("title")
    private String title;

    /**
     * 任务描述
     */
    @TableField("description")
    private String description;

    /**
     * 任务优先级 URGENT, HIGHT, and LOW
     */
    @TableField("priority")
    private String priority;

    /**
     * 任务状态 OPEN, FAILED, or COMPLETED.
     */
    @TableField("status")
    private String status;


}
