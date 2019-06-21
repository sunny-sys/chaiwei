package com.bangdao.responseVo.purchasing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 采购退货 响应实体
 *
 * @author chaiwei
 * @date 2018-11-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PurchaseReturnResp {
    private static final long serialVersionUID = 1L;

    /**
     * 采购退货头表
     */
    private PurchaseReturnBaseResp purchaseReturnBaseResp;
    /**
     * 采购退货明细
     */
    private List<PurchaseReturnDetailedResp> purchaseReturnDetailedResp;
}
