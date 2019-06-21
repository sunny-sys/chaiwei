package com.bangdao.requestVo.essentialInfo;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 收付款账户表 ess_receive_payment_account 请求实体
 *
 * @author chenshao
 * @date 2018-10-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceivePaymentAccountRequest extends RequestEntity {

    private static final long serialVersionUID = 1L;

    /**  */
    private Integer id;
    /** 账户名称 */
    private String name;
    /** 账户类别id */
    private Integer accountTypeId;
    /** 账户类别 */
    private String accountType;
    /** 会计科目表id */
    private Integer subjectId;
    /** 开户名 */
    private String bankAccountName;
    /** 开户银行名称 */
    private String bankName;
    /** 银行账号 */
    private String bankAccount;
    /** 币种 */
    private String currency;
    /** 部门表id */
    private Integer deptId;
    /** 数据状态（-1:无效;1:有效） */
    private Integer status;
//    /** 创建时间 */
//    private Date createTime;
//    /** 修改时间 */
//    private Date updateTime;
}
