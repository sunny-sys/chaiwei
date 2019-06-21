package com.bangdao.service.essentialInfo;

import com.bangdao.domain.essentialInfo.Category;
import com.bangdao.domain.system.Menu;
import com.bangdao.requestVo.essentialInfo.CategoryRequest;
import com.bangdao.framework.web.domain.Result;

import java.util.List;

/**
 * 类别 服务层
 * 
 * @author chaiwei
 * @date 2018-09-30
 */
public interface ICategoryService {
	/**
     * 查询类别信息
     * @author chaiwei
     * @param id 类别ID
     * @return 类别信息
     */
	public Result selectCategoryById(Integer id) throws Exception;
	
	/**
     * 查询类别列表
     * @author chaiwei
     * @param category 类别信息
     * @return 类别集合
     */
	public List<Category> selectCategoryList(CategoryRequest category) throws Exception;

	/**
	 * 获取类别树
	 * @author chaiwei
	 * @param category 类别信息
	 * @return 类别树
	 */
	public List<Menu> getCategoryTree(CategoryRequest category) throws Exception;


	/**
     * 新增类别
     * @author chaiwei
     * @param category 类别信息
     * @return 结果
     */
	public Result insertCategory(CategoryRequest category) throws Exception;
	
	/**
     * 修改类别
     * @author chaiwei
     * @param category 类别信息
     * @return 结果
     */
	public Result updateCategory(CategoryRequest category) throws Exception;
		
	/**
     * 删除类别信息
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteCategoryByIds(String ids) throws Exception;
	
}
