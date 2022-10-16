package com.klarity.common.dal.service.template;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.royee.common.idempotent.model.IdempotentDTO;
import com.royee.common.idempotent.service.IdempotentDaoService;
import com.klarity.common.dal.entity.TblIdempotentEntity;
import com.klarity.common.dal.service.TblIdempotentDaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: pengcheng091
 */
@Service
public class IdempotentDaoServiceImpl implements IdempotentDaoService {
    @Autowired
    TblIdempotentDaoService tblIdempotentDaoService;

    @Override
    public Boolean insert(IdempotentDTO idempotentDTO) {
        TblIdempotentEntity entity = IdempotentConverter.conveter2TblIdempotentEntity(idempotentDTO);
        return this.tblIdempotentDaoService.save(entity);
    }

    @Override
    public IdempotentDTO selectById(String txnId) {
        LambdaQueryWrapper<TblIdempotentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblIdempotentEntity::getTxnId, txnId);
        TblIdempotentEntity entity = this.tblIdempotentDaoService.getOne(wrapper);
        return IdempotentConverter.converter2IdempotentDTO(entity);
    }

    @Override
    public IdempotentDTO selectForUpdate(String txnId) {
        LambdaQueryWrapper<TblIdempotentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblIdempotentEntity::getTxnId, txnId);
        wrapper.last("for update");
        TblIdempotentEntity entity = this.tblIdempotentDaoService.getOne(wrapper);
        return IdempotentConverter.converter2IdempotentDTO(entity);
    }

    @Override
    public Boolean updateById(IdempotentDTO idempotentDTO) {
        TblIdempotentEntity update = IdempotentConverter.conveter2TblIdempotentEntity(idempotentDTO);
        return this.tblIdempotentDaoService.updateById(update);
    }

    public static class IdempotentConverter {

        /**
         * @param entity
         * @return
         */
        public static IdempotentDTO converter2IdempotentDTO(TblIdempotentEntity entity) {
            IdempotentDTO idempotentDTO = new IdempotentDTO();
            BeanUtils.copyProperties(entity, idempotentDTO);
            return idempotentDTO;

        }

        /**
         * @param idempotentDTO
         * @return
         */
        public static TblIdempotentEntity conveter2TblIdempotentEntity(IdempotentDTO idempotentDTO) {
            TblIdempotentEntity entity = new TblIdempotentEntity();
            BeanUtils.copyProperties(idempotentDTO, entity);
            return entity;
        }
    }
}
