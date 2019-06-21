package com.bangdao.domain.purchase;

import com.bangdao.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 采购入库详情表 pur_purchasing_warehousing_detailed
 *
 * @author chaiwei
 * @date 2018-10-25
 */
public class PurchasingWarehousingDetailed extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**  */
    private Integer id;
    /**
     * 父标签id
     */
    private Integer parentId;
    /**
     * 商品id
     */
    private Integer commodityId;
    /**
     * 仓库Id
     */
    private Integer positionId;
    /**
     * 数量
     */
    private BigDecimal number;
    /**
     * 单价
     */
    private BigDecimal unitPrice;
    /**
     * 折扣
     */
    private String discount;
    /**
     * 折后价
     */
    private BigDecimal postDiscountPrice;
    /**
     * 总额
     */
    private BigDecimal total;

    /** 状态（-1：无效，1：有效） */
    private Integer status;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setPostDiscountPrice(BigDecimal postDiscountPrice) {
        this.postDiscountPrice = postDiscountPrice;
    }

    public BigDecimal getPostDiscountPrice() {
        return postDiscountPrice;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("parentId", getParentId())
                .append("commodityId", getCommodityId())
                .append("positionId", getPositionId())
                .append("number", getNumber())
                .append("unitPrice", getUnitPrice())
                .append("discount", getDiscount())
                .append("postDiscountPrice", getPostDiscountPrice())
                .append("total", getTotal())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("status", getStatus())
                .toString();
    }
}
