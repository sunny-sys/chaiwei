package com.bangdao.domain.repertoryManage;

import com.bangdao.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;


public class CheckListDetail extends BaseEntity{
    private static final long serialVersionUID=1L;
    /** 主键id */
    private Integer id;
    /**  */
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
    /** 状态（-1：无效，1：有效） */
    private Integer status;

    public void setId(Integer id){
        this.id = id;
    }
    public Integer getId(){
        return id;
    }
    public void setParentId(Integer parentId){
        this.parentId = parentId;
    }
    public Integer getParentId(){
        return parentId;
    }
    public void setCommodityId(Integer commodityId){
        this.commodityId = commodityId;
    }
    public Integer getCommodityId(){
        return commodityId;
    }
    public void setNumber(BigDecimal number){
        this.number = number;
    }
    public BigDecimal getNumber(){
        return number;
    }
    public void setUnitPrice(BigDecimal unitPrice){
        this.unitPrice = unitPrice;
    }
    public BigDecimal getUnitPrice(){
        return unitPrice;
    }
    public void setCheckNumber(BigDecimal checkNumber){
        this.checkNumber = checkNumber;
    }
    public BigDecimal getCheckNumber(){
        return checkNumber;
    }
    public void setCheckUnitPrice(BigDecimal checkUnitPrice){
        this.checkUnitPrice = checkUnitPrice;
    }
    public BigDecimal getCheckUnitPrice(){
        return checkUnitPrice;
    }
    public void setProfitLossNumber(BigDecimal profitLossNumber){
        this.profitLossNumber = profitLossNumber;
    }
    public BigDecimal getProfitLossNumber(){
        return profitLossNumber;
    }
    public void setProfitLossTotal(BigDecimal profitLossTotal){
        this.profitLossTotal = profitLossTotal;
    }
    public BigDecimal getProfitLossTotal(){
        return profitLossTotal;
    }
    public void setStatus(Integer status){
        this.status = status;
    }
    public Integer getStatus(){
        return status;
    }

    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id",getId())
                .append("parentId",getParentId())
                .append("commodityId",getCommodityId())
                .append("number",getNumber())
                .append("unitPrice",getUnitPrice())
                .append("checkNumber",getCheckNumber())
                .append("checkUnitPrice",getCheckUnitPrice())
                .append("profitLossNumber",getProfitLossNumber())
                .append("profitLossTotal",getProfitLossTotal())
                .append("remark",getRemark())
                .append("createBy",getCreateBy())
                .append("createTime",getCreateTime())
                .append("updateBy",getUpdateBy())
                .append("updateTime",getUpdateTime())
                .append("status",getStatus())
            .toString();
    }
}
