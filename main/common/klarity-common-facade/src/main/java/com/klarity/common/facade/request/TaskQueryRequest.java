package com.klarity.common.facade.request;

import com.royee.common.utils.share.helper.RuleValidHelper;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 18:03
 */
@Data
public class TaskQueryRequest {
    /**
     * 员工Id
     */
    String employeeId;
    /**
     * 医院Id
     */
    String hospitalId;

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

    public void checkForEmployee() {
        this.check();
        if (!StringUtils.isNotBlank(employeeId)) {
            RuleValidHelper.wrapBiz("employeeId should not be blank");
        }
    }

    public void checkForHospital() {
        this.check();
        if (!StringUtils.isNotBlank(hospitalId)) {
            RuleValidHelper.wrapBiz("hospitalId should not be blank");
        }
    }
}
