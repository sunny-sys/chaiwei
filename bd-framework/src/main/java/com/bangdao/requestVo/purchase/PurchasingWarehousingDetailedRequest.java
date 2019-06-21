package com.bangdao.requestVo.purchase;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 采购入库详情表 pur_purchasing_warehousing_detailed 请求实体
 *
 * @author chaiwei
 * @date 2018-10-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchasingWarehousingDetailedRequest extends RequestEntity {

    private static final long serialVersionUID = 1L;

    /**  */
    @ApiModelProperty(value = "id")
    private Integer id;
    /**
     * 父标签id
     */
    @ApiModelProperty(value = "父标签id",hidden = true)
    private Integer parentId;
    /**
     * 商品id
     */
    @NotNull(message = "商品不能为空")
    @ApiModelProperty(value = "商品id")
    private Integer commodityId;
    /**
     * 仓库id
     */
    @NotNull(message = "仓库不能为空")
    @ApiModelProperty(value = "仓库id")
    private Integer positionId;
    /**
     * 数量
     */
    @NotNull(message = "数量不能为空")
    @ApiModelProperty(value = "数量")
    private BigDecimal number;
    /**
     * 单价
     */
    @NotNull(message = "单价不能为空")
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;
    /**
     * 折扣
     */
    @NotNull(message = "折扣不能为空")
    @ApiModelProperty(value = "折扣")
    private String discount;
    /**
     * 折后价
     */
    @ApiModelProperty(value = "折后价")
    private BigDecimal postDiscountPrice;
    /**
     * 总额
     */
    @ApiModelProperty(value = "总额")
    private BigDecimal total;
    /**
     * 状态标志位（-1表示删除）
     */
    @ApiModelProperty(value = "状态标志位（-1表示删除）")
    private Integer isDelete;
}
