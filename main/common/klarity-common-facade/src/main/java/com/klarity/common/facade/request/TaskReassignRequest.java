package com.klarity.common.facade.request;

import com.klarity.common.facade.enums.TaskPriorityEnums;
import com.klarity.common.facade.enums.TaskStatusEnums;
import com.royee.common.utils.share.helper.RuleValidHelper;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 18:02
 */
@Data
public class TaskReassignRequest {
    /**
     * 任务Id
     */
    String taskId;

    /**
     * 员工Id
     */
    String employeeId;

    /**
     * 医院Id
     */
    String hospitalId;

    /**
     * 输入参数验证
     */
    public void check() {
        if (!StringUtils.isNotBlank(taskId)) {
            RuleValidHelper.wrapBiz("taskId should not be blank");
        }
        if (!StringUtils.isNotBlank(employeeId)) {
            RuleValidHelper.wrapBiz("employeeId should not be blank");
        }
        if (!StringUtils.isNotBlank(hospitalId)) {
            RuleValidHelper.wrapBiz("hospitalId should not be blank");
        }
    }
}
