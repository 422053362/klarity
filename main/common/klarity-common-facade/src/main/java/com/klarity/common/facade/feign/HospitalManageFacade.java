package com.klarity.common.facade.feign;

import com.klarity.common.facade.vo.HospitalVO;
import com.klarity.common.facade.request.HospitalCreateRequest;
import com.klarity.common.facade.request.HospitalQueryRequest;
import com.klarity.common.facade.response.HospitalQueryResponse;
import com.royee.common.utils.share.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 13:43
 */
public interface HospitalManageFacade {
    /**
     * @param request
     * @return
     */
    @PostMapping(value = "/hospital/create")
    Result<HospitalVO> create(@RequestBody HospitalCreateRequest request);

    /**
     * @param request
     * @return
     */
    @GetMapping(value = "/hospital/query")
    Result<HospitalQueryResponse> query(HospitalQueryRequest request);
}
