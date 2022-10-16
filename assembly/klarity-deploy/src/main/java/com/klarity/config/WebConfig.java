package com.klarity.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @author: pengcheng091
 * @date 2020-09-22 16:16
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration config = new CorsConfiguration();
        //开放哪些ip、端口、域名的访问权限，星号表示开放所有域
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        //是否允许发送Cookie信息
        config.setAllowCredentials(true);
        //开放哪些Http方法，允许跨域访问
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        //允许HTTP请求中的携带哪些Header信息
        config.addAllowedHeader("*");
        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
        config.addExposedHeader("*");

        //添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        return new CorsFilter(configSource);
    }

    @Bean
    FastJsonHttpMessageConverter getConverters() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig jsonConfig = new FastJsonConfig();
        jsonConfig.setCharset(Charset.forName("UTF-8"));
        converter.setFastJsonConfig(jsonConfig);
        converter.setDefaultCharset(Charset.forName("Utf-8"));
        return converter;
    }
}
