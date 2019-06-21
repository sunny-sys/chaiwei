package com.bangdao.responseVo.repertoryManage;

import com.bangdao.framework.web.responseVo.ResponseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CheckListBaseResp extends ResponseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Integer id;
    /** 仓库表id */
    private Integer warehouseId;
    /** 仓库表名称 */
    private String warehouseName;
    /**
     * 经手人id
     */
    private Integer handManId;
    /**
     * 经手人名字
     */
    private String handManName;
    /** 盈亏总金额 */
    private BigDecimal totalAmount;
    /** 盘点日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date allotDate;
    /** 单据编号 */
    private String documentNumber;
    /** 制单人 */
    private String singlePerson;
    /** 制单日期 */
    private Date dateOfProduction;
    /**审批状态*/
    private Integer approvalStatus;
    /**提交时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date submitTime;
    private String submitBy;

    /**单据生效时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date effectiveTime;
}
