package com.bangdao.controller.outWarehouse;

import com.bangdao.common.constant.Constants;
import com.bangdao.common.utils.security.ShiroUtils;
import com.bangdao.domain.approval.Approval;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.requestVo.outWarehouse.ReturnBaseReq;
import com.bangdao.requestVo.outWarehouse.ReturnReq;
import com.bangdao.responseVo.outWarehouse.ReturnBaseResp;
import com.bangdao.service.outWarehouse.IReturnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 退货 信息操作处理
 * 
 * @author chaiwei
 * @date 2018-11-07
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/return")
@Api(tags = "出库-退货")
public class ReturnController extends BaseController {
    
	@Autowired
	private IReturnService returnService;

	//@RequiresPermissions("bangdao:return:view")
	@GetMapping
	@ApiIgnore
	public Result returnBase() {
		return success();
	}

	/**
	 * 查询个人任务列表
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:return:list")
	@PostMapping("/list")
	@ResponseBody
	@ApiOperation(value ="个人任务列表",notes = "个人任务列表")
	@ApiImplicitParam(name ="base" ,value ="查询实体",required =true ,dataType ="ReturnBaseReq" )
	public TableDataInfo list(@RequestBody ReturnBaseReq base) throws Exception {
		startPage();
		List<ReturnBaseResp> returnBaseResps = returnService.selectBaseList(base);
		return getDataTable(returnBaseResps);
	}


	/**
	 * 个人订单列表
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:outWarehouse:list")
	@PostMapping("/billList")
	@ResponseBody
	@ApiOperation(value = "个人订单列表",notes = "个人订单列表")
	@ApiImplicitParam(name = "base",value = "查询实体",required = true,dataType = "ReturnBaseReq")
	public TableDataInfo billList(@RequestBody ReturnBaseReq base) throws Exception {
		startPage();
		base.setCreateBy(ShiroUtils.getUserId()+"");
		List<ReturnBaseResp> baseResps = returnService.billList(base);
		return getDataTable(baseResps);
	}

	/**
	 * 新增保存退货基本
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:return:add")
	@Log(title = "退货基本", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	@ApiOperation(value ="新增",notes = "新增")
	@ApiImplicitParam(name ="returnReq" ,value ="新增实体",required =true ,dataType ="ReturnReq" )
	public Result addSave(@RequestBody @Validated ReturnReq returnReq,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		returnReq.getReturnReq().setApprovalStatus(Constants.ApprovalStatus.DRAFT_INT);
		return returnService.insertReturn(returnReq);
	}

	/**
	 * 提交
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:outWarehouse:add")
	@Log(title = "退货基本", businessType = BusinessType.INSERT)
	@PostMapping("/submit")
	@ResponseBody
	@ApiOperation(value = "提交",notes = "提交")
	@ApiImplicitParam(name = "returnReq",value = "提交新增实体",required = true,dataType = "ReturnReq")
	public Result submit(@RequestBody @Validated ReturnReq returnReq,
						 BindingResult result) throws Exception {
		if (result.hasErrors()){
			return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return returnService.submit(returnReq);
	}

	/**
	 * 审批
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:outWarehouse:add")
	@Log(title = "退货基本", businessType = BusinessType.UPDATE)
	@PostMapping("/approval")
	@ResponseBody
	@ApiOperation(value = "审批",notes = "审批")
	@ApiImplicitParam(name = "approval", value = "审批实体",required = true, dataType = "Approval")
	public Result approval(@RequestBody Approval approval,
						 BindingResult result) throws Exception {
		if (result.hasErrors()){
			return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return returnService.approval(approval);
	}

	/**
	 * 查询退货基本详情
	 * @author chaiwei
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
	@ApiOperation(value ="详情",notes = "详情")
	@ApiImplicitParam(name ="id" ,value ="基本信息id",required =true ,dataType ="int" )
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return returnService.selectReturnById(id);
	}

	/**
	 * 修改保存退货基本
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:return:edit")
	@Log(title = "退货基本", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	@ApiOperation(value ="修改",notes = "修改")
	@ApiImplicitParam(name ="returnReq" ,value ="修改实体",required =true ,dataType ="ReturnReq" )
	public Result editSave(@RequestBody @Validated ReturnReq returnReq,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
		returnReq.getReturnReq().setApprovalStatus(Constants.ApprovalStatus.DRAFT_INT);
	    return returnService.updateReturn(returnReq);
	}

	/**
	 * 删除退货基本
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:return:remove")
	@Log(title = "退货基本", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	@ApiOperation(value ="删除",notes = "批量删除")
	@ApiImplicitParam(name ="ids" ,value ="基本信息id集合",required =true ,dataType ="String" )
	public Result remove(String ids) throws Exception {
		return returnService.deleteReturnByIds(ids);
	}

	@PostMapping("/adoptList")
	@ResponseBody
	@ApiOperation(value="获取审批通过列表", notes="获取审批通过列表")
	@ApiImplicitParam(name = "req", value = "查询集合",required = true, dataType = "ReturnBaseReq")
	public TableDataInfo adoptList(@RequestBody ReturnBaseReq req) throws Exception {
		startPage();
		req.setApprovalStatus(Constants.ApprovalStatus.ADOPT_INT);
		List<ReturnBaseResp> baseResps = returnService.billList(req);
		return getDataTable(baseResps);
	}
}
