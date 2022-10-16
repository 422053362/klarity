package com.klarity.common.dal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 医院数据信息表
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tbl_klarity_hospital")
public class HospitalEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 医院id
     */
    @TableField("hospital_id")
    private String hospitalId;

    /**
     * 医院名称
     */
    @TableField("hospital_name")
    private String hospitalName;

    /**
     * 扩展信息
     */
    @TableField("ext_info")
    private String extInfo;


}
