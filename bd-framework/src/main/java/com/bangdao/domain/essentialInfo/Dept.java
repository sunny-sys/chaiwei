package com.bangdao.domain.essentialInfo;

import com.bangdao.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 部门表 sys_dept
 *
 * @author chaiwei
 * @date 2018-10-15
 */
public class Dept extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 部门id */
    private Integer deptId;
    /** 父部门id */
    private Integer parentId;
    /** 祖级列表 */
    private String ancestors;
    /** 部门名称 */
    private String deptName;
    /** 显示顺序 */
    private Integer orderNum;
    /** 负责人 */
    private String leader;
    /** 联系电话 */
    private String phone;
    /** 邮箱 */
    private String email;

    /** 部门状态（1正常 -1停用） */
    private Integer status;
    /** 排序 */
    private Integer sort;

    public void setDeptId(Integer deptId){
        this.deptId = deptId;
    }

    public Integer getDeptId(){
        return deptId;
    }
    public void setParentId(Integer parentId){
        this.parentId = parentId;
    }

    public Integer getParentId(){
        return parentId;
    }
    public void setAncestors(String ancestors){
        this.ancestors = ancestors;
    }

    public String getAncestors(){
        return ancestors;
    }
    public void setDeptName(String deptName){
        this.deptName = deptName;
    }

    public String getDeptName(){
        return deptName;
    }
    public void setOrderNum(Integer orderNum){
        this.orderNum = orderNum;
    }

    public Integer getOrderNum(){
        return orderNum;
    }
    public void setLeader(String leader){
        this.leader = leader;
    }

    public String getLeader(){
        return leader;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhone(){
        return phone;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public Integer getStatus(){
        return status;
    }

    public void setSort(Integer sort){
        this.sort = sort;
    }

    public Integer getSort(){
        return sort;
    }

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("deptId", getDeptId())
                .append("parentId", getParentId())
                .append("ancestors", getAncestors())
                .append("deptName", getDeptName())
                .append("orderNum", getOrderNum())
                .append("leader", getLeader())
                .append("phone", getPhone())
                .append("email", getEmail())
                .append("remark", getRemark())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("sort", getSort())
                .toString();
    }
}
