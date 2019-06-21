package com.bangdao.service.essentialInfo;

import com.bangdao.domain.essentialInfo.Employee;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.requestVo.essentialInfo.EmployeeRequest;

import java.util.List;

/**
 * 员工 服务层
 * 
 * @author xupj
 * @date 2018-10-13
 */
public interface IEmployeeService {
	/**
     * 查询员工信息
     * @author xupj
     * @param id 员工ID
     * @return 员工信息
     */
	public Result selectEmployeeById(Integer id) throws Exception;
	
	/**
     * 查询员工列表
     * @author xupj
     * @param employee 员工信息
     * @return 员工集合
     */
	public List<Employee> selectEmployeeList(EmployeeRequest employee) throws Exception;
	
	/**
     * 新增员工
     * @author xupj
     * @param employee 员工信息
     * @return 结果
     */
	public Result insertEmployee(EmployeeRequest employee) throws Exception;
	
	/**
     * 修改员工
     * @author xupj
     * @param employee 员工信息
     * @return 结果
     */
	public Result updateEmployee(EmployeeRequest employee) throws Exception;
		
	/**
     * 删除员工信息
     * @author xupj
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteEmployeeByIds(String ids) throws Exception;
	
}
