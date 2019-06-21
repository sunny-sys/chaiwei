package com.bangdao.controller.essentialInfo;

import com.bangdao.domain.essentialInfo.Category;
import com.bangdao.domain.system.Menu;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.group.essentialInfo.category.CategoryAdd;
import com.bangdao.group.essentialInfo.category.CategoryUpdate;
import com.bangdao.requestVo.essentialInfo.CategoryRequest;
import com.bangdao.service.essentialInfo.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类别 信息操作处理
 * 
 * @author chaiwei
 * @date 2018-09-30
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/category")
public class CategoryController extends BaseController {
    
	@Autowired
	private ICategoryService categoryService;

	@RequiresPermissions("bangdao:category:view")
	@GetMapping
	public Result category() {
		return success();
	}

	/**
	 * 查询类别列表
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:category:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody CategoryRequest category) throws Exception {
		startPage();
        List<Category> list = categoryService.selectCategoryList(category);
		return getDataTable(list);
	}

	/**
	 * 获取类别树
	 * @author chaiwei
	 */
	@PostMapping("/getCategoryTree")
	@ResponseBody
	public Result getCategoryTree(@RequestBody CategoryRequest category) throws Exception {
		List<Menu> list = categoryService.getCategoryTree(category);
		return Result.success().put("categoryTree", list);
	}

	/**
	 * 新增保存类别
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:category:add")
	@Log(title = "类别", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result addSave(@RequestBody @Validated({CategoryAdd.class}) CategoryRequest category,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return categoryService.insertCategory(category);
	}

	/**
	 * 查询类别详情
	 * @author chaiwei
	 */
	@GetMapping("/edit/{id}")
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return categoryService.selectCategoryById(id);
	}

	/**
	 * 修改保存类别
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:category:edit")
	@Log(title = "类别", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public Result editSave(@RequestBody @Validated({CategoryUpdate.class}) CategoryRequest category,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
	    return categoryService.updateCategory(category);
	}

	/**
	 * 删除类别
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:category:remove")
	@Log(title = "类别", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public Result remove(String ids) throws Exception {
		return categoryService.deleteCategoryByIds(ids);
	}

}
