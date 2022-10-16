package com.klarity.common.dal.service.impl;

import com.klarity.common.dal.entity.TblTopicMessageEntity;
import com.klarity.common.dal.mapper.TblTopicMessageMapper;
import com.klarity.common.dal.service.TblTopicMessageDaoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 事务消息记录表 服务实现类
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
@Service
public class TblTopicMessageDaoServiceImpl extends ServiceImpl<TblTopicMessageMapper, TblTopicMessageEntity> implements TblTopicMessageDaoService {

}
