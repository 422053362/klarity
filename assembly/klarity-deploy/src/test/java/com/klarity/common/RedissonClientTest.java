package com.klarity.common;

import com.klarity.TestApplication;
import com.klarity.KlarityBaseTest;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;


@SpringBootTest(classes = TestApplication.class)
public class RedissonClientTest extends KlarityBaseTest {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void stringTest() {
        String key = "archetypeDemo_001";
        RList list = redissonClient.getList(key);
        list.add(0, 1);
    }
}
