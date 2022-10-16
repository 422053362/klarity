package com.klarity.core.service.flow;

import com.royee.common.starters.template.context.BizContext;
import com.royee.common.starters.template.flow.orchestration.FlowNodeOrchestrationFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service("FlowNodeOrchestrationFactoryTestImpl")
public class FlowNodeOrchestrationFactoryTestImpl implements FlowNodeOrchestrationFactory {
    @Override
    public String getFlowNodeOrchestration(BizContext context) {
        return "FlowNodeOrchestrationTest";
    }
}