package com.bangdao.controller.system;

import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.requestVo.system.WarehouseReq;
import com.bangdao.responseVo.system.WarehouseResp;
import com.bangdao.service.system.IWarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 仓库 信息操作处理
 * 
 * @author chaiwei
 * @date 2018-12-18
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/warehouse")
@Api(tags = {"仓库接口"})
public class WarehouseController extends BaseController {
    
	@Autowired
	private IWarehouseService warehouseService;

	//@RequiresPermissions("bangdao:warehouse:view")
	@GetMapping
	@ApiIgnore
	public Result warehouse() {
		return success();
	}

	/**
	 * 查询仓库列表
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:warehouse:list")
	@PostMapping("/list")
	@ResponseBody
	@ApiOperation(value="列表", notes="获取仓库列表")
	@ApiImplicitParam(name = "warehouse", value = "查询实体",required = true, dataType = "WarehouseReq")
	public TableDataInfo list(@RequestBody WarehouseReq warehouse) throws Exception {
		startPage();
        List<WarehouseResp> list = warehouseService.selectWarehouseList(warehouse);
		return getDataTable(list);
	}

	/**
	 * 新增保存仓库
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:warehouse:add")
	@Log(title = "仓库", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	@ApiIgnore
	@ApiOperation(value="新增", notes="新增")
	@ApiImplicitParam(name = "warehouse", value = "新增实体",required = true, dataType = "WarehouseReq")
	public Result addSave(@RequestBody  WarehouseReq warehouse,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return warehouseService.insertWarehouse(warehouse);
	}

	/**
	 * 查询仓库详情
	 * @author chaiwei
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
	@ApiOperation(value="查看详情", notes="获取仓库详情")
	@ApiImplicitParam(name = "id", value = "仓库id",required = true, dataType = "String")
	public Result edit(@PathVariable("id") String id) throws Exception {
		return warehouseService.selectWarehouseById(id);
	}

	/**
	 * 修改保存仓库
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:warehouse:edit")
	@Log(title = "仓库", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	@ApiIgnore
	@ApiOperation(value="修改", notes="修改实体")
	@ApiImplicitParam(name = "warehouse", value = "修改实体",required = true, dataType = "WarehouseReq")
	public Result editSave(@RequestBody WarehouseReq warehouse,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
	    return warehouseService.updateWarehouse(warehouse);
	}

	/**
	 * 删除仓库
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:warehouse:remove")
	@Log(title = "仓库", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	@ApiIgnore
	@ApiOperation(value="删除", notes="删除")
	@ApiImplicitParam(name = "ids", value = "仓库id集合（用,分割如“12,45”）",required = true, dataType = "String")
	public Result remove(String ids) throws Exception {
		return warehouseService.deleteWarehouseByIds(ids);
	}

}
