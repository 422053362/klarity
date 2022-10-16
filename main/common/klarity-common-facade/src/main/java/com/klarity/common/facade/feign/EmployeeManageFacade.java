package com.klarity.common.facade.feign;

import com.klarity.common.facade.request.EmployeeCreateRequest;
import com.klarity.common.facade.request.EmployeeQueryRequest;
import com.klarity.common.facade.request.HospitalCreateRequest;
import com.klarity.common.facade.request.HospitalQueryRequest;
import com.klarity.common.facade.response.EmployeeQueryResponse;
import com.klarity.common.facade.response.HospitalQueryResponse;
import com.klarity.common.facade.vo.EmployeeVO;
import com.klarity.common.facade.vo.HospitalVO;
import com.royee.common.utils.share.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 16:12
 */
public interface EmployeeManageFacade {
    /**
     * @param request
     * @return
     */
    @PostMapping(value = "/employee/create")
    Result<EmployeeVO> create(@RequestBody EmployeeCreateRequest request);

    /**
     * @param request
     * @return
     */
    @GetMapping(value = "/employee/query")
    Result<EmployeeQueryResponse> query(EmployeeQueryRequest request);
}
