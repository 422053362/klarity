package com.klarity.core.repository;

import com.klarity.common.facade.request.EmployeeCreateRequest;
import com.klarity.core.model.context.EmployeeCreateContext;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 16:42
 */
public interface EmployeeContextRepository {
    /**
     * @return
     */
    EmployeeCreateContext buildEmployeeCreateContext(EmployeeCreateRequest request);

    /**
     * @param context
     */
    void persistEmployeeCreateContext(EmployeeCreateContext context);
}
