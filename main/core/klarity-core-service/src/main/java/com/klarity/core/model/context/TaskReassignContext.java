package com.klarity.core.model.context;

import com.klarity.common.dal.entity.*;
import com.klarity.common.facade.request.TaskReassignRequest;
import com.klarity.core.model.context.base.EmploymentContext;
import lombok.Data;

/**
 * @author: chengpeng
 * @createDate: 2022-10-16 14:16
 */
@Data
public class TaskReassignContext extends EmploymentContext {
    //创建任务的请求参数
    TaskReassignRequest request;
    //被更新的任务
    TaskEntity taskEntity;

    //重新分配任务的结果
    TaskEntity updateTaskEntity;
    TaskActionEntity newTaskActionEntity;
    TaskAssignmentEntity newTaskAssignmentEntity;

    /**
     * 构建一条任务记录
     *
     * @return
     */
    public TaskEntity buildUpdateTaskEntity() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(this.taskEntity.getId());
        taskEntity.setOwnerId(request.getEmployeeId());
        return taskEntity;
    }

    /**
     * 构建一条任务操作记录
     *
     * @return
     */
    public TaskActionEntity buildNewTaskActionEntity() {
        TaskActionEntity taskActionEntity = new TaskActionEntity();
        taskActionEntity.setTaskId(taskEntity.getTaskId());
        taskActionEntity.setActionName("reassign");
        taskActionEntity.setActionInfo("");
        return taskActionEntity;
    }

    /**
     * 构建一条分配记录
     *
     * @return
     */
    public TaskAssignmentEntity buildNewTaskAssignmentEntity() {
        TaskAssignmentEntity taskActionEntity = new TaskAssignmentEntity();
        taskActionEntity.setTaskId(taskEntity.getTaskId());
        taskActionEntity.setEmployeeId(request.getEmployeeId());
        taskActionEntity.setState("");
        return taskActionEntity;
    }
}
