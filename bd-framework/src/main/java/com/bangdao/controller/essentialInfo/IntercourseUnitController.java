package com.bangdao.controller.essentialInfo;

import com.bangdao.domain.essentialInfo.IntercourseUnit;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.group.essentialInfo.intercourseUnit.IntercourseUnitAdd;
import com.bangdao.group.essentialInfo.intercourseUnit.IntercourseUnitUpdate;
import com.bangdao.requestVo.essentialInfo.IntercourseUnitRequest;
import com.bangdao.responseVo.essentialInfo.IntercourseUnitResp;
import com.bangdao.service.essentialInfo.IIntercourseUnitService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 往来单位基本 信息操作处理
 * 
 * @author chaiwei
 * @date 2018-10-12
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/intercourseUnit")
public class IntercourseUnitController extends BaseController {
    
	@Autowired
	private IIntercourseUnitService intercourseUnitService;

	@RequiresPermissions("bangdao:intercourseUnit:view")
	@GetMapping
	public Result intercourseUnit() {
		return success();
	}

	/**
	 * 查询往来单位基本列表
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:intercourseUnit:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody IntercourseUnitRequest intercourseUnit) throws Exception {
		startPage();
        List<IntercourseUnitResp> list = intercourseUnitService.selectRelationList(intercourseUnit);
		return getDataTable(list);
	}

	/**
	 * 新增保存往来单位基本
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:intercourseUnit:add")
	@Log(title = "往来单位基本", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result addSave(@RequestBody @Validated({IntercourseUnitAdd.class}) IntercourseUnitRequest intercourseUnit,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return intercourseUnitService.insertIntercourseUnit(intercourseUnit);
	}

	/**
	 * 查询往来单位基本详情
	 * @author chaiwei
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return intercourseUnitService.selectIntercourseUnitById(id);
	}

	/**
	 * 修改保存往来单位基本
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:intercourseUnit:edit")
	@Log(title = "往来单位基本", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public Result editSave(@RequestBody @Validated({IntercourseUnitUpdate.class}) IntercourseUnitRequest intercourseUnit,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
	    return intercourseUnitService.updateIntercourseUnit(intercourseUnit);
	}

	/**
	 * 删除往来单位基本
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:intercourseUnit:remove")
	@Log(title = "往来单位基本", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public Result remove(String ids) throws Exception {
		return intercourseUnitService.deleteIntercourseUnitByIds(ids);
	}

}
