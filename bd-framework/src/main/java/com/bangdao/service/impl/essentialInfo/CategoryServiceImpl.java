package com.bangdao.service.impl.essentialInfo;

import com.bangdao.common.utils.TreeUtils;
import com.bangdao.domain.essentialInfo.Category;
import com.bangdao.domain.system.Menu;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.mapper.essentialInfo.CategoryMapper;
import com.bangdao.requestVo.essentialInfo.CategoryRequest;
import com.bangdao.service.essentialInfo.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 类别 服务层实现
 * 
 * @author chaiwei
 * @date 2018-09-30
 */
@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
	private CategoryMapper categoryMapper;

	/**
     * 查询类别信息
     * @author chaiwei
     * @param id 类别ID
     * @return 类别信息
     */
    @Override
	public Result selectCategoryById(Integer id) throws Exception {
        if (ObjectUtils.isEmpty(id)){
            return Result.error("参数id不能为空");
        }
		Category data = categoryMapper.selectCategoryById(id);
	    return Result.success().put("category", data);
	}
	
	/**
     * 查询类别列表
     * @author chaiwei
     * @param category 类别信息
     * @return 类别集合
     */
	@Override
	public List<Category> selectCategoryList(CategoryRequest category) throws Exception {
		if(null == category.getStatus())
		{
			category.setStatus(1);
		}
	    return categoryMapper.selectCategoryList(category);
	}

	/**
	 * 获取类别树
	 * @author chaiwei
	 * @param category 类别信息
	 * @return 类别树
	 */
	public List<Menu> getCategoryTree(CategoryRequest category) throws Exception
	{
		List<Category>  categoryList = selectCategoryList(category);

		List<Menu> list = new ArrayList<Menu>();
		//将类别组装成树
		for (Category  commodity :  categoryList) {
			Menu menu = new Menu();
			//类别名称
			menu.setMenuName(commodity.getCommodityCategoryName());
			//类别父id
			menu.setParentId(Long.valueOf(commodity.getParentId()));
			//类别id
			menu.setMenuId(Long.valueOf(commodity.getId()));
			//类别排序
			menu.setOrderNum(commodity.getSort());
			//备注
            menu.setRemark(commodity.getRemark());
			//类别状态 1 显示 -1隐藏
			menu.setVisible(commodity.getStatus()+"");
			list.add(menu);
		}
		//获取列表树
		List<Menu> treeMenu= TreeUtils.getChildPerms(list,0);
		return treeMenu;
	}


    /**
     * 新增类别
     * @author chaiwei
     * @param category 类别信息
     * @return 结果
     */
	@Override
	public Result insertCategory(CategoryRequest category) throws Exception {
		Category temp = new Category();
        BeanUtils.copyProperties(category,temp);
        int count = categoryMapper.insertCategory(temp);
	    return count > 0 ? Result.success() : Result.error();
	}
	
	/**
     * 修改类别
     * @author chaiwei
     * @param category 类别信息
     * @return 结果
     */
	@Override
	public Result updateCategory(CategoryRequest category) throws Exception {
		Category temp = new Category();
        BeanUtils.copyProperties(category,temp);
        int count = categoryMapper.updateCategory(temp);
	    return count > 0 ? Result.success() : Result.error();
	}

	/**
     * 删除类别对象
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public Result deleteCategoryByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return Result.error("参数ids不能为空");
        }
		int count = categoryMapper.deleteCategoryByIds(ids.split(","));
		return count > 0 ? Result.success() : Result.error();
	}
	
}
