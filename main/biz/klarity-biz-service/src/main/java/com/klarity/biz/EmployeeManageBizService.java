package com.klarity.biz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.klarity.common.facade.request.EmployeeCreateRequest;
import com.klarity.common.facade.request.EmployeeQueryRequest;
import com.klarity.common.facade.request.HospitalCreateRequest;
import com.klarity.common.facade.request.HospitalQueryRequest;
import com.klarity.common.facade.vo.EmployeeVO;
import com.klarity.common.facade.vo.HospitalVO;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 16:15
 */
public interface EmployeeManageBizService {
    /**
     * 注册一个员工
     *
     * @param request
     * @return
     */
    EmployeeVO createEmployee(EmployeeCreateRequest request);

    /**
     * 列出全部的员工
     *
     * @param request
     * @return
     */
    IPage<EmployeeVO> listEmployee(EmployeeQueryRequest request);
}
