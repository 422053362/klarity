package com.klarity.biz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.klarity.common.facade.request.TaskCreateRequest;
import com.klarity.common.facade.request.TaskQueryRequest;
import com.klarity.common.facade.request.TaskReassignRequest;
import com.klarity.common.facade.request.TaskUpdateRequest;
import com.klarity.common.facade.vo.TaskVO;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 19:53
 */
public interface TaskManageBizService {
    /**
     * 创建一个任务
     *
     * @return
     */
    TaskVO createTask(TaskCreateRequest request);

    /**
     * 重新分配一个任务
     *
     * @return
     */
    TaskVO reassignTask(TaskReassignRequest request);

    /**
     * 更新一个任务
     *
     * @return
     */
    TaskVO updateTask(TaskUpdateRequest request);

    /**
     * 查询员工任务列表
     *
     * @return
     */
    IPage<TaskVO> listTaskByOwnerId(TaskQueryRequest request);

    /**
     * 查询整个医院的任务列表
     *
     * @return
     */
    IPage<TaskVO> listTaskByHospitalId(TaskQueryRequest request);


}
