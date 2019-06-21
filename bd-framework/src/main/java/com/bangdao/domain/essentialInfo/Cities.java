package com.bangdao.domain.essentialInfo;

import com.bangdao.framework.web.domain.BaseEntity;

/**
 * 行政区域地州市表 sys_cities
 * 
 * @author xupj
 * @date 2018-09-16
 */
public class Cities extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private String cityid;
	/**  */
	private String city;
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
	public void setCityid(String cityid){
		this.cityid = cityid;
	}

	public String getCityid(){
		return cityid;
	}
	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
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
		return "Cities{" +
				"id=" + id +
				", cityid='" + cityid + '\'' +
				", city='" + city + '\'' +
				", parentid='" + parentid + '\'' +
				", sort=" + sort +
				'}';
	}
}
