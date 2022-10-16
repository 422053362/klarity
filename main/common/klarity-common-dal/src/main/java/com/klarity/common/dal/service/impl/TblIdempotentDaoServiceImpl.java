package com.klarity.common.dal.service.impl;

import com.klarity.common.dal.entity.TblIdempotentEntity;
import com.klarity.common.dal.mapper.TblIdempotentMapper;
import com.klarity.common.dal.service.TblIdempotentDaoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 幂等记录表 服务实现类
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
@Service
public class TblIdempotentDaoServiceImpl extends ServiceImpl<TblIdempotentMapper, TblIdempotentEntity> implements TblIdempotentDaoService {

}
