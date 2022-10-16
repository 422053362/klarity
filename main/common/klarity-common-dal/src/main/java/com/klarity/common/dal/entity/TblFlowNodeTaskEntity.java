package com.klarity.common.dal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 业务节点实例id
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tbl_flow_node_task")
public class TblFlowNodeTaskEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 业务流程实例id
     */
    @TableField("flow_task_id")
    private String flowTaskId;

    /**
     * 业务节点名称
     */
    @TableField("node_name")
    private String nodeName;

    /**
     * 业务节点开始时间
     */
    @TableField("start_time")
    private LocalDateTime startTime;

    /**
     * 业务节点结束时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;

    /**
     * 实例状态
     */
    @TableField("status")
    private String status;

    /**
     * 扩展字段
     */
    @TableField("ext_value")
    private String extValue;


}
