package com.klarity.biz.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.klarity.biz.HospitalManageBizService;
import com.klarity.biz.converter.HospitalEntityConverter;
import com.klarity.common.dal.entity.HospitalEntity;
import com.klarity.common.dal.service.HospitalDaoService;
import com.klarity.common.facade.vo.HospitalVO;
import com.klarity.common.facade.request.HospitalCreateRequest;
import com.klarity.common.facade.request.HospitalQueryRequest;
import com.klarity.common.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 13:51
 */
@Service
public class HospitalManageBizServiceImpl implements HospitalManageBizService {

    @Autowired
    HospitalDaoService hospitalDaoService;

    @Override
    public HospitalVO createHospital(HospitalCreateRequest request) {
        //
        String hospitalId = IdUtils.nextVal();
        //
        HospitalEntity entity = new HospitalEntity();
        entity.setHospitalId(hospitalId);
        entity.setHospitalName(request.getHospitalName());
        entity.setExtInfo("");
        hospitalDaoService.save(entity);
        //
        return HospitalEntityConverter.convert2HospitalVO(entity);
    }

    @Override
    public IPage<HospitalVO> listHospital(HospitalQueryRequest request) {
        //
        IPage<HospitalEntity> page = new Page<>(request.getPage(), request.getSize());
        page = hospitalDaoService.page(page);
        //
        return page.convert(HospitalEntityConverter::convert2HospitalVO);
    }
}
