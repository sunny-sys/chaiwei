package com.bangdao.requestVo.repertoryManage;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckListReq extends RequestEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 调拨单头表
     */
    @ApiModelProperty(value = "基本信息",required = true)
    private CheckListBaseReq base;
    /**
     * 商品明细
     */
    @ApiModelProperty(value = "明细信息",required = true)
    private List<CheckListDetailReq> detail;
}
