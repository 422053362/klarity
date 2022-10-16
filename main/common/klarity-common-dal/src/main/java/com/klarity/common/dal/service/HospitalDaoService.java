package com.klarity.common.dal.service;

import com.klarity.common.dal.entity.HospitalEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 医院数据信息表 服务类
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
public interface HospitalDaoService extends IService<HospitalEntity> {
    /**
     * @param hospitalId
     * @return
     */
    HospitalEntity getHospitalByHospitalId(String hospitalId);
}
