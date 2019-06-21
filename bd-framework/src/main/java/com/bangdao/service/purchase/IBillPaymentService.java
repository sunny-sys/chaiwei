package com.bangdao.service.purchase;

import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.requestVo.purchase.BillPaymentReq;
import com.bangdao.responseVo.purchasing.BillPaymentResp;

import java.util.List;

/**
 * 付款单 服务层
 * 
 * @author chaiwei
 * @date 2018-11-13
 */
public interface IBillPaymentService {
	/**
     * 根据供应商查询付款单（包含采购入库，还采购退货）
     * @author chaiwei
     * @param billPayment
     * @return 采购入库，采购退货
     */
	public Result getReturnAndWarehouse(BillPaymentReq billPayment) throws Exception;

	/**
     * 查询付款单信息
     * @author chaiwei
     * @param id 付款单ID
     * @return 付款单信息
     */
	public Result selectBillPaymentById(Integer id) throws Exception;

	/**
     * 查询付款单列表
     * @author chaiwei
     * @param billPayment 付款单信息
     * @return 付款单集合
     */
	public TableDataInfo selectBillPaymentList(BillPaymentReq billPayment) throws Exception;
	
	/**
     * 新增付款单
     * @author chaiwei
     * @param billPayment 付款单信息
     * @return 结果
     */
	public Result insertBillPayment(BillPaymentReq billPayment) throws Exception;
	
	/**
     * 修改付款单
     * @author chaiwei
     * @param billPayment 付款单信息
     * @return 结果
     */
	public Result updateBillPayment(BillPaymentReq billPayment) throws Exception;
		
	/**
     * 删除付款单信息
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteBillPaymentByIds(String ids) throws Exception;
	
}
