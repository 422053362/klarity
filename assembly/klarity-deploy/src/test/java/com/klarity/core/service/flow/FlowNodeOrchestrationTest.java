package com.klarity.core.service.flow;

import com.royee.common.starters.template.context.BizContext;
import com.royee.common.starters.template.flow.orchestration.FlowNodeOrchestration;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("FlowNodeOrchestrationTest")
public class FlowNodeOrchestrationTest implements FlowNodeOrchestration {
    @Override
    public List<String> getFlowNodeList(BizContext bizContext) {
        return Arrays.asList(
                "FlowNode1",
                "FlowNode2",
                "FlowNode3",
                "FlowNode4"
        );
    }
}
