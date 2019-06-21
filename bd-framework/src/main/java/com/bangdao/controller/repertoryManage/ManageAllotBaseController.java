package com.bangdao.controller.repertoryManage;

import com.bangdao.common.constant.Constants;
import com.bangdao.common.utils.security.ShiroUtils;
import com.bangdao.domain.approval.Approval;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.requestVo.repertoryManage.ManageAllotBaseReq;
import com.bangdao.requestVo.repertoryManage.RepertoryManageRequest;
import com.bangdao.responseVo.repertoryManage.ManageAllotBaseResp;
import com.bangdao.service.repertoryManage.IManageAllotBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 库存管理调拨单基本 信息操作处理
 * 
 * @author chenshao
 * @date 2018-11-08
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/manageAllotBase")
@Api(tags = {"调拨单"})
public class ManageAllotBaseController extends BaseController {
    
	@Autowired
	private IManageAllotBaseService manageAllotBaseService;

	@RequiresPermissions("bangdao:manageAllotBase:view")
	@GetMapping
	@ApiIgnore
	public Result manageAllotBase() {
		return success();
	}

	/**
	  * 个人任务列表
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:manageAllotBase:list")
	@PostMapping("/list")
	@ResponseBody
	@ApiOperation(value = "个人任务列表",notes = "个人任务列表")
	@ApiImplicitParam(name = "base",value = "查询实体",required = true,dataType = "ManageAllotBaseReq")
	public TableDataInfo list(@RequestBody ManageAllotBaseReq base) throws Exception {
		startPage();
		List<ManageAllotBaseResp> respList = manageAllotBaseService.selectBaseList(base);
		return getDataTable(respList);
	}

	/**
	  * 个人订单列表
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:manageAllotBase:list")
	@PostMapping("/billList")
	@ResponseBody
	@ApiOperation(value = "个人订单列表",notes = "个人订单列表")
	@ApiImplicitParam(name = "base",value = "查询实体",required = true,dataType = "ManageAllotBaseReq")
	public TableDataInfo billList(@RequestBody ManageAllotBaseReq base) throws Exception {
		startPage();
		base.setCreateBy(ShiroUtils.getUserId()+"");
		List<ManageAllotBaseResp> respList = manageAllotBaseService.billList(base);
		return getDataTable(respList);
	}

	/**
	 * 新增调拨单
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:manageAllotBase:add")
	@Log(title = "调拨单新增", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	@ApiOperation(value = "调拨单新增",notes = "新增调拨单")
	@ApiImplicitParam(name = "manageAllot",value = "查询实体",required = true,dataType = "RepertoryManageRequest")
	public Result addSave(@RequestBody @Validated RepertoryManageRequest manageAllot,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		manageAllot.getBase().setApprovalStatus(Constants.ApprovalStatus.DRAFT_INT);
		return manageAllotBaseService.insertManageAllotBase(manageAllot);
	}

	/**
	 * 修改保存库存管理调拨单基本
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:manageAllotBase:edit")
	@Log(title = "调拨单修改", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	@ApiOperation(value = "修改",notes = "修改")
	@ApiImplicitParam(name = "manageAllot",value = "查询实体",required = true,dataType = "RepertoryManageRequest")
	public Result editSave(@RequestBody @Validated RepertoryManageRequest manageAllot,
						   BindingResult result) throws Exception {
		if (result.hasErrors()){
			return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		manageAllot.getBase().setApprovalStatus(Constants.ApprovalStatus.DRAFT_INT);
		return manageAllotBaseService.updateManageAllotBase(manageAllot);
	}

	/**
	 * 提交
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:outWarehouse:add")
	@Log(title = "调拨单提交", businessType = BusinessType.INSERT)
	@PostMapping("/submit")
	@ResponseBody
	@ApiOperation(value = "提交",notes = "提交")
	@ApiImplicitParam(name = "manageAllot",value = "提交新增实体",required = true,dataType = "RepertoryManageRequest")
	public Result submit(@RequestBody @Validated RepertoryManageRequest manageAllot,
						 BindingResult result) throws Exception {
		if (result.hasErrors()){
			return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return manageAllotBaseService.submit(manageAllot);
	}

	/**
	 * 审批
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:outWarehouse:add")
	@Log(title = "调拨单审批", businessType = BusinessType.UPDATE)
	@PostMapping("/approval")
	@ResponseBody
	@ApiOperation(value = "审批",notes = "审批")
	@ApiImplicitParam(name = "approval", value = "审批实体",required = true, dataType = "Approval")
	public Result approval(@RequestBody Approval approval,
						   BindingResult result) throws Exception {
		if (result.hasErrors()){
			return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return manageAllotBaseService.approval(approval);
	}

	/**
	 * 查询库存管理调拨单基本详情
	 * @author chenshao
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
	@ApiOperation(value = "查询详情",notes = "根据基本信息id查询详情")
	@ApiImplicitParam(name = "id",value = "查询实体",required = true,dataType = "int")
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return manageAllotBaseService.selectManageAllotBaseById(id);
	}

	/**
	 * 删除库存管理调拨单基本
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:manageAllotBase:remove")
	@Log(title = "调拨单删除", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	@ApiOperation(value = "删除",notes = "批量删除")
	@ApiImplicitParam(name = "ids",value = "基本id集合",required = true,dataType = "String")
	public Result remove(String ids) throws Exception {
		return manageAllotBaseService.deleteManageAllotByIds(ids);
	}

	@PostMapping("/adoptList")
	@ResponseBody
	@ApiOperation(value="获取审批通过列表", notes="获取审批通过列表")
	@ApiImplicitParam(name = "req", value = "查询集合",required = true, dataType = "ManageAllotBaseReq")
	public TableDataInfo adoptList(@RequestBody ManageAllotBaseReq req) throws Exception {
		startPage();
		req.setApprovalStatus(Constants.ApprovalStatus.ADOPT_INT);
		List<ManageAllotBaseResp> respList = manageAllotBaseService.billList(req);
		return getDataTable(respList);
	}
}
