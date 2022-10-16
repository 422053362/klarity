package com.klarity.common.facade.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.klarity.common.facade.vo.EmployeeVO;
import com.klarity.common.facade.vo.HospitalVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 13:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeQueryResponse {

    /**
     * 分页数据
     */
    IPage<EmployeeVO> page;

}
