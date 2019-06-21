package com.bangdao.domain.approval;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 审批实体
 */
@Data
public class Approval implements Serializable {
    /**
     * 订单的id
     */
    @ApiModelProperty(value = "订单id")
    private Integer id;

    /**
     * 审批动作
     */
    @ApiModelProperty(value = "审批动作：1同意，-1驳回")
    private String action;
    /**
     * 备注
     */
    @ApiModelProperty(value = "审批意见")
    private String comment;

    /**
     * 业务键key（用于关联activiti表）
     */
    @JsonIgnore
    private String businessKey;
}
