package com.klarity.common.dal.service.template;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.royee.common.aliyun.service.IAliYunOssService;
import com.royee.common.sequence.service.MysqlSequence;
import com.royee.common.starters.template.flow.context.FlowContext;
import com.royee.common.starters.template.flow.enums.FlowTaskErrorCodeEnum;
import com.royee.common.starters.template.flow.enums.FlowTaskStatus;
import com.royee.common.starters.template.flow.service.IFlowTaskRepositoryService;
import com.royee.common.starters.template.flow.service.model.FlowNodeTaskDO;
import com.royee.common.starters.template.flow.service.model.FlowTaskDO;
import com.royee.common.starters.template.flow.service.model.NodeTaskExtValueDO;
import com.royee.common.starters.template.flow.service.model.TaskExtValueDO;
import com.royee.common.utils.share.exception.BusinessException;
import com.klarity.common.dal.entity.TblFlowNodeTaskEntity;
import com.klarity.common.dal.entity.TblFlowTaskEntity;
import com.klarity.common.dal.service.TblFlowNodeTaskDaoService;
import com.klarity.common.dal.service.TblFlowTaskDaoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: pengcheng091
 */
@Slf4j
@Service
public class FlowTaskRepositoryServiceImpl implements IFlowTaskRepositoryService {

    @Autowired
    TblFlowTaskDaoService tblFlowTaskDaoService;
    @Autowired
    protected TblFlowNodeTaskDaoService tblFlowNodeTaskDaoService;
    @Autowired
    IAliYunOssService aliYunOssService;
    @Autowired
    MysqlSequence sequence;


    @Override
    public FlowTaskDO lockFlowTask(String flowTaskId) {
        FlowTaskDO flowTaskDO = null;
        TblFlowTaskEntity entity = tblFlowTaskDaoService.getByFlowTaskId(flowTaskId);
        if (entity != null) {
            entity = tblFlowTaskDaoService.lockFlowTask(flowTaskId);
            flowTaskDO = FlowTaskConverter.converter2FlowTaskDO(entity);
        }
        return flowTaskDO;
    }

    @Override
    public Boolean updateFlowTaskStatus(Long id, String status) {
        FlowTaskDO update = new FlowTaskDO();
        update.setId(id);
        update.setStatus(status);
        TblFlowTaskEntity entity = FlowTaskConverter.converter2TblFlowTaskEntity(update);
        return this.tblFlowTaskDaoService.updateById(entity);
    }

    @Override
    public Boolean saveOrUpdateFlowNodeTask(FlowNodeTaskDO flowNodeTask) {
        TblFlowNodeTaskEntity flowNodeTaskEntity = FlowTaskConverter.converter2TblFlowNodeTaskEntity(flowNodeTask);
        Boolean success = this.tblFlowNodeTaskDaoService.saveOrUpdate(flowNodeTaskEntity);
        if (flowNodeTask.getId() == null) {
            flowNodeTask.setId(flowNodeTaskEntity.getId());
        }
        return success;
    }

