package com.klarity.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.klarity.biz.HospitalManageBizService;
import com.klarity.common.facade.feign.HospitalManageFacade;
import com.klarity.common.facade.vo.HospitalVO;
import com.klarity.common.facade.request.HospitalCreateRequest;
import com.klarity.common.facade.request.HospitalQueryRequest;
import com.klarity.common.facade.response.HospitalQueryResponse;
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
 * @createDate: 2022-10-16 14:46
 */
@RestController
public class HospitalManageFacadeImpl implements HospitalManageFacade {

    @Autowired
    HospitalManageBizService hospitalManageBizService;

    @Autowired
    TemplateHelper templateHelper;

    @Autowired
    IdempotentControlService idempotentControlService;

    public Result<HospitalVO> create(@RequestBody HospitalCreateRequest request) {
        return templateHelper.resultOperator(KlarityEventEnum.HOSPITAL_REGISTER, new ServiceOptCallback<HospitalVO>() {
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
            public HospitalVO execute() {
                return hospitalManageBizService.createHospital(request);
            }
        });
    }

    public Result<HospitalQueryResponse> query(HospitalQueryRequest request) {
        return templateHelper.resultQuery(KlarityEventEnum.HOSPITAL_LIST, new ServiceQueryCallback<HospitalQueryResponse>() {
            @Override
            public void checkParam() {
                request.check();
            }

            @Override
            public void buildContext() {

            }

            @Override
            public HospitalQueryResponse execute() {
                IPage<HospitalVO> hospitalPage = hospitalManageBizService.listHospital(request);
                return new HospitalQueryResponse(hospitalPage);
            }
        });

    }
}
