package com.bangdao.mapper.outWarehouse;

import com.bangdao.domain.outWarehouse.ReceiptBase;
import com.bangdao.requestVo.outWarehouse.ReceiptBaseReq;
import com.bangdao.responseVo.outWarehouse.ReceiptBaseResp;

import java.util.List;


/**
 * 收款单基本 数据层
 * 
 * @author chaiwei
 * @date 2018-11-13
 */
public interface ReceiptBaseMapper {
	/**
     * 查询收款单基本信息
     * @author chaiwei
     * @param id 收款单基本ID
     * @return 收款单基本信息
     */
	public ReceiptBase selectReceiptBaseById(Integer id);

	/**
     * 查询收款单基本信息（关联员工，往来单位表）
     * @author chaiwei
     * @param id 收款单基本ID
     * @return 收款单基本信息
     */
	public ReceiptBaseResp selectRelationById(Integer id);

	/**
	 * 查询收款单基本信息（关联员工，往来单位表）
	 * @author chaiwei
	 * @param receiptBase 收款单基本信息
	 * @return 收款单基本集合
	 */
	public List<ReceiptBaseResp> selectRelationList(ReceiptBaseReq receiptBase);

	/**
     * 查询收款单基本列表
     * @author chaiwei
     * @param receiptBase 收款单基本信息
     * @return 收款单基本集合
     */
	public List<ReceiptBase> selectReceiptBaseList(ReceiptBaseReq receiptBase);
	
	/**
     * 新增收款单基本
     * @author chaiwei
     * @param receiptBase 收款单基本信息
     * @return 结果
     */
	public int insertReceiptBase(ReceiptBase receiptBase);
	
	/**
     * 修改收款单基本
     * @author chaiwei
     * @param receiptBase 收款单基本信息
     * @return 结果
     */
	public int updateReceiptBase(ReceiptBase receiptBase);
	
	/**
     * 删除收款单基本
     * @author chaiwei
     * @param id 收款单基本ID
     * @return 结果
     */
	public int deleteReceiptBaseById(Integer id);
	
	/**
     * 批量删除收款单基本
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteReceiptBaseByIds(String[] ids);
	
}