package com.klarity.core.service.flow;

import com.royee.common.starters.template.flow.context.FlowContext;
import com.royee.common.starters.template.flow.enums.FlowNodeTaskStatus;
import com.royee.common.starters.template.flow.event.FlowExeEvent;
import com.royee.common.starters.template.flow.node.FlowNode;
import com.royee.common.starters.template.flow.service.model.FlowNodeTaskDO;
import com.royee.common.starters.template.flow.service.model.NodeTaskExtValueDO;
import org.springframework.stereotype.Component;

@Component("FlowNode2")
public class FlowNode2 implements FlowNode {
    @Override
    public FlowExeEvent execute(FlowContext context) {
        FlowTestContext flowTestContext = (FlowTestContext) context;
        String flowId = context.getFlowId();
        switch (flowId) {
            case "0":
                flowTestContext.log(FlowNode1.class.getSimpleName(), FlowNodeTaskStatus.SUCCESS);
                break;
            case "1":
                FlowNodeTaskDO flowNodeTask = context.getFlowNodeTask("FlowNode2");
                NodeTaskExtValueDO extValue = flowNodeTask.getExtValue();
                if (extValue == null || extValue.getEvent() == null) {
                    flowTestContext.log(FlowNode2.class.getSimpleName(), FlowNodeTaskStatus.FAILED);
                    return FlowExeEvent.WAIT_RETRY;
                } else {
                    flowTestContext.log(FlowNode2.class.getSimpleName(), FlowNodeTaskStatus.SUCCESS);
                    return FlowExeEvent.SUCCESS;
                }
            case "2":
                flowTestContext.log(FlowNode2.class.getSimpleName(), FlowNodeTaskStatus.SUCCESS);
                return FlowExeEvent.WAIT_TRIGGER;
            case "3":
                flowTestContext.log(FlowNode2.class.getSimpleName(), FlowNodeTaskStatus.EXE);
                return FlowExeEvent.EXE;
        }
        return FlowExeEvent.SUCCESS;
    }
}

