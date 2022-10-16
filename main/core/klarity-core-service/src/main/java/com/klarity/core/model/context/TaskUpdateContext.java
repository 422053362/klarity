package com.klarity.core.model.context;

import com.klarity.common.dal.entity.TaskActionEntity;
import com.klarity.common.dal.entity.TaskEntity;
import com.klarity.common.facade.request.TaskCreateRequest;
import com.klarity.common.facade.request.TaskUpdateRequest;
import lombok.Data;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 20:11
 */
@Data
public class TaskUpdateContext {
    //创建任务的请求参数
    TaskUpdateRequest request;
    //被更新的任务
    TaskEntity taskEntity;

    //任务创建的结果
    TaskEntity updateTaskEntity;
    TaskActionEntity newTaskActionEntity;

    /**
     * 构建一条任务记录
     *
     * @return
     */
    public TaskEntity buildUpdateTaskEntity() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(this.taskEntity.getId());
        taskEntity.setDescription(request.getDescription());
        taskEntity.setTitle(request.getTitle());
        taskEntity.setPriority(request.getPriority());
        taskEntity.setStatus(request.getStatus());
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
        taskActionEntity.setActionName("update");
        taskActionEntity.setActionInfo("");
        return taskActionEntity;
    }

}
