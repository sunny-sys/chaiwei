package com.bangdao.domain.repertoryManage;

import com.bangdao.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
                            
/**
 * 库存管理调拨单基本表 rep_manage_allot_base
 *
 * @author chenshao
 * @date 2018-11-08
 */
@Data
public class ManageAllotBase extends BaseEntity{
    private static final long serialVersionUID=1L;

    /** 主键id */
    private Integer id;
    /** 仓库表id */
    private Integer outWarehouseId;
    /** 仓库表id */
    private Integer inWarehouseId;
    /**
     * 经手人id
     */
    private String handManId;
    /** 金额 */
    private BigDecimal totalAmount;
    /** 调拨日期 */
    private Date allotDate;
    /** 单据编号 */
    private String documentNumber;
    /** 制单人 */
    private String singlePerson;
    /** 制单日期 */
    private Date dateOfProduction;
    /** 状态（-1：无效，1：有效） */
    private Integer status;

	/** 审批状态*/
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

}
