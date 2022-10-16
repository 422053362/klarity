package com.klarity.core.service.flow;

import com.royee.common.starters.template.flow.context.FlowContext;
import com.royee.common.starters.template.flow.enums.FlowNodeTaskStatus;
import com.royee.common.starters.template.flow.event.FlowExeEvent;
import com.royee.common.starters.template.flow.node.FlowNode;
import org.springframework.stereotype.Component;

@Component("FlowNode1")
public class FlowNode1 implements FlowNode {
    @Override
    public FlowExeEvent execute(FlowContext context) {
        FlowTestContext flowTestContext = (FlowTestContext) context;
        String flowId = context.getFlowId();
        switch (flowId) {
            case "0":
            case "1":
            case "2":
            case "3":
                flowTestContext.log(FlowNode1.class.getSimpleName(), FlowNodeTaskStatus.SUCCESS);
        }
        return FlowExeEvent.SUCCESS;
    }
}
