package com.bangdao.controller.purchase;

import com.bangdao.common.constant.Constants;
import com.bangdao.domain.approval.Approval;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.requestVo.purchase.PurchaseReturnBaseReq;
import com.bangdao.requestVo.purchase.PurchaseReturnBaseSearchReq;
import com.bangdao.requestVo.purchase.PurchaseReturnReq;
import com.bangdao.responseVo.purchasing.PurchaseReturnBaseResp;
import com.bangdao.service.purchase.IPurchaseReturnService;
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
 * 采购退货 信息操作处理
 * 
 * @author chaiwei
 * @date 2018-11-07
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/purchaseReturnBase")
@Api(tags = {"采购退货"})
public class PurchaseReturnController extends BaseController {
    
	@Autowired
	private IPurchaseReturnService purchaseReturnService;

	//@RequiresPermissions("bangdao:purchaseReturn:view")
	@GetMapping
	@ApiIgnore
	public Result purchaseReturnBase() {
		return success();
	}

	/**
	 * 查询采购退货基本列表
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:purchaseReturn:list")
	@PostMapping("/list")
	@ResponseBody
	@ApiOperation(value="当前用户任务列表", notes="获取当前用户任务列表")
	@ApiImplicitParam(name = "baseReq", value = "退货查询实体",required = true, dataType = "PurchaseReturnBaseReq")
	public TableDataInfo list(@RequestBody PurchaseReturnBaseReq baseReq) throws Exception {
		startPage();
		List<PurchaseReturnBaseResp> baseResps = purchaseReturnService.selectBaseList(baseReq);
		return getDataTable(baseResps);
	}


	/**
	 * 获取当前用户订单列表
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:purchasingWarehousing:list")
	@ApiOperation(value="当前用户订单列表", notes="获取当前用户订单列表")
	@ApiImplicitParam(name = "baseReq", value = "退货查询实体",required = true, dataType = "PurchaseReturnBaseReq")
	@PostMapping("/billList")
	@ResponseBody
	public TableDataInfo billList(@RequestBody PurchaseReturnBaseReq baseReq) throws Exception {
		startPage();
		List<PurchaseReturnBaseResp> baseResps = purchaseReturnService.selectBillList(baseReq);
		return getDataTable(baseResps);
	}

	/**
	 * 新增保存采购退货基本
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:purchaseReturn:add")
	@Log(title = "采购退货基本", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	@ApiOperation(value="新增", notes="新增")
	@ApiImplicitParam(name = "purchaseReturn", value = "新增实体",required = true, dataType = "PurchaseReturnReq")
	public Result addSave(@RequestBody @Validated PurchaseReturnReq purchaseReturn,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return purchaseReturnService.insertPurchaseReturn(purchaseReturn);
	}

	/**
	 * 提交采购入库
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:purchasingWarehousing:add")
	@Log(title = "采购退货基本", businessType = BusinessType.INSERT)
	@PostMapping("/submit")
	@ResponseBody
	@ApiOperation(value="提交", notes="提交采购退货（不用保存可以直接提交）")
	@ApiImplicitParam(name = "purchaseReturn", value = "提交实体",required = true, dataType = "PurchaseReturnReq")
	public Result addSubmit(@RequestBody @Validated PurchaseReturnReq purchaseReturn,
							BindingResult result) throws Exception {
		if (result.hasErrors()){
			return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return purchaseReturnService.submit(purchaseReturn);
	}

	/**
	 * 审批
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:purchasingWarehousing:add")
	@Log(title = "采购退货基本", businessType = BusinessType.UPDATE)
	@PostMapping("/approval")
	@ResponseBody
	@ApiOperation(value="审批", notes="退货审批")
	@ApiImplicitParam(name = "approval", value = "审批实体",required = true, dataType = "Approval")
	public Result approval(@RequestBody Approval approval,
						   BindingResult result) throws Exception {
		if (result.hasErrors()){
			return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return purchaseReturnService.approval(approval);
	}

	/**
	 * 查询采购退货基本详情
	 * @author chaiwei
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
	@ApiOperation(value="退货详情", notes="退货详情")
	@ApiImplicitParam(name = "id", value = "基本信息id",required = true, dataType = "int")
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return purchaseReturnService.selectPurchaseReturnById(id);
	}

	/**
	 * 修改保存采购退货基本
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:purchaseReturn:edit")
	@Log(title = "采购退货基本", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	@ApiOperation(value="修改", notes="修改")
	@ApiImplicitParam(name = "purchaseReturn", value = "修改实体",required = true, dataType = "PurchaseReturnReq")
	public Result editSave(@RequestBody @Validated PurchaseReturnReq purchaseReturn,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
	    return purchaseReturnService.updatePurchaseReturn(purchaseReturn);
	}

	/**
	 * 删除采购退货基本
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:purchaseReturn:remove")
	@Log(title = "采购退货基本", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	@ApiOperation(value="删除", notes="批量删除")
	@ApiImplicitParam(name = "ids", value = "基本信息id集合",required = true, dataType = "String")
	public Result remove(String ids) throws Exception {
		return purchaseReturnService.deletePurchaseReturnByIds(ids);
	}

	/**
	 * @Des: 采购退货查询
	 * @Author: xupj
	 * @Date: 2018/11/7 17:48
	 **/
	@PostMapping("/cgthcx/list")
	@ResponseBody
	@ApiOperation(value="获取审批通过列表", notes="获取审批通过列表")
	@ApiImplicitParam(name = "request", value = "查询集合",required = true, dataType = "PurchaseReturnBaseSearchReq")
	public TableDataInfo cgthcx(@RequestBody PurchaseReturnBaseSearchReq request) throws Exception {
		request.setApprovalStatus(Constants.ApprovalStatus.ADOPT_INT);
		return purchaseReturnService.selectCgthByCondition(request);
	}

}
