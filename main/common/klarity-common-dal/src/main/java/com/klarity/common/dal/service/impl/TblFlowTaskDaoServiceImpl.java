package com.klarity.common.dal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.klarity.common.dal.entity.TblFlowTaskEntity;
import com.klarity.common.dal.mapper.TblFlowTaskMapper;
import com.klarity.common.dal.service.TblFlowTaskDaoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务流程实例 服务实现类
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
@Service
public class TblFlowTaskDaoServiceImpl extends ServiceImpl<TblFlowTaskMapper, TblFlowTaskEntity> implements TblFlowTaskDaoService {

    @Override
    public TblFlowTaskEntity getByFlowTaskId(String flowTaskId) {
        LambdaQueryWrapper<TblFlowTaskEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblFlowTaskEntity::getFlowTaskId, flowTaskId);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public TblFlowTaskEntity lockFlowTask(String flowTaskId) {
        LambdaQueryWrapper<TblFlowTaskEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblFlowTaskEntity::getFlowTaskId, flowTaskId);
        wrapper.last("for update");
        return this.baseMapper.selectOne(wrapper);
    }
}
