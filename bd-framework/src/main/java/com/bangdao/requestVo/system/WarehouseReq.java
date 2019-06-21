package com.bangdao.requestVo.system;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 仓库表 sys_warehouse 请求实体
 *
 * @author chaiwei
 * @date 2018-12-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WarehouseReq extends RequestEntity {

    private static final long serialVersionUID = 1L;
    /**  */
    @ApiModelProperty(value = "id")
    private String warehouseId;
    /** 供应商Id（关联往来单位表id） */
    @ApiModelProperty(value = "供应商Id")
    private Integer supplierId;
    private String supplierName;
    /** 经手人id(关联员工表) */
    @ApiModelProperty(value = "经手人id")
    private Integer handManId;
    /** 入库日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "入库日期")
    private Date dateOfStorage;
    /** 单据编号 */
    @ApiModelProperty(value = "单据编号")
    private String documentNumber;
    /** 制单人 */
    @ApiModelProperty(value = "制单人")
    private String singlePerson;
    /** 制单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "制单日期")
    private Date dateOfProduction;
    /** 订单来源明细id */
    @ApiModelProperty(value = "商品来源明细id")
    private Integer sourceDetailedId;
    /** 商品id(关联商品基本信息表) */
    @ApiModelProperty(value = "商品id")
    private Integer commodityId;
    /** 商品名称 */
    @ApiModelProperty(value = "商品名称")
    private String commodityName;
    /** 仓库id(关联仓库表） */
    @ApiModelProperty(value = "仓库id")
    private Integer positionId;
    private String positionName;
    /** 数量 */
    @ApiModelProperty(value = "数量")
    private BigDecimal number;
    /** 单价 */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;
    /** 折扣 */
    @ApiModelProperty(value = "折扣")
    private String discount;
    /** 折后价 */
    @ApiModelProperty(value = "折后价")
    private BigDecimal postDiscountPrice;
    /** 单据来源（标记商品来源哪里，目前之来源采购入库） */
    @ApiModelProperty(value = "单据来源")
    private String billSource;
}
