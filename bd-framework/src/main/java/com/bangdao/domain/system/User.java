package com.bangdao.domain.system;

import com.bangdao.domain.essentialInfo.Dept;
import com.bangdao.framework.aspectj.lang.annotation.Excel;
import com.bangdao.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import java.util.Arrays;
import java.util.Date;

/**
 * 用户对象 sys_user
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Excel(name = "用户序号")
    @ApiModelProperty(value = "id")
    private Long userId;

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    /**
     * 部门父ID
     */
    private Long parentId;

    /**
     * 登录名称
     */
    @Excel(name = "登录名称")
    @ApiModelProperty(value = "登录账号")
    private String loginName;

    /**
     * 用户名称
     */
    @Excel(name = "用户名称")
    @ApiModelProperty(value = "用户名称")
    private String userName;

    /**
     * 用户邮箱
     */
    @Excel(name = "用户邮箱")
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    @ApiModelProperty(value = "手机号码")
    private String phonenumber;

    /**
     * 用户性别
     */
    @Excel(name = "用户性别")
    @ApiModelProperty(value = "用户性别")
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 密码
     */
    @ApiModelProperty(value = "旧密码(只用于修改密码时使用)")
    private String oldPassword;

    /**
     * 盐加密
     */
    @JsonIgnore
    private String salt;

    /**
     * 帐号状态（0正常 1停用）
     */
    @Excel(name = "帐号状态")
    @ApiModelProperty(value = "帐号状态")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @JsonIgnore
    private String delFlag;

    /**
     * 最后登陆IP
     */
    @JsonIgnore
    @Excel(name = "最后登陆IP")
    private String loginIp;

    /**
     * 最后登陆时间
     */
    @JsonIgnore
    @Excel(name = "最后登陆时间")
    private Date loginDate;

    /**
     * 部门对象
     */
    @ApiModelProperty(value = "部门对象")
    private Dept dept;

    /**
     * 角色组
     */
    @ApiModelProperty(value = "用户角色组")
    private Long[] roleIds;

    /**
     * 岗位组
     */
    private Long[] postIds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isAdmin() {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getOldPassword() {
        return oldPassword;
    }

    @JsonProperty
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 生成随机盐
     */
    public void randomSalt() {
        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = secureRandom.nextBytes(3).toHex();
        setSalt(hex);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public Long[] getPostIds() {
        return postIds;
    }

    public void setPostIds(Long[] postIds) {
        this.postIds = postIds;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", deptId=" + deptId + ", parentId=" + parentId + ", loginName=" + loginName
                + ", userName=" + userName + ", email=" + email + ", phonenumber=" + phonenumber + ", sex=" + sex
                + ", avatar=" + avatar + ", password=" + password + ", salt=" + salt + ", status=" + status
                + ", delFlag=" + delFlag + ", loginIp=" + loginIp + ", loginDate=" + loginDate + ", dept=" + dept
                + ", roleIds=" + Arrays.toString(roleIds) + ", postIds=" + Arrays.toString(postIds) + "]";
    }

}
