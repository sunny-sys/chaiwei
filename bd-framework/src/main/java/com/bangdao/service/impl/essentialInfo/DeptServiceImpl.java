package com.bangdao.service.impl.essentialInfo;

import com.bangdao.common.utils.TreeUtils;
import com.bangdao.domain.essentialInfo.Dept;
import com.bangdao.domain.system.Menu;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.mapper.essentialInfo.DeptMapper;
import com.bangdao.requestVo.essentialInfo.DeptRequest;
import com.bangdao.service.essentialInfo.IDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门 服务层实现
 * 
 * @author chaiwei
 * @date 2018-10-15
 */
@Slf4j
@Service
public class DeptServiceImpl implements IDeptService {

    @Autowired
	private DeptMapper deptMapper;

	/**
     * 查询部门信息
     * @author chaiwei
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
	public Result selectDeptById(Integer deptId) throws Exception {
        if (ObjectUtils.isEmpty(deptId)){
            return Result.error("参数deptId不能为空");
        }
		Dept data = deptMapper.selectDeptById(deptId);
	    return Result.success().put("dept", data);
	}
	
	/**
     * 查询部门列表
     * @author chaiwei
     * @param dept 部门信息
     * @return 部门集合
     */
	@Override
	public List<Dept> selectDeptList(DeptRequest dept) throws Exception {
		if (null == dept.getStatus())
		{
			dept.setStatus(1);
		}
	    return deptMapper.selectDeptList(dept);
	}

	/**
	 * 获取部门树
	 * @author chaiwei
	 * @param dept 部门信息
	 * @return 部门树
	 */
	public List<Menu> getCategoryTree(DeptRequest dept) throws Exception
	{
		List<Dept>  deptList = selectDeptList(dept);

		List<Menu> list = new ArrayList<Menu>();
		//将组装成树
		for (Dept  temp :  deptList) {
			Menu menu = new Menu();
			//名称
			menu.setMenuName(temp.getDeptName());
			//父id
			menu.setParentId(Long.valueOf(temp.getParentId()));
			//id
			menu.setMenuId(Long.valueOf(temp.getDeptId()));
			//排序
			menu.setOrderNum(temp.getSort()+"");
			//备注
			menu.setRemark(temp.getRemark());
			//状态 1 显示 -1隐藏
			menu.setVisible(temp.getStatus()+"");
			list.add(menu);
		}
		//获取列表树
		List<Menu> treeMenu= TreeUtils.getChildPerms(list,0);
		return treeMenu;
	}



    /**
     * 新增部门
     * @author chaiwei
     * @param dept 部门信息
     * @return 结果
     */
	@Override
	public Result insertDept(DeptRequest dept) throws Exception {
		Dept temp = new Dept();
        BeanUtils.copyProperties(dept,temp);
        int count = deptMapper.insertDept(temp);
	    return count > 0 ? Result.success() : Result.error();
	}
	
	/**
     * 修改部门
     * @author chaiwei
     * @param dept 部门信息
     * @return 结果
     */
	@Override
	public Result updateDept(DeptRequest dept) throws Exception {
		Dept temp = new Dept();
        BeanUtils.copyProperties(dept,temp);
        int count = deptMapper.updateDept(temp);
	    return count > 0 ? Result.success() : Result.error();
	}

	/**
     * 删除部门对象
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public Result deleteDeptByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return Result.error("参数ids不能为空");
        }
		int count = deptMapper.deleteDeptByIds(ids.split(","));
		return count > 0 ? Result.success() : Result.error();
	}
	
}
