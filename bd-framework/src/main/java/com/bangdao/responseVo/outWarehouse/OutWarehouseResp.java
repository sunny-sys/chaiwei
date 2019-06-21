package com.bangdao.responseVo.outWarehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 出库 响应实体
 *
 * @author chaiwei
 * @date 2018-11-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OutWarehouseResp {
    private static final long serialVersionUID = 1L;

    /**
     * 出库头表
     */
    private OutWarehouseBaseResp outWarehouseBaseResp;
    /**
     * 出库明细
     */
    private List<OutWarehouseDetailedResp> outWarehouseDetailedResp;
}
