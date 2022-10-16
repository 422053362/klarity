package com.klarity.core.service.flow;

import com.klarity.common.dal.service.TblFlowTaskDaoService;
import com.royee.common.sequence.service.MysqlSequence;
import com.royee.common.starters.template.context.BizContext;
import com.royee.common.starters.template.context.BizThreadLocal;
import com.royee.common.starters.template.flow.FlowTemplateCoreService;
import com.royee.common.starters.template.flow.service.IFlowTaskRepositoryService;
import com.klarity.TestApplication;
import com.klarity.KlarityBaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.util.UUID;

@SpringBootTest(classes = TestApplication.class)
public class FlowTestExecute extends KlarityBaseTest {
    private static final Logger logger = LoggerFactory.getLogger(FlowTestExecute.class);
    @Autowired
    FlowTemplateCoreService flowTemplateCoreService;
    @Autowired
    IFlowTaskRepositoryService flowTaskRepositoryService;
    @Autowired
    MysqlSequence sequence;
    @Autowired
    TblFlowTaskDaoService tblFlowTaskDaoService;

    //一次顺利全部执行
    @Test
    public void testSuccess() {
        BizContext bizContext = BizThreadLocal.get();
        FlowTestContext context = new FlowTestContext(bizContext);
        String taskId = UUID.randomUUID().toString();
        String flowTaskId = flowTemplateCoreService.submitFlowTask(taskId, "0", "全部成功的流程", "test", context);
        context = flowTaskRepositoryService.createFlowContext(flowTaskId, FlowTestContext.class);
        flowTemplateCoreService.execute(context);
        System.out.println("success");
    }


    //正常中断后重启执行
    @Test
    public void testTrigger() {
        BizContext bizContext = BizThreadLocal.get();
        FlowTestContext context = new FlowTestContext(bizContext);
        String taskId = UUID.randomUUID().toString();
        String flowTaskId = flowTemplateCoreService.submitFlowTask(taskId, "1", "node2重试", "test", context);
        context = flowTaskRepositoryService.createFlowContext(flowTaskId, FlowTestContext.class);
        flowTemplateCoreService.execute(context);
        System.out.println("success");
        //重试
        context = flowTaskRepositoryService.createFlowContext(flowTaskId, FlowTestContext.class);
        flowTemplateCoreService.execute(context);
    }

    //异常中断后重启执行
    @Test
    public void testRetry() {
        BizContext bizContext = BizThreadLocal.get();
        FlowTestContext context = new FlowTestContext(bizContext);
        String taskId = UUID.randomUUID().toString();
        String flowTaskId = flowTemplateCoreService.submitFlowTask(taskId, "2", "node2主动终止", "test", context);
        context = flowTaskRepositoryService.createFlowContext(flowTaskId, FlowTestContext.class);
        flowTemplateCoreService.execute(context);
        logger.info("success");
        //重试
        context = flowTaskRepositoryService.createFlowContext(flowTaskId, FlowTestContext.class);
        flowTemplateCoreService.execute(context);
    }

    //幂等检查
    public void testIdempotent() {
    }
}
