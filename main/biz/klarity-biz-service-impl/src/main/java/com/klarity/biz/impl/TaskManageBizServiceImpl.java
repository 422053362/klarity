package com.klarity.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.klarity.biz.TaskManageBizService;
import com.klarity.biz.converter.TaskEntityConverter;
import com.klarity.common.dal.entity.TaskEntity;
import com.klarity.common.dal.service.TaskDaoService;
import com.klarity.common.facade.request.TaskCreateRequest;
import com.klarity.common.facade.request.TaskQueryRequest;
import com.klarity.common.facade.request.TaskReassignRequest;
import com.klarity.common.facade.request.TaskUpdateRequest;
import com.klarity.common.facade.vo.TaskVO;
import com.klarity.core.model.context.TaskCreateContext;
import com.klarity.core.model.context.TaskReassignContext;
import com.klarity.core.model.context.TaskUpdateContext;
import com.klarity.core.repository.TaskContextRepository;
import com.klarity.core.service.TaskCoreService;
import com.royee.common.transaction.service.RoyeeTransactionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 19:53
 */
@Service
public class TaskManageBizServiceImpl implements TaskManageBizService {
    @Autowired
    TaskCoreService taskCoreService;
    @Autowired
    TaskContextRepository taskContextRepository;

    @Autowired
    TaskDaoService taskDaoService;

    @Autowired
    RoyeeTransactionTemplate transactionTemplate;

    @Override
    public TaskVO createTask(TaskCreateRequest request) {
        //构建上下文，创建任务，无须加锁，只需要做好幂等
        TaskCreateContext context = taskContextRepository.buildTaskCreateContext(request);
        //核心业务
        taskCoreService.createTask(context);
        //事务保存
        transactionTemplate.execute(transactionStatus -> {
            taskContextRepository.persistTaskCreateContext(context);
        });
        //构建返回值
        TaskEntity taskEntity = context.getNewTaskEntity();
        return TaskEntityConverter.convert2TaskVO(taskEntity);
    }

    @Override
    public TaskVO reassignTask(TaskReassignRequest request) {
        //构建上下文，创建任务，无须加锁，只需要做好幂等
        TaskReassignContext context = taskContextRepository.buildTaskReassignContext(request);
        //核心业务
        taskCoreService.reassignTask(context);
        //事务保存
        transactionTemplate.execute(transactionStatus -> {
            taskContextRepository.persistTaskReassignContext(context);
        });
        //构建返回值
        TaskEntity taskEntity = this.taskDaoService.getTaskEntityByTaskId(request.getTaskId());
        return TaskEntityConverter.convert2TaskVO(taskEntity);
    }

    @Override
    public TaskVO updateTask(TaskUpdateRequest request) {
        //构建上下文，创建任务，无须加锁，只需要做好幂等
        TaskUpdateContext context = taskContextRepository.buildTaskUpdateContext(request);
        //核心业务
        taskCoreService.updateTask(context);
        //事务保存
        transactionTemplate.execute(transactionStatus -> {
            taskContextRepository.persistTaskUpdateContext(context);
        });
        //构建返回值
        TaskEntity taskEntity = this.taskDaoService.getTaskEntityByTaskId(request.getTaskId());
        return TaskEntityConverter.convert2TaskVO(taskEntity);
    }

    @Override
    public IPage<TaskVO> listTaskByOwnerId(TaskQueryRequest request) {
        //分页
        IPage<TaskEntity> page = new Page<>(request.getPage(), request.getSize());
        //条件
        LambdaQueryWrapper<TaskEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TaskEntity::getOwnerId, request.getEmployeeId());
        //查询
        page = this.taskDaoService.page(page, wrapper);
        return page.convert(TaskEntityConverter::convert2TaskVO);
    }

    @Override
    public IPage<TaskVO> listTaskByHospitalId(TaskQueryRequest request) {
        //分页
        IPage<TaskEntity> page = new Page<>(request.getPage(), request.getSize());
        //条件
        LambdaQueryWrapper<TaskEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TaskEntity::getHospitalId, request.getHospitalId());
        //查询
        page = this.taskDaoService.page(page, wrapper);
        return page.convert(TaskEntityConverter::convert2TaskVO);
    }
}
