package com.bangdao.controller.essentialInfo;

import com.bangdao.domain.essentialInfo.Enclosure;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.group.essentialInfo.enclosure.EnclosureAdd;
import com.bangdao.group.essentialInfo.enclosure.EnclosureUpdate;
import com.bangdao.requestVo.essentialInfo.EnclosureRequest;
import com.bangdao.service.essentialInfo.IEnclosureService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 附件 信息操作处理
 * 
 * @author xml
 * @date 2018-09-18
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/enclosure")
public class EnclosureController extends BaseController {
    
	@Autowired
	private IEnclosureService enclosureService;

	@RequiresPermissions("bangdao:enclosure:view")
	@GetMapping
	public Result enclosure() {
		return success();
	}

	/**
	 * 查询附件列表
	 * @author xml
	 */
	@RequiresPermissions("bangdao:enclosure:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody EnclosureRequest enclosure) throws Exception {
		startPage();
        List<Enclosure> list = enclosureService.selectEnclosureList(enclosure);
		return getDataTable(list);
	}

	/**
	 * 新增保存附件
	 * @author xml
	 */
	@RequiresPermissions("bangdao:enclosure:add")
	@Log(title = "附件", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result addSave(@RequestBody @Validated({EnclosureAdd.class}) EnclosureRequest enclosure,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return enclosureService.insertEnclosure(enclosure);
	}

	/**
	 * 查询附件详情
	 * @author xml
	 */
	@GetMapping("/edit/{id}")
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return enclosureService.selectEnclosureById(id);
	}

	/**
	 * 修改保存附件
	 * @author xml
	 */
	@RequiresPermissions("bangdao:enclosure:edit")
	@Log(title = "附件", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public Result editSave(@RequestBody @Validated({EnclosureUpdate.class}) EnclosureRequest enclosure,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
	    return enclosureService.updateEnclosure(enclosure);
	}

	/**
	 * 删除附件
	 * @author xml
	 */
	@RequiresPermissions("bangdao:enclosure:remove")
	@Log(title = "附件", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public Result remove(String ids) throws Exception {
		return enclosureService.deleteEnclosureByIds(ids);
	}

}
