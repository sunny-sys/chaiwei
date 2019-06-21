package com.bangdao.controller.essentialInfo;

import com.bangdao.domain.essentialInfo.Subject;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.group.essentialInfo.subject.SubjectAdd;
import com.bangdao.group.essentialInfo.subject.SubjectUpdate;
import com.bangdao.requestVo.essentialInfo.SubjectRequest;
import com.bangdao.service.essentialInfo.ISubjectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 科目 信息操作处理
 * 
 * @author chenshao
 * @date 2018-10-11
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/subject")
public class SubjectController extends BaseController {
    
	@Autowired
	private ISubjectService subjectService;

	@RequiresPermissions("bangdao:subject:view")
	@GetMapping
	public Result subject() {
		return success();
	}

	/**
	 * 查询科目列表
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:subject:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody SubjectRequest subject) throws Exception {
		startPage();
        List<Subject> list = subjectService.selectSubjectList(subject);
		return getDataTable(list);
	}

	/**
	 * 新增保存科目
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:subject:add")
	@Log(title = "科目", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result addSave(@RequestBody @Validated({SubjectAdd.class}) SubjectRequest subject,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return subjectService.insertSubject(subject);
	}

	/**
	 * 查询科目详情
	 * @author chenshao
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return subjectService.selectSubjectById(id);
	}

	/**
	 * 修改保存科目
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:subject:edit")
	@Log(title = "科目", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public Result editSave(@RequestBody @Validated({SubjectUpdate.class}) SubjectRequest subject,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
	    return subjectService.updateSubject(subject);
	}

	/**
	 * 删除科目
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:subject:remove")
	@Log(title = "科目", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public Result remove(int id) throws Exception {
		return subjectService.deleteSubjectById(id);
	}

}
