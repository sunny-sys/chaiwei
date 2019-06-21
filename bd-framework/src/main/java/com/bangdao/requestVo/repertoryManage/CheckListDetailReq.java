package com.bangdao.requestVo.repertoryManage;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckListDetailReq extends RequestEntity {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    private Integer id;
    @ApiModelProperty(value = "基础表id")
    private Integer parentId;
    /** 商品表id */
    @ApiModelProperty(value = "商品表id")
    private Integer commodityId;
    /** 数量 */
    @ApiModelProperty(value = "数量")
    private BigDecimal number;
    /** 单价 */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;
    /** 盘点数量 */
    @ApiModelProperty(value = "盘点数量")
    private BigDecimal checkNumber;
    /** 盘点单价 */
    @ApiModelProperty(value = "盘点单价")
    private BigDecimal checkUnitPrice;
    /** 盈亏数量 */
    @ApiModelProperty(value = "盈亏数量")
    private BigDecimal profitLossNumber;
    /** 盈亏金额 */
    @ApiModelProperty(value = "盈亏金额")
    private BigDecimal profitLossTotal;
    /**
     * 状态标志位（-1表示删除）
     */
    @ApiModelProperty(value = "状态标志位（-1表示删除）")
    private Integer isDelete;
}
