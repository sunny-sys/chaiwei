package com.bangdao.service.repertoryManage;

import com.bangdao.domain.approval.Approval;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.requestVo.repertoryManage.CheckListBaseReq;
import com.bangdao.requestVo.repertoryManage.CheckListReq;
import com.bangdao.responseVo.repertoryManage.CheckListBaseResp;

import java.util.List;

/**
 * 盘点单
 */
public interface ICheckListService {
	
	/**
     * 查询详情
     * @author chenshao
     * @param id 库存管理盘点单基本ID
     * @return 库存管理盘点单基本信息
     */
	public Result selectCheckListById(Integer id) throws Exception;
	
	/**
     * 查询库存管理盘点单列表
     * @author chenshao
     * @param req 库存管理盘点单基本信息
     * @return 库存管理盘点单基本集合
     */
	public TableDataInfo selectCheckListList(CheckListReq req) throws Exception;
	
	/**
     * 个人任务列表
     * @author chenshao
     * @param base 拨单基本信息
     * @return 盘点单基本列表
     */
	public List<CheckListBaseResp> selectBaseList(CheckListBaseReq base) throws Exception;

	/**
     * 个人订单列表
     * @author chenshao
     * @param base 拨单基本信息
     * @return 盘点单基本列表
     */
	public List<CheckListBaseResp> billList(CheckListBaseReq base) throws Exception;

	/**
     * 新增库存管理盘点单基本
     * @author chenshao
     * @param req 库存管理盘点单基本信息
     * @return 结果
     */
	public Result insertCheckList(CheckListReq req) throws Exception;

	/**
     * 提交
     * @author chenshao
     * @param req 库存管理盘点单基本信息
     * @return 结果
     */
	public Result submit(CheckListReq req) throws Exception;

	/**
     * 修改库存管理盘点单基本
     * @author chenshao
     * @param req 库存管理盘点单基本信息
     * @return 结果
     */
	public Result updateCheckList(CheckListReq req) throws Exception;
		
	/**
     * 删除库存管理盘点单基本信息
     * @author chenshao
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteCheckListBaseByIds(String ids) throws Exception;

	/**
     * 删除盘点单
     * @author chenshao
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteCheckListByIds(String ids) throws Exception;

	/**
	 * 审批
	 * @param approval
	 * @return
	 */
	Result approval(Approval approval) throws Exception;
}
