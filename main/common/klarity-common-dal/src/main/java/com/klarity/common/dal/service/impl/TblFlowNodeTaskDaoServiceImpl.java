package com.klarity.common.dal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.klarity.common.dal.entity.TblFlowNodeTaskEntity;
import com.klarity.common.dal.mapper.TblFlowNodeTaskMapper;
import com.klarity.common.dal.service.TblFlowNodeTaskDaoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 业务节点实例id 服务实现类
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
@Service
public class TblFlowNodeTaskDaoServiceImpl extends ServiceImpl<TblFlowNodeTaskMapper, TblFlowNodeTaskEntity> implements TblFlowNodeTaskDaoService {

    @Override
    public TblFlowNodeTaskEntity getOneBy(String flowTaskId, String nodeName) {
        LambdaQueryWrapper<TblFlowNodeTaskEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblFlowNodeTaskEntity::getFlowTaskId, flowTaskId);
        wrapper.eq(TblFlowNodeTaskEntity::getNodeName, nodeName);
        return this.getOne(wrapper);
    }

    @Override
    public TblFlowNodeTaskEntity lockOneBy(String flowTaskId, String nodeName) {
        LambdaQueryWrapper<TblFlowNodeTaskEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblFlowNodeTaskEntity::getFlowTaskId, flowTaskId);
        wrapper.eq(TblFlowNodeTaskEntity::getNodeName, nodeName);
        wrapper.last("for update");
        return this.getOne(wrapper);
    }

    @Override
    public List<TblFlowNodeTaskEntity> listByFlowTaskId(String flowTaskId) {
        LambdaQueryWrapper<TblFlowNodeTaskEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblFlowNodeTaskEntity::getFlowTaskId, flowTaskId);
        return this.list(wrapper);
    }
}
