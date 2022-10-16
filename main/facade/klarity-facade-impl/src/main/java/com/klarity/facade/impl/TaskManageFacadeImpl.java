package com.klarity.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.klarity.biz.TaskManageBizService;
import com.klarity.common.facade.feign.TaskManageFacade;
import com.klarity.common.facade.request.TaskCreateRequest;
import com.klarity.common.facade.request.TaskQueryRequest;
import com.klarity.common.facade.request.TaskReassignRequest;
import com.klarity.common.facade.request.TaskUpdateRequest;
import com.klarity.common.facade.response.TaskQueryResponse;
import com.klarity.common.facade.vo.TaskVO;
import com.klarity.common.util.event.KlarityEventEnum;
import com.royee.common.idempotent.service.IdempotentControlService;
import com.royee.common.starters.template.core.TemplateHelper;
import com.royee.common.starters.template.core.reset.ServiceOptCallback;
import com.royee.common.starters.template.core.reset.ServiceQueryCallback;
import com.royee.common.utils.share.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 20:24
 */
@RestController
public class TaskManageFacadeImpl implements TaskManageFacade {

    @Autowired
    TaskManageBizService taskManageBizService;

    @Autowired
    IdempotentControlService idempotentControlService;

    @Autowired
    TemplateHelper templateHelper;

    @Override
    public Result<TaskVO> create(TaskCreateRequest request) {
        return templateHelper.resultOperator(KlarityEventEnum.CREATE_TASK, new ServiceOptCallback<TaskVO>() {
            @Override
            public void checkConcurrent() {
                String idempotentKey = idempotentControlService.calWebIdempotentKey();
                idempotentControlService.checkConcurrentFastly(idempotentKey);
            }

            @Override
            public void checkParam() {
                request.check();
            }

            @Override
            public void buildContext() {

            }

            @Override
            public TaskVO execute() {
                return taskManageBizService.createTask(request);
            }
        });
    }

    @Override
    public Result<TaskVO> update(TaskUpdateRequest request) {
        return templateHelper.resultOperator(KlarityEventEnum.UPDATE_TASK, new ServiceOptCallback<TaskVO>() {
            @Override
            public void checkConcurrent() {
                String idempotentKey = idempotentControlService.calWebIdempotentKey();
                idempotentControlService.checkConcurrentFastly(idempotentKey);
            }

            @Override
            public void checkParam() {
                request.check();
            }

            @Override
            public void buildContext() {

            }

            @Override
            public TaskVO execute() {
                return taskManageBizService.updateTask(request);
            }
        });
    }

    @Override
    public Result<TaskVO> reassign(TaskReassignRequest request) {
        return templateHelper.resultOperator(KlarityEventEnum.REASSIGN_TASK, new ServiceOptCallback<TaskVO>() {
            @Override
            public void checkConcurrent() {
                String idempotentKey = idempotentControlService.calWebIdempotentKey();
                idempotentControlService.checkConcurrentFastly(idempotentKey);
            }

            @Override
            public void checkParam() {
                request.check();
            }

            @Override
            public void buildContext() {

            }

            @Override
            public TaskVO execute() {
                return taskManageBizService.reassignTask(request);
            }
        });
    }

    @Override
    public Result<TaskQueryResponse> queryTaskByEmployee(TaskQueryRequest request) {
        return templateHelper.resultQuery(KlarityEventEnum.LIST_TASK_OWNED_BY_EMPLOYEE, new ServiceQueryCallback<TaskQueryResponse>() {
            @Override
            public void checkParam() {
                request.checkForEmployee();
            }

            @Override
            public void buildContext() {

            }

            @Override
            public TaskQueryResponse execute() {
                IPage<TaskVO> page = taskManageBizService.listTaskByOwnerId(request);
                return new TaskQueryResponse(page);
            }
        });
    }

    @Override
    public Result<TaskQueryResponse> queryTaskByHospital(TaskQueryRequest request) {
        return templateHelper.resultQuery(KlarityEventEnum.LIST_TASK_OWNED_BY_EMPLOYEE, new ServiceQueryCallback<TaskQueryResponse>() {
            @Override
            public void checkParam() {
                request.checkForHospital();
            }

            @Override
            public void buildContext() {

            }

            @Override
            public TaskQueryResponse execute() {
                IPage<TaskVO> page = taskManageBizService.listTaskByHospitalId(request);
                return new TaskQueryResponse(page);
            }
        });
    }
}
