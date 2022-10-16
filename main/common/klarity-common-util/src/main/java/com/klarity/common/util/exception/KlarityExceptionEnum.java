package com.klarity.common.util.exception;

import com.royee.common.utils.share.enums.ErrorCodeBaseEnum;

/**
 * 异常类
 *
 * @author: pengcheng091
 */
public enum KlarityExceptionEnum implements ErrorCodeBaseEnum {
    TASK_STATUS_ERROR("task_status_error", "任务状态错误"),
    CREATE_EMPLOYEE_ERROR("create_employee_error", "员工注册错误"),
    EMPLOYMENT_NOT_EXIST("employment_not_exist", "员工关系不存在"),
    ;

    String code;
    String messsage;

    KlarityExceptionEnum(String code, String messsage) {
        this.code = code;
        this.messsage = messsage;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return messsage;
    }
}
