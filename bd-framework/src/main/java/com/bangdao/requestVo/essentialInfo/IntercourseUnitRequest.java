package com.bangdao.requestVo.essentialInfo;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 往来单位基本表 ess_intercourse_unit 请求实体
 *
 * @author chaiwei
 * @date 2018-10-12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class IntercourseUnitRequest extends RequestEntity {

    private static final long serialVersionUID = 1L;

    /** 单位id */
    private Integer id;
    /** 单位名称 */
    private String unitName;
    /** 单位性质 */
    private String unitNature;
    /** 单位类别 */
    private String unitCategory;
    /** 所属部门 */
    private String department;
    /** 省 */
    private String province;
    /** 市 */
    private String city;
    /** 区县 */
    private String county;
    /** 地址 */
    private String address;
    /** 单位编码 */
    private String unitCode;
    /** 所属业务员 */
    private String salesman;
    /** 联系人 */
    private String contacts;
    /** 联系电话 */
    private String contactNumber;
    /** 手机 */
    private String phone;
    /** 传真 */
    private String fax;
    /** 所属地区 */
    private String affiliatedArea;
    /** 邮政编码 */
    private String postalCode;
    /** 电子邮箱 */
    private String mailBox;
    /** 联系人id */
    private String contactsId;
    /** 附件信息id */
    private String enclosureId;
}
