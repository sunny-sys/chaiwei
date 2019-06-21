package com.bangdao.mapper.purchase;

import com.bangdao.domain.purchase.PurchasingWarehousingBase;
import com.bangdao.requestVo.purchase.PurchasingWarehousingBaseRequest;
import com.bangdao.requestVo.purchase.PurchasingWarehousingSearchReq;
import com.bangdao.responseVo.purchasing.PurchasingWarehousingBaseResponse;

import java.util.List;

/**
 * 采购入库基本 数据层
 * 
 * @author chaiwei
 * @date 2018-10-25
 */
public interface PurchasingWarehousingBaseMapper {
	/**
     * 查询采购入库基本信息
     * @author chaiwei
     * @param id 采购入库基本ID
     * @return 采购入库基本信息
     */
	public PurchasingWarehousingBase selectPurchasingWarehousingBaseById(Integer id);

	/**
	 * 查询采购入库关联后的基本信息（关联员工，往来单位表）
	 * @author chaiwei
	 * @param id 采购入库基本ID
	 * @return 采购入库基本信息
	 */
	public PurchasingWarehousingBaseResponse selectRelationById(Integer id);

	/**
	 * 查询采购入库关联后的基本信息列表（关联员工，往来单位表）
	 * @author chaiwei
	 * @param purchasingWarehousingBase 采购入库基本信息
	 * @return 采购入库基本集合
	 */
	public List<PurchasingWarehousingBaseResponse> selectRelationList(PurchasingWarehousingBaseRequest purchasingWarehousingBase);


	/**
     * 查询采购入库基本列表
     * @author chaiwei
     * @param purchasingWarehousingBase 采购入库基本信息
     * @return 采购入库基本集合
     */
	public List<PurchasingWarehousingBase> selectPurchasingWarehousingBaseList(PurchasingWarehousingBaseRequest purchasingWarehousingBase);
	
	/**
     * 新增采购入库基本
     * @author chaiwei
     * @param purchasingWarehousingBase 采购入库基本信息
     * @return 结果
     */
	public int insertPurchasingWarehousingBase(PurchasingWarehousingBase purchasingWarehousingBase);
	
	/**
     * 修改采购入库基本
     * @author chaiwei
     * @param purchasingWarehousingBase 采购入库基本信息
     * @return 结果
     */
	public int updatePurchasingWarehousingBase(PurchasingWarehousingBase purchasingWarehousingBase);
	
	/**
     * 删除采购入库基本
     * @author chaiwei
     * @param id 采购入库基本ID
     * @return 结果
     */
	public int deletePurchasingWarehousingBaseById(Integer id);
	
	/**
     * 批量删除采购入库基本
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePurchasingWarehousingBaseByIds(String[] ids);

	/**
	 * @Des: 采购入库查询
	 * @Author: xupj
	 * @Date: 2018/11/7 17:48
	 **/
    List<PurchasingWarehousingBaseResponse> selectCgrkByCondition(PurchasingWarehousingSearchReq req);
}