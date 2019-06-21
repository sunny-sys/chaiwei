package com.bangdao.domain.purchase;

import com.bangdao.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购退货基本表 pur_purchase_return_base
 *
 * @author chaiwei
 * @date 2018-11-07
 */
@Data
@ToString
public class PurchaseReturnBase extends BaseEntity{
    private static final long serialVersionUID=1L;

    /** id */
    private Integer id;
    /** 供应商Id（关联往来单位表id） */
    private Integer supplierId;
    /** 经手人id(关联员工表) */
    private Integer handManId;
    /** 总金额 */
    private BigDecimal totalAmount;
    /** 退货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date dateOfReturn;
    /** 单据编号 */
    private String documentNumber;
    /** 制单人 */
    private String singlePerson;
    /** 制单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date dateOfProduction;
    /** 状态（-1：无效，1：有效） */
    private Integer status;

    /** 审批状态*/
    private Integer approvalStatus;

    /**
     * 提单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date submitTime;
    private String submitBy;

    /**
     * 订单生效时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date effectiveTime;
}
