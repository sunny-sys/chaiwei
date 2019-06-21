package com.bangdao.mapper.essentialInfo;

import com.bangdao.domain.essentialInfo.Category;
import com.bangdao.requestVo.essentialInfo.CategoryRequest;
import java.util.List;	

/**
 * 类别 数据层
 * 
 * @author chaiwei
 * @date 2018-09-30
 */
public interface CategoryMapper {
	/**
     * 查询类别信息
     * @author chaiwei
     * @param id 类别ID
     * @return 类别信息
     */
	public Category selectCategoryById(Integer id);

	/**
     * 查询类别列表
     * @author chaiwei
     * @param category 类别信息
     * @return 类别集合
     */
	public List<Category> selectCategoryList(CategoryRequest category);
	
	/**
     * 新增类别
     * @author chaiwei
     * @param category 类别信息
     * @return 结果
     */
	public int insertCategory(Category category);
	
	/**
     * 修改类别
     * @author chaiwei
     * @param category 类别信息
     * @return 结果
     */
	public int updateCategory(Category category);
	
	/**
     * 删除类别
     * @author chaiwei
     * @param id 类别ID
     * @return 结果
     */
	public int deleteCategoryById(Integer id);
	
	/**
     * 批量删除类别
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCategoryByIds(String[] ids);
	
}