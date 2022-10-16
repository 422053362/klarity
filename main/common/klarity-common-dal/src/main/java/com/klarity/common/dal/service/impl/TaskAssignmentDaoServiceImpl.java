package com.klarity.common.dal.service.impl;

import com.klarity.common.dal.entity.TaskAssignmentEntity;
import com.klarity.common.dal.mapper.TaskAssignmentMapper;
import com.klarity.common.dal.service.TaskAssignmentDaoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务操作记录表 服务实现类
 * </p>
 *
 * @author pengcheng091
 * @since 2022-10-16
 */
@Service
public class TaskAssignmentDaoServiceImpl extends ServiceImpl<TaskAssignmentMapper, TaskAssignmentEntity> implements TaskAssignmentDaoService {

}
