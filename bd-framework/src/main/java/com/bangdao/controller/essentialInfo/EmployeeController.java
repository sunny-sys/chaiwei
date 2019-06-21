package com.bangdao.controller.essentialInfo;

import com.bangdao.domain.essentialInfo.Employee;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.group.essentialInfo.employee.EmployeeAdd;
import com.bangdao.group.essentialInfo.employee.EmployeeUpdate;
import com.bangdao.requestVo.essentialInfo.EmployeeRequest;
import com.bangdao.service.essentialInfo.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工 信息操作处理
 * 
 * @author xupj
 * @date 2018-10-13
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/employee")
public class EmployeeController extends BaseController {
    
	@Autowired
	private IEmployeeService employeeService;

	@RequiresPermissions("bangdao:employee:view")
	@GetMapping
	public Result employee() {
		return success();
	}

	/**
	 * 查询员工列表
	 * @author xupj
	 */
	//@RequiresPermissions("bangdao:employee:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody EmployeeRequest employee) throws Exception {
		startPage();
        List<Employee> list = employeeService.selectEmployeeList(employee);
		return getDataTable(list);
	}

	/**
	 * 新增保存员工
	 * @author xupj
	 */
	//@RequiresPermissions("bangdao:employee:add")
	@Log(title = "员工", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result addSave(@RequestBody @Validated({EmployeeAdd.class}) EmployeeRequest employee,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return employeeService.insertEmployee(employee);
	}

	/**
	 * 查询员工详情
	 * @author xupj
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return employeeService.selectEmployeeById(id);
	}

	/**
	 * 修改保存员工
	 * @author xupj
	 */
	//@RequiresPermissions("bangdao:employee:edit")
	@Log(title = "员工", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public Result editSave(@RequestBody @Validated({EmployeeUpdate.class}) EmployeeRequest employee,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
	    return employeeService.updateEmployee(employee);
	}

	/**
	 * 删除员工
	 * @author xupj
	 */
	//@RequiresPermissions("bangdao:employee:remove")
	@Log(title = "员工", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public Result remove(String ids) throws Exception {
		return employeeService.deleteEmployeeByIds(ids);
	}

}
