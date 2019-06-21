package com.bangdao.domain.essentialInfo;


import com.bangdao.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 字典类型表 sys_dictionary_type
 * 
 * @author xupj
 * @date 2018-09-14
 */
public class DictionaryType extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/** 字典类型id */
	private Integer id;
	/** 字典类型名称 */
	private String dicName;
	/** 字典类型 */
	private String dicType;
	/** 字典名称 */
	private String dicNameSpell;

	public DictionaryType setId(Integer id){
		this.id = id;
		return this;
	}

	public Integer getId(){
		return id;
	}
	public void setDicName(String dicName){
		this.dicName = dicName;
	}

	public String getDicName(){
		return dicName;
	}
	public DictionaryType setDicType(String dicType){
		this.dicType = dicType;
		return this;
	}

	public String getDicType(){
		return dicType;
	}
	public DictionaryType setDicNameSpell(String dicNameSpell){
		this.dicNameSpell = dicNameSpell;
		return this;
	}

	public String getDicNameSpell(){
		return dicNameSpell;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("dicName", getDicName())
            .append("dicType", getDicType())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("dicNameSpell", getDicNameSpell())
            .toString();
    }
}
