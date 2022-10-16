package com.klarity.common.facade.request;

import com.klarity.common.util.exception.KlarityException;
import com.klarity.common.util.exception.KlarityExceptionEnum;
import com.royee.common.utils.share.helper.RuleValidHelper;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 13:41
 */
@Data
public class EmployeeQueryRequest {
    /**
     * 当前页编号
     */
    Long page = 0L;

    /**
     * 分页大小
     */
    Long size = 20L;

    /**
     * 查询邮箱
     */
    String hospitalId;

    public void check() {
        if (page == null || page < 0) {
            page = 0L;
        }
        if (size == null || size > 100L) {
            size = 20L;
        }
        if (!StringUtils.isNotBlank(hospitalId)) {
            RuleValidHelper.wrapBiz("hospitalId should not be blank");
        }
    }
}
