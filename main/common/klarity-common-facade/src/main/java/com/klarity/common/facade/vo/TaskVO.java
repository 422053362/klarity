package com.klarity.common.facade.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 19:54
 */
@Data
public class TaskVO {
    /**
     * 任务Id
     */
    private String taskId;

    /**
     * 医院Id
     */
    private String hospitalId;

    /**
     * 员工Id
     */
    private String ownerId;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 任务优先级 URGENT, HIGHT, and LOW
     */
    private String priority;

    /**
     * 任务状态 OPEN, FAILED, or COMPLETED.
     */
    private String status;

}
