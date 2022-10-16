package com.klarity.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.klarity.biz.EmployeeManageBizService;
import com.klarity.biz.converter.EmployeeEntityConverter;
import com.klarity.common.dal.entity.EmployeeEntity;
import com.klarity.common.dal.entity.M2mHospitalEmployeeEntity;
import com.klarity.common.dal.service.EmployeeDaoService;
import com.klarity.common.dal.service.M2mHospitalEmployeeDaoService;
import com.klarity.common.facade.enums.EmploymentStateEnums;
import com.klarity.common.facade.request.EmployeeCreateRequest;
import com.klarity.common.facade.request.EmployeeQueryRequest;
import com.klarity.common.facade.vo.EmployeeVO;
import com.klarity.common.util.exception.KlarityException;
import com.klarity.common.util.exception.KlarityExceptionEnum;
import com.klarity.core.model.context.EmployeeCreateContext;
import com.klarity.core.repository.EmployeeContextRepository;
import com.klarity.core.service.EmployeeCoreService;
import com.royee.common.transaction.service.RoyeeTransactionTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 16:16
 */
@Slf4j
@Service
public class EmployeeManageBizServiceImpl implements EmployeeManageBizService {
    @Autowired
    EmployeeDaoService employeeDaoService;

    @Autowired
    M2mHospitalEmployeeDaoService m2mHospitalEmployeeDaoService;
    @Autowired
    RedissonClient redissonClient;
    @Autowired
    EmployeeContextRepository employeeContextRepository;
    @Autowired
    EmployeeCoreService employeeCoreService;
    @Autowired
    TransactionTemplate transactionTemplate;
    @Autowired
    RoyeeTransactionTemplate royeeTransactionTemplate;

    public static String CreateEmployeePrefix = "create_employee:";

    @Override
    public EmployeeVO createEmployee(EmployeeCreateRequest request) {
        EmployeeVO employeeVO = null;
        //验证用户是否存在 email 唯一
        //锁
        RLock lock = redissonClient.getLock(CreateEmployeePrefix + request.getEmail());
        try {
            if (lock.tryLock()) {
                //构建上下文
                EmployeeCreateContext context = employeeContextRepository.buildEmployeeCreateContext(request);
                //调用核心业务
                employeeCoreService.createEmployee(context);
                //持久化上下文
                royeeTransactionTemplate.execute(status -> {
                    employeeContextRepository.persistEmployeeCreateContext(context);
                });
                EmployeeEntity employeeEntity = context.getEmployeeEntity();
                employeeVO = EmployeeEntityConverter.convert2HospitalVO(employeeEntity);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("createEmployee:", throwable);
            throw new KlarityException(KlarityExceptionEnum.CREATE_EMPLOYEE_ERROR, throwable);
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
        return employeeVO;
    }

    @Override
    public IPage<EmployeeVO> listEmployee(EmployeeQueryRequest request) {
        //分页查询雇佣关系
        IPage<M2mHospitalEmployeeEntity> page = new Page<>(request.getPage(), request.getSize());
        LambdaQueryWrapper<M2mHospitalEmployeeEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(M2mHospitalEmployeeEntity::getHospitalId, request.getHospitalId());
        wrapper.eq(M2mHospitalEmployeeEntity::getStatus, EmploymentStateEnums.WORK.getCode());
        page = m2mHospitalEmployeeDaoService.page(page, wrapper);
        //转化
        List<M2mHospitalEmployeeEntity> list = page.getRecords();
        Map<String, EmployeeVO> employeeVOMap = new HashedMap();
        for (M2mHospitalEmployeeEntity entity : list) {
            String employeeId = entity.getEmployeeId();
            EmployeeEntity employeeEntity = this.employeeDaoService.getEmployeeByEmployeeId(employeeId);
            EmployeeVO employeeVO = EmployeeEntityConverter.convert2HospitalVO(employeeEntity);
            employeeVOMap.put(employeeEntity.getEmployeeId(), employeeVO);
        }
        return page.convert(m2mHospitalEmployeeEntity -> {
            String employeeId = m2mHospitalEmployeeEntity.getEmployeeId();
            EmployeeVO employeeVO = employeeVOMap.get(employeeId);
            return employeeVO;
        });
    }
}
