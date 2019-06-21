package com.bangdao.requestVo.essentialInfo;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 物流表 ess_logistics_info 请求实体
 *
 * @author xupj
 * @date 2018-10-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogisticsInfoRequest extends RequestEntity {

    private static final long serialVersionUID = 1L;

    /**  */
    private Integer id;
    /** 公司名称 */
    private String name;
    /** 公司简称 */
    private String shortName;
    /** 公司地址 */
    private String address;
    /** 联系人 */
    private String linkman;
    /** 联系电话 */
    private String contactNumber;
    /** 手机号 */
    private String phoneNumber;
    /** 传真 */
    private String facsimile;
    /** 电子邮箱 */
    private String email;
    /** 邮政编码 */
    private String postcode;
    /** 开户银行名称 */
    private String bankName;
    /** 银行账号 */
    private String bankAccount;
    /** 公司网址 */
    private String website;
    /** 数据状态（-1:无效;1:有效） */
    private Integer status;
}
