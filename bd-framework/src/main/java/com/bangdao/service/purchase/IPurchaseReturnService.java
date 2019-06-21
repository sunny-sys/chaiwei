package com.bangdao.service.purchase;

import com.bangdao.domain.approval.Approval;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.requestVo.purchase.PurchaseReturnBaseReq;
import com.bangdao.requestVo.purchase.PurchaseReturnBaseSearchReq;
import com.bangdao.requestVo.purchase.PurchaseReturnReq;
import com.bangdao.responseVo.purchasing.PurchaseReturnBaseResp;
import com.bangdao.responseVo.purchasing.PurchasingWarehousingBaseResponse;

import java.util.List;

/**
 * 采购退货 服务层
 * 
 * @author chaiwei
 * @date 2018-11-07
 */
public interface IPurchaseReturnService {
	/**
     * 查询采购退货信息
     * @author chaiwei
     * @param id 采购退货ID
     * @return 采购退货信息
     */
	public Result selectPurchaseReturnById(Integer id) throws Exception;
	
	/**
     * 获取当前用户任务列表
     * @author chaiwei
     * @param baseReq 采购退货信息
     * @return
     */
    public List<PurchaseReturnBaseResp> selectBaseList(PurchaseReturnBaseReq baseReq) throws Exception;

	/**
     * 查询采购退货列表
     * @author chaiwei
     * @param purchaseReturn 采购退货信息
     * @return 采购退货集合
     */
	public TableDataInfo selectPurchaseReturnList(PurchaseReturnReq purchaseReturn) throws Exception;
	/**
     * 新增采购退货
     * @author chaiwei
     * @param purchaseReturn 采购退货信息
     * @return 结果
     */
	public Result insertPurchaseReturn(PurchaseReturnReq purchaseReturn) throws Exception;
	
	/**
     * 修改采购退货
     * @author chaiwei
     * @param purchaseReturn 采购退货信息
     * @return 结果
     */
	public Result updatePurchaseReturn(PurchaseReturnReq purchaseReturn) throws Exception;
		
	/**
     * 删除采购退货信息
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deletePurchaseReturnByIds(String ids) throws Exception;

	/**
	 * @Des: 采购退货查询
	 * @Author: xupj
	 * @Date: 2018/11/7 17:55
	 **/
	TableDataInfo selectCgthByCondition(PurchaseReturnBaseSearchReq request);

	/**
	 * 审批退货单
	 * @param approval
	 * @return
	 */
	Result approval(Approval approval);

	/**
	 * 提交退货单
	 * @param purchaseReturn
	 * @return
	 */
	Result submit(PurchaseReturnReq purchaseReturn);

	/**
	 * 获取当前用户订单列表
	 * @param baseReq
	 * @return
	 */
	List<PurchaseReturnBaseResp> selectBillList(PurchaseReturnBaseReq baseReq);
}
