package com.klarity.core.model.enums;

import com.royee.common.utils.share.enums.ErrorCodeBaseEnum;

/**
 * @author: pengcheng091
 * @date 2020-09-22 16:16
 **/
public enum ErrorCodeEnum implements ErrorCodeBaseEnum {

    /**
     * 延迟任务不存在
     */
    EMPLOYEE_NOT_FOUND("200001", "延迟任务不存在"),
    DELAY_TASK_NOT_INIT_STATE("200002", "任务不是初始化状态"),
    DELAY_TASK_NOT_PRELOAD_STATE("200003", "任务不是待完结状态"),
    DELAY_TASK_MODIFIED("200003", "任务不是待完结状态");

    ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private final String code;

    private final String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
