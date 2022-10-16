package com.klarity.common.facade.request;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 13:41
 */
@Data
public class HospitalQueryRequest {
    /**
     * 当前页编号
     */
    Long page = 0L;

    /**
     * 分页大小
     */
    Long size = 20L;

    public void check() {
        if (page == null || page < 0) {
            page = 0L;
        }
        if (size == null || size > 100L) {
            size = 20L;
        }
    }
}
