package com.bangdao.responseVo.purchasing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 付款单 响应实体
 *
 * @author chaiwei
 * @date 2018-11-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BillPaymentResp {
    private static final long serialVersionUID = 1L;

    private BillPaymentBaseResp baseResp;

    private List<BillPaymentDetailResp> detailResp;
}
