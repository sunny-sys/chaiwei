package com.bangdao.requestVo.purchase;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 采购退货基本表 pur_purchase_return_base 请求实体
 *
 * @author chaiwei
 * @date 2018-11-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseReturnBaseReq extends RequestEntity {

    private static final long serialVersionUID = 1L;

    /** id */
    @ApiModelProperty(value = "id")
    private Integer id;
    /** 供应商Id（关联往来单位表id） */
    @NotNull(message = "供应商不能为空")
    @ApiModelProperty(value = "供应商Id")
    private Integer supplierId;
    /**
     * 供应商名称
     */
    private String supplierName;
    /** 经手人id(关联员工表) */
    @ApiModelProperty(value = "经手人id")
    private Integer handManId;
    /**
     * 经手人名称
     */
    private String handManName;
    /** 总金额 */
    @ApiModelProperty(value = "总金额")
    private BigDecimal totalAmount;
    /** 退货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "退货日期")
    private Date dateOfReturn;
    /** 单据编号 */
    @ApiModelProperty(value = "单据编号")
    private String documentNumber;
    /** 制单人 */
    @ApiModelProperty(value = "制单人")
    private String singlePerson;
    /** 制单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "制单日期")
    private Date dateOfProduction;
    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "开始时间")
    private Date startDate;
    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "结束时间")
    private Date endDate;
    /**单据审批状态*/
    @ApiModelProperty(value = "单据审批状态")
    private Integer approvalStatus;

    /**
     * 提单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date submitTime;
    private String submitBy;

    /**
     * 订单生效时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date effectiveTime;
    /**
     * 当前用户任务id集合
     */
    @JsonIgnore
    private List<Integer> ids;
}
