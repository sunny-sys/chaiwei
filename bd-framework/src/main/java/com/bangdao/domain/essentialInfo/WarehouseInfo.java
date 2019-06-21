package com.bangdao.domain.essentialInfo;

import com.bangdao.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
    
/**
 * 仓库表 ess_warehouse_info
 * 
 * @author chenshao
 * @date 2018-10-16
 */
public class WarehouseInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 仓库名称 */
	private String name;
	/** 仓库编码 */
	private String code;
	/** 仓库类别id */
	private Integer categoryId;
	/** 备注 */
//	private Integer remark;
	/** 数据状态（-1:无效;1:有效） */
	private Integer status;
//	/** 创建时间 */
//	private Date createTime;
//	/** 修改时间 */
//	private Date updateTime;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}
	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}
	public void setCategoryId(Integer categoryId){
		this.categoryId = categoryId;
	}

	public Integer getCategoryId(){
		return categoryId;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return status;
	}
	
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("code", getCode())
            .append("categoryId", getCategoryId())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
