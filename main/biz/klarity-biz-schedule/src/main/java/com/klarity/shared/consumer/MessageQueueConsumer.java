package com.klarity.shared.consumer;

import com.alibaba.fastjson.JSON;
import com.royee.common.transaction.consumer.RoyeeConsumerMethod;
import org.springframework.stereotype.Component;

/**
 * @author: pengcheng091
 */
@Component
public class MessageQueueConsumer {

    @RoyeeConsumerMethod(topic = "default", tag = "klarity")
    public void onMessage(String message) {
        System.out.println("===================== consumer : " + JSON.toJSONString(message));
    }

}
