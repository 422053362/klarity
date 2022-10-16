package com.klarity.common.dal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 事务消息记录表
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tbl_topic_message")
public class TblTopicMessageEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 业务节点名称
     */
    @TableField("transaction_id")
    private String transactionId;

    /**
     * 消息Topic
     */
    @TableField("topic")
    private String topic;

    /**
     * 消息Tag
     */
    @TableField("tag")
    private String tag;

    /**
     * 消息内容
     */
    @TableField("payload")
    private String payload;

    /**
     * 消息发送时间
     */
    @TableField("trigger_time")
    private LocalDateTime triggerTime;

    /**
     * 消息重试时间
     */
    @TableField("retry_times")
    private Integer retryTimes;

    /**
     * 消息状态
     */
    @TableField("status")
    private String status;


}
