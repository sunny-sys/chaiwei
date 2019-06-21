package com.bangdao.requestVo.outWarehouse;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 退货详情 请求实体
 *
 * @author chaiwei
 * @date 2018-11-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReturnDetailedReq extends RequestEntity {

    private static final long serialVersionUID = 1L;

    /**  */
    private Integer id;
    /** 父标签id */
    private Integer parentId;
    /** 商品id(关联商品基本信息表) */
    private Integer commodityId;
    /** 退货仓库id(关联仓库表） */
    private Integer positionId;
    /** 数量 */
    @NotNull(message = "数量不能为空")
    private BigDecimal number;
    /** 单价 */
    @NotNull(message = "单价不能为空")
    private BigDecimal unitPrice;
    /** 折扣 */
    @NotNull(message = "折扣不能为空")
    private String discount;
    /** 折后价 */
    private BigDecimal postDiscountPrice;
    /** 总额 */
    private BigDecimal total;
    /** 状态（-1：无效，1：有效） */
    private Integer status;
    /**
     * 状态标志位（-1表示删除）
     */
    private Integer isDelete;
}
