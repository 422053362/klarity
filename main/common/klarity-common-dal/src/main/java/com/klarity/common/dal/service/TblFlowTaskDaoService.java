package com.klarity.common.dal.service;

import com.klarity.common.dal.entity.TblFlowTaskEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 业务流程实例 服务类
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
public interface TblFlowTaskDaoService extends IService<TblFlowTaskEntity> {
    /**
     * 通过业务流程实例Id
     *
     * @param flowTaskId
     * @return
     */
    TblFlowTaskEntity getByFlowTaskId(String flowTaskId);

    /**
     * 锁定一个业务流程实例
     *
     * @param flowTaskId
     * @return
     */
    TblFlowTaskEntity lockFlowTask(String flowTaskId);
}
