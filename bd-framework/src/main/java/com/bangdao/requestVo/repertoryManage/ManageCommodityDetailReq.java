package com.bangdao.requestVo.repertoryManage;

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
 * 调拨单 rep_manage_commodity_detail 请求实体
 *
 * @author chenshao
 * @date 2018-11-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManageCommodityDetailReq extends RequestEntity {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    private Integer id;
    /** 库存管理基础表id */
    @ApiModelProperty(value = "库存管理基础表id")
    private Integer parentId;
    /** 仓库表id */
    @ApiModelProperty(value = "仓库表id")
    private String warehouseId;
    /** 数量 */
    @NotNull(message = "数量不能为空")
    @ApiModelProperty(value = "数量")
    private BigDecimal number;
    /** 单价 */
    @NotNull(message = "单价不能为空")
    @ApiModelProperty(value = "单价")
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
