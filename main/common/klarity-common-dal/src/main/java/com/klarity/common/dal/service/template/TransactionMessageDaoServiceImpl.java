package com.klarity.common.dal.service.template;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.royee.common.sequence.service.MysqlSequence;
import com.royee.common.transaction.enums.RoyeeMessageStatus;
import com.royee.common.transaction.model.TransactionMessageBO;
import com.royee.common.transaction.service.TransactionMessageDaoService;
import com.klarity.common.dal.entity.TblTopicMessageEntity;
import com.klarity.common.dal.service.TblTopicMessageDaoService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: pengcheng091
 */
@Service
public class TransactionMessageDaoServiceImpl implements TransactionMessageDaoService {
    @Autowired
    MysqlSequence mysqlSequence;
    @Autowired
    TblTopicMessageDaoService topicMessageDaoService;

    @Override
    public String getTransactionId() {
        return mysqlSequence.nextVal();
    }

    @Override
    public void saveTransactionMessage(TransactionMessageBO transactionMessageBO) {
        TblTopicMessageEntity topicMessageEntity = TblTopicMessageEntityConverter.converter2TblTopicMessageEntity(transactionMessageBO);
        this.topicMessageDaoService.save(topicMessageEntity);
    }

    @Override
    public TransactionMessageBO lockTransactionMessage(String transactionId) {
        LambdaQueryWrapper<TblTopicMessageEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblTopicMessageEntity::getTransactionId, transactionId);
        wrapper.last("for update");
        TblTopicMessageEntity entity = this.topicMessageDaoService.getOne(wrapper);
        return TblTopicMessageEntityConverter.converter2TransactionMessageBO(entity);
    }

    @Override
    public void updateTransactionMessage(TransactionMessageBO transactionMessageBO) {
        TblTopicMessageEntity entity = TblTopicMessageEntityConverter.converter2TblTopicMessageEntity(transactionMessageBO);
        this.topicMessageDaoService.updateById(entity);
    }

    @Override
    public List<TransactionMessageBO> getTransactionMessage(LocalDateTime maxDateTime, RoyeeMessageStatus status, Integer pageSize) {
        Page<TblTopicMessageEntity> page = new Page<>(1, 10);
        LambdaQueryWrapper<TblTopicMessageEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.le(TblTopicMessageEntity::getTriggerTime, maxDateTime);
        queryWrapper.eq(TblTopicMessageEntity::getStatus, status.getCode());
        page = this.topicMessageDaoService.page(page, queryWrapper);
        List<TblTopicMessageEntity> list = page.getRecords();
        List<TransactionMessageBO> retList = new LinkedList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            retList = list.stream().map(TblTopicMessageEntityConverter::converter2TransactionMessageBO).collect(Collectors.toList());
        }
        return retList;
    }

    public static class TblTopicMessageEntityConverter {

        /**
         * @param transactionMessageBO
         * @return
         */
        public static TblTopicMessageEntity converter2TblTopicMessageEntity(TransactionMessageBO transactionMessageBO) {
            TblTopicMessageEntity topicMessageEntity = new TblTopicMessageEntity();
            BeanUtils.copyProperties(transactionMessageBO, topicMessageEntity);
            Object payload = transactionMessageBO.getPayload();
            topicMessageEntity.setPayload(JSONObject.toJSONString(payload));
            return topicMessageEntity;
        }

        /**
         * @param entity
         * @return
         */
        public static TransactionMessageBO converter2TransactionMessageBO(TblTopicMessageEntity entity) {
            TransactionMessageBO transactionMessageBO = new TransactionMessageBO();
            BeanUtils.copyProperties(entity, transactionMessageBO);
            return transactionMessageBO;
        }
    }
}
