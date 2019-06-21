package com.bangdao.controller.essentialInfo;

import com.bangdao.domain.essentialInfo.Dept;
import com.bangdao.domain.system.Menu;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.group.essentialInfo.dept.DeptAdd;
import com.bangdao.group.essentialInfo.dept.DeptUpdate;
import com.bangdao.requestVo.essentialInfo.DeptRequest;
import com.bangdao.service.essentialInfo.IDeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门 信息操作处理
 * 
 * @author chaiwei
 * @date 2018-10-15
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/dept")
public class DeptController extends BaseController {
    
	@Autowired
	private IDeptService deptService;

	@RequiresPermissions("bangdao:dept:view")
	@GetMapping
	public Result dept() {
		return success();
	}

	/**
	 * 查询部门列表
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:dept:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody DeptRequest dept) throws Exception {
		startPage();
        List<Dept> list = deptService.selectDeptList(dept);
		return getDataTable(list);
	}

	/**
	 * 获取部门树
	 * @author chaiwei
	 */
	@PostMapping("/getDeptTree")
	@ResponseBody
	public Result getCategoryTree(@RequestBody DeptRequest dept) throws Exception {
		List<Menu> list = deptService.getCategoryTree(dept);
		return Result.success().put("deptTree", list);
	}


	/**
	 * 新增保存部门
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:dept:add")
	@Log(title = "部门", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result addSave(@RequestBody @Validated({DeptAdd.class}) DeptRequest dept,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return deptService.insertDept(dept);
	}

	/**
	 * 查询部门详情
	 * @author chaiwei
	 */
	@GetMapping("/edit/{deptId}")
	@ResponseBody
	public Result edit(@PathVariable("deptId") Integer deptId) throws Exception {
		return deptService.selectDeptById(deptId);
	}

	/**
	 * 修改保存部门
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:dept:edit")
	@Log(title = "部门", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public Result editSave(@RequestBody @Validated({DeptUpdate.class}) DeptRequest dept,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
	    return deptService.updateDept(dept);
	}

	/**
	 * 删除部门
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:dept:remove")
	@Log(title = "部门", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public Result remove(String ids) throws Exception {
		return deptService.deleteDeptByIds(ids);
	}

}
