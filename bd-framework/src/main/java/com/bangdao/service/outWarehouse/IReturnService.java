package com.bangdao.service.outWarehouse;

import com.bangdao.domain.approval.Approval;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.requestVo.outWarehouse.ReturnBaseReq;
import com.bangdao.requestVo.outWarehouse.ReturnReq;
import com.bangdao.responseVo.outWarehouse.ReturnBaseResp;

import java.util.List;

/**
 * 退货 服务层
 * 
 * @author chaiwei
 * @date 2018-11-07
 */
public interface IReturnService {
	/**
     * 查询退货信息
     * @author chaiwei
     * @param id 退货ID
     * @return 退货信息
     */
	public Result selectReturnById(Integer id) throws Exception;
	
	/**
     * 个人任务列表
     * @author chaiwei
     * @param baseReq 退货信息
     * @return 退货集合
     */
	public List<ReturnBaseResp> selectBaseList(ReturnBaseReq baseReq) throws Exception;

	/**
     * 查询退货列表
     * @author chaiwei
     * @param saleReturn 退货信息
     * @return 退货集合
     */
	public TableDataInfo selectReturnList(ReturnReq saleReturn) throws Exception;

	/**
     * 新增退货
     * @author chaiwei
     * @param saleReturn 退货信息
     * @return 结果
     */
	public Result insertReturn(ReturnReq saleReturn) throws Exception;
	
	/**
     * 修改退货
     * @author chaiwei
     * @param saleReturn 退货信息
     * @return 结果
     */
	public Result updateReturn(ReturnReq saleReturn) throws Exception;
		
	/**
     * 删除退货信息
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteReturnByIds(String ids) throws Exception;

	/**
	 * 个人订单列表
	 * @param base
	 * @return
	 */
	List<ReturnBaseResp> billList(ReturnBaseReq base);

	/**
	 * 提交
	 * @author chaiwei
	 */
	Result submit (ReturnReq returnReq) throws Exception;

	/**
	 * 审批
	 * @author chaiwei
	 */
	Result approval (Approval approval) throws Exception;
}
