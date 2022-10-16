package com.klarity.config.dal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: pengcheng091
 * @date 2020-09-22 16:16
 **/
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.klarity.common.dal.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
public class DataSourceConfiguration {
}
