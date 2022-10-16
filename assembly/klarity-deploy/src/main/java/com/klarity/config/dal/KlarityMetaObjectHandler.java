package com.klarity.config.dal;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.royee.common.starters.template.context.BizContext;
import com.royee.common.starters.template.context.BizThreadLocal;
import com.royee.common.utils.share.exception.BusinessException;
import com.klarity.common.dal.entity.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: pengcheng091
 */
@Component
public class KlarityMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object object = metaObject.getOriginalObject();
        if (object instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) object;
            entity.setCreateTime(new Date());
            entity.setModifyTime(new Date());
            //线程变量
            BizContext bizContext = BizThreadLocal.get();
            String userId = bizContext.getUserId();
            if (StringUtils.isNotBlank(userId)) {
                entity.setModifier(userId);
                entity.setCreator(userId);
            } else {
                throw new BusinessException("userId", "当前操作用户的userId不存在");
            }
            String tenantId = bizContext.getTenantId();
            if (StringUtils.isNotBlank(tenantId)) {
                entity.setTenantId(tenantId);
            } else {
                throw new BusinessException("tenantId", "tenantId不存在");
            }
            entity.setDeleted(0L);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object object = metaObject.getOriginalObject();
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        if (object instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) object;
            entity.setModifyTime(new Date());
            //线程变量
            BizContext bizContext = BizThreadLocal.get();
            String userId = bizContext.getUserId();
            if (StringUtils.isNotBlank(userId)) {
                entity.setModifier(userId);
            }
        }
    }
}