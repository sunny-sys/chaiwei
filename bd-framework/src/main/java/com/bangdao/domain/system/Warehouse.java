package com.bangdao.domain.system;

import com.bangdao.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 仓库表 sys_warehouse
 *
 * @author chaiwei
 * @date 2018-12-18
 */
@Data
public class Warehouse extends BaseEntity{
    private static final long serialVersionUID=1L;
    /**  */
    private String warehouseId;
    /** 供应商Id（关联往来单位表id） */
    private Integer supplierId;
    /** 经手人id(关联员工表) */
    private Integer handManId;
    /** 入库日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date dateOfStorage;
    /** 单据编号 */
    private String documentNumber;
    /** 制单人 */
    private String singlePerson;
    /** 制单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date dateOfProduction;
    /** 订单来源明细id */
    private Integer sourceDetailedId;
    /** 商品id(关联商品基本信息表) */
    private Integer commodityId;
    /** 仓库id(关联仓库表） */
    private Integer positionId;
    /** 数量 */
    private BigDecimal number;
    /** 单价 */
    private BigDecimal unitPrice;
    /** 折扣 */
    private String discount;
    /** 折后价 */
    private BigDecimal postDiscountPrice;
    /** 状态（-1：无效，1：有效） */
    private Integer status;
    /** 单据来源（标记商品来源哪里，目前之来源采购入库） */
    private String billSource;
}
