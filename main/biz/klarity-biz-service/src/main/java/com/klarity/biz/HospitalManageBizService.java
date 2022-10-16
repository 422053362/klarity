package com.klarity.biz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.klarity.common.facade.vo.HospitalVO;
import com.klarity.common.facade.request.HospitalCreateRequest;
import com.klarity.common.facade.request.HospitalQueryRequest;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 13:49
 */
public interface HospitalManageBizService {
    /**
     * 注册一个医院
     *
     * @param request
     * @return
     */
    HospitalVO createHospital(HospitalCreateRequest request);

    /**
     * 查询医院列表
     *
     * @param request
     * @return
     */
    IPage<HospitalVO> listHospital(HospitalQueryRequest request);
}
