package com.bangdao.domain.essentialInfo;

import com.bangdao.framework.web.domain.BaseEntity;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
    
/**
 * 物流表 ess_logistics_info
 * 
 * @author xupj
 * @date 2018-10-10
 */
public class LogisticsInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 公司名称 */
	private String name;
	/** 公司简称 */
	private String shortName;
	/** 公司地址 */
	private String address;
	/** 联系人 */
	private String linkman;
	/** 联系电话 */
	private String contactNumber;
	/** 手机号 */
	private String phoneNumber;
	/** 传真 */
	private String facsimile;
	/** 电子邮箱 */
	private String email;
	/** 邮政编码 */
	private String postcode;
	/** 开户银行名称 */
	private String bankName;
	/** 银行账号 */
	private String bankAccount;
	/** 公司网址 */
	private String website;
	/** 数据状态（-1:无效;1:有效） */
	private Integer status;

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
	public void setShortName(String shortName){
		this.shortName = shortName;
	}

	public String getShortName(){
		return shortName;
	}
	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}
	public void setLinkman(String linkman){
		this.linkman = linkman;
	}

	public String getLinkman(){
		return linkman;
	}
	public void setContactNumber(String contactNumber){
		this.contactNumber = contactNumber;
	}

	public String getContactNumber(){
		return contactNumber;
	}
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}
	public void setFacsimile(String facsimile){
		this.facsimile = facsimile;
	}

	public String getFacsimile(){
		return facsimile;
	}
	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
	public void setPostcode(String postcode){
		this.postcode = postcode;
	}

	public String getPostcode(){
		return postcode;
	}
	public void setBankName(String bankName){
		this.bankName = bankName;
	}

	public String getBankName(){
		return bankName;
	}
	public void setBankAccount(String bankAccount){
		this.bankAccount = bankAccount;
	}

	public String getBankAccount(){
		return bankAccount;
	}
	public void setWebsite(String website){
		this.website = website;
	}

	public String getWebsite(){
		return website;
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
            .append("shortName", getShortName())
            .append("address", getAddress())
            .append("linkman", getLinkman())
            .append("contactNumber", getContactNumber())
            .append("phoneNumber", getPhoneNumber())
            .append("facsimile", getFacsimile())
            .append("email", getEmail())
            .append("postcode", getPostcode())
            .append("bankName", getBankName())
            .append("bankAccount", getBankAccount())
            .append("website", getWebsite())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
