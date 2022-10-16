package com.klarity.config.dal;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.royee.common.starters.template.context.BizContext;
import com.royee.common.starters.template.context.BizThreadLocal;
import com.royee.common.utils.share.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: pengcheng091
 */
@Slf4j
@Configuration
public class MybatisPlusConfig {

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
            @Override
            public Expression getTenantId() {
                BizContext context = BizThreadLocal.get();
                String tenantId = context.getTenantId();
                if (StringUtils.isBlank(tenantId)) {
                    log.error("租户Id不能为空,context:{}", JSONObject.toJSONString(context));
                    throw new BusinessException("tenantId", "租户id不能为空");
                }
                return new StringValue(tenantId);
            }

            @Override
            public boolean ignoreTable(String tableName) {
                if (tableName.startsWith("tbl_klarity")) {
                    return false;
                } else {
                    return true;
                }
            }

        }));

        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

}
