package com.bangdao.service.purchase;

import com.bangdao.domain.approval.Approval;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.requestVo.purchase.PurchasingWarehousingBaseRequest;
import com.bangdao.requestVo.purchase.PurchasingWarehousingRequest;
import com.bangdao.requestVo.purchase.PurchasingWarehousingSearchReq;
import com.bangdao.responseVo.purchasing.PurchasingWarehousingBaseResponse;

import java.util.List;

/**
 * 采购入库 服务层
 *
 * @author chaiwei
 * @date 2018-10-25
 */
public interface IPurchasingWarehousingService {
	/**
     * 查询采购入库信息
     * @author chaiwei
     * @param id 采购入库ID
     * @return 采购入库信息
     */
	public Result selectPurchasingWarehousingById(Integer id) throws Exception;

	/**
     * 查询当前用户任务列表
     * @author chaiwei
     * @param baseRequest 基本信息
     * @return 采合
     */
	public List<PurchasingWarehousingBaseResponse> selectBaseList(PurchasingWarehousingBaseRequest baseRequest) throws Exception;

	/**
     * 查询采购入库列表
     * @author chaiwei
     * @param purchasingWarehousing 采购入库信息
     * @return 采购入库集合
     */
	public TableDataInfo selectPurchasingWarehousingList(PurchasingWarehousingRequest purchasingWarehousing) throws Exception;

	/**
     * 新增采购入库
     * @author chaiwei
     * @param purchasingWarehousing 采购入库信息
     * @return 结果
     */
	public Result insertPurchasingWarehousing(PurchasingWarehousingRequest purchasingWarehousing) throws Exception;

	/**
     * 修改采购入库
     * @author chaiwei
     * @param purchasingWarehousing 采购入库信息
     * @return 结果
     */
	public Result updatePurchasingWarehousing(PurchasingWarehousingRequest purchasingWarehousing) throws Exception;

	/**
     * 删除采购入库信息
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deletePurchasingWarehousingByIds(String ids) throws Exception;

	/**
	 * @Des: 采购入库查询
	 * @Author: xupj
	 * @Date: 2018/11/7 17:48
	 **/
    TableDataInfo selectCgrkByCondition(PurchasingWarehousingSearchReq req);

	/**
	 * 提交采购入库
	 * @param purchasingWarehousing
	 * @return
	 */
	Result submitPurchasingWarehousing(PurchasingWarehousingRequest purchasingWarehousing) throws Exception ;

	/**
	 * 审批
	 * @param approval 审批实体
	 * @return
	 */
	Result approvalPurchasingWarehousing(Approval approval) throws Exception;

	/**
	 * 获取当前用户订单列表
	 * @param baseRequest
	 * @return
	 */
	List<PurchasingWarehousingBaseResponse> selectBillList(PurchasingWarehousingBaseRequest baseRequest);
}
