package com.bangdao.requestVo.purchase;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Des: 采购入库查询请求实体
 * @Author: xupj
 * @Date: 2018/11/8 11:11
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchasingWarehousingSearchReq extends RequestEntity {

    private static final long serialVersionUID = -9110978785933933081L;
    /** 供应商名称 */
    @ApiModelProperty(value = "供应商名称")
    private String supplierName;
    /** 经手人名称 */
    @ApiModelProperty(value = "经手人名称")
    private String handManName;
    /** 单据编号 */
    @ApiModelProperty(value = "单据编号")
    private String documentNumber;
    /** 制单人 */
    @ApiModelProperty(value = "制单人")
    private String singlePerson;
    /**单据状态*/
    private Integer approvalStatus;
}
