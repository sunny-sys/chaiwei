package com.bangdao.domain.outWarehouse;

import com.bangdao.framework.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 退货详情表
 *
 * @author chaiwei
 * @date 2018-11-07
 */
@Data
public class ReturnDetailed extends BaseEntity{
    private static final long serialVersionUID=1L;

    /**  */
    private Integer id;
    /** 父标签id */
    private Integer parentId;
    /** 商品id(关联商品基本信息表) */
    private Integer commodityId;
    /** 退货仓库id(关联仓库表） */
    private Integer positionId;
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
    /** 状态（-1：无效，1：有效） */
    private Integer status;
}
