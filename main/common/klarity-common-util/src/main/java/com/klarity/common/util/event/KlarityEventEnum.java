package com.klarity.common.util.event;

import com.royee.common.starters.template.enums.TemplateEventEnum;
import lombok.AllArgsConstructor;

/**
 * @author: pengcheng091
 */

@AllArgsConstructor
public enum KlarityEventEnum implements TemplateEventEnum {
    //hospital management
    HOSPITAL_REGISTER("HospitalRegister", "医院注册"),
    HOSPITAL_LIST("HospitalList", "医院列表"),
    //employee management
    EMPLOYEE_CREATE("EmployeeCreate", "员工注册"),
    EMPLOYEE_LIST("EmployeeList", "员工列表"),
    //task management
    CREATE_TASK("CreateTask", "创建一个任务"),
    UPDATE_TASK("UpdateTask", "更新一个任务"),
    REASSIGN_TASK("ReassignTask", "重新分配任务给一个员工"),
    LIST_TASK_OWNED_BY_EMPLOYEE("ListTaskOwnedByEmployee", "员工任务列表"),
    LIST_TASK_UNDER_HOSPITAL("ListTaskUnderHospital", "医院任务列表"),
    ;


    String code;
    String desc;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
