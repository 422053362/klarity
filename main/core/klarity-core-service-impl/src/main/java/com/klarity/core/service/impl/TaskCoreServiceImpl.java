package com.klarity.core.service.impl;

import com.klarity.common.dal.entity.TaskActionEntity;
import com.klarity.common.dal.entity.TaskAssignmentEntity;
import com.klarity.common.dal.entity.TaskEntity;
import com.klarity.common.util.exception.KlarityException;
import com.klarity.common.util.exception.KlarityExceptionEnum;
import com.klarity.core.model.context.TaskCreateContext;
import com.klarity.core.model.context.TaskReassignContext;
import com.klarity.core.model.context.TaskUpdateContext;
import com.klarity.core.service.TaskCoreService;
import org.springframework.stereotype.Service;

/**
 * @author: pengcheng091
 */
@Service
public class TaskCoreServiceImpl implements TaskCoreService {
    @Override
    public TaskCreateContext createTask(TaskCreateContext context) {
        Boolean hasEmployment = context.hasEmployment();
        if (!hasEmployment) {
            throw new KlarityException(KlarityExceptionEnum.EMPLOYMENT_NOT_EXIST);
        }
        //进行业务处理
        TaskEntity newTaskEntity = context.buildNewTaskEntity();
        TaskActionEntity newTaskActionEntity = context.buildNewTaskActionEntity(newTaskEntity.getTaskId());
        TaskAssignmentEntity newTaskAssignmentEntity = context.buildNewTaskAssignmentEntity(newTaskEntity.getTaskId());
        //
        context.setNewTaskEntity(newTaskEntity);
        context.setNewTaskActionEntity(newTaskActionEntity);
        context.setNewTaskAssignmentEntity(newTaskAssignmentEntity);
        return context;
    }

    @Override
    public TaskReassignContext reassignTask(TaskReassignContext context) {
        Boolean hasEmployment = context.hasEmployment();
        if (!hasEmployment) {
            throw new KlarityException(KlarityExceptionEnum.EMPLOYMENT_NOT_EXIST);
        }
        //进行业务处理
        TaskEntity updateTaskEntity = context.buildUpdateTaskEntity();
        TaskActionEntity newTaskActionEntity = context.buildNewTaskActionEntity();
        TaskAssignmentEntity newTaskAssignmentEntity = context.buildNewTaskAssignmentEntity();
        //
        context.setUpdateTaskEntity(updateTaskEntity);
        context.setNewTaskActionEntity(newTaskActionEntity);
        context.setNewTaskAssignmentEntity(newTaskAssignmentEntity);
        return context;
    }

    @Override
    public TaskUpdateContext updateTask(TaskUpdateContext context) {
        //进行业务处理
        TaskEntity updateTaskEntity = context.buildUpdateTaskEntity();
        TaskActionEntity newTaskActionEntity = context.buildNewTaskActionEntity();
        //
        context.setUpdateTaskEntity(updateTaskEntity);
        context.setNewTaskActionEntity(newTaskActionEntity);
        return context;
    }
}
