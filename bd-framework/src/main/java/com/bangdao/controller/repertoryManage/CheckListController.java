package com.bangdao.controller.repertoryManage;

import com.bangdao.common.constant.Constants;
import com.bangdao.common.utils.security.ShiroUtils;
import com.bangdao.domain.approval.Approval;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.requestVo.repertoryManage.CheckListBaseReq;
import com.bangdao.requestVo.repertoryManage.CheckListReq;
import com.bangdao.responseVo.repertoryManage.CheckListBaseResp;
import com.bangdao.service.repertoryManage.ICheckListService;
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
 * 盘点单 信息操作处理
 * 
 * @author chenshao
 * @date 2018-11-08
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/checkList")
@Api(tags = {"盘点单"})
public class CheckListController extends BaseController {
    
	@Autowired
	private ICheckListService checkListService;

	@RequiresPermissions("bangdao:checkList:view")
	@GetMapping
	@ApiIgnore
	public Result checkList() {
		return success();
	}

	/**
	  * 查询盘点单列表
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:checkList:list")
	@PostMapping("/list")
	@ResponseBody
	@ApiOperation(value = "个人任务列表",notes = "个人任务列表")
	@ApiImplicitParam(name = "base",value = "查询实体",required = true,dataType = "CheckListBaseReq")
	public TableDataInfo list(@RequestBody CheckListBaseReq base) throws Exception {
		startPage();
		List<CheckListBaseResp> respList = checkListService.selectBaseList(base);
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
	public TableDataInfo billList(@RequestBody CheckListBaseReq base) throws Exception {
		startPage();
		base.setCreateBy(ShiroUtils.getUserId()+"");
		List<CheckListBaseResp> respList = checkListService.billList(base);
		return getDataTable(respList);
	}

	/**
	 * 新增保存盘点单
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:checkList:add")
	@Log(title = "盘点单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	@ApiOperation(value = "新增",notes = "新增调拨单")
	@ApiImplicitParam(name = "req",value = "查询实体",required = true,dataType = "CheckListReq")
	public Result addSave(@RequestBody @Validated CheckListReq req,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		req.getBase().setApprovalStatus(Constants.ApprovalStatus.DRAFT_INT);
		return checkListService.insertCheckList(req);
	}

	/**
	 * 提交
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:outWarehouse:add")
	@Log(title = "盘点单提交", businessType = BusinessType.INSERT)
	@PostMapping("/submit")
	@ResponseBody
	@ApiOperation(value = "提交",notes = "提交")
	@ApiImplicitParam(name = "manageAllot",value = "提交新增实体",required = true,dataType = "RepertoryManageRequest")
	public Result submit(@RequestBody @Validated CheckListReq req,
						 BindingResult result) throws Exception {
		if (result.hasErrors()){
			return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return checkListService.submit(req);
	}

	/**
	 * 查询盘点单详情
	 * @author chenshao
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
	@ApiOperation(value = "查询详情",notes = "根据基本信息id查询详情")
	@ApiImplicitParam(name = "id",value = "查询实体",required = true,dataType = "int")
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return checkListService.selectCheckListById(id);
	}

	/**
	 * 修改保存盘点单
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:checkList:edit")
	@Log(title = "盘点单修改", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	@ApiOperation(value = "修改",notes = "修改")
	@ApiImplicitParam(name = "req",value = "查询实体",required = true,dataType = "CheckListReq")
	public Result editSave(@RequestBody CheckListReq req,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
		req.getBase().setApprovalStatus(Constants.ApprovalStatus.DRAFT_INT);
	    return checkListService.updateCheckList(req);
	}

	/**
	 * 审批
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:outWarehouse:add")
	@Log(title = "盘点单审批", businessType = BusinessType.UPDATE)
	@PostMapping("/approval")
	@ResponseBody
	@ApiOperation(value = "审批",notes = "审批")
	@ApiImplicitParam(name = "approval", value = "审批实体",required = true, dataType = "Approval")
	public Result approval(@RequestBody Approval approval,
						   BindingResult result) throws Exception {
		if (result.hasErrors()){
			return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return checkListService.approval(approval);
	}

	/**
	 * 删除盘点单
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:checkList:remove")
	@Log(title = "盘点单", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	@ApiOperation(value = "删除",notes = "批量删除")
	@ApiImplicitParam(name = "ids",value = "基本id集合",required = true,dataType = "String")
	public Result remove(String ids) throws Exception {
		return checkListService.deleteCheckListByIds(ids);
	}

	//@RequiresPermissions("bangdao:checkList:list")
	@PostMapping("/allList")
	@ResponseBody
	@ApiOperation(value = "全部单据列表",notes = "全部单据")
	@ApiImplicitParam(name = "base",value = "查询实体",required = true,dataType = "CheckListBaseReq")
	public TableDataInfo allList(@RequestBody CheckListBaseReq base) throws Exception {
		startPage();
		List<CheckListBaseResp> respList = checkListService.selectBaseList(base);
		return getDataTable(respList);
	}
}
