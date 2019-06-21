package com.bangdao.service.outWarehouse;

import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.requestVo.outWarehouse.ReceiptReq;

/**
 * 收款单 服务层
 * 
 * @author chaiwei
 * @date 2018-11-13
 */
public interface IReceiptService {
	/**
     * 根据客户查询收款单（包含出库，还退货）
     * @author chaiwei
     * @param receipt
     * @return 出库，退货
     */
	public Result getReturnAndWarehouse(ReceiptReq receipt) throws Exception;

	/**
     * 查询收款单信息
     * @author chaiwei
     * @param id 收款单ID
     * @return 收款单信息
     */
	public Result selectReceiptById(Integer id) throws Exception;

	/**
     * 查询收款单列表
     * @author chaiwei
     * @param billPayment 收款单信息
     * @return 收款单集合
     */
	public TableDataInfo selectReceiptList(ReceiptReq billPayment) throws Exception;
	
	/**
     * 新增收款单
     * @author chaiwei
     * @param billPayment 收款单信息
     * @return 结果
     */
	public Result insertReceipt(ReceiptReq billPayment) throws Exception;
	
	/**
     * 修改收款单
     * @author chaiwei
     * @param billPayment 收款单信息
     * @return 结果
     */
	public Result updateReceipt(ReceiptReq billPayment) throws Exception;
		
	/**
     * 删除收款单信息
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteReceiptByIds(String ids) throws Exception;
	
}
