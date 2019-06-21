package com.bangdao.mapper.repertoryManage;

import com.bangdao.domain.repertoryManage.CheckListBase;
import com.bangdao.requestVo.repertoryManage.CheckListBaseReq;
import com.bangdao.responseVo.repertoryManage.CheckListBaseResp;

import java.util.List;

public interface CheckListBaseMapper {
	public CheckListBaseResp selectCheckListBaseById(Integer id);


	public List<CheckListBaseResp> selectCheckListBaseList(CheckListBaseReq manageAllotBase);
	
	public int insertCheckListBase(CheckListBase manageAllotBase);
	
	public int updateCheckListBase(CheckListBase manageAllotBase);
	
	public int deleteCheckListBaseById(Integer id);
	
	public int deleteCheckListBaseByIds(String[] ids);
	
}