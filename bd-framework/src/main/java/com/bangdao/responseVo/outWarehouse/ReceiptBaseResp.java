package com.bangdao.responseVo.outWarehouse;

import com.bangdao.framework.web.responseVo.ResponseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 收款单基本表  响应实体
 *
 * @author chaiwei
 * @date 2018-11-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ReceiptBaseResp extends ResponseEntity {

    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;
    /** 供应商Id（关联往来单位表id） */
    private Integer supplierId;
    /** 供应商Id（关联往来单位表id） */
    private String supplierName;
    /** 经手人id(关联员工表) */
    private Integer handManId;
    /** 经手人id(关联员工表) */
    private String handManName;
    /** 收款类型 */
    private String receivablesType;
    /** 本次核销 */
    private BigDecimal writeOff;
    /** 整单折扣 */
    private BigDecimal billDiscount;
    /** 实收金额 */
    private BigDecimal amountCollected;
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
