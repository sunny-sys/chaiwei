package com.bangdao.service.impl.essentialInfo;

import com.bangdao.domain.essentialInfo.Subject;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.mapper.essentialInfo.SubjectMapper;
import com.bangdao.requestVo.essentialInfo.SubjectRequest;
import com.bangdao.service.essentialInfo.ISubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 科目 服务层实现
 * 
 * @author chenshao
 * @date 2018-10-11
 */
@Slf4j
@Service
public class SubjectServiceImpl implements ISubjectService {

    @Autowired
	private SubjectMapper subjectMapper;

	/**
     * 查询科目信息
     * @author chenshao
     * @param id 科目ID
     * @return 科目信息
     */
    @Override
	public Result selectSubjectById(Integer id) throws Exception {
        if (ObjectUtils.isEmpty(id)){
            return Result.error("参数id不能为空");
        }
		Subject data = subjectMapper.selectSubjectById(id);
	    return Result.success().put("subject", data);
	}
	
	/**
     * 查询科目列表
     * @author chenshao
     * @param subject 科目信息
     * @return 科目集合
     */
	@Override
	public List<Subject> selectSubjectList(SubjectRequest subject) throws Exception {
	    return subjectMapper.selectSubjectList(subject);
	}
	
	/**
     * 查询科目(收付款账户)
     * @author chenshao
     * @param subject 科目信息
     * @return 科目集合
     */
	@Override
	public List<Subject> selectSubjectToAccount()  {
	    return subjectMapper.selectSubjectToAccount();
	}
	
    /**
     * 新增科目
     * @author chenshao
     * @param subject 科目信息
     * @return 结果
     */
	@Override
	public Result insertSubject(SubjectRequest subject) throws Exception {
		Subject temp = new Subject();
        BeanUtils.copyProperties(subject,temp);
        if(subject.getIsEquative()==1){
        	int id = temp.getId();
            if(id==1 || id==2 || id==3 || id==4 || id==5){
            	return Result.error("一级科目不可新增同级科目");
            }
        }
        int count = subjectMapper.insertSubject(temp);
	    return count > 0 ? Result.success() : Result.error();
	}
	
	/**
     * 修改科目
     * @author chenshao
     * @param subject 科目信息
     * @return 结果
     */
	@Override
	public Result updateSubject(SubjectRequest subject) throws Exception {
		Subject temp = new Subject();
        BeanUtils.copyProperties(subject,temp);
        Subject data = subjectMapper.selectSubjectById(temp.getId());
        if(data.getStandardSubject()==1){
        	return Result.error("标准科目不可修改");
        }
        int count = subjectMapper.updateSubject(temp);
	    return count > 0 ? Result.success() : Result.error();
	}

	/**
     * 删除科目对象
     * @author chenshao
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public Result deleteSubjectById(int id) throws Exception {
        if (StringUtils.isEmpty(id+"")){
            return Result.error("参数id不能为空");
        }
        Subject data = subjectMapper.selectSubjectById(id);
        if(data.getStandardSubject()==1){
        	return Result.error("标准科目不可删除");
        }
		int count = subjectMapper.deleteSubjectById(id);
		return count > 0 ? Result.success() : Result.error();
	}
	
}
