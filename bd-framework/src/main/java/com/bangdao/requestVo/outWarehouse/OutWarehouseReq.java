package com.bangdao.requestVo.outWarehouse;

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
 * 出库 请求实体
 *
 * @author chaiwei
 * @date 2018-11-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OutWarehouseReq extends RequestEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 出库头表
     */
    @NotNull
    @Valid
    @ApiModelProperty(value = "基本信息",required = true)
    private OutWarehouseBaseReq outWarehouseReq;
    /**
     * 出库明细
     */
    @Valid
    @ApiModelProperty(value = "明细信息",required = true)
    private List<OutWarehouseDetailedReq> outWarehouseDetailedReq;
}
