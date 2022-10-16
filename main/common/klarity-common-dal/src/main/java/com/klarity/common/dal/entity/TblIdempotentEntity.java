package com.klarity.common.dal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 幂等记录表
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tbl_idempotent")
public class TblIdempotentEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 事务Id
     */
    @TableField("txn_id")
    private String txnId;

    /**
     * 事务状态
     */
    @TableField("txn_st")
    private String txnSt;


}
