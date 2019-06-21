package com.bangdao.domain.essentialInfo;

import com.bangdao.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
    
/**
 * 附件表 ess_enclosure
 * 
 * @author xml
 * @date 2018-09-18
 */
public class Enclosure extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/** 附件id */
	private Integer id;
	/** 法定代表人 */
	private String legalRepresentative;
	/** 注册资金（万元） */
	private String registeredCapital;
	/** 开户银行 */
	private String accountOpeningBank;
	/** 账号 */
	private String accountNumber;
	/** 经济类型 */
	private String economicType;
	/** 税号 */
	private String dutyParagraph;
	/** 客户来源 */
	private String customerSource;
	/** 所属行业 */
	private String industry;
	/** 发货方式 */
	private String delivery;
	/** 经营类型 */
	private String businessType;
	/** 客户级别 */
	private String customerLevel;
	/** 客户状态 */
	private String customerStatus;
	/** 客户概况 */
	private String customerProfile;
	/** 状态（-1：无效，1：有效） */
	private Integer status;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}
	public void setLegalRepresentative(String legalRepresentative){
		this.legalRepresentative = legalRepresentative;
	}

	public String getLegalRepresentative(){
		return legalRepresentative;
	}
	public void setRegisteredCapital(String registeredCapital){
		this.registeredCapital = registeredCapital;
	}

	public String getRegisteredCapital(){
		return registeredCapital;
	}
	public void setAccountOpeningBank(String accountOpeningBank){
		this.accountOpeningBank = accountOpeningBank;
	}

	public String getAccountOpeningBank(){
		return accountOpeningBank;
	}
	public void setAccountNumber(String accountNumber){
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber(){
		return accountNumber;
	}
	public void setEconomicType(String economicType){
		this.economicType = economicType;
	}

	public String getEconomicType(){
		return economicType;
	}
	public void setDutyParagraph(String dutyParagraph){
		this.dutyParagraph = dutyParagraph;
	}

	public String getDutyParagraph(){
		return dutyParagraph;
	}
	public void setCustomerSource(String customerSource){
		this.customerSource = customerSource;
	}

	public String getCustomerSource(){
		return customerSource;
	}
	public void setIndustry(String industry){
		this.industry = industry;
	}

	public String getIndustry(){
		return industry;
	}
	public void setDelivery(String delivery){
		this.delivery = delivery;
	}

	public String getDelivery(){
		return delivery;
	}
	public void setBusinessType(String businessType){
		this.businessType = businessType;
	}

	public String getBusinessType(){
		return businessType;
	}
	public void setCustomerLevel(String customerLevel){
		this.customerLevel = customerLevel;
	}

	public String getCustomerLevel(){
		return customerLevel;
	}
	public void setCustomerStatus(String customerStatus){
		this.customerStatus = customerStatus;
	}

	public String getCustomerStatus(){
		return customerStatus;
	}
	public void setCustomerProfile(String customerProfile){
		this.customerProfile = customerProfile;
	}

	public String getCustomerProfile(){
		return customerProfile;
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
            .append("legalRepresentative", getLegalRepresentative())
            .append("registeredCapital", getRegisteredCapital())
            .append("accountOpeningBank", getAccountOpeningBank())
            .append("accountNumber", getAccountNumber())
            .append("economicType", getEconomicType())
            .append("dutyParagraph", getDutyParagraph())
            .append("customerSource", getCustomerSource())
            .append("industry", getIndustry())
            .append("delivery", getDelivery())
            .append("businessType", getBusinessType())
            .append("customerLevel", getCustomerLevel())
            .append("customerStatus", getCustomerStatus())
            .append("customerProfile", getCustomerProfile())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .toString();
    }
}
