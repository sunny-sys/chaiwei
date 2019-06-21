package com.bangdao.controller.outWarehouse;

import com.bangdao.common.constant.Constants;
import com.bangdao.common.utils.security.ShiroUtils;
import com.bangdao.domain.approval.Approval;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.requestVo.outWarehouse.OutWarehouseBaseReq;
import com.bangdao.requestVo.outWarehouse.OutWarehouseReq;
import com.bangdao.responseVo.outWarehouse.OutWarehouseBaseResp;
import com.bangdao.service.outWarehouse.IOutWarehouseService;
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
 * 出库 信息操作处理
 * 
 * @author chaiwei
 * @date 2018-11-07
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/outWarehouse")
@Api(tags = {"出库"})
public class OutWarehouseController extends BaseController {
    
	@Autowired
	private IOutWarehouseService outWarehouseService;

	//@RequiresPermissions("bangdao:outWarehouse:view")
	@GetMapping
	@ApiIgnore
	public Result outWarehouseBase() {
		return success();
	}

	/**
	 * 查询出库个人任务列表
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:outWarehouse:list")
	@PostMapping("/list")
	@ResponseBody
	@ApiOperation(value = "个人任务列表",notes = "个人任务列表")
	@ApiImplicitParam(name = "base",value = "查询实体",required = true,dataType = "OutWarehouseBaseReq")
	public TableDataInfo list(@RequestBody OutWarehouseBaseReq base) throws Exception {
		startPage();
		List<OutWarehouseBaseResp> baseResps = outWarehouseService.selectBaseList(base);
		return getDataTable(baseResps);
	}

	/**
	 * 查询出库个人订单列表
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:outWarehouse:list")
	@PostMapping("/billList")
	@ResponseBody
	@ApiOperation(value = "个人订单列表",notes = "个人订单列表")
	@ApiImplicitParam(name = "base",value = "查询实体",required = true,dataType = "OutWarehouseBaseReq")
	public TableDataInfo billList(@RequestBody OutWarehouseBaseReq base) throws Exception {
		startPage();
		base.setCreateBy(ShiroUtils.getUserId()+"");
		List<OutWarehouseBaseResp> baseResps = outWarehouseService.billList(base);
		return getDataTable(baseResps);
	}

	/**
	 * 新增保存出库基本
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:outWarehouse:add")
	@Log(title = "出库基本", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
    @ApiOperation(value = "新增",notes = "新增")
    @ApiImplicitParam(name = "outWarehouse",value = "新增实体",required = true,dataType = "OutWarehouseReq")
    public Result addSave(@RequestBody @Validated OutWarehouseReq outWarehouse,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		outWarehouse.getOutWarehouseReq().setApprovalStatus(Constants.ApprovalStatus.DRAFT_INT);
		return outWarehouseService.insertOutWarehouse(outWarehouse);
	}

	/**
	 * 提交
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:outWarehouse:add")
	@Log(title = "出库基本", businessType = BusinessType.INSERT)
	@PostMapping("/submit")
	@ResponseBody
    @ApiOperation(value = "提交",notes = "提交")
    @ApiImplicitParam(name = "outWarehouse",value = "提交新增实体",required = true,dataType = "OutWarehouseReq")
    public Result submit(@RequestBody @Validated OutWarehouseReq outWarehouse,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return outWarehouseService.submit(outWarehouse);
	}

	/**
	 * 审批
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:outWarehouse:add")
	@Log(title = "出库基本", businessType = BusinessType.UPDATE)
	@PostMapping("/approval")
	@ResponseBody
    @ApiOperation(value = "审批",notes = "审批")
	@ApiImplicitParam(name = "approval", value = "审批实体",required = true, dataType = "Approval")
    public Result approval(@RequestBody Approval approval,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return outWarehouseService.approval(approval);
	}

	/**
	 * 查询出库基本详情
	 * @author chaiwei
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
    @ApiOperation(value = "详情",notes = "详情")
    @ApiImplicitParam(name = "id",value = "基本信息id",required = true,dataType = "int")
    public Result edit(@PathVariable("id") Integer id) throws Exception {
		return outWarehouseService.selectOutWarehouseById(id);
	}

	/**
	 * 修改保存出库基本
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:outWarehouse:edit")
	@Log(title = "出库基本", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
    @ApiOperation(value = "修改",notes = "修改")
    @ApiImplicitParam(name = "outWarehouse",value = "修改实体",required = true,dataType = "OutWarehouseReq")
    public Result editSave(@RequestBody @Validated OutWarehouseReq outWarehouse,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
		outWarehouse.getOutWarehouseReq().setApprovalStatus(Constants.ApprovalStatus.DRAFT_INT);
	    return outWarehouseService.updateOutWarehouse(outWarehouse);
	}

	/**
	 * 删除出库基本
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:outWarehouse:remove")
	@Log(title = "出库基本", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
    @ApiOperation(value = "删除",notes = "批量删除")
    @ApiImplicitParam(name = "ids",value = "基本信息id集合",required = true,dataType = "String")
    public Result remove(String ids) throws Exception {
		return outWarehouseService.deleteOutWarehouseByIds(ids);
	}

	@PostMapping("/adoptList")
	@ResponseBody
	@ApiOperation(value="获取审批通过列表", notes="获取审批通过列表")
	@ApiImplicitParam(name = "req", value = "查询集合",required = true, dataType = "OutWarehouseBaseReq")
	public TableDataInfo adoptList(@RequestBody OutWarehouseBaseReq req) throws Exception {
		startPage();
		req.setApprovalStatus(Constants.ApprovalStatus.ADOPT_INT);
		List<OutWarehouseBaseResp> baseResps = outWarehouseService.billList(req);
		return getDataTable(baseResps);
	}

}