    @Override
    public Boolean updateFlowNodeTask(String flowTaskId, String nodeName, FlowNodeTaskDO flowNodeTask) {
        LambdaQueryWrapper<TblFlowNodeTaskEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblFlowNodeTaskEntity::getFlowTaskId, flowTaskId);
        wrapper.eq(TblFlowNodeTaskEntity::getNodeName, nodeName);
        TblFlowNodeTaskEntity tblFlowNodeTaskEntity = FlowTaskConverter.converter2TblFlowNodeTaskEntity(flowNodeTask);
        return tblFlowNodeTaskDaoService.update(tblFlowNodeTaskEntity, wrapper);
    }


    @Override
    public String submitFlowTask(String flowTaskId,String flowId, String flowName, String flowType, FlowContext flowContext) {
        String taskId = flowTaskId;
        //
        flowContext.setFlowTaskId(taskId);
        flowContext.setFlowId(flowId);
        TaskExtValueDO extValue = uploadFlowContext(flowContext);
        //
        TblFlowTaskEntity entity = new TblFlowTaskEntity();
        entity.setFlowTaskId(taskId);
        entity.setFlowId(flowId);
        entity.setFlowName(flowName);
        entity.setStartTime(LocalDateTime.now());
        entity.setStatus(FlowTaskStatus.INIT.getCode());
        entity.setFlowType(flowType);
        entity.setExtValue(JSONObject.toJSONString(extValue));
        tblFlowTaskDaoService.save(entity);
        return taskId;
    }


    @Override
    public <T extends FlowContext> T createFlowContext(String flowTaskId, Class<? extends FlowContext> clazz) {
        TblFlowTaskEntity flowTaskEntity = this.tblFlowTaskDaoService.getByFlowTaskId(flowTaskId);
        if (flowTaskEntity == null) {
            throw new BusinessException(FlowTaskErrorCodeEnum.FLOW_TASK_NOT_EXIST);
        }
        T flowContext = null;
        //初始化上下文
        FlowTaskDO flowTaskDO = FlowTaskConverter.converter2FlowTaskDO(flowTaskEntity);
        try {
            TaskExtValueDO extValue = flowTaskDO.getExtValue();
            if (extValue != null) {
                String contextPath = extValue.getContextPath();
                InputStream in = aliYunOssService.getDocument(contextPath);
                flowContext = JSONObject.parseObject(in, clazz);
            } else {
                InputStream in = new ByteArrayInputStream("{}".getBytes(StandardCharsets.UTF_8));
                flowContext = JSONObject.parseObject(in, clazz);
            }
        } catch (IOException e) {
            log.error("初始化任务上下文异常", e);
            throw new BusinessException(FlowTaskErrorCodeEnum.FLOW_TASK_INIT_FAILED);
        }

        List<TblFlowNodeTaskEntity> tblFlowNodeTaskList = this.tblFlowNodeTaskDaoService.listByFlowTaskId(flowTaskId);
        Map<String, FlowNodeTaskDO> flowNodeTaskDOMap = tblFlowNodeTaskList.stream().map(FlowTaskConverter::converter2FlowNodeTaskDO).collect(Collectors.toMap(e -> e.getNodeName(), e -> e));
        flowContext.setFlowTaskId(flowTaskId);
        flowContext.setFlowTaskDO(flowTaskDO);
        flowContext.setName2FlowNodeTaskMap(flowNodeTaskDOMap);
        return flowContext;
    }

    @Override
    public Boolean persistFlowContext(FlowContext flowContext) {
        TaskExtValueDO extValue = uploadFlowContext(flowContext);
        {
            //持久化业务流程实例
            FlowTaskDO flowTaskDO = flowContext.getFlowTaskDO();
            flowTaskDO.updateExtValueContextPath(extValue.getContextPath());
            TblFlowTaskEntity flowTaskEntity = FlowTaskConverter.converter2TblFlowTaskEntity(flowTaskDO);
            this.tblFlowTaskDaoService.updateById(flowTaskEntity);
        }
        {
            //持久化业务节点实例
            Map<String, FlowNodeTaskDO> flowNodeTaskDOList = flowContext.getName2FlowNodeTaskMap();
            for (Map.Entry<String, FlowNodeTaskDO> entry : flowNodeTaskDOList.entrySet()) {
                FlowNodeTaskDO flowNodeTaskDO = entry.getValue();
                TblFlowNodeTaskEntity tblFlowNodeTaskDO = FlowTaskConverter.converter2TblFlowNodeTaskEntity(flowNodeTaskDO);
                this.tblFlowNodeTaskDaoService.updateById(tblFlowNodeTaskDO);
            }
        }
        return true;
    }

    private TaskExtValueDO uploadFlowContext(FlowContext flowContext) {
        String content = JSONObject.toJSONString(flowContext);
        String path = "temporary/flow/context/" + flowContext.getFlowTaskId() + ".json";
        aliYunOssService.saveDocument(content, path, MediaType.APPLICATION_JSON_VALUE);

        TaskExtValueDO extValue = new TaskExtValueDO();
        extValue.setContextPath(path);

        return extValue;
    }


    public static class FlowTaskConverter {
        /**
         * @param entity
         * @return
         */
        public static FlowNodeTaskDO converter2FlowNodeTaskDO(TblFlowNodeTaskEntity entity) {
            FlowNodeTaskDO flowNodeTaskDO = new FlowNodeTaskDO();
            BeanUtils.copyProperties(entity, flowNodeTaskDO);
            String extValue = entity.getExtValue();
            if (StringUtils.isNotBlank(extValue)) {
                NodeTaskExtValueDO extValueDO = JSONObject.parseObject(extValue, NodeTaskExtValueDO.class);
                flowNodeTaskDO.setExtValue(extValueDO);
            }
            return flowNodeTaskDO;

        }

        /**
         * @param flowNodeTaskDO
         * @return
         */
        public static TblFlowNodeTaskEntity converter2TblFlowNodeTaskEntity(FlowNodeTaskDO flowNodeTaskDO) {
            TblFlowNodeTaskEntity entity = new TblFlowNodeTaskEntity();
            BeanUtils.copyProperties(flowNodeTaskDO, entity);
            NodeTaskExtValueDO extValue = flowNodeTaskDO.getExtValue();
            if (extValue != null) {
                String value = JSONObject.toJSONString(extValue);
                entity.setExtValue(value);
            }
            return entity;
        }

        /**
         * @param entity
         * @return
         */
        public static FlowTaskDO converter2FlowTaskDO(TblFlowTaskEntity entity) {
            FlowTaskDO flowTaskDO = new FlowTaskDO();
            BeanUtils.copyProperties(entity, flowTaskDO);
            //
            String extValue = entity.getExtValue();
            if (StringUtils.isNotBlank(extValue)) {
                TaskExtValueDO extValueDO = JSONObject.parseObject(extValue, TaskExtValueDO.class);
                flowTaskDO.setExtValue(extValueDO);
            }
            return flowTaskDO;

        }

        /**
         * @param flowTaskDO
         * @return
         */
        public static TblFlowTaskEntity converter2TblFlowTaskEntity(FlowTaskDO flowTaskDO) {
            TblFlowTaskEntity entity = new TblFlowTaskEntity();
            BeanUtils.copyProperties(flowTaskDO, entity);
            TaskExtValueDO extValue = flowTaskDO.getExtValue();
            if (extValue != null) {
                String value = JSONObject.toJSONString(extValue);
                entity.setExtValue(value);
            }
            return entity;
        }
    }
}
