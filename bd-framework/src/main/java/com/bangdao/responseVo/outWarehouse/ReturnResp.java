package com.bangdao.responseVo.outWarehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 退货 响应实体
 *
 * @author chaiwei
 * @date 2018-11-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReturnResp {
    private static final long serialVersionUID = 1L;

    /**
     * 退货头表
     */
    private ReturnBaseResp returnBaseResp;
    /**
     * 退货明细
     */
    private List<ReturnDetailedResp> returnDetailedResp;
}
