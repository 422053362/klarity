package com.klarity.common.facade.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.klarity.common.facade.vo.HospitalVO;
import com.klarity.common.facade.vo.TaskVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 20:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskQueryResponse {
    /**
     * 分页数据
     */
    IPage<TaskVO> page;
}
