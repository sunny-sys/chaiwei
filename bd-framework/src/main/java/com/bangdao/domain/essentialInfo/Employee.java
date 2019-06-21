package com.bangdao.domain.essentialInfo;

import com.bangdao.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 员工表 ess_employee
 * 
 * @author chaiwei
 * @date 2018-10-15
 */
public class Employee extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/** 员工id */
	private Integer id;
	/** 父标签id */
	private String parentId;
	/** 身份证号 */
	private String idCard;
	/** 姓名 */
	private String name;
	/** 人员编号 */
	private String personnelNumber;
	/** 性别 */
	private String sex;
	/** 出生年月 */
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date dateOfBirth;
	/** 籍贯 */
	private String nativePlace;
	/** 所属部门 */
	private String subordinateDepartmentId;
	/** 职位岗位 */
	private String post;
	/** 手机号码 */
	private String phoneNumber;
	/** 家庭电话 */
	private String homePhone;
	/** 其他电话 */
	private String otherPhone;
	/** 邮政编码 */
	private String postalCode;
	/** 现住址 */
	private String presentAddress;
	/** 家庭住址 */
	private String homeAddress;
	/** 政治面貌 */
	private String politicalOutlook;
	/** 文化程度 */
	private String degreeOfEducation;
	/** 入职时间 */
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date timeOfEntry;
	/** 婚姻状况 */
	private String maritalStatus;
	/** 毕业院校 */
	private String graduateSchool;
	/** 人员状态 */
	private String employeeStatus;
	/** 状态（-1：无效，1：有效） */
	private Integer status;

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
	public void setIdCard(String idCard){
		this.idCard = idCard;
	}

	public String getIdCard(){
		return idCard;
	}
	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
	public void setPersonnelNumber(String personnelNumber){
		this.personnelNumber = personnelNumber;
	}

	public String getPersonnelNumber(){
		return personnelNumber;
	}
	public void setSex(String sex){
		this.sex = sex;
	}

	public String getSex(){
		return sex;
	}
	public void setDateOfBirth(Date dateOfBirth){
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfBirth(){
		return dateOfBirth;
	}
	public void setNativePlace(String nativePlace){
		this.nativePlace = nativePlace;
	}

	public String getNativePlace(){
		return nativePlace;
	}
	public void setSubordinateDepartmentId(String subordinateDepartmentId){
		this.subordinateDepartmentId = subordinateDepartmentId;
	}

	public String getSubordinateDepartmentId(){
		return subordinateDepartmentId;
	}
	public void setPost(String post){
		this.post = post;
	}

	public String getPost(){
		return post;
	}
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}
	public void setHomePhone(String homePhone){
		this.homePhone = homePhone;
	}

	public String getHomePhone(){
		return homePhone;
	}
	public void setOtherPhone(String otherPhone){
		this.otherPhone = otherPhone;
	}

	public String getOtherPhone(){
		return otherPhone;
	}
	public void setPostalCode(String postalCode){
		this.postalCode = postalCode;
	}

	public String getPostalCode(){
		return postalCode;
	}
	public void setPresentAddress(String presentAddress){
		this.presentAddress = presentAddress;
	}

	public String getPresentAddress(){
		return presentAddress;
	}
	public void setHomeAddress(String homeAddress){
		this.homeAddress = homeAddress;
	}

	public String getHomeAddress(){
		return homeAddress;
	}
	public void setPoliticalOutlook(String politicalOutlook){
		this.politicalOutlook = politicalOutlook;
	}

	public String getPoliticalOutlook(){
		return politicalOutlook;
	}
	public void setDegreeOfEducation(String degreeOfEducation){
		this.degreeOfEducation = degreeOfEducation;
	}

	public String getDegreeOfEducation(){
		return degreeOfEducation;
	}
	public void setTimeOfEntry(Date timeOfEntry){
		this.timeOfEntry = timeOfEntry;
	}

	public Date getTimeOfEntry(){
		return timeOfEntry;
	}
	public void setMaritalStatus(String maritalStatus){
		this.maritalStatus = maritalStatus;
	}

	public String getMaritalStatus(){
		return maritalStatus;
	}
	public void setGraduateSchool(String graduateSchool){
		this.graduateSchool = graduateSchool;
	}

	public String getGraduateSchool(){
		return graduateSchool;
	}
	public void setEmployeeStatus(String employeeStatus){
		this.employeeStatus = employeeStatus;
	}

	public String getEmployeeStatus(){
		return employeeStatus;
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
            .append("parentId", getParentId())
            .append("idCard", getIdCard())
            .append("name", getName())
            .append("personnelNumber", getPersonnelNumber())
            .append("sex", getSex())
            .append("dateOfBirth", getDateOfBirth())
            .append("nativePlace", getNativePlace())
            .append("subordinateDepartmentId", getSubordinateDepartmentId())
            .append("post", getPost())
            .append("phoneNumber", getPhoneNumber())
            .append("homePhone", getHomePhone())
            .append("otherPhone", getOtherPhone())
            .append("postalCode", getPostalCode())
            .append("presentAddress", getPresentAddress())
            .append("homeAddress", getHomeAddress())
            .append("politicalOutlook", getPoliticalOutlook())
            .append("degreeOfEducation", getDegreeOfEducation())
            .append("timeOfEntry", getTimeOfEntry())
            .append("maritalStatus", getMaritalStatus())
            .append("graduateSchool", getGraduateSchool())
            .append("employeeStatus", getEmployeeStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .toString();
    }
}
