package com.bangdao.requestVo.essentialInfo;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 员工表 ess_employee 请求实体
 *
 * @author chaiwei
 * @date 2018-10-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeRequest extends RequestEntity {

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
}
