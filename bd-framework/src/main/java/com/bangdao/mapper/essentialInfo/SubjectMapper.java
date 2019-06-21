package com.bangdao.mapper.essentialInfo;

import com.bangdao.domain.essentialInfo.Subject;
import com.bangdao.requestVo.essentialInfo.SubjectRequest;
import java.util.List;	

/**
 * 科目 数据层
 * 
 * @author chenshao
 * @date 2018-10-11
 */
public interface SubjectMapper {
	/**
     * 查询科目信息
     * @author chenshao
     * @param id 科目ID
     * @return 科目信息
     */
	public Subject selectSubjectById(Integer id);

	/**
     * 查询科目列表
     * @author chenshao
     * @param subject 科目信息
     * @return 科目集合
     */
	public List<Subject> selectSubjectList(SubjectRequest subject);
	/**
     * 查询科目(收付款账户)
     * @author chenshao
     * @param subject 科目信息
     * @return 科目集合
     */
	public List<Subject> selectSubjectToAccount();
	
	/**
     * 新增科目
     * @author chenshao
     * @param subject 科目信息
     * @return 结果
     */
	public int insertSubject(Subject subject);
	
	/**
     * 修改科目
     * @author chenshao
     * @param subject 科目信息
     * @return 结果
     */
	public int updateSubject(Subject subject);
	
	/**
     * 删除科目
     * @author chenshao
     * @param id 科目ID
     * @return 结果
     */
	public int deleteSubjectById(Integer id);
	
	/**
     * 批量删除科目
     * @author chenshao
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSubjectByIds(String[] ids);
	
}