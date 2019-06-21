package com.bangdao.requestVo.essentialInfo;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 商品基本表 ess_commodity_base 请求实体
 *
 * @author chaiwei
 * @date 2018-10-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommodityBaseRequest extends RequestEntity {

    private static final long serialVersionUID = 1L;

    /** 商品id */
    private Integer id;
    /** 父标签id */
    private String parentId;
    /** 商品名称 */
    private String commodityName;
    /** 商品简称 */
    private String commodityAbbreviation;
    /** 商品编码 */
    private String commodityCode;
    /** 商品性质 */
    private String commodityNature;
    /** 商品类别 */
    private String commodityGoods;
    /** 条形码 */
    private String barCode;
    /** 基本单位 */
    private String basicUnit;
    /** 规格 */
    private String specifications;
    /** 成本预算方法 */
    private String costAccountingMethod;
    /** 商品产地 */
    private String commodityOrigin;
    /** 货位 */
    private String cargoPosition;
    /** 状态（-1：无效，1：有效） */
    private Integer status;
    /** 附件id */
    private String fileId;
}
