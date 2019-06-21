package com.bangdao.service.repertoryManage;

import com.bangdao.domain.approval.Approval;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.requestVo.repertoryManage.ManageAllotBaseReq;
import com.bangdao.requestVo.repertoryManage.RepertoryManageRequest;
import com.bangdao.responseVo.repertoryManage.ManageAllotBaseResp;

import java.util.List;

/**
 * 调拨单基本 服务层
 * 
 * @author chenshao
 * @date 2018-11-08
 */
public interface IManageAllotBaseService {
	/**
     * 查询详情
     * @author chenshao
     * @param id 调拨单基本ID
     * @return 调拨单基本信息
     */
	public Result selectManageAllotBaseById(Integer id) throws Exception;
	/**
     * 个人任务列表
     * @author chenshao
     * @param base 拨单基本信息
     * @return 调拨单基本列表
     */
	public List<ManageAllotBaseResp> selectBaseList(ManageAllotBaseReq base) throws Exception;

	/**
     * 新增调拨单基本
     * @author chenshao
     * @param manageAllotBase 调拨单基本信息
     * @return 结果
     */
	public Result insertManageAllotBase(RepertoryManageRequest manageAllotBase) throws Exception;
	
	/**
     * 修改调拨单基本
     * @author chenshao
     * @param manageAllotBase 调拨单基本信息
     * @return 结果
     */
	public Result updateManageAllotBase(RepertoryManageRequest manageAllotBase) throws Exception;

	/**
     * 删除调拨单
     * @author chenshao
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteManageAllotByIds(String ids) throws Exception;

	/**查询个人订单列表**/
	List<ManageAllotBaseResp> billList(ManageAllotBaseReq base) throws Exception;

	/**提交**/
	Result submit(RepertoryManageRequest manageAllot) throws Exception;

	/**审批**/
	Result approval(Approval approval) throws Exception;
}
