package com.bangdao.domain.outWarehouse;

import com.bangdao.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 收款单基本表
 *
 * @author chaiwei
 * @date 2018-11-13
 */
public class ReceiptBase extends BaseEntity{
    private static final long serialVersionUID=1L;

    /** id */
    private Integer id;
    /** 供应商Id（关联往来单位表id） */
    private Integer supplierId;
    /** 经手人id(关联员工表) */
    private Integer handManId;
    /** 收款类型 */
    private String receivablesType;
    /** 本次核销 */
    private BigDecimal writeOff;
    /** 整单折扣 */
    private BigDecimal billDiscount;
    /** 实收金额 */
    private BigDecimal amountCollected;
    /** 单据日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date billDate;
    /** 单据编号 */
    private String documentNumber;
    /** 制单人 */
    private String singlePerson;
    /** 制单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date dateOfProduction;
    /** 状态（-1：无效，1：有效） */
    private Integer status;

    public void setId(Integer id){
        this.id = id;
    }
    public Integer getId(){
        return id;
    }
    public void setSupplierId(Integer supplierId){
        this.supplierId = supplierId;
    }
    public Integer getSupplierId(){
        return supplierId;
    }
    public void setHandManId(Integer handManId){
        this.handManId = handManId;
    }
    public Integer getHandManId(){
        return handManId;
    }
    public void setReceivablesType(String receivablesType){
        this.receivablesType = receivablesType;
    }
    public String getReceivablesType(){
        return receivablesType;
    }
    public void setWriteOff(BigDecimal writeOff){
        this.writeOff = writeOff;
    }
    public BigDecimal getWriteOff(){
        return writeOff;
    }
    public void setBillDiscount(BigDecimal billDiscount){
        this.billDiscount = billDiscount;
    }
    public BigDecimal getBillDiscount(){
        return billDiscount;
    }
    public void setAmountCollected(BigDecimal amountCollected){
        this.amountCollected = amountCollected;
    }
    public BigDecimal getAmountCollected(){
        return amountCollected;
    }
    public void setBillDate(Date billDate){
        this.billDate = billDate;
    }
    public Date getBillDate(){
        return billDate;
    }
    public void setDocumentNumber(String documentNumber){
        this.documentNumber = documentNumber;
    }
    public String getDocumentNumber(){
        return documentNumber;
    }
    public void setSinglePerson(String singlePerson){
        this.singlePerson = singlePerson;
    }
    public String getSinglePerson(){
        return singlePerson;
    }
    public void setDateOfProduction(Date dateOfProduction){
        this.dateOfProduction = dateOfProduction;
    }
    public Date getDateOfProduction(){
        return dateOfProduction;
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
                .append("supplierId",getSupplierId())
                .append("handManId",getHandManId())
                .append("receivablesType",getReceivablesType())
                .append("writeOff",getWriteOff())
                .append("billDiscount",getBillDiscount())
                .append("amountCollected",getAmountCollected())
                .append("billDate",getBillDate())
                .append("documentNumber",getDocumentNumber())
                .append("singlePerson",getSinglePerson())
                .append("dateOfProduction",getDateOfProduction())
                .append("remark",getRemark())
                .append("createBy",getCreateBy())
                .append("createTime",getCreateTime())
                .append("updateBy",getUpdateBy())
                .append("updateTime",getUpdateTime())
                .append("status",getStatus())
            .toString();
    }
}
