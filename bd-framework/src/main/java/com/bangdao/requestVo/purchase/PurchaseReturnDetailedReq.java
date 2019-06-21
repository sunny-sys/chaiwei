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
 * 采购退货详情表 pur_purchase_return_detailed 请求实体
 *
 * @author chaiwei
 * @date 2018-11-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseReturnDetailedReq extends RequestEntity {

    private static final long serialVersionUID = 1L;

    /**  */
    @ApiModelProperty(value = "id")
    private String id;
    /** 父标签id */
    @ApiModelProperty(value = "父标签id")
    private Integer parentId;
    /** 仓库id(关联仓库表） */
    @ApiModelProperty(value = "仓库id(关联仓库表）")
    private String warehouseId;
    /** 数量 */
    @NotNull(message = "数量不能为空")
    @ApiModelProperty(value = "数量")
    private BigDecimal number;
    /** 单价（折后价） */
    @ApiModelProperty(value = "（折后价）")
    private BigDecimal postDiscountPrice;
    /** 总额 */
    @ApiModelProperty(value = "总额")
    private BigDecimal total;
    /**
     * 状态标志位（-1表示删除）
     */
    @ApiModelProperty(value = "状态标志位（-1表示删除）")
    private Integer isDelete;
}
