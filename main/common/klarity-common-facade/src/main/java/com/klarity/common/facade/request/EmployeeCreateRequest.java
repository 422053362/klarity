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
public class EmployeeCreateRequest {
    /**
     * 医院Id
     */
    String hospitalId;

    /**
     * 员工名称
     */
    String employeeName;

    /**
     * 员工邮箱
     */
    String email;

    /**
     * 输入参数验证
     */
    public void check() {
        if (!StringUtils.isNotBlank(email)) {
            RuleValidHelper.wrapBiz("email should not be blank");
        }
        if (!StringUtils.isNotBlank(hospitalId)) {
            RuleValidHelper.wrapBiz("hospitalId should not be blank");
        }
        if (!StringUtils.isNotBlank(employeeName)) {
            RuleValidHelper.wrapBiz("employeeName should not be blank");
        }
    }
}
