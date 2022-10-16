package com.klarity.core.service.flow;

import com.royee.common.starters.template.context.BizContext;
import com.royee.common.starters.template.flow.context.FlowContext;
import com.royee.common.starters.template.flow.enums.FlowNodeTaskStatus;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.Map;

public class FlowTestContext extends FlowContext {

    Map<String, FlowNodeTaskStatus> map = new HashMap<>();

    public FlowTestContext() {
    }

    public FlowTestContext(BizContext bizContext) {
        BeanUtils.copyProperties(bizContext, this);
    }

    public Map<String, FlowNodeTaskStatus> getMap() {
        return map;
    }

    public void setMap(Map<String, FlowNodeTaskStatus> map) {
        this.map = map;
    }

    public void log(String nodeName, FlowNodeTaskStatus taskStatus) {
        map.put(nodeName, taskStatus);
    }
}
