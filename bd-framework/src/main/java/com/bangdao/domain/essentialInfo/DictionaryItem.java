package com.bangdao.domain.essentialInfo;


import com.bangdao.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 字典明细表 sys_dictionary_item
 * 
 * @author xupj
 * @date 2018-09-14
 */
public class DictionaryItem extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private Integer id;
	/** 字典名称 */
	private String itemName;
	/** 字典值 */
	private Integer itemValue;
	/** 排序值 */
	private Integer sort;
	/** 字典类型 */
	private String dicType;
	/** 是否启用：默认是1，表示启用，2表示禁用 */
	private Integer status;

	public DictionaryItem setId(Integer id){
		this.id = id;
		return this;
	}

	public Integer getId(){
		return id;
	}
	public DictionaryItem setItemName(String itemName){
		this.itemName = itemName;
		return this;
	}

	public String getItemName(){
		return itemName;
	}
	public DictionaryItem setItemValue(Integer itemValue){
		this.itemValue = itemValue;
		return this;
	}

	public Integer getItemValue(){
		return itemValue;
	}
	public DictionaryItem setSort(Integer sort){
		this.sort = sort;
		return this;
	}

	public Integer getSort(){
		return sort;
	}
	public DictionaryItem setDicType(String dicType){
		this.dicType = dicType;
		return this;
	}

	public String getDicType(){
		return dicType;
	}
	public DictionaryItem setStatus(Integer status){
		this.status = status;
		return this;
	}

	public Integer getStatus(){
		return status;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("itemName", getItemName())
            .append("itemValue", getItemValue())
            .append("sort", getSort())
            .append("remark", getRemark())
            .append("dicType", getDicType())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .toString();
    }
}
