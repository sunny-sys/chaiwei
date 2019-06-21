package com.bangdao.mapper.essentialInfo;

import com.bangdao.domain.essentialInfo.Dept;
import com.bangdao.requestVo.essentialInfo.DeptRequest;
import java.util.List;	

/**
 * 部门 数据层
 * 
 * @author chaiwei
 * @date 2018-10-15
 */
public interface DeptMapper {
	/**
     * 查询部门信息
     * @author chaiwei
     * @param deptId 部门ID
     * @return 部门信息
     */
	public Dept selectDeptById(Integer deptId);

	/**
     * 查询部门列表
     * @author chaiwei
     * @param dept 部门信息
     * @return 部门集合
     */
	public List<Dept> selectDeptList(DeptRequest dept);
	
	/**
     * 新增部门
     * @author chaiwei
     * @param dept 部门信息
     * @return 结果
     */
	public int insertDept(Dept dept);
	
	/**
     * 修改部门
     * @author chaiwei
     * @param dept 部门信息
     * @return 结果
     */
	public int updateDept(Dept dept);
	
	/**
     * 删除部门
     * @author chaiwei
     * @param deptId 部门ID
     * @return 结果
     */
	public int deleteDeptById(Integer deptId);
	
	/**
     * 批量删除部门
     * @author chaiwei
     * @param deptIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteDeptByIds(String[] deptIds);
	
}