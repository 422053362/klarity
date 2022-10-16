package com.klarity.common.facade.enums;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 13:43
 */
public enum EmploymentStateEnums {
    WORK("work"),
    LEAVE("leave"),
    ;
    private final String code;

    EmploymentStateEnums(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}