package com.klarity.common.dal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 任务操作记录表
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tbl_klarity_task_action")
public class TaskActionEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 任务Id，一个任务多个执行记录
     */
    @TableField("task_id")
    private String taskId;

    /**
     * 操作名称
     */
    @TableField("action_name")
    private String actionName;

    /**
     * 操作名称
     */
    @TableField("action_info")
    private String actionInfo;


}
