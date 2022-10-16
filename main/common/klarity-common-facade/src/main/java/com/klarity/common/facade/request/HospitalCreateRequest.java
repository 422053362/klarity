package com.klarity.common.facade.request;

import com.klarity.common.util.exception.KlarityException;
import com.klarity.common.util.exception.KlarityExceptionEnum;
import com.royee.common.utils.share.helper.RuleValidHelper;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 14:48
 */
@Data
public class HospitalCreateRequest {
    /**
     * 医院名称
     */
    String hospitalName;

    public void check() {
        if (!StringUtils.isNotBlank(hospitalName)) {
            RuleValidHelper.wrapBiz("hospitalName should not be blank");
        }
    }
}
