package com.bangdao.responseVo.system;

import com.bangdao.common.constant.Constants;
import com.bangdao.framework.web.responseVo.ResponseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 仓库表 sys_warehouse 响应实体
 *
 * @author chaiwei
 * @date 2018-12-18
 */
@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WarehouseResp extends ResponseEntity {

    private static final long serialVersionUID = 1L;
    /**  */
    private String warehouseId;
    /** 供应商Id（关联往来单位表id） */
    private Integer supplierId;
    /** 供应商名称 */
    private String supplierName;
    /** 经手人id(关联员工表) */
    private Integer handManId;
    /** 经手人名称*/
    private String handManName;
    /** 入库日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date dateOfStorage;
    /** 单据编号 */
    private String documentNumber;
    /** 制单人 */
    private String singlePerson;
    /** 制单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date dateOfProduction;
    /** 订单来源明细id */
    private Integer sourceDetailedId;
    /** 商品id(关联商品基本信息表) */
    private Integer commodityId;
    /** 商品名称 */
    private String commodityName;
    /** 商品编码 */
    private String commodityCode;
    /** 商品规格 */
    private String specifications;
    /** 单位 */
    private String basicUnit;
    /** 仓库id(关联仓库表） */
    private Integer positionId;
    /** 仓库名称 */
    private String positionName;
    /** 数量 */
    private BigDecimal number;
    /** 单价 */
    private BigDecimal unitPrice;
    /** 折扣 */
    private String discount;
    /** 折后价 */
    private BigDecimal postDiscountPrice;
	/** 总额 */
	private BigDecimal total;
    /** 单据来源（标记商品来源哪里） */
    private String billSource;
	
	public BigDecimal getTotal()
	{
		if(unitPrice != null && postDiscountPrice != null)
		{
			return number.multiply(postDiscountPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return BigDecimal.ZERO;
	}
	
	public String getBillSource()
	{
		switch(billSource){
			case Constants
                    .BillSource.PURCHASE_WAREHOUSE :
			    return "采购入库";
			case Constants
                    .BillSource.RETURN :
                return "退货";
            case Constants
                    .BillSource.ALLOT :
                return "调拨";
			default :
                return "";
		}
	}
	
	
}
