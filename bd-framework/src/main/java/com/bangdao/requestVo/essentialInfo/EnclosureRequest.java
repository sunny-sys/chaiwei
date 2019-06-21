package com.bangdao.requestVo.essentialInfo;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 附件表 ess_enclosure 请求实体
 *
 * @author xml
 * @date 2018-09-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnclosureRequest extends RequestEntity {

    private static final long serialVersionUID = 1L;

    /** 附件id */
    private Integer id;
    /** 法定代表人 */
    private String legalRepresentative;
    /** 注册资金（万元） */
    private String registeredCapital;
    /** 开户银行 */
    private String accountOpeningBank;
    /** 账号 */
    private String accountNumber;
    /** 经济类型 */
    private String economicType;
    /** 税号 */
    private String dutyParagraph;
    /** 客户来源 */
    private String customerSource;
    /** 所属行业 */
    private String industry;
    /** 发货方式 */
    private String delivery;
    /** 经营类型 */
    private String businessType;
    /** 客户级别 */
    private String customerLevel;
    /** 客户状态 */
    private String customerStatus;
    /** 客户概况 */
    private String customerProfile;
    /** 状态（-1：无效，1：有效） */
    private Integer status;
}
