package com.bangdao.controller.essentialInfo;

import com.bangdao.domain.essentialInfo.WarehouseInfo;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.group.essentialInfo.warehouseInfo.WarehouseInfoAdd;
import com.bangdao.group.essentialInfo.warehouseInfo.WarehouseInfoUpdate;
import com.bangdao.requestVo.essentialInfo.WarehouseInfoRequest;
import com.bangdao.service.essentialInfo.IWarehouseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 仓库 信息操作处理
 * 
 * @author chenshao
 * @date 2018-10-16
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/warehouseInfo")
public class WarehouseInfoController extends BaseController {
    
	@Autowired
	private IWarehouseInfoService warehouseInfoService;

	@RequiresPermissions("bangdao:warehouseInfo:view")
	@GetMapping
	public Result warehouseInfo() {
		return success();
	}

	/**
	 * 查询仓库列表
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:warehouseInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody WarehouseInfoRequest warehouseInfo) throws Exception {
		startPage();
        List<WarehouseInfo> list = warehouseInfoService.selectWarehouseInfoList(warehouseInfo);
		return getDataTable(list);
	}

	/**
	 * 新增保存仓库
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:warehouseInfo:add")
	@Log(title = "仓库", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result addSave(@RequestBody @Validated({WarehouseInfoAdd.class}) WarehouseInfoRequest warehouseInfo,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return warehouseInfoService.insertWarehouseInfo(warehouseInfo);
	}

	/**
	 * 查询仓库详情
	 * @author chenshao
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return warehouseInfoService.selectWarehouseInfoById(id);
	}

	/**
	 * 修改保存仓库
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:warehouseInfo:edit")
	@Log(title = "仓库", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public Result editSave(@RequestBody @Validated({WarehouseInfoUpdate.class}) WarehouseInfoRequest warehouseInfo,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
	    return warehouseInfoService.updateWarehouseInfo(warehouseInfo);
	}

	/**
	 * 删除仓库
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:warehouseInfo:remove")
	@Log(title = "仓库", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public Result remove(String ids) throws Exception {
		return warehouseInfoService.deleteWarehouseInfoByIds(ids);
	}

}
