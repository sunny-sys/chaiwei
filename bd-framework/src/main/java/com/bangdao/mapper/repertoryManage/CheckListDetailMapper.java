package com.bangdao.mapper.repertoryManage;


import com.bangdao.domain.repertoryManage.CheckListDetail;
import com.bangdao.requestVo.repertoryManage.CheckListDetailReq;
import com.bangdao.responseVo.repertoryManage.CheckListDetailResp;

import java.util.List;

public interface CheckListDetailMapper {
	public CheckListDetail selectCheckListDetailById(Integer id);

	public List<CheckListDetailResp> selectCheckListDetailList(CheckListDetailReq detailReq);
	
	public int insertCheckListDetail(CheckListDetail manageCommodityDetail);
	
	public int updateCheckListDetail(CheckListDetail manageCommodityDetail);
	
	public int deleteCheckListDetailById(Integer id);
	
	public int deleteCheckListDetailByIds(String[] ids);

	public int deleteCheckListDetailByParentIds(String[] parentId);

	public void batchInsertOrUpdate(List<CheckListDetail> manageCommodityDetailList);
	
}