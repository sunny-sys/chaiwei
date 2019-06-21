package com.bangdao.domain.essentialInfo;

import com.bangdao.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 类别表 sys_category
 * 
 * @author chaiwei
 * @date 2018-09-30
 */
public class Category extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/** 商品类别id */
	private Integer id;
	/** 父标签id */
	private String parentId;
	/** 商品类别名称 */
	private String commodityCategoryName;
	/** 商品编码 */
	private String commodityCategoryCode;
	/** 拼音码 */
	private String pinyinCode;
	/** 排序 */
	private String sort;
	/** 状态（-1：无效，1：有效） */
	private Integer status;
	/** 类别分类（商品类别，单位类别等） */
	private String category;

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
	public void setCommodityCategoryName(String commodityCategoryName){
		this.commodityCategoryName = commodityCategoryName;
	}

	public String getCommodityCategoryName(){
		return commodityCategoryName;
	}
	public void setCommodityCategoryCode(String commodityCategoryCode){
		this.commodityCategoryCode = commodityCategoryCode;
	}

	public String getCommodityCategoryCode(){
		return commodityCategoryCode;
	}
	public void setPinyinCode(String pinyinCode){
		this.pinyinCode = pinyinCode;
	}

	public String getPinyinCode(){
		return pinyinCode;
	}
	public void setSort(String sort){
		this.sort = sort;
	}

	public String getSort(){
		return sort;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return status;
	}
	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentId", getParentId())
            .append("commodityCategoryName", getCommodityCategoryName())
            .append("commodityCategoryCode", getCommodityCategoryCode())
            .append("pinyinCode", getPinyinCode())
            .append("sort", getSort())
            .append("status", getStatus())
            .append("category", getCategory())
            .toString();
    }
}
