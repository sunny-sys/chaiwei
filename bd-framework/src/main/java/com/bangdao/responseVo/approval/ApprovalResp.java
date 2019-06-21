package com.bangdao.responseVo.approval;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 审批实体
 */
@Data
@ToString
public class ApprovalResp implements Serializable, Comparable<ApprovalResp> {
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

    /**审批时间*/
    @ApiModelProperty(value = "审批时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date time;

    /**审批人*/
    @ApiModelProperty(value = "审批时间")
    private String approver;

    @Override
    public int compareTo(ApprovalResp o) {
        long time1 = this.getTime().getTime();
        long time2 = o.getTime().getTime();
        return time1 - time2>0 ? 1:-1;// 根据时间升序排列，降序修改相减顺序即可
    }
}
