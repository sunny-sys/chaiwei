package com.bangdao.requestVo.purchase;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;


/**
 * 付款单详情表 pur_bill_payment_detail 请求实体
 *
 * @author chaiwei
 * @date 2018-11-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillPaymentDetailReq extends RequestEntity {

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
    /**
     * 删除标志
     */
    private Integer IsDelete;
}
