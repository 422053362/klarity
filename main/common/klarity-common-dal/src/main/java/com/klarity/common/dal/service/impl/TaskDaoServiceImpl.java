package com.klarity.common.dal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.klarity.common.dal.entity.TaskEntity;
import com.klarity.common.dal.mapper.TaskMapper;
import com.klarity.common.dal.service.TaskDaoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务记录表 服务实现类
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
@Service
public class TaskDaoServiceImpl extends ServiceImpl<TaskMapper, TaskEntity> implements TaskDaoService {

    @Override
    public TaskEntity getTaskEntityByTaskId(String taskId) {
        LambdaQueryWrapper<TaskEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TaskEntity::getTaskId, taskId);
        return this.baseMapper.selectOne(wrapper);
    }
}
