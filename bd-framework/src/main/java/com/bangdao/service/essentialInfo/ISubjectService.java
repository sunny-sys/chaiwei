package com.bangdao.service.essentialInfo;

import com.bangdao.domain.essentialInfo.Subject;
import com.bangdao.requestVo.essentialInfo.SubjectRequest;
import com.bangdao.framework.web.domain.Result;

import java.util.List;

/**
 * 科目 服务层
 * 
 * @author chenshao
 * @date 2018-10-11
 */
public interface ISubjectService {
	/**
     * 查询科目信息
     * @author chenshao
     * @param id 科目ID
     * @return 科目信息
     */
	public Result selectSubjectById(Integer id) throws Exception;
	
	/**
     * 查询科目列表
     * @author chenshao
     * @param subject 科目信息
     * @return 科目集合
     */
	public List<Subject> selectSubjectList(SubjectRequest subject) throws Exception;
	
	/**
     * 新增科目
     * @author chenshao
     * @param subject 科目信息
     * @return 结果
     */
	public Result insertSubject(SubjectRequest subject) throws Exception;
	
	/**
     * 修改科目
     * @author chenshao
     * @param subject 科目信息
     * @return 结果
     */
	public Result updateSubject(SubjectRequest subject) throws Exception;
		
//	/**
//     * 删除科目信息
//     * @author chenshao
//     * @param ids 需要删除的数据ID
//     * @return 结果
//     */
//	public Result deleteSubjectByIds(String ids) throws Exception;
	
	/**
     * 删除科目信息
     * @author chenshao
     * @param id 需要删除的数据ID
     * @return 结果
     */
	public Result deleteSubjectById(int id) throws Exception;

	public List<Subject> selectSubjectToAccount();
	
}
