package com.bangdao.requestVo.essentialInfo;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 联系人表 ess_contacts 请求实体
 *
 * @author xml
 * @date 2018-09-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactsRequest extends RequestEntity {

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
}
