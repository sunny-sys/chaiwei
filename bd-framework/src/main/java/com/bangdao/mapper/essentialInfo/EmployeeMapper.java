package com.bangdao.mapper.essentialInfo;

import com.bangdao.domain.essentialInfo.Employee;
import com.bangdao.requestVo.essentialInfo.EmployeeRequest;

import java.util.List;

/**
 * 员工 数据层
 * 
 * @author xupj
 * @date 2018-10-13
 */
public interface EmployeeMapper {
	/**
     * 查询员工信息
     * @author xupj
     * @param id 员工ID
     * @return 员工信息
     */
	public Employee selectEmployeeById(Integer id);

	/**
     * 查询员工列表
     * @author xupj
     * @param employee 员工信息
     * @return 员工集合
     */
	public List<Employee> selectEmployeeList(EmployeeRequest employee);
	
	/**
     * 新增员工
     * @author xupj
     * @param employee 员工信息
     * @return 结果
     */
	public int insertEmployee(Employee employee);
	
	/**
     * 修改员工
     * @author xupj
     * @param employee 员工信息
     * @return 结果
     */
	public int updateEmployee(Employee employee);
	
	/**
     * 删除员工
     * @author xupj
     * @param id 员工ID
     * @return 结果
     */
	public int deleteEmployeeById(Integer id);
	
	/**
     * 批量删除员工
     * @author xupj
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteEmployeeByIds(String[] ids);
	
}