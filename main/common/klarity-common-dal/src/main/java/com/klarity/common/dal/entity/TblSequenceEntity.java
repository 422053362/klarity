package com.klarity.common.dal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 全局唯一Id生成表
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tbl_sequence")
public class TblSequenceEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 业务节点名称
     */
    @TableField("seq_name")
    private String seqName;

    /**
     * 业务节点开始时间
     */
    @TableField("seq_value")
    private Long seqValue;

    /**
     * 业务节点结束时间
     */
    @TableField("min_value")
    private Long minValue;

    /**
     * 实例状态
     */
    @TableField("max_value")
    private Long maxValue;

    /**
     * 扩展字段
     */
    @TableField("step")
    private Long step;


}
