package com.bangdao.domain.essentialInfo;

import com.bangdao.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
    
/**
 * 往来单位基本表 ess_intercourse_unit
 * 
 * @author chaiwei
 * @date 2018-10-12
 */
public class IntercourseUnit extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/** 单位id */
	private Integer id;
	/** 单位名称 */
	private String unitName;
	/** 单位性质 */
	private String unitNature;
	/** 单位类别 */
	private String unitCategory;
	/** 所属部门 */
	private String department;
	/** 省 */
	private String province;
	/** 市 */
	private String city;
	/** 区县 */
	private String county;
	/** 地址 */
	private String address;
	/** 单位编码 */
	private String unitCode;
	/** 所属业务员 */
	private String salesman;
	/** 联系人 */
	private String contacts;
	/** 联系电话 */
	private String contactNumber;
	/** 手机 */
	private String phone;
	/** 传真 */
	private String fax;
	/** 所属地区 */
	private String affiliatedArea;
	/** 邮政编码 */
	private String postalCode;
	/** 电子邮箱 */
	private String mailBox;
	/** 联系人id */
	private String contactsId;
	/** 附件信息id */
	private String enclosureId;
	/** 状态（-1：无效，1：有效） */
	private Integer status;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}
	public void setUnitName(String unitName){
		this.unitName = unitName;
	}

	public String getUnitName(){
		return unitName;
	}
	public void setUnitNature(String unitNature){
		this.unitNature = unitNature;
	}

	public String getUnitNature(){
		return unitNature;
	}
	public void setUnitCategory(String unitCategory){
		this.unitCategory = unitCategory;
	}

	public String getUnitCategory(){
		return unitCategory;
	}
	public void setDepartment(String department){
		this.department = department;
	}

	public String getDepartment(){
		return department;
	}
	public void setProvince(String province){
		this.province = province;
	}

	public String getProvince(){
		return province;
	}
	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}
	public void setCounty(String county){
		this.county = county;
	}

	public String getCounty(){
		return county;
	}
	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}
	public void setUnitCode(String unitCode){
		this.unitCode = unitCode;
	}

	public String getUnitCode(){
		return unitCode;
	}
	public void setSalesman(String salesman){
		this.salesman = salesman;
	}

	public String getSalesman(){
		return salesman;
	}
	public void setContacts(String contacts){
		this.contacts = contacts;
	}

	public String getContacts(){
		return contacts;
	}
	public void setContactNumber(String contactNumber){
		this.contactNumber = contactNumber;
	}

	public String getContactNumber(){
		return contactNumber;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}
	public void setFax(String fax){
		this.fax = fax;
	}

	public String getFax(){
		return fax;
	}
	public void setAffiliatedArea(String affiliatedArea){
		this.affiliatedArea = affiliatedArea;
	}

	public String getAffiliatedArea(){
		return affiliatedArea;
	}
	public void setPostalCode(String postalCode){
		this.postalCode = postalCode;
	}

	public String getPostalCode(){
		return postalCode;
	}
	public void setMailBox(String mailBox){
		this.mailBox = mailBox;
	}

	public String getMailBox(){
		return mailBox;
	}
	public void setContactsId(String contactsId){
		this.contactsId = contactsId;
	}

	public String getContactsId(){
		return contactsId;
	}
	public void setEnclosureId(String enclosureId){
		this.enclosureId = enclosureId;
	}

	public String getEnclosureId(){
		return enclosureId;
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
            .append("unitName", getUnitName())
            .append("unitNature", getUnitNature())
            .append("unitCategory", getUnitCategory())
            .append("department", getDepartment())
            .append("province", getProvince())
            .append("city", getCity())
            .append("county", getCounty())
            .append("address", getAddress())
            .append("unitCode", getUnitCode())
            .append("salesman", getSalesman())
            .append("contacts", getContacts())
            .append("contactNumber", getContactNumber())
            .append("phone", getPhone())
            .append("fax", getFax())
            .append("affiliatedArea", getAffiliatedArea())
            .append("postalCode", getPostalCode())
            .append("mailBox", getMailBox())
            .append("contactsId", getContactsId())
            .append("enclosureId", getEnclosureId())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .toString();
    }
}
