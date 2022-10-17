package com.klarity.biz.converter;

import com.klarity.common.dal.entity.EmployeeEntity;
import com.klarity.common.facade.vo.EmployeeVO;
import org.springframework.beans.BeanUtils;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 17:13
 */
public class EmployeeEntityConverter {
    /**
     * @param employeeEntity
     * @return
     */
    public static EmployeeVO convert2EmployeeVO(EmployeeEntity employeeEntity) {
        EmployeeVO employeeVO = new EmployeeVO();
        BeanUtils.copyProperties(employeeEntity, employeeVO);
        return employeeVO;
    }
}
