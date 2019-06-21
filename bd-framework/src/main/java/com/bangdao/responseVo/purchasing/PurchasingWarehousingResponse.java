package com.bangdao.responseVo.purchasing;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PurchasingWarehousingResponse{
    private static final long serialVersionUID = 1L;

    /**
     * 采购入库头表
     */
    private PurchasingWarehousingBaseResponse purchasingWarehousingBaseResp;
    /**
     * 采购入库明细
     */
    private List<PurchasingWarehousingDetailedResponse> purchasingWarehousingDetailedResp;
}
