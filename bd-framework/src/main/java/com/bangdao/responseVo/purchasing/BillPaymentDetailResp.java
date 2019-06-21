package com.bangdao.responseVo.purchasing;

import com.bangdao.framework.web.responseVo.ResponseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 付款单详情表 pur_bill_payment_detail 响应实体
 *
 * @author chaiwei
 * @date 2018-11-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BillPaymentDetailResp extends ResponseEntity {

    private static final long serialVersionUID = 1L;

    /**  */
    private Integer id;
    /** 付款单基本表id */
    private Integer parentId;
    /** 来源id */
    private Integer sourceId;
    /** 来源（标记来源采购还是退货） */
    private String source;
    /**
     * 本次核销金额
     */
    private BigDecimal writeOff;
    /**
     * 未核销金额
     */
    private BigDecimal unwrittenCancellation;
}
