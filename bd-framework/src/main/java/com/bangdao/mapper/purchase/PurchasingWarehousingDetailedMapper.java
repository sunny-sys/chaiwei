package com.bangdao.mapper.purchase;

import com.bangdao.domain.essentialInfo.CommodityBase;
import com.bangdao.domain.purchase.PurchasingWarehousingDetailed;
import com.bangdao.requestVo.essentialInfo.CommodityBaseRequest;
import com.bangdao.requestVo.purchase.PurchasingWarehousingDetailedRequest;
import com.bangdao.responseVo.purchasing.PurchasingWarehousingDetailedResponse;

import java.util.List;

/**
 * 采购入库详情 数据层
 * 
 * @author chaiwei
 * @date 2018-10-25
 */
public interface PurchasingWarehousingDetailedMapper {
	/**
     * 查询采购入库详情信息
     * @author chaiwei
     * @param id 采购入库详情ID
     * @return 采购入库详情信息
     */
	public PurchasingWarehousingDetailed selectPurchasingWarehousingDetailedById(Integer id);

	/**
	 * 查询采购退货详情信息(关联商品，仓库表)
	 * @author chaiwei
	 * @param id 采购退货详情ID
	 * @return 采购退货详情信息
	 */
	public PurchasingWarehousingDetailedResponse selectRelationById(Integer id);

	/**
	 * 查询采购退货详情列表(关联商品，仓库表)
	 * @author chaiwei
	 * @param purchasingWarehousingDetailed 采购退货详情信息
	 * @return 采购退货详情集合
	 */
	public List<PurchasingWarehousingDetailedResponse> selectRelationList(PurchasingWarehousingDetailedRequest purchasingWarehousingDetailed);

	/**
     * 查询采购入库详情列表
     * @author chaiwei
     * @param purchasingWarehousingDetailed 采购入库详情信息
     * @return 采购入库详情集合
     */
	public List<PurchasingWarehousingDetailedResponse> selectPurchasingWarehousingDetailedList(PurchasingWarehousingDetailedRequest purchasingWarehousingDetailed);
	
	/**
     * 新增采购入库详情
     * @author chaiwei
     * @param purchasingWarehousingDetailed 采购入库详情信息
     * @return 结果
     */
	public int insertPurchasingWarehousingDetailed(PurchasingWarehousingDetailed purchasingWarehousingDetailed);

	/**
	 * 批量新增或更新采购入库详情(插入数据的表必须有主键或者是唯一索引)
	 * @author chaiwei
	 * @param purchasingWarehousingDetailed 采购入库详情信息
	 * @return 结果
	 */
	public int batchInsertOrUpdate(List<PurchasingWarehousingDetailed> purchasingWarehousingDetailed);

	/**
     * 修改采购入库详情
     * @author chaiwei
     * @param purchasingWarehousingDetailed 采购入库详情信息
     * @return 结果
     */
	public int updatePurchasingWarehousingDetailed(PurchasingWarehousingDetailed purchasingWarehousingDetailed);

	/**
     * 删除采购入库详情
     * @author chaiwei
     * @param id 采购入库详情ID
     * @return 结果
     */
	public int deletePurchasingWarehousingDetailedById(Integer id);
	
	/**
     * 批量删除采购入库详情
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePurchasingWarehousingDetailedByIds(String[] ids);

	/**
     * 批量删除采购入库详情
     * @author chaiwei
     * @param parentId 需要删除的数据parentId
     * @return 结果
     */
	public int deleteByParentId(String[] parentId);

	public List<PurchasingWarehousingDetailedResponse> selectCommodityBaseList();

}