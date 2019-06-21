package com.bangdao.responseVo.repertoryManage;

import com.bangdao.framework.web.responseVo.ResponseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CheckListDetailResp extends ResponseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Integer id;
    /** 基础表id */
    private Integer parentId;
    /** 商品表id */
    private Integer commodityId;
    /** 数量 */
    private BigDecimal number;
    /** 单价 */
    private BigDecimal unitPrice;
    /** 盘点数量 */
    private BigDecimal checkNumber;
    /** 盘点单价 */
    private BigDecimal checkUnitPrice;
    /** 盈亏数量 */
    private BigDecimal profitLossNumber;
    /** 盈亏金额 */
    private BigDecimal profitLossTotal;
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
}
