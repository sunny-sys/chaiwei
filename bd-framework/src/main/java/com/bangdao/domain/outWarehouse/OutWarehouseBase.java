package com.bangdao.domain.outWarehouse;

import com.bangdao.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 退货基本表
 *
 * @author chaiwei
 * @date 2018-11-07
 */
@Data
public class OutWarehouseBase extends BaseEntity{
    private static final long serialVersionUID=1L;

    /** id */
    private Integer id;
    /** 供应商Id（关联往来单位表id） */
    private Integer supplierId;
    /** 经手人id(关联员工表) */
    private Integer handManId;
    /** 总金额 */
    private BigDecimal totalAmount;
    /** 发货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date dateDeliver;
    /** 单据编号 */
    private String documentNumber;
    /** 制单人 */
    private String singlePerson;
    /** 制单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date dateOfProduction;
    /** 状态（-1：无效，1：有效） */
    private Integer status;

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
    private String submitBy;

    /**
     * 订单生效时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date effectiveTime;
}
