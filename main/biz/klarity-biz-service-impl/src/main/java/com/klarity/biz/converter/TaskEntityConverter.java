package com.klarity.biz.converter;

import com.klarity.common.dal.entity.EmployeeEntity;
import com.klarity.common.dal.entity.TaskEntity;
import com.klarity.common.facade.vo.EmployeeVO;
import com.klarity.common.facade.vo.TaskVO;
import org.springframework.beans.BeanUtils;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 20:03
 */
public class TaskEntityConverter {
    /**
     * @param taskEntity
     * @return
     */
    public static TaskVO convert2TaskVO(TaskEntity taskEntity) {
        TaskVO taskVO = new TaskVO();
        BeanUtils.copyProperties(taskEntity, taskVO);
        return taskVO;
    }
}
