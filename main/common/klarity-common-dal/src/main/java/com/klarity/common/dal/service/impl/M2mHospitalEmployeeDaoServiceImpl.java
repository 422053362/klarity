package com.klarity.common.dal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.klarity.common.dal.entity.M2mHospitalEmployeeEntity;
import com.klarity.common.dal.mapper.M2mHospitalEmployeeMapper;
import com.klarity.common.dal.service.M2mHospitalEmployeeDaoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 医院与员工之间的关系表 服务实现类
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
@Service
public class M2mHospitalEmployeeDaoServiceImpl extends ServiceImpl<M2mHospitalEmployeeMapper, M2mHospitalEmployeeEntity> implements M2mHospitalEmployeeDaoService {

    @Override
    public M2mHospitalEmployeeEntity getM2mHospitalEmployee(String employeeId, String hospitalId, String employmentState) {
        LambdaQueryWrapper<M2mHospitalEmployeeEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(M2mHospitalEmployeeEntity::getEmployeeId, employeeId);
        wrapper.eq(M2mHospitalEmployeeEntity::getHospitalId, hospitalId);
        wrapper.eq(M2mHospitalEmployeeEntity::getStatus, employmentState);
        List<M2mHospitalEmployeeEntity> list = this.baseMapper.selectList(wrapper);
        //没有唯一索引，所以需要这么处理
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<String> getM2mHospitalEmployeeList(String employeeId, String employmentState) {
        LambdaQueryWrapper<M2mHospitalEmployeeEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(M2mHospitalEmployeeEntity::getEmployeeId, employeeId);
        wrapper.eq(M2mHospitalEmployeeEntity::getStatus, employmentState);
        wrapper.select(M2mHospitalEmployeeEntity::getHospitalId);
        List<M2mHospitalEmployeeEntity> list = this.baseMapper.selectList(wrapper);
        //返回医院Id列表
        return list.stream().map(M2mHospitalEmployeeEntity::getHospitalId).collect(Collectors.toList());
    }
}
