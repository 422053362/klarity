package com.klarity.common.dal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.klarity.common.dal.entity.HospitalEntity;
import com.klarity.common.dal.mapper.HospitalMapper;
import com.klarity.common.dal.service.HospitalDaoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 医院数据信息表 服务实现类
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
@Service
public class HospitalDaoServiceImpl extends ServiceImpl<HospitalMapper, HospitalEntity> implements HospitalDaoService {

    @Override
    public HospitalEntity getHospitalByHospitalId(String hospitalId) {
        LambdaQueryWrapper<HospitalEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HospitalEntity::getHospitalId, hospitalId);
        return this.baseMapper.selectOne(wrapper);
    }
}
