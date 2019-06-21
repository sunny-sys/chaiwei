package com.bangdao.mapper.purchase;

import com.bangdao.domain.purchase.PurchaseReturnBase;
import com.bangdao.requestVo.purchase.PurchaseReturnBaseReq;
import com.bangdao.requestVo.purchase.PurchaseReturnBaseSearchReq;
import com.bangdao.responseVo.purchasing.PurchaseReturnBaseResp;

import java.util.List;

/**
 * 采购退货基本 数据层
 * 
 * @author chaiwei
 * @date 2018-11-07
 */
public interface PurchaseReturnBaseMapper {
	/**
     * 查询采购退货基本信息
     * @author chaiwei
     * @param id 采购退货基本ID
     * @return 采购退货基本信息
     */
	public PurchaseReturnBase selectPurchaseReturnBaseById(Integer id);

	/**
     * 查询采购退货关联后的基本信息（关联员工，往来单位表）
     * @author chaiwei
     * @param id 采购退货基本ID
     * @return 采购退货基本信息
     */
	public PurchaseReturnBaseResp selectRelationById(Integer id);

	/**
     * 查询采购退货基本列表
     * @author chaiwei
     * @param purchaseReturnBase 采购退货基本信息
     * @return 采购退货基本集合
     */
	public List<PurchaseReturnBase> selectPurchaseReturnBaseList(PurchaseReturnBaseReq purchaseReturnBase);

	/**
     * 查询采购退货关联后的基本信息列表（关联员工，往来单位表）
     * @author chaiwei
     * @param purchaseReturnBase 采购退货基本信息
     * @return 采购退货基本集合
     */
	public List<PurchaseReturnBaseResp> selectRelationList(PurchaseReturnBaseReq purchaseReturnBase);

	/**
     * 新增采购退货基本
     * @author chaiwei
     * @param purchaseReturnBase 采购退货基本信息
     * @return 结果
     */
	public int insertPurchaseReturnBase(PurchaseReturnBase purchaseReturnBase);
	
	/**
     * 修改采购退货基本
     * @author chaiwei
     * @param purchaseReturnBase 采购退货基本信息
     * @return 结果
     */
	public int updatePurchaseReturnBase(PurchaseReturnBase purchaseReturnBase);
	
	/**
     * 删除采购退货基本
     * @author chaiwei
     * @param id 采购退货基本ID
     * @return 结果
     */
	public int deletePurchaseReturnBaseById(Integer id);
	
	/**
     * 批量删除采购退货基本
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePurchaseReturnBaseByIds(String[] ids);

	/**
	 * @Des: 采购退货查询
	 * @Author: xupj
	 * @Date: 2018/11/8 11:22
	 **/
	List<PurchaseReturnBaseResp> selectCgthByCondition(PurchaseReturnBaseSearchReq request);
}