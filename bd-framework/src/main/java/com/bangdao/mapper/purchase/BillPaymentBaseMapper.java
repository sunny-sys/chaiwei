package com.bangdao.mapper.purchase;

import com.bangdao.domain.purchase.BillPaymentBase;
import com.bangdao.requestVo.purchase.BillPaymentBaseReq;
import com.bangdao.responseVo.purchasing.BillPaymentBaseResp;

import java.util.List;


/**
 * 付款单基本 数据层
 * 
 * @author chaiwei
 * @date 2018-11-13
 */
public interface BillPaymentBaseMapper {
	/**
     * 查询付款单基本信息
     * @author chaiwei
     * @param id 付款单基本ID
     * @return 付款单基本信息
     */
	public BillPaymentBase selectBillPaymentBaseById(Integer id);

	/**
     * 查询付款单基本信息（关联员工，往来单位表）
     * @author chaiwei
     * @param id 付款单基本ID
     * @return 付款单基本信息
     */
	public BillPaymentBaseResp selectRelationById(Integer id);

	/**
	 * 查询付款单基本信息（关联员工，往来单位表）
	 * @author chaiwei
	 * @param billPaymentBase 采购退货基本信息
	 * @return 采购退货基本集合
	 */
	public List<BillPaymentBaseResp> selectRelationList(BillPaymentBaseReq billPaymentBase);

	/**
     * 查询付款单基本列表
     * @author chaiwei
     * @param billPaymentBase 付款单基本信息
     * @return 付款单基本集合
     */
	public List<BillPaymentBase> selectBillPaymentBaseList(BillPaymentBaseReq billPaymentBase);
	
	/**
     * 新增付款单基本
     * @author chaiwei
     * @param billPaymentBase 付款单基本信息
     * @return 结果
     */
	public int insertBillPaymentBase(BillPaymentBase billPaymentBase);
	
	/**
     * 修改付款单基本
     * @author chaiwei
     * @param billPaymentBase 付款单基本信息
     * @return 结果
     */
	public int updateBillPaymentBase(BillPaymentBase billPaymentBase);
	
	/**
     * 删除付款单基本
     * @author chaiwei
     * @param id 付款单基本ID
     * @return 结果
     */
	public int deleteBillPaymentBaseById(Integer id);
	
	/**
     * 批量删除付款单基本
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBillPaymentBaseByIds(String[] ids);
	
}