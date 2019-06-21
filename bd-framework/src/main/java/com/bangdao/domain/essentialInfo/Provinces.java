package com.bangdao.domain.essentialInfo;

import com.bangdao.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 省份表 sys_provinces
 * 
 * @author xupj
 * @date 2018-09-16
 */
public class Provinces extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private String provinceid;
	/**  */
	private String province;
	/**  */
	private Integer sort;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}
	public void setProvinceid(String provinceid){
		this.provinceid = provinceid;
	}

	public String getProvinceid(){
		return provinceid;
	}
	public void setProvince(String province){
		this.province = province;
	}

	public String getProvince(){
		return province;
	}
	public void setSort(Integer sort){
		this.sort = sort;
	}

	public Integer getSort(){
		return sort;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("provinceid", getProvinceid())
            .append("province", getProvince())
            .append("sort", getSort())
            .toString();
    }
}
