package com.bangdao.responseVo.purchasing;

import com.bangdao.framework.web.responseVo.ResponseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.math.BigDecimal;

/**
 * 采购入库详情表 pur_purchasing_warehousing_detailed 请求实体
 *
 * @author chaiwei
 * @date 2018-11-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PurchasingWarehousingDetailedResponse extends ResponseEntity {

    private static final long serialVersionUID = 1L;

    /**  */
    private Integer id;
    /** 父标签id */
    private Integer parentId;
    /** 商品id(关联商品基本信息表) */
    private Integer commodityId;
    /** 商品名称 */
    private String commodityName;
    /** 商品简称 */
    private String commodityAbbreviation;
    /** 商品编码 */
    private String commodityCode;
    /** 基本单位 */
    private String basicUnit;
    /** 规格 */
    private String specifications;
    /** 仓库Id */
    private Integer positionId;
    /** 入货仓库 */
    private String positionName;
    /** 数量 */
    private BigDecimal number;
    /** 单价 */
    private BigDecimal unitPrice;
    /** 折扣 */
    private String discount;
    /** 折后价 */
    private BigDecimal postDiscountPrice;
    /** 总额 */
    private BigDecimal total;
}
