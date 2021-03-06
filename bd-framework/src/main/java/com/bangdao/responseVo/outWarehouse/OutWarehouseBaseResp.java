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
 * 出库基本表  响应实体
 *
 * @author chaiwei
 * @date 2018-11-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class OutWarehouseBaseResp extends ResponseEntity {

    private static final long serialVersionUID = 1L;
    /** id */
    private Integer id;
    /**
     * 客户id
     */
    private Integer supplierId;
    /**
     * 供应商名字
     */
    private String supplierName;
    /**
     * 经手人id
     */
    private Integer handManId;
    /**
     * 经手人名字
     */
    private String handManName;
    /** 总金额 */
    private BigDecimal totalAmount;
    /** 发货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date dateDeliver;
    /** 单据编号 */
    private String documentNumber;
    /** 制单人 */
    private String singlePerson;
    /** 制单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date dateOfProduction;
    /**审批状态*/
    private Integer approvalStatus;

    /**提交时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date submitTime;
    private String submitBy;

    /**单据生效时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date effectiveTime;
}
