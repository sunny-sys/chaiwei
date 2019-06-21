package com.bangdao.domain.essentialInfo;

import com.bangdao.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
    
/**
 * 联系人表 ess_contacts
 * 
 * @author xml
 * @date 2018-09-18
 */
public class Contacts extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/** 联系人id */
	private Integer id;
	/** 姓名 */
	private String name;
	/** 职务 */
	private String post;
	/** 手机 */
	private String phone;
	/** 单位电话 */
	private String workTelephone;
	/** 单位传真 */
	private String unitFax;
	/** 家庭电话 */
	private String homePhone;
	/** 生日 */
	private String birthday;
	/** 状态（-1：无效，1：有效） */
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
	public void setPost(String post){
		this.post = post;
	}

	public String getPost(){
		return post;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}
	public void setWorkTelephone(String workTelephone){
		this.workTelephone = workTelephone;
	}

	public String getWorkTelephone(){
		return workTelephone;
	}
	public void setUnitFax(String unitFax){
		this.unitFax = unitFax;
	}

	public String getUnitFax(){
		return unitFax;
	}
	public void setHomePhone(String homePhone){
		this.homePhone = homePhone;
	}

	public String getHomePhone(){
		return homePhone;
	}
	public void setBirthday(String birthday){
		this.birthday = birthday;
	}

	public String getBirthday(){
		return birthday;
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
            .append("post", getPost())
            .append("phone", getPhone())
            .append("workTelephone", getWorkTelephone())
            .append("unitFax", getUnitFax())
            .append("homePhone", getHomePhone())
            .append("birthday", getBirthday())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .toString();
    }
}
