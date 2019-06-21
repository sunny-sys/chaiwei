package com.bangdao.domain.essentialInfo;

import com.bangdao.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
    
/**
 * 商品基本表 ess_commodity_base
 * 
 * @author xupj
 * @date 2018-10-10
 */
public class CommodityBase extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/** 商品id */
	private Integer id;
	/** 父标签id */
	private String parentId;
	/** 商品名称 */
	private String commodityName;
	/** 商品简称 */
	private String commodityAbbreviation;
	/** 商品编码 */
	private String commodityCode;
	/** 商品性质 */
	private String commodityNature;
	/** 商品类别 */
	private String commodityGoods;
	/** 条形码 */
	private String barCode;
	/** 基本单位 */
	private String basicUnit;
	/** 规格 */
	private String specifications;
	/** 成本预算方法 */
	private String costAccountingMethod;
	/** 商品产地 */
	private String commodityOrigin;
	/** 货位 */
	private String cargoPosition;
	/** 状态（-1：无效，1：有效） */
	private Integer status;
	/** 附件id */
	private String fileId;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}
	public void setParentId(String parentId){
		this.parentId = parentId;
	}

	public String getParentId(){
		return parentId;
	}
	public void setCommodityName(String commodityName){
		this.commodityName = commodityName;
	}

	public String getCommodityName(){
		return commodityName;
	}
	public void setCommodityAbbreviation(String commodityAbbreviation){
		this.commodityAbbreviation = commodityAbbreviation;
	}

	public String getCommodityAbbreviation(){
		return commodityAbbreviation;
	}
	public void setCommodityCode(String commodityCode){
		this.commodityCode = commodityCode;
	}

	public String getCommodityCode(){
		return commodityCode;
	}
	public void setCommodityNature(String commodityNature){
		this.commodityNature = commodityNature;
	}

	public String getCommodityNature(){
		return commodityNature;
	}
	public void setCommodityGoods(String commodityGoods){
		this.commodityGoods = commodityGoods;
	}

	public String getCommodityGoods(){
		return commodityGoods;
	}
	public void setBarCode(String barCode){
		this.barCode = barCode;
	}

	public String getBarCode(){
		return barCode;
	}
	public void setBasicUnit(String basicUnit){
		this.basicUnit = basicUnit;
	}

	public String getBasicUnit(){
		return basicUnit;
	}
	public void setSpecifications(String specifications){
		this.specifications = specifications;
	}

	public String getSpecifications(){
		return specifications;
	}
	public void setCostAccountingMethod(String costAccountingMethod){
		this.costAccountingMethod = costAccountingMethod;
	}

	public String getCostAccountingMethod(){
		return costAccountingMethod;
	}

	public void setCommodityOrigin(String commodityOrigin){
		this.commodityOrigin = commodityOrigin;
	}

	public String getCommodityOrigin(){
		return commodityOrigin;
	}
	public void setCargoPosition(String cargoPosition){
		this.cargoPosition = cargoPosition;
	}

	public String getCargoPosition(){
		return cargoPosition;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return status;
	}
	public void setFileId(String fileId){
		this.fileId = fileId;
	}

	public String getFileId(){
		return fileId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentId", getParentId())
            .append("commodityName", getCommodityName())
            .append("commodityAbbreviation", getCommodityAbbreviation())
            .append("commodityCode", getCommodityCode())
            .append("commodityNature", getCommodityNature())
            .append("commodityGoods", getCommodityGoods())
            .append("barCode", getBarCode())
            .append("basicUnit", getBasicUnit())
            .append("specifications", getSpecifications())
            .append("costAccountingMethod", getCostAccountingMethod())
            .append("remark", getRemark())
            .append("commodityOrigin", getCommodityOrigin())
            .append("cargoPosition", getCargoPosition())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .append("fileId", getFileId())
            .toString();
    }
}
