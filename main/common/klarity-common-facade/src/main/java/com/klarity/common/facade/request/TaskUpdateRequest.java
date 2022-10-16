package com.klarity.common.facade.request;

import com.klarity.common.facade.enums.TaskPriorityEnums;
import com.klarity.common.facade.enums.TaskStatusEnums;
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
public class TaskUpdateRequest {
    /**
     * 任务id
     */
    String taskId;
    /**
     * 任务Id
     */
    String title;

    /**
     * 任务描述
     */
    String description;

    /**
     * 任务优先级
     */
    String priority;

    /**
     * 任务状态
     */
    String status;

    /**
     * 输入参数验证
     */
    public void check() {
        if (!StringUtils.isNotBlank(taskId)) {
            RuleValidHelper.wrapBiz("taskId should not be blank");
        }
        if (!StringUtils.isNotBlank(title)) {
            RuleValidHelper.wrapBiz("title should not be blank");
        }
        if (!StringUtils.isNotBlank(description)) {
            RuleValidHelper.wrapBiz("description should not be blank");
        }
        if (!StringUtils.isNotBlank(priority) || !TaskPriorityEnums.isValid(priority)) {
            RuleValidHelper.wrapBiz("priority should not be blank  or is not valid");
        }
        if (!StringUtils.isNotBlank(status) || !TaskStatusEnums.isValid(status)) {
            RuleValidHelper.wrapBiz("status should not be blank or is not valid");
        }
    }
}
