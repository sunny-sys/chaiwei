package com.bangdao.controller.essentialInfo;

import com.bangdao.domain.essentialInfo.Areas;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.group.essentialInfo.areas.AreasAdd;
import com.bangdao.group.essentialInfo.areas.AreasUpdate;
import com.bangdao.requestVo.essentialInfo.AreasRequest;
import com.bangdao.service.essentialInfo.IAreasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 行政区域县区 信息操作处理
 * 
 * @author xupj
 * @date 2018-09-17
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/areas")
public class AreasController extends BaseController {
    
	@Autowired
	private IAreasService areasService;

//	@RequiresPermissions("bangdao:areas:view")
	@GetMapping
	public Result areas() {
		return success();
	}

	/**
	 * 查询行政区域县区列表
	 * @author xupj
	 */
//	@RequiresPermissions("bangdao:areas:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody AreasRequest areas) throws Exception {
		startPage();
        List<Areas> list = areasService.selectAreasList(areas);
		return getDataTable(list);
	}

	/**
	 * 新增保存行政区域县区
	 * @author xupj
	 */
//	@RequiresPermissions("bangdao:areas:add")
	@Log(title = "行政区域县区", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result addSave(@RequestBody @Validated({AreasAdd.class}) AreasRequest areas,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return areasService.insertAreas(areas);
	}

	/**
	 * 查询行政区域县区详情
	 * @author xupj
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return areasService.selectAreasById(id);
	}

	/**
	 * 修改保存行政区域县区
	 * @author xupj
	 */
//	@RequiresPermissions("bangdao:areas:edit")
	@Log(title = "行政区域县区", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public Result editSave(@RequestBody @Validated({AreasUpdate.class}) AreasRequest areas,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
	    return areasService.updateAreas(areas);
	}

	/**
	 * 删除行政区域县区
	 * @author xupj
	 */
//	@RequiresPermissions("bangdao:areas:remove")
	@Log(title = "行政区域县区", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public Result remove(String ids) throws Exception {
		return areasService.deleteAreasByIds(ids);
	}

	/**
	 * 根据城市的code查询该城市下的县区
	 */
	@GetMapping("/data/{parentid}")
	@ResponseBody
	public TableDataInfo dataCityid(@PathVariable("parentid") String parentid) throws Exception {
		startPage();
		List<Areas> list = areasService.selectAreasListByCityid(parentid);
		return getDataTable(list);
	}
}
