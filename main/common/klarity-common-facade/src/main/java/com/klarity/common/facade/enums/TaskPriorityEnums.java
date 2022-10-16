package com.klarity.common.facade.enums;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 20:21
 */
public enum TaskPriorityEnums {
    URGENT("urgent"),
    HIGHT("hight"),
    LOW("low"),

    ;
    private final String code;

    TaskPriorityEnums(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Boolean isValid(String priority) {
        return URGENT.getCode().equals(priority) ||
                HIGHT.getCode().equals(priority) ||
                LOW.getCode().equals(priority);
    }
}
