package com.bangdao.domain.essentialInfo;

import com.bangdao.framework.web.domain.BaseEntity;

/**
 * 行政区域县区表 sys_areas
 * 
 * @author xupj
 * @date 2018-09-16
 */
public class Areas extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private String areaid;
	/**  */
	private String area;
	/**  */
	private String parentid;
	/**  */
	private Integer sort;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}
	public void setAreaid(String areaid){
		this.areaid = areaid;
	}

	public String getAreaid(){
		return areaid;
	}
	public void setArea(String area){
		this.area = area;
	}

	public String getArea(){
		return area;
	}
	public void setSort(Integer sort){
		this.sort = sort;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public Integer getSort(){
		return sort;
	}

	@Override
	public String toString() {
		return "Areas{" +
				"id=" + id +
				", areaid='" + areaid + '\'' +
				", area='" + area + '\'' +
				", parentid='" + parentid + '\'' +
				", sort=" + sort +
				'}';
	}
}
