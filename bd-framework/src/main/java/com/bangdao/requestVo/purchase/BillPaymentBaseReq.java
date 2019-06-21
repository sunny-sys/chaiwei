package com.bangdao.requestVo.purchase;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 付款单基本表 pur_bill_payment_base 请求实体
 *
 * @author chaiwei
 * @date 2018-11-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillPaymentBaseReq extends RequestEntity {

    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;
    /** 供应商Id（关联往来单位表id） */
    private Integer supplierId;
    /** 经手人id(关联员工表) */
    private Integer handManId;
    /** 付款类型 */
    private String paymentType;
    /** 本次核销 */
    private BigDecimal writeOff;
    /** 整单折扣 */
    private BigDecimal billDiscount;
    /** 实付金额 */
    private BigDecimal amountPayment;
    /** 单据日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date billDate;
    /** 单据编号 */
    private String documentNumber;
    /** 制单人 */
    private String singlePerson;
    /** 制单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date dateOfProduction;
}
