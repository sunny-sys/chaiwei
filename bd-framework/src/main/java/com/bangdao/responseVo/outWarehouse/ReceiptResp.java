package com.bangdao.responseVo.outWarehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 收款单 响应实体
 *
 * @author chaiwei
 * @date 2018-11-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReceiptResp {
    private static final long serialVersionUID = 1L;

    private ReceiptBaseResp baseResp;

    private List<ReceiptDetailResp> detailResp;
}
