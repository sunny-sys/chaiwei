package com.bangdao.requestVo.repertoryManage;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckListBaseReq extends RequestEntity {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "id")
    private Integer id;
    /** 仓库表id */
    @ApiModelProperty(value = "仓库id")
    private Integer warehouseId;
    private String warehouseName;
    /** 经手人 */
    @ApiModelProperty(value = "经手人id")
    private String handManId;
    private String handManName;
    /** 盈亏总金额 */
    @ApiModelProperty(value = "盈亏总金额")
    private BigDecimal totalAmount;
    /** 盘点日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "盘点日期")
    private Date allotDate;
    /** 单据编号 */
    @ApiModelProperty(value = "单据编号")
    private String documentNumber;
    /** 制单人 */
    @ApiModelProperty(value = "制单人")
    private String singlePerson;
    /** 制单日期 */
    @ApiModelProperty(value = "制单日期")
    private Date dateOfProduction;

    @JsonIgnore
    /** 审批状态*/
    private Integer approvalStatus;

    /**
     * 当前用户任务id集合
     */
    @JsonIgnore
    private List<Integer> ids;

    /**
     * 提单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date submitTime;

    /** 提单人 */
    private String submitBy;
    /**
     * 订单生效时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date effectiveTime;
}
