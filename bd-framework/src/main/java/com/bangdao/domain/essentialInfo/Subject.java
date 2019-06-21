package com.bangdao.domain.essentialInfo;

import com.bangdao.framework.web.domain.BaseEntity;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
    
/**
 * 科目表 sys_subject
 * 
 * @author chenshao
 * @date 2018-10-11
 */
public class Subject extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 科目代码 */
	private String subjectCode;
	/** 科目名称 */
	private String subjectName;
	/** 上级科目代码 */
	private String parentSubjectCode;
//	/**  */
//	private Date createTime;
//	/** 更新时间 */
//	private Date updateTime;
	/** 是否标准科目（1:是;0:否） */
	private Integer standardSubject;
	/** 数据状态（-1:无效;1:有效） */
	private Integer status;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}
	public void setSubjectCode(String subjectCode){
		this.subjectCode = subjectCode;
	}

	public String getSubjectCode(){
		return subjectCode;
	}
	public void setSubjectName(String subjectName){
		this.subjectName = subjectName;
	}

	public String getSubjectName(){
		return subjectName;
	}
	public void setParentSubjectCode(String parentSubjectCode){
		this.parentSubjectCode = parentSubjectCode;
	}

	public String getParentSubjectCode(){
		return parentSubjectCode;
	}
	
	public void setStandardSubject(Integer standardSubject){
		this.standardSubject = standardSubject;
	}

	public Integer getStandardSubject(){
		return standardSubject;
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
            .append("subjectCode", getSubjectCode())
            .append("subjectName", getSubjectName())
            .append("parentSubjectCode", getParentSubjectCode())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("standardSubject", getStandardSubject())
            .append("status", getStatus())
            .toString();
    }
}
