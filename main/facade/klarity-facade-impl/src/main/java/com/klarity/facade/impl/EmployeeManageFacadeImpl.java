package com.klarity.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.klarity.biz.EmployeeManageBizService;
import com.klarity.common.facade.feign.EmployeeManageFacade;
import com.klarity.common.facade.request.EmployeeCreateRequest;
import com.klarity.common.facade.request.EmployeeQueryRequest;
import com.klarity.common.facade.response.EmployeeQueryResponse;
import com.klarity.common.facade.vo.EmployeeVO;
import com.klarity.common.util.event.KlarityEventEnum;
import com.royee.common.idempotent.service.IdempotentControlService;
import com.royee.common.starters.template.core.TemplateHelper;
import com.royee.common.starters.template.core.reset.ServiceOptCallback;
import com.royee.common.starters.template.core.reset.ServiceQueryCallback;
import com.royee.common.utils.share.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 16:14
 */
@RestController
public class EmployeeManageFacadeImpl implements EmployeeManageFacade {
    @Autowired
    EmployeeManageBizService employeeManageBizService;
    @Autowired
    IdempotentControlService idempotentControlService;
    @Autowired
    TemplateHelper templateHelper;

    @Override
    public Result<EmployeeVO> create(@RequestBody EmployeeCreateRequest request) {
        return templateHelper.resultOperator(KlarityEventEnum.EMPLOYEE_CREATE, new ServiceOptCallback<EmployeeVO>() {
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
            public EmployeeVO execute() {
                return employeeManageBizService.createEmployee(request);
            }
        });
    }

    @Override
    public Result<EmployeeQueryResponse> query(EmployeeQueryRequest request) {
        return templateHelper.resultQuery(KlarityEventEnum.EMPLOYEE_LIST, new ServiceQueryCallback<EmployeeQueryResponse>() {
            @Override
            public void checkParam() {
                request.check();
            }

            @Override
            public void buildContext() {

            }

            @Override
            public EmployeeQueryResponse execute() {
                IPage<EmployeeVO> page = employeeManageBizService.listEmployee(request);
                return new EmployeeQueryResponse(page);
            }
        });
    }
}
