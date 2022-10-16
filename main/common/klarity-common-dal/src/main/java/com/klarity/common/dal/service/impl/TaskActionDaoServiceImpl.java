package com.klarity.common.dal.service.impl;

import com.klarity.common.dal.entity.TaskActionEntity;
import com.klarity.common.dal.mapper.TaskActionMapper;
import com.klarity.common.dal.service.TaskActionDaoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务操作记录表 服务实现类
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
@Service
public class TaskActionDaoServiceImpl extends ServiceImpl<TaskActionMapper, TaskActionEntity> implements TaskActionDaoService {

}
