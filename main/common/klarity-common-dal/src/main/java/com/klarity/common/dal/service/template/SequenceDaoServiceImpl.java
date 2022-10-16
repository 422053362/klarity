package com.klarity.common.dal.service.template;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.royee.common.sequence.model.SequenceBo;
import com.royee.common.sequence.service.SequenceDaoService;
import com.klarity.common.dal.entity.TblSequenceEntity;
import com.klarity.common.dal.service.TblSequenceDaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: pengcheng091
 */
@Service
public class SequenceDaoServiceImpl implements SequenceDaoService {
    @Autowired
    TblSequenceDaoService tblSequenceDaoService;

    @Override
    public int createSequence(SequenceBo bo) {
        TblSequenceEntity entity = new TblSequenceEntity();
        BeanUtils.copyProperties(bo, entity);
        this.tblSequenceDaoService.save(entity);
        return 1;
    }

    @Override
    public int modifySequence(String seqName, long oldValue, long newValue) {
        LambdaUpdateWrapper<TblSequenceEntity> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(TblSequenceEntity::getSeqName, seqName);
        wrapper.eq(TblSequenceEntity::getSeqValue, oldValue);
        wrapper.set(TblSequenceEntity::getSeqValue, newValue);
        boolean success = this.tblSequenceDaoService.update(wrapper);
        return success ? 1 : 0;
    }

    @Override
    public int deleteSequence(String seqName) {
        LambdaQueryWrapper<TblSequenceEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TblSequenceEntity::getSeqName, seqName);
        boolean success = this.tblSequenceDaoService.remove(wrapper);
        return success ? 1 : 0;
    }

    @Override
    public SequenceBo getSequence(String seqName) {
        LambdaUpdateWrapper<TblSequenceEntity> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(TblSequenceEntity::getSeqName, seqName);
        TblSequenceEntity entity = this.tblSequenceDaoService.getOne(wrapper);
        SequenceBo bo = new SequenceBo();
        BeanUtils.copyProperties(entity, bo);
        return bo;
    }

    @Override
    public List<SequenceBo> list() {
        LambdaUpdateWrapper<TblSequenceEntity> wrapper = new LambdaUpdateWrapper<>();
        List<TblSequenceEntity> entityList = this.tblSequenceDaoService.list(wrapper);
        List<SequenceBo> list = entityList.stream().map(entity -> {
            SequenceBo bo = new SequenceBo();
            BeanUtils.copyProperties(entity, bo);
            return bo;
        }).collect(Collectors.toList());
        return list;
    }
}
