package com.bangdao.domain.repertoryManage;

import com.bangdao.framework.web.domain.BaseEntity;

import java.math.BigDecimal;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
                                                                
/**
 * 调拨单明细 rep_manage_commodity_detail
 *
 * @author chenshao
 * @date 2018-11-08
 */
@Data
public class ManageCommodityDetail extends BaseEntity{
    private static final long serialVersionUID=1L;
    /** 主键id */
    private Integer id;
    /** 库存管理基础表id */
    private Integer parentId;
    /** 仓库表id */
    private String warehouseId;
    /** 数量 */
    private BigDecimal number;
    /** 单价 */
    private BigDecimal postDiscountPrice;
    /** 总额 */
    private BigDecimal total;
    /** 状态（-1：无效，1：有效） */
    private Integer status;
}
