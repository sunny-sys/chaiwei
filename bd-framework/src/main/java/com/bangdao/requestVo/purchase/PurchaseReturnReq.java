package com.bangdao.requestVo.purchase;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 采购退货 请求实体
 *
 * @author chaiwei
 * @date 2018-11-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseReturnReq extends RequestEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 采购退货头表
     */
    @Valid
    @NotNull
    @ApiModelProperty(value = "基本信息",required = true)
    private PurchaseReturnBaseReq purchaseReturnBaseReq;
    /**
     * 采购退货明细
     */
    @Valid
    @ApiModelProperty(value = "明细信息",required = true)
    private List<PurchaseReturnDetailedReq> purchaseReturnDetailedReq;
}
