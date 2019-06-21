package com.bangdao.controller.essentialInfo;

import com.bangdao.domain.essentialInfo.Provinces;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.group.essentialInfo.provinces.ProvincesAdd;
import com.bangdao.group.essentialInfo.provinces.ProvincesUpdate;
import com.bangdao.requestVo.essentialInfo.ProvincesRequest;
import com.bangdao.service.essentialInfo.IProvincesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 省份 信息操作处理
 * 
 * @author xupj
 * @date 2018-09-16
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/provinces")
public class ProvincesController extends BaseController {
    
	@Autowired
	private IProvincesService provincesService;

//	@RequiresPermissions("bangdao:provinces:view")
	@GetMapping
	public Result provinces() {
		return success();
	}

	/**
	 * 查询省份列表
	 * @author xupj
	 */
//	@RequiresPermissions("bangdao:provinces:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody ProvincesRequest provinces) throws Exception {
		startPage();
        List<Provinces> list = provincesService.selectProvincesList(provinces);
		return getDataTable(list);
	}

	/**
	 * 新增保存省份
	 * @author xupj
	 */
//	@RequiresPermissions("bangdao:provinces:add")
	@Log(title = "省份", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result addSave(@RequestBody @Validated({ProvincesAdd.class}) ProvincesRequest provinces,
						  BindingResult result) throws Exception {
		if (result.hasErrors()){
			return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return provincesService.insertProvinces(provinces);
	}

	/**
	 * 查询省份详情
	 * @author xupj
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return provincesService.selectProvincesById(id);
	}

	/**
	 * 修改保存省份
	 * @author xupj
	 */
//	@RequiresPermissions("bangdao:provinces:edit")
	@Log(title = "省份", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public Result editSave(@RequestBody @Validated({ProvincesUpdate.class}) ProvincesRequest provinces,
						   BindingResult result) throws Exception {
		if (result.hasErrors()){
			return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return provincesService.updateProvinces(provinces);
	}

	/**
	 * 删除省份
	 * @author xupj
	 */
//	@RequiresPermissions("bangdao:provinces:remove")
	@Log(title = "省份", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public Result remove(String ids) throws Exception {
		return provincesService.deleteProvincesByIds(ids);
	}

}
