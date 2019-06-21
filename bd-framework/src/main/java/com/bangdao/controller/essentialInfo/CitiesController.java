package com.bangdao.controller.essentialInfo;

import com.bangdao.domain.essentialInfo.Cities;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.group.essentialInfo.cities.CitiesAdd;
import com.bangdao.group.essentialInfo.cities.CitiesUpdate;
import com.bangdao.requestVo.essentialInfo.CitiesRequest;
import com.bangdao.service.essentialInfo.ICitiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 行政区域地州市 信息操作处理
 * 
 * @author xupj
 * @date 2018-09-17
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/cities")
public class CitiesController extends BaseController {
    
	@Autowired
	private ICitiesService citiesService;

//	@RequiresPermissions("bangdao:cities:view")
	@GetMapping
	public Result cities() {
		return success();
	}

	/**
	 * 查询行政区域地州市列表
	 * @author xupj
	 */
//	@RequiresPermissions("bangdao:cities:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody CitiesRequest cities) throws Exception {
		startPage();
        List<Cities> list = citiesService.selectCitiesList(cities);
		return getDataTable(list);
	}

	/**
	 * 新增保存行政区域地州市
	 * @author xupj
	 */
//	@RequiresPermissions("bangdao:cities:add")
	@Log(title = "行政区域地州市", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result addSave(@RequestBody @Validated({CitiesAdd.class}) CitiesRequest cities,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return citiesService.insertCities(cities);
	}

	/**
	 * 查询行政区域地州市详情
	 * @author xupj
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return citiesService.selectCitiesById(id);
	}

	/**
	 * 修改保存行政区域地州市
	 * @author xupj
	 */
//	@RequiresPermissions("bangdao:cities:edit")
	@Log(title = "行政区域地州市", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public Result editSave(@RequestBody @Validated({CitiesUpdate.class}) CitiesRequest cities,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
	    return citiesService.updateCities(cities);
	}

	/**
	 * 删除行政区域地州市
	 * @author xupj
	 */
//	@RequiresPermissions("bangdao:cities:remove")
	@Log(title = "行政区域地州市", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public Result remove(String ids) throws Exception {
		return citiesService.deleteCitiesByIds(ids);
	}

	/**
	 * 根据省份的code查询该省份下的城市
	 */
	@GetMapping("/data/{parentid}")
	@ResponseBody
	public TableDataInfo dataProvinces(@PathVariable("parentid") String parentid) throws Exception {
		startPage();
		List<Cities> list = citiesService.selectCitiesListByProvinces(parentid);
		return getDataTable(list);
	}

}
