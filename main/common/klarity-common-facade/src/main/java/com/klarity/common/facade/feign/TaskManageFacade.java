package com.klarity.common.facade.feign;

import com.klarity.common.facade.request.*;
import com.klarity.common.facade.response.TaskQueryResponse;
import com.klarity.common.facade.vo.HospitalVO;
import com.klarity.common.facade.vo.TaskVO;
import com.royee.common.utils.share.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 18:01
 */
public interface TaskManageFacade {
    @PostMapping(value = "/task/create")
    Result<TaskVO> create(@RequestBody TaskCreateRequest request);

    @PostMapping(value = "/task/update")
    Result<TaskVO> update(@RequestBody TaskUpdateRequest request);

    @PostMapping(value = "/task/reassign")
    Result<TaskVO> reassign(@RequestBody TaskReassignRequest request);

    @GetMapping(value = "/task/query/by/employee")
    Result<TaskQueryResponse> queryTaskByEmployee(TaskQueryRequest request);

    @GetMapping(value = "/task/query/by/hospital")
    Result<TaskQueryResponse> queryTaskByHospital(TaskQueryRequest request);
}
