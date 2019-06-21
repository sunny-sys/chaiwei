package com.bangdao.domain.essentialInfo;

import com.bangdao.framework.web.domain.BaseEntity;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
    
/**
 * 收付款账户表 ess_receive_payment_account
 * 
 * @author chenshao
 * @date 2018-10-15
 */
public class ReceivePaymentAccount extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 账户名称 */
	private String name;
	/** 账户类别id */
	private Integer accountTypeId;
	/** 账户类别 */
	private String accountType;
	/** 会计科目表id */
	private Integer subjectId;
	/** 开户名 */
	private String bankAccountName;
	/** 开户银行名称 */
	private String bankName;
	/** 银行账号 */
	private String bankAccount;
	/** 币种 */
	private String currency;
	/** 部门表id */
	private Integer deptId;
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
	public void setAccountTypeId(Integer accountTypeId){
		this.accountTypeId = accountTypeId;
	}

	public Integer getAccountTypeId(){
		return accountTypeId;
	}
	public void setAccountType(String accountType){
		this.accountType = accountType;
	}

	public String getAccountType(){
		return accountType;
	}
	public void setSubjectId(Integer subjectId){
		this.subjectId = subjectId;
	}

	public Integer getSubjectId(){
		return subjectId;
	}
	public void setBankAccountName(String bankAccountName){
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountName(){
		return bankAccountName;
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
	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getCurrency(){
		return currency;
	}
	public void setDeptId(Integer deptId){
		this.deptId = deptId;
	}

	public Integer getDeptId(){
		return deptId;
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
            .append("accountTypeId", getAccountTypeId())
            .append("accountType", getAccountType())
            .append("subjectId", getSubjectId())
            .append("bankAccountName", getBankAccountName())
            .append("bankName", getBankName())
            .append("bankAccount", getBankAccount())
            .append("currency", getCurrency())
            .append("deptId", getDeptId())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
