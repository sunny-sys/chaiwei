package com.bangdao.domain.purchase;

import com.bangdao.framework.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 采购退货详情表 pur_purchase_return_detailed
 *
 * @author chaiwei
 * @date 2018-11-07
 */
@Data
public class PurchaseReturnDetailed extends BaseEntity{
    private static final long serialVersionUID=1L;

    /**  */
    private String id;
    /** 父标签id */
    private Integer parentId;
    /** 仓库表id */
    private String warehouseId;
    /** 数量 */
    private BigDecimal number;
    /** 单价（折后价） */
    private BigDecimal postDiscountPrice;
    /** 总额 */
    private BigDecimal total;
    /** 状态（-1：无效，1：有效） */
    private Integer status;
}
