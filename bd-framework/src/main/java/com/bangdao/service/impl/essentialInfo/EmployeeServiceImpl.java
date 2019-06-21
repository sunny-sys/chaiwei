package com.bangdao.service.impl.essentialInfo;

import com.bangdao.domain.essentialInfo.Employee;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.mapper.essentialInfo.EmployeeMapper;
import com.bangdao.requestVo.essentialInfo.EmployeeRequest;
import com.bangdao.service.essentialInfo.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 员工 服务层实现
 * 
 * @author xupj
 * @date 2018-10-13
 */
@Slf4j
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
	private EmployeeMapper employeeMapper;

	/**
     * 查询员工信息
     * @author xupj
     * @param id 员工ID
     * @return 员工信息
     */
    @Override
	public Result selectEmployeeById(Integer id) throws Exception {
        if (ObjectUtils.isEmpty(id)){
            return Result.error("参数id不能为空");
        }
		Employee data = employeeMapper.selectEmployeeById(id);
	    return Result.success().put("employee", data);
	}
	
	/**
     * 查询员工列表
     * @author xupj
     * @param employee 员工信息
     * @return 员工集合
     */
	@Override
	public List<Employee> selectEmployeeList(EmployeeRequest employee) throws Exception {
		if(null == employee.getStatus())
		{
			employee.setStatus(1);
		}
	    return employeeMapper.selectEmployeeList(employee);
	}
	
    /**
     * 新增员工
     * @author xupj
     * @param employee 员工信息
     * @return 结果
     */
	@Override
	public Result insertEmployee(EmployeeRequest employee) throws Exception {
		Employee temp = new Employee();
        BeanUtils.copyProperties(employee,temp);
        int count = employeeMapper.insertEmployee(temp);
	    return count > 0 ? Result.success() : Result.error();
	}
	
	/**
     * 修改员工
     * @author xupj
     * @param employee 员工信息
     * @return 结果
     */
	@Override
	public Result updateEmployee(EmployeeRequest employee) throws Exception {
		Employee temp = new Employee();
        BeanUtils.copyProperties(employee,temp);
        int count = employeeMapper.updateEmployee(temp);
	    return count > 0 ? Result.success() : Result.error();
	}

	/**
     * 删除员工对象
     * @author xupj
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public Result deleteEmployeeByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return Result.error("参数ids不能为空");
        }
		int count = employeeMapper.deleteEmployeeByIds(ids.split(","));
		return count > 0 ? Result.success() : Result.error();
	}
	
}
