package com.klarity.core.repository;

import com.klarity.common.facade.request.TaskCreateRequest;
import com.klarity.common.facade.request.TaskReassignRequest;
import com.klarity.common.facade.request.TaskUpdateRequest;
import com.klarity.core.model.context.TaskCreateContext;
import com.klarity.core.model.context.TaskReassignContext;
import com.klarity.core.model.context.TaskUpdateContext;

/**
 * @author: pengcheng091
 * @date 2020-09-22 16:16
 **/
public interface TaskContextRepository {
    /**
     * 构建任务创建的上下文
     *
     * @param request
     * @return
     */
    TaskCreateContext buildTaskCreateContext(TaskCreateRequest request);

    /**
     * 持久化任务创建上下文
     *
     * @param context
     */
    void persistTaskCreateContext(TaskCreateContext context);

    /**
     * 构建任务更新的上下文
     *
     * @param request
     * @return
     */
    TaskUpdateContext buildTaskUpdateContext(TaskUpdateRequest request);

    /**
     * 持久化任务更新上下文
     *
     * @param context
     */
    void persistTaskUpdateContext(TaskUpdateContext context);

    /**
     * 创建重新分配任务的上下文
     *
     * @param request
     * @return
     */
    TaskReassignContext buildTaskReassignContext(TaskReassignRequest request);

    /**
     * 持久化任务分配上下文
     *
     * @param context
     */
    void persistTaskReassignContext(TaskReassignContext context);
}
