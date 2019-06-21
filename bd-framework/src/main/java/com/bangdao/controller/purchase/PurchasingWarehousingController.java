package com.bangdao.controller.purchase;

import com.bangdao.common.constant.Constants;
import com.bangdao.domain.approval.Approval;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.requestVo.purchase.PurchasingWarehousingBaseRequest;
import com.bangdao.requestVo.purchase.PurchasingWarehousingRequest;
import com.bangdao.requestVo.purchase.PurchasingWarehousingSearchReq;
import com.bangdao.responseVo.purchasing.PurchasingWarehousingBaseResponse;
import com.bangdao.service.purchase.IPurchasingWarehousingService;
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
 * 采购入库 信息操作处理
 *
 * @author chaiwei
 * @date 2018-10-25
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/purchasingWarehousing")
@Api(tags ={"采购入库"})//接口说明
public class PurchasingWarehousingController extends BaseController {

	@Autowired
	private IPurchasingWarehousingService purchasingWarehousingService;

	//@RequiresPermissions("bangdao:purchasingWarehousing:view")
	@GetMapping
	@ApiIgnore//忽略接口
	public Result purchasingWarehousing() {
		return success();
	}

	/**
	 * 查询当前用户任务列表
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:purchasingWarehousing:list")
	@ApiOperation(value="获取当前用户任务列表", notes="获取当前用户任务列表")
	@ApiImplicitParam(name = "baseRequest", value = "入库查询实体",required = true, dataType = "PurchasingWarehousingBaseRequest")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody PurchasingWarehousingBaseRequest baseRequest) throws Exception {
		startPage();
		List<PurchasingWarehousingBaseResponse> baseResponsesList = purchasingWarehousingService.selectBaseList(baseRequest);
		return getDataTable(baseResponsesList);
	}


	/**
	 * 获取当前用户订单列表
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:purchasingWarehousing:list")
	@ApiOperation(value="获取当前用户订单列表", notes="获取当前用户订单列表")
	@ApiImplicitParam(name = "baseRequest", value = "入库查询实体",required = true, dataType = "PurchasingWarehousingBaseRequest")
	@PostMapping("/billList")
	@ResponseBody
	public TableDataInfo billList(@RequestBody PurchasingWarehousingBaseRequest baseRequest) throws Exception {
		startPage();
		List<PurchasingWarehousingBaseResponse> baseResponsesList = purchasingWarehousingService.selectBillList(baseRequest);
		return getDataTable(baseResponsesList);
	}

	/**
	 * 新增保存采购入库基本
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:purchasingWarehousing:add")
	@Log(title = "采购入库基本", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	@ApiOperation(value="新增", notes="新增")
	@ApiImplicitParam(name = "purchasingWarehousing", value = "新增实体",required = true, dataType = "PurchasingWarehousingRequest")
	public Result addSave(@RequestBody @Validated PurchasingWarehousingRequest purchasingWarehousing,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return purchasingWarehousingService.insertPurchasingWarehousing(purchasingWarehousing);
	}

	/**
	 * 提交采购入库
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:purchasingWarehousing:add")
	@Log(title = "采购入库基本", businessType = BusinessType.INSERT)
	@PostMapping("/submit")
	@ResponseBody
	@ApiOperation(value="提交采购入库", notes="提交采购入库（不用保存可以直接提交）")
	@ApiImplicitParam(name = "purchasingWarehousing", value = "提交实体",required = true, dataType = "PurchasingWarehousingRequest")
	public Result addSubmit(@RequestBody @Validated PurchasingWarehousingRequest purchasingWarehousing,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return purchasingWarehousingService.submitPurchasingWarehousing(purchasingWarehousing);
	}


	/**
	 * 审批
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:purchasingWarehousing:add")
	@Log(title = "采购入库基本", businessType = BusinessType.INSERT)
	@PostMapping("/approval")
	@ResponseBody
	@ApiOperation(value="采购入库审批", notes="采购入库审批")
	@ApiImplicitParam(name = "approval", value = "审批实体",required = true, dataType = "Approval")
	public Result approval(@RequestBody Approval approval,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return purchasingWarehousingService.approvalPurchasingWarehousing(approval);
	}

	/**
	 * 查询采购入库基本详情
	 * @author chaiwei
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
	@ApiOperation(value="查询详情", notes="查询详情")
	@ApiImplicitParam(name = "id", value = "基本信息id",required = true, dataType = "int")
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return purchasingWarehousingService.selectPurchasingWarehousingById(id);
	}

	/**
	 * 修改保存采购入库基本
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:purchasingWarehousing:edit")
	@Log(title = "采购入库基本", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	@ApiOperation(value="修改", notes="修改")
	@ApiImplicitParam(name = "purchasingWarehousing", value = "修改实体",required = true, dataType = "PurchasingWarehousingRequest")
	public Result editSave(@RequestBody @Validated PurchasingWarehousingRequest purchasingWarehousing,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
		return purchasingWarehousingService.updatePurchasingWarehousing(purchasingWarehousing);
	}

	/**
	 * 删除采购入库基本
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:purchasingWarehousing:remove")
	@Log(title = "采购入库基本", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	@ApiOperation(value="删除", notes="批量删除")
	@ApiImplicitParam(name = "ids", value = "基本信息id集合",required = true, dataType = "String")
	public Result remove(String ids) throws Exception {
		return purchasingWarehousingService.deletePurchasingWarehousingByIds(ids);
	}

	/**
	 * @Des: 采购入库查询
	 * @Author: xupj
	 * @Date: 2018/11/7 17:48
	 **/
	@PostMapping("/cgrkcx/list")
	@ResponseBody
	@ApiOperation(value="获取审批通过列表", notes="获取审批通过列表")
	@ApiImplicitParam(name = "req", value = "查询集合",required = true, dataType = "PurchasingWarehousingSearchReq")
	public TableDataInfo cgrkcx(@RequestBody PurchasingWarehousingSearchReq req) throws Exception {
		req.setApprovalStatus(Constants.ApprovalStatus.ADOPT_INT);
		return purchasingWarehousingService.selectCgrkByCondition(req);
	}
}
