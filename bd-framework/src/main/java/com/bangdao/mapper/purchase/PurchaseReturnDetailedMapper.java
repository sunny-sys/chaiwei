package com.bangdao.mapper.purchase;

import com.bangdao.domain.purchase.PurchaseReturnDetailed;
import com.bangdao.requestVo.purchase.PurchaseReturnDetailedReq;
import com.bangdao.responseVo.purchasing.PurchaseReturnDetailedResp;

import java.util.List;

/**
 * 采购退货详情 数据层
 * 
 * @author chaiwei
 * @date 2018-11-07
 */
public interface PurchaseReturnDetailedMapper {
	/**
     * 查询采购退货详情信息
     * @author chaiwei
     * @param id 采购退货详情ID
     * @return 采购退货详情信息
     */
	public PurchaseReturnDetailed selectPurchaseReturnDetailedById(String id);

	/**
     * 查询采购退货详情信息(关联商品，仓库表)
     * @author chaiwei
     * @param id 采购退货详情ID
     * @return 采购退货详情信息
     */
	public PurchaseReturnDetailedResp selectRelationById(String id);

	/**
     * 查询采购退货详情列表(关联商品，仓库表)
     * @author chaiwei
     * @param purchaseReturnDetailed 采购退货详情信息
     * @return 采购退货详情集合
     */
	public List<PurchaseReturnDetailedResp> selectRelationList(PurchaseReturnDetailedReq purchaseReturnDetailed);
	
	/**
     * 查询采购退货详情列表
     * @author chaiwei
     * @param purchaseReturnDetailed 采购退货详情信息
     * @return 采购退货详情集合
     */
	public List<PurchaseReturnDetailed> selectPurchaseReturnDetailedList(PurchaseReturnDetailedReq purchaseReturnDetailed);

	/**
     * 新增采购退货详情
     * @author chaiwei
     * @param purchaseReturnDetailed 采购退货详情信息
     * @return 结果
     */
	public int insertPurchaseReturnDetailed(PurchaseReturnDetailed purchaseReturnDetailed);

	/**
	 * 批量新增或更新采购退货详情(插入数据的表必须有主键或者是唯一索引)
	 * @author chaiwei
	 * @param purchaseReturnDetailedList 采购退货详情信息
	 * @return 结果
	 */
	public int batchInsertOrUpdate(List<PurchaseReturnDetailed> purchaseReturnDetailedList);

	/**
     * 修改采购退货详情
     * @author chaiwei
     * @param purchaseReturnDetailed 采购退货详情信息
     * @return 结果
     */
	public int updatePurchaseReturnDetailed(PurchaseReturnDetailed purchaseReturnDetailed);
	
	/**
     * 删除采购退货详情
     * @author chaiwei
     * @param id 采购退货详情ID
     * @return 结果
     */
	public int deletePurchaseReturnDetailedById(String id);
	
	/**
     * 批量删除采购退货详情
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePurchaseReturnDetailedByIds(String[] ids);

	/**
	 * 批量删除采购退货详情
	 * @author chaiwei
	 * @param parentId 需要删除的数据parentId
	 * @return 结果
	 */
	public int deleteByParentId(String[] parentId);
	
}