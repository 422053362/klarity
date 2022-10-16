package com.klarity.common.facade.enums;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 20:19
 */
public enum TaskStatusEnums {
    OPEN("open"),
    FAILED("failed"),
    COMPLETED("completed"),
    ;
    private final String code;

    TaskStatusEnums(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Boolean isValid(String priority) {
        return OPEN.getCode().equals(priority) ||
                FAILED.getCode().equals(priority) ||
                COMPLETED.getCode().equals(priority);
    }
}
