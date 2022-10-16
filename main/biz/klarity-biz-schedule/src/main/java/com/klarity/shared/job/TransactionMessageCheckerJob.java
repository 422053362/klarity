package com.klarity.shared.job;

import com.alibaba.fastjson.JSONObject;
import com.royee.common.transaction.checker.TransactionMessageChecker;
import com.royee.common.transaction.enums.RoyeeMessageStatus;
import com.royee.common.transaction.model.TransactionMessageBO;
import com.royee.common.transaction.service.RoyeeTransactionTemplate;
import com.royee.common.transaction.service.TransactionMessageDaoService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 事务消息检查任务
 *
 * @author: pengcheng091
 */
@Slf4j
@Component
public class TransactionMessageCheckerJob implements TransactionMessageChecker {

    @Autowired
    TransactionMessageDaoService transactionMessageDaoService;
    @Autowired
    RoyeeTransactionTemplate transactionTemplate;
    @Autowired
    RedissonClient redissonClient;

    public void checkTransactionMessage() {

    }

    @Override
    public void check() {
        LocalDateTime localDateTime = LocalDateTime.now();
        List<TransactionMessageBO> list = transactionMessageDaoService.getTransactionMessage(localDateTime, RoyeeMessageStatus.INIT, 10);
        for (TransactionMessageBO transactionMessageBO : list) {
            String transactionId = transactionMessageBO.getTransactionId();
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    TransactionMessageBO bo = transactionMessageDaoService.lockTransactionMessage(transactionId);
                    if (bo != null) {
                        if (RoyeeMessageStatus.INIT.getCode().equals(bo.getStatus()) || RoyeeMessageStatus.FAILED.getCode().equals(bo.getStatus())) {
                            boolean success = sendMessage(bo);
                            log.info("发送消息 message:{},result:{}", JSONObject.toJSONString(bo), success);
                            if (success) {
                                TransactionMessageBO update = new TransactionMessageBO();
                                update.setId(bo.getId());
                                update.setStatus(RoyeeMessageStatus.SUCCESS.getCode());
                            }
                        }
                    }
                }
            });
        }
    }

    Boolean sendMessage(TransactionMessageBO transactionMessageBO) {
        String topic = transactionMessageBO.getTopic();
        RList<String> list = redissonClient.getList(topic);
        if (list.size() < 10) {
            list.add(JSONObject.toJSONString(transactionMessageBO));
            return true;
        } else {
            return false;
        }
    }
}
