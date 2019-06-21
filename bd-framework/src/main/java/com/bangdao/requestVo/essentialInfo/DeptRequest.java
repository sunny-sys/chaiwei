package com.bangdao.requestVo.essentialInfo;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 部门表 sys_dept 请求实体
 *
 * @author chaiwei
 * @date 2018-10-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeptRequest extends RequestEntity {

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
}
