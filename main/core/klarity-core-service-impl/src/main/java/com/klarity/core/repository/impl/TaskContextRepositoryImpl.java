package com.klarity.core.repository.impl;

import com.klarity.common.dal.entity.*;
import com.klarity.common.dal.service.*;
import com.klarity.common.facade.enums.EmploymentStateEnums;
import com.klarity.common.facade.request.TaskCreateRequest;
import com.klarity.common.facade.request.TaskReassignRequest;
import com.klarity.common.facade.request.TaskUpdateRequest;
import com.klarity.core.model.context.TaskUpdateContext;
import com.klarity.core.model.context.base.EmploymentContext;
import com.klarity.core.model.context.TaskCreateContext;
import com.klarity.core.model.context.TaskReassignContext;
import com.klarity.core.repository.TaskContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author: pengcheng091
 * @date 2020-09-22 16:16
 **/
@Repository
public class TaskContextRepositoryImpl implements TaskContextRepository {
    @Autowired
    HospitalDaoService hospitalDaoService;
    @Autowired
    EmployeeDaoService employeeDaoService;
    @Autowired
    TaskDaoService taskDaoService;
    @Autowired
    TaskActionDaoService taskActionDaoService;
    @Autowired
    TaskAssignmentDaoService taskAssignmentDaoService;
    @Autowired
    M2mHospitalEmployeeDaoService m2mHospitalEmployeeDaoService;

    @Override
    public TaskReassignContext buildTaskReassignContext(TaskReassignRequest request) {
        String hospitalId = request.getHospitalId();
        String employeeId = request.getEmployeeId();
        String taskId = request.getTaskId();

        TaskReassignContext context = new TaskReassignContext();
        context.setRequest(request);
        //任务信息
        TaskEntity taskEntity = this.taskDaoService.getTaskEntityByTaskId(taskId);
        context.setTaskEntity(taskEntity);
        //雇佣关系上下文
        this.buildEmploymentContext(hospitalId, employeeId, context);
        return context;
    }

    @Override
    public TaskCreateContext buildTaskCreateContext(TaskCreateRequest request) {
        String hospitalId = request.getHospitalId();
        String employeeId = request.getOwnerId();

        TaskCreateContext context = new TaskCreateContext();
        context.setRequest(request);
        //雇佣关系上下文
        this.buildEmploymentContext(hospitalId, employeeId, context);
        return context;
    }


    @Override
    public TaskUpdateContext buildTaskUpdateContext(TaskUpdateRequest request) {
        String taskId = request.getTaskId();

        TaskUpdateContext context = new TaskUpdateContext();
        context.setRequest(request);
        //任务信息
        TaskEntity taskEntity = this.taskDaoService.getTaskEntityByTaskId(taskId);
        context.setTaskEntity(taskEntity);

        return context;
    }

    @Override
    public void persistTaskCreateContext(TaskCreateContext context) {
        TaskEntity taskEntity = context.getNewTaskEntity();
        TaskActionEntity taskActionEntity = context.getNewTaskActionEntity();
        TaskAssignmentEntity taskAssignmentEntity = context.getNewTaskAssignmentEntity();
        this.taskDaoService.save(taskEntity);
        this.taskActionDaoService.save(taskActionEntity);
        this.taskAssignmentDaoService.save(taskAssignmentEntity);
    }

    @Override
    public void persistTaskUpdateContext(TaskUpdateContext context) {
        TaskEntity taskEntity = context.getUpdateTaskEntity();
        TaskActionEntity taskActionEntity = context.getNewTaskActionEntity();
        this.taskDaoService.updateById(taskEntity);
        this.taskActionDaoService.save(taskActionEntity);
    }

    @Override
    public void persistTaskReassignContext(TaskReassignContext context) {
        TaskEntity taskEntity = context.getUpdateTaskEntity();
        TaskActionEntity taskActionEntity = context.getNewTaskActionEntity();
        TaskAssignmentEntity taskAssignmentEntity = context.getNewTaskAssignmentEntity();
        this.taskDaoService.updateById(taskEntity);
        this.taskActionDaoService.save(taskActionEntity);
        this.taskAssignmentDaoService.save(taskAssignmentEntity);
    }

    protected EmploymentContext buildEmploymentContext(String hospitalId, String employeeId, EmploymentContext context) {
        //医院主体
        HospitalEntity hospitalEntity = this.hospitalDaoService.getHospitalByHospitalId(hospitalId);
        context.setHospitalEntity(hospitalEntity);
        //员工主体
        EmployeeEntity employeeEntity = this.employeeDaoService.getEmployeeByEmployeeId(employeeId);
        context.setEmployeeEntity(employeeEntity);
        //雇佣关系
        if (employeeEntity != null && hospitalEntity != null) {
            M2mHospitalEmployeeEntity m2mHospitalEmployeeEntity = this.m2mHospitalEmployeeDaoService.getM2mHospitalEmployee(employeeId, hospitalId, EmploymentStateEnums.WORK.getCode());
            context.setM2mHospitalEmployeeEntity(m2mHospitalEmployeeEntity);
        }
        return context;
    }

}
