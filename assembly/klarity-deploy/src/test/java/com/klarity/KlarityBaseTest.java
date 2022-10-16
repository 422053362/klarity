package com.klarity;

import com.royee.common.starters.template.constants.LoggerConstants;
import com.royee.common.starters.template.context.BizContext;
import com.royee.common.starters.template.context.BizThreadLocal;
import org.slf4j.MDC;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.UUID;
import java.util.concurrent.locks.LockSupport;

@SpringBootTest(classes = TestApplication.class)
public abstract class KlarityBaseTest extends AbstractTestNGSpringContextTests {
    @BeforeTest
    public void beforeTest() {
        BizContext bizContext = BizThreadLocal.init();
        String tracerId = "beforeMethod:" + UUID.randomUUID().toString();
        MDC.put(LoggerConstants.TRACER_ID, tracerId);
        bizContext.setTenantId("mockTenantId");
        bizContext.setLang("zh-cn");
        bizContext.setToken("mockToken");
        bizContext.setTracerId(tracerId);
        bizContext.setUserId("mockUser:" + UUID.randomUUID());
    }

    @BeforeMethod
    public void beforeMethod() {
        BizContext bizContext = BizThreadLocal.init();
        String tracerId = "beforeMethod:" + UUID.randomUUID().toString();
        MDC.put(LoggerConstants.TRACER_ID, tracerId);
        bizContext.setTenantId("mockTenantId");
        bizContext.setLang("zh-cn");
        bizContext.setToken("mockToken");
        bizContext.setTracerId(tracerId);
        bizContext.setUserId("mockUser:" + UUID.randomUUID());
    }

    public static class Solution extends Thread {
        Thread mainThread;

        int factor;

        public Solution(Thread mainThread) {
            this.mainThread = mainThread;
        }

        public void setFactor(int factor) {
            this.factor = factor;
        }

        public synchronized void run() {
            while (factor < 100) {
                LockSupport.park();
                if (factor < 100) {
                    System.out.println(factor);
                }
                LockSupport.unpark(mainThread);
            }
        }
    }

    public static synchronized void main(String[] args) {
        Solution tA = new Solution(Thread.currentThread());
        Solution tB = new Solution(Thread.currentThread());
        Solution tC = new Solution(Thread.currentThread());
        tA.start();
        tB.start();
        tC.start();
        for (int i = 0; i < 100; i++) {
            if (i % 3 == 0) {
                tA.setFactor(i);
                LockSupport.unpark(tA);
                LockSupport.park();
            } else if (i % 5 == 0) {
                tB.setFactor(i);
                LockSupport.unpark(tB);
                LockSupport.park();
            } else {
                tC.setFactor(i);
                LockSupport.unpark(tC);
                LockSupport.park();
            }

        }
        tA.setFactor(101);
        tB.setFactor(101);
        tC.setFactor(101);
        LockSupport.unpark(tA);
        LockSupport.unpark(tB);
        LockSupport.unpark(tC);
    }
}
