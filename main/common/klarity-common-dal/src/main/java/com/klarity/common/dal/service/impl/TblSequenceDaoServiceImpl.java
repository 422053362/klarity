package com.klarity.common.dal.service.impl;

import com.klarity.common.dal.entity.TblSequenceEntity;
import com.klarity.common.dal.mapper.TblSequenceMapper;
import com.klarity.common.dal.service.TblSequenceDaoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 全局唯一Id生成表 服务实现类
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
@Service
public class TblSequenceDaoServiceImpl extends ServiceImpl<TblSequenceMapper, TblSequenceEntity> implements TblSequenceDaoService {

}
