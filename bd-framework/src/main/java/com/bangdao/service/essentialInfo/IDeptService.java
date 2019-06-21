package com.bangdao.service.essentialInfo;

import com.bangdao.domain.essentialInfo.Dept;
import com.bangdao.domain.system.Menu;
import com.bangdao.requestVo.essentialInfo.DeptRequest;
import com.bangdao.framework.web.domain.Result;

import java.util.List;

/**
 * 部门 服务层
 * 
 * @author chaiwei
 * @date 2018-10-15
 */
public interface IDeptService {
	/**
     * 查询部门信息
     * @author chaiwei
     * @param deptId 部门ID
     * @return 部门信息
     */
	public Result selectDeptById(Integer deptId) throws Exception;
	
	/**
     * 查询部门列表
     * @author chaiwei
     * @param dept 部门信息
     * @return 部门集合
     */
	public List<Dept> selectDeptList(DeptRequest dept) throws Exception;


	/**
	 * 获取部门树
	 * @author chaiwei
	 * @param dept 部门信息
	 * @return 部门树
	 */
	public List<Menu> getCategoryTree(DeptRequest dept) throws Exception;



	/**
     * 新增部门
     * @author chaiwei
     * @param dept 部门信息
     * @return 结果
     */
	public Result insertDept(DeptRequest dept) throws Exception;
	
	/**
     * 修改部门
     * @author chaiwei
     * @param dept 部门信息
     * @return 结果
     */
	public Result updateDept(DeptRequest dept) throws Exception;
		
	/**
     * 删除部门信息
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteDeptByIds(String ids) throws Exception;
	
}
