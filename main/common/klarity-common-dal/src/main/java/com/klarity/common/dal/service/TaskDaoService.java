package com.klarity.common.dal.service;

import com.klarity.common.dal.entity.TaskEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 任务记录表 服务类
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
public interface TaskDaoService extends IService<TaskEntity> {
    /**
     * @param taskId
     * @return
     */
    public TaskEntity getTaskEntityByTaskId(String taskId);

}
