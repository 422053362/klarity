package com.klarity.biz.converter;

import com.klarity.common.dal.entity.HospitalEntity;
import com.klarity.common.facade.vo.HospitalVO;
import org.springframework.beans.BeanUtils;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 13:52
 */
public class HospitalEntityConverter {
    /**
     * @param hospitalEntity
     * @return
     */
    public static HospitalVO convert2HospitalVO(HospitalEntity hospitalEntity) {
        HospitalVO hospitalVO = new HospitalVO();
        BeanUtils.copyProperties(hospitalEntity, hospitalVO);
        return hospitalVO;
    }
}
