package com.bangdao.controller.essentialInfo;

import com.bangdao.domain.essentialInfo.DictionaryType;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.group.essentialInfo.dictionaryType.DictionaryTypeAdd;
import com.bangdao.group.essentialInfo.dictionaryType.DictionaryTypeUpdate;
import com.bangdao.requestVo.essentialInfo.DictionaryTypeRequest;
import com.bangdao.service.essentialInfo.IDictionaryTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典类型 信息操作处理
 * 
 * @author xupj
 * @date 2018-09-14
 */
@Controller
@RequestMapping("/module/dictionaryType")
public class DictionaryTypeController extends BaseController {

	@Autowired
	private IDictionaryTypeService dictionaryTypeService;

	@RequiresPermissions("module:dictionaryType:view")
	@GetMapping
	public Result dictionaryType() {
	    return success();
	}

	/**
	 * 查询字典类型列表
	 */
//	@RequiresPermissions("module:dictionaryType:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody DictionaryTypeRequest dictionaryType) {
		startPage();
        List<DictionaryType> list = dictionaryTypeService.selectDictionaryTypeList(dictionaryType);
		return getDataTable(list);
	}
	
	/**
	 * 新增保存字典类型
	 */
//	@RequiresPermissions("module:dictionaryType:add")
	@Log(title = "字典类型", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result addSave(@RequestBody @Validated({DictionaryTypeAdd.class}) DictionaryTypeRequest dictionaryType,
						  BindingResult result) throws Exception {
		if (result.hasErrors()){
			return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return dictionaryTypeService.insertDictionaryType(dictionaryType);
	}

	/**
	 * 查询字典类型详情
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
	public Result edit(@PathVariable("id") Integer id) {
	    return dictionaryTypeService.selectDictionaryTypeById(id);
	}

	/**
	 * 修改保存字典类型
	 */
//	@RequiresPermissions("module:dictionaryType:edit")
	@Log(title = "字典类型", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public Result editSave(@RequestBody @Validated({DictionaryTypeUpdate.class}) DictionaryTypeRequest dictionaryType,
						  BindingResult result) throws Exception {
		if (result.hasErrors()){
			return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return dictionaryTypeService.updateDictionaryType(dictionaryType);
	}
	
	/**
	 * 删除字典类型
	 */
//	@RequiresPermissions("module:dictionaryType:remove")
	@Log(title = "字典类型", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public Result remove(String ids) {
		return dictionaryTypeService.deleteDictionaryTypeByIds(ids);
	}
	
}
