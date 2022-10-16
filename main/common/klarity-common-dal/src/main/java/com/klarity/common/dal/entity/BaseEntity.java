package com.klarity.common.dal.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.royee.common.utils.share.base.BaseBean;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author: pengcheng091
 */

@Getter
@Setter
public class BaseEntity extends BaseBean {
    /**
     * 自增主键Id
     */
    @TableId(type = IdType.AUTO)
    Long id;

    /**
     * 租户Id
     */
    @TableField(fill = FieldFill.INSERT)
    private String tenantId;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String creator;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String modifier;

    /**
     * 是否删除
     */
    @TableLogic(delval = "now()")
    @TableField(fill = FieldFill.INSERT)
    private Long deleted;


}
