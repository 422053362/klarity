package com.klarity.core.model.context;

import com.klarity.common.dal.entity.*;
import com.klarity.common.facade.enums.TaskStatusEnums;
import com.klarity.common.facade.request.TaskCreateRequest;
import com.klarity.common.util.IdUtils;
import com.klarity.core.model.context.base.EmploymentContext;
import lombok.Data;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 18:14
 */
@Data
public class TaskCreateContext extends EmploymentContext {
    //创建任务的请求参数
    TaskCreateRequest request;

    //任务创建的结果
    TaskEntity newTaskEntity;
    TaskActionEntity newTaskActionEntity;
    TaskAssignmentEntity newTaskAssignmentEntity;

    /**
     * 构建一条任务记录
     *
     * @return
     */
    public TaskEntity buildNewTaskEntity() {
        String id = IdUtils.nextVal();
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskId(id);
        //request 里面的数据已经经过验证，所以可以直接使用
        taskEntity.setDescription(request.getDescription());
        taskEntity.setTitle(request.getTitle());
        taskEntity.setPriority(request.getPriority());
        taskEntity.setHospitalId(request.getHospitalId());
        taskEntity.setOwnerId(request.getOwnerId());
        taskEntity.setStatus(TaskStatusEnums.OPEN.getCode());
        return taskEntity;
    }

    /**
     * 构建一条任务操作记录
     *
     * @param taskId
     * @return
     */
    public TaskActionEntity buildNewTaskActionEntity(String taskId) {
        TaskActionEntity taskActionEntity = new TaskActionEntity();
        taskActionEntity.setTaskId(taskId);
        taskActionEntity.setActionName("create");
        taskActionEntity.setActionInfo("");
        return taskActionEntity;
    }

    /**
     * 构建一条分配记录
     *
     * @param taskId
     * @return
     */
    public TaskAssignmentEntity buildNewTaskAssignmentEntity(String taskId) {
        TaskAssignmentEntity taskActionEntity = new TaskAssignmentEntity();
        taskActionEntity.setTaskId(taskId);
        taskActionEntity.setEmployeeId(request.getOwnerId());
        taskActionEntity.setState("");
        return taskActionEntity;
    }
}
