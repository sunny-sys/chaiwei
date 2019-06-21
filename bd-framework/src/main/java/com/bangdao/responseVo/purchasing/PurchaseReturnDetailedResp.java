package com.bangdao.responseVo.purchasing;

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
 * 采购退货详情表 pur_purchase_return_detailed 响应实体
 *
 * @author chaiwei
 * @date 2018-11-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PurchaseReturnDetailedResp extends ResponseEntity {

    private static final long serialVersionUID = 1L;

    /**  */
    private String id;
    /** 父标签id */
    private Integer parentId;
    /** 商品简称 */
    private String commodityAbbreviation;
    /** 入库日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date dateOfStorage;
    /** 商品简称 */
    private String commodityName;
    /** 商品编码 */
    private String commodityCode;
    /** 基本单位 */
    private String basicUnit;
    /** 规格 */
    private String specifications;
    /** 商品id(关联商品基本信息表) */
    private Integer commodityId;
    /** 退货仓库id(关联仓库信息表） */
    private Integer positionId;
    /** 仓库表id（关联仓库表） */
    private String warehouseId;
    /** 退货仓库 */
    private String positionName;
    /** 数量 */
    private BigDecimal number;
    /** 单价 */
    private BigDecimal unitPrice;
    /** 折后价 */
    private BigDecimal postDiscountPrice;
    /** 总额 */
    private BigDecimal total;
}
