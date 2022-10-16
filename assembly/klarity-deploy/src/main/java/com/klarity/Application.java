package com.klarity;

import com.klarity.filter.AuthFilter;
import com.royee.common.starters.template.constants.LoggerConstants;
import com.royee.common.starters.template.context.BizContext;
import com.royee.common.starters.template.context.BizThreadLocal;
import com.royee.common.transaction.DistributeTransactionConfiguration;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 应用启动入口
 * **包扫描路径尽可能小**
 *
 * @version $
 * @author: pengcheng091
 */
@SpringBootApplication(scanBasePackages = {"com.royee.common", "com.klarity"})
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(
                Application.class
        );
    }

    public static void main(String[] args) {
        {
            BizContext bizContext = BizThreadLocal.init();
            String tracerId = "application-startup-" + System.currentTimeMillis();
            MDC.put(LoggerConstants.TRACER_ID, tracerId);
            bizContext.setTenantId("system");
            bizContext.setTracerId(tracerId);
        }
        new SpringApplication(Application.class).run(args);
    }

    @Autowired
    AuthFilter authFilter;

    @Bean
    public FilterRegistrationBean<AuthFilter> orderFilter() {
        FilterRegistrationBean<AuthFilter> filter = new FilterRegistrationBean<>();
        filter.setName("ReqFilter");
        filter.setFilter(authFilter);
        filter.setOrder(-1);
        return filter;
    }

}