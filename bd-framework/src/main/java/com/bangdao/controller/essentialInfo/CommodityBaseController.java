package com.bangdao.controller.essentialInfo;

import com.bangdao.domain.essentialInfo.CommodityBase;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.group.essentialInfo.commodityBase.CommodityBaseAdd;
import com.bangdao.group.essentialInfo.commodityBase.CommodityBaseUpdate;
import com.bangdao.requestVo.essentialInfo.CommodityBaseRequest;
import com.bangdao.service.essentialInfo.ICommodityBaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品基本 信息操作处理
 * 
 * @author chaiwei
 * @date 2018-10-10
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/commodityBase")
public class CommodityBaseController extends BaseController {
    
	@Autowired
	private ICommodityBaseService commodityBaseService;

	@RequiresPermissions("bangdao:commodityBase:view")
	@GetMapping
	public Result commodityBase() {
		return success();
	}

	/**
	 * 查询商品基本列表
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:commodityBase:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody CommodityBaseRequest commodityBase) throws Exception {
		startPage();
        List<CommodityBase> list = commodityBaseService.selectCommodityBaseList(commodityBase);
		return getDataTable(list);
	}

	/**
	 * 新增保存商品基本
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:commodityBase:add")
	@Log(title = "商品基本", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result addSave(@RequestBody @Validated({CommodityBaseAdd.class}) CommodityBaseRequest commodityBase,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return commodityBaseService.insertCommodityBase(commodityBase);
	}

	/**
	 * 查询商品基本详情
	 * @author chaiwei
	 */
	@GetMapping("/edit/{id}")
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return commodityBaseService.selectCommodityBaseById(id);
	}

	/**
	 * 修改保存商品基本
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:commodityBase:edit")
	@Log(title = "商品基本", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public Result editSave(@RequestBody @Validated({CommodityBaseUpdate.class}) CommodityBaseRequest commodityBase,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
	    return commodityBaseService.updateCommodityBase(commodityBase);
	}

	/**
	 * 删除商品基本
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:commodityBase:remove")
	@Log(title = "商品基本", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public Result remove(String ids) throws Exception {
		return commodityBaseService.deleteCommodityBaseByIds(ids);
	}

}
