package com.klarity.common.dal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.klarity.common.dal.entity.EmployeeEntity;
import com.klarity.common.dal.mapper.EmployeeMapper;
import com.klarity.common.dal.service.EmployeeDaoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工信息表 服务实现类
 * </p>
 *
 * @author: pengcheng091
 * @since 2022-10-16
 */
@Service
public class EmployeeDaoServiceImpl extends ServiceImpl<EmployeeMapper, EmployeeEntity> implements EmployeeDaoService {

    @Override
    public EmployeeEntity selectEmployeeByEmail(String email) {
        LambdaQueryWrapper<EmployeeEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EmployeeEntity::getEmail, email);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public EmployeeEntity getEmployeeByEmployeeId(String employeeId) {
        LambdaQueryWrapper<EmployeeEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EmployeeEntity::getEmployeeId, employeeId);
        return this.baseMapper.selectOne(wrapper);
    }
}
