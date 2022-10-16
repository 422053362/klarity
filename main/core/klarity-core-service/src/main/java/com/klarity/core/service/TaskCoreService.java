package com.klarity.core.service;


import com.klarity.common.facade.request.TaskCreateRequest;
import com.klarity.common.facade.request.TaskReassignRequest;
import com.klarity.core.model.context.TaskCreateContext;
import com.klarity.core.model.context.TaskReassignContext;
import com.klarity.core.model.context.TaskUpdateContext;

/**
 * @author: pengcheng091
 * VPN通道 领域服务
 */
public interface TaskCoreService {
    /**
     * 创建一个任务
     *
     * @param context
     * @return
     */
    TaskCreateContext createTask(TaskCreateContext context);

    /**
     * 重新分配一个任务
     *
     * @param context
     */
    TaskReassignContext reassignTask(TaskReassignContext context);

    /**
     * 更新一个任务
     *
     * @param context
     * @return
     */
    TaskUpdateContext updateTask(TaskUpdateContext context);
}
