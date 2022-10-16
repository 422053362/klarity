package com.klarity.filter;

import com.alibaba.fastjson.JSON;
import com.royee.common.starters.template.constants.LoggerConstants;
import com.royee.common.starters.template.context.BizContext;
import com.royee.common.starters.template.context.BizThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: pengcheng091
 */
@Slf4j
@WebFilter(filterName = "AuthFilter", urlPatterns = "/*")
@Component
public class AuthFilter implements Filter {
    @Value("${environment:development}")
    String environment;

    public AuthFilter() {
        System.out.println("init AuthFilter");
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        BizContext context = BizThreadLocal.init();
        if (StringUtils.equals("development", environment)) {
            context.setUserId("100202001");
            context.setTracerId("webTracerId");
            context.setTenantId("webTenantId");
            context.setLang("zh-cn");
        }
        MDC.put(LoggerConstants.TRACER_ID, context.getTracerId());
        log.info("url={}, params={}", req.getRequestURI(), JSON.toJSONString(req.getHeaderNames()));
        chain.doFilter(req, response);
    }

    @Override
    public void destroy() {
    }
}