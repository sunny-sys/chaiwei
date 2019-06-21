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

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchasingWarehousingRequest extends RequestEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 采购入库头表
     */
    @NotNull
    @Valid
    @ApiModelProperty(value = "基本信息",required = true)
    private PurchasingWarehousingBaseRequest purchasingWarehousingBase;
    @Valid
    @ApiModelProperty(value = "明细信息",required = true)
    private List<PurchasingWarehousingDetailedRequest> purchasingWarehousingDetailed;
}
