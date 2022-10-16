package com.klarity.common.dal.service;

import com.klarity.common.dal.entity.TblFlowNodeTaskEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 业务节点实例id 服务类
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
public interface TblFlowNodeTaskDaoService extends IService<TblFlowNodeTaskEntity> {
    /**
     * 获取一个业务节点实例
     *
     * @param flowTaskId
     * @param nodeName
     * @return
     */
    TblFlowNodeTaskEntity getOneBy(String flowTaskId, String nodeName);

    /**
     * 获取一个业务节点实例
     *
     * @param flowTaskId
     * @param nodeName
     * @return
     */
    TblFlowNodeTaskEntity lockOneBy(String flowTaskId, String nodeName);

    /**
     * 获取业务节点实例列表
     *
     * @param flowTaskId
     * @return
     */
    List<TblFlowNodeTaskEntity> listByFlowTaskId(String flowTaskId);
}
