package com.bangdao.service.impl.repertoryManage;

import com.bangdao.common.constant.Constants;
import com.bangdao.common.utils.ActivitiUtil;
import com.bangdao.common.utils.BaseUtil;
import com.bangdao.common.utils.bean.BeanUtils;
import com.bangdao.common.utils.security.ShiroUtils;
import com.bangdao.domain.approval.Approval;
import com.bangdao.domain.repertoryManage.CheckListBase;
import com.bangdao.domain.repertoryManage.CheckListDetail;
import com.bangdao.domain.repertoryManage.ManageAllotBase;
import com.bangdao.domain.repertoryManage.ManageCommodityDetail;
import com.bangdao.domain.system.Warehouse;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.framework.web.page.TableSupport;
import com.bangdao.mapper.repertoryManage.CheckListBaseMapper;
import com.bangdao.mapper.repertoryManage.CheckListDetailMapper;
import com.bangdao.requestVo.repertoryManage.CheckListBaseReq;
import com.bangdao.requestVo.repertoryManage.CheckListDetailReq;
import com.bangdao.requestVo.repertoryManage.CheckListReq;
import com.bangdao.requestVo.repertoryManage.ManageCommodityDetailReq;
import com.bangdao.responseVo.repertoryManage.CheckListBaseResp;
import com.bangdao.responseVo.repertoryManage.CheckListDetailResp;
import com.bangdao.responseVo.repertoryManage.CheckListResp;
import com.bangdao.responseVo.repertoryManage.RepertoryManageResponse;
import com.bangdao.service.repertoryManage.ICheckListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 库存管理调拨单基本 服务层实现
 * 
 * @author chenshao
 * @date 2018-11-08
 */
@Slf4j
@Service
public class CheckListServiceImpl implements ICheckListService {

    @Autowired
	private CheckListBaseMapper baseMapper;

    @Autowired
	private CheckListDetailMapper detailMapper;
    
    @Override
	public Result selectCheckListById(Integer id) throws Exception {
        if (ObjectUtils.isEmpty(id)){
            return Result.error("参数id不能为空");
        }
        //基本信息
        CheckListBaseResp baseResp = baseMapper.selectCheckListBaseById(id);
        //明细
        CheckListDetailReq detailReq = new CheckListDetailReq();
		detailReq.setParentId(id);
  		List<CheckListDetailResp> manageCommodityDetailList = detailMapper.selectCheckListDetailList(detailReq);
  	    //添加响应到集合中
		CheckListResp responseData = getResponseData(baseResp, manageCommodityDetailList);
		return Result.success().put("checkList", responseData);
	}
	
	@Override
	public TableDataInfo selectCheckListList(CheckListReq req) throws Exception {
		//基础信息
        CheckListBaseReq baseReq = req.getBase();
		if (null == baseReq)//默认查询有效数据
		{
			baseReq = new CheckListBaseReq();
			baseReq.setStatus(Constants.Status.NORMAL_INT);
		}

		TableSupport.startPage(req);
		List<CheckListBaseResp> baseList = baseMapper.selectCheckListBaseList(baseReq);

		//存放结果
		List<CheckListResp> respList = new ArrayList<CheckListResp>();
		//明细
		for (CheckListBaseResp base : baseList)
		{
			CheckListDetailReq detailReq = new CheckListDetailReq();
			detailReq.setParentId(base.getId());
			detailReq.setStatus(Constants.Status.NORMAL_INT);
	  		List<CheckListDetailResp> detailResps = detailMapper.selectCheckListDetailList(detailReq);

			//添加响应到集合中
			CheckListResp responseData = getResponseData(base, detailResps);
			respList.add(responseData);
		}
		return TableDataInfo.success(respList,baseList);
	}

	@Override
	public List<CheckListBaseResp> selectBaseList(CheckListBaseReq baseReq) throws Exception {
		List<Integer> ids = ActivitiUtil.getTaskListByName(Constants.Key.CHECK);
		if(null == ids || ids.size()==0)
		{
			return new ArrayList<CheckListBaseResp>();
		}
		baseReq.setIds(ids);
		List<CheckListBaseResp> manageAllotBaseList = this.billList(baseReq);
		return manageAllotBaseList;
	}

	@Override
	public List<CheckListBaseResp> billList(CheckListBaseReq baseReq) throws Exception {
		List<CheckListBaseResp> billList = baseMapper.selectCheckListBaseList(baseReq);
		return billList;
	}

	@Override
	@Transactional
	public Result insertCheckList(CheckListReq req) throws Exception {
        //基础信息
        CheckListBaseReq baseReq = req.getBase();
  		//明细
  		List<CheckListDetailReq> detailReqList = req.getDetail();

  		//插入基础信息，并返回id
		Integer id = this.insertBase(baseReq);

  		if (ObjectUtils.isEmpty(id))
  		{
			return Result.error();
		}
		//添加明细
		this.insertDetail(detailReqList,id);

		ActivitiUtil.startProcessInstanceByKey(Constants.Key.CHECK,id);
		return Result.success();
	}

	/**提交**/
	@Override
	@Transactional
	public Result submit(CheckListReq req) throws Exception{
		CheckListBaseReq base = req.getBase();
		base.setApprovalStatus(Constants.ApprovalStatus.IN_APPROVAL_INT);
		base.setSubmitTime(new Date());
		base.setSubmitBy(ShiroUtils.getUserId()+"");
		Integer id = base.getId();
		if(ObjectUtils.isEmpty(id))
		{
			//插入基础信息，并返回id
			id = this.insertBase(base);
			//添加明细
			this.insertDetail(req.getDetail(),id);
		}
		else
		{
			this.updateCheckList(req);
		}
		ActivitiUtil.finishTask(Constants.Key.CHECK+"."+id);
		return Result.success();
	}


	@Override
	@Transactional
	public Result updateCheckList(CheckListReq req) throws Exception {
	    //基础信息
        CheckListBaseReq baseReq = req.getBase();
  		//明细
  		List<CheckListDetailReq> detailReqList = req.getDetail();

  		//插入基础信息，并返回id
  		CheckListBase base = BeanUtils.reqToEntity(baseReq, CheckListBase.class);
  		BaseUtil.setUpdateBy(base);

  		int count = baseMapper.updateCheckListBase(base);
  		List<CheckListDetail> detailList = new ArrayList<CheckListDetail>();
  		if (count<=0)
  		{
  			return Result.error();
  		}
  			//插入明细
  		 //插入明细
		if (null != detailReqList &&detailReqList.size()>0)
		{
  			//拷贝
  			for (CheckListDetailReq detailReq: detailReqList)
  			{
  				CheckListDetail detail = BeanUtils.reqToEntity(detailReq, CheckListDetail.class);
				detail.setParentId(base.getId());
  				Integer isDelete = detailReq.getIsDelete();
				if (null != isDelete && Constants.Status.DELETED_INT == isDelete)
				{
					detail.setStatus(Constants.Status.DELETED_INT);
				}
  				BaseUtil.setUpdateBy(detail);
				detailList.add(detail);
  			}
  			detailMapper.batchInsertOrUpdate(detailList);
		}
	 return Result.success();
	}

	@Override
	@Transactional
	public Result deleteCheckListBaseByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return Result.error("参数ids不能为空");
        }
		int count = baseMapper.deleteCheckListBaseByIds(ids.split(","));
		return count > 0 ? Result.success() : Result.error();
	}

	@Override
	@Transactional
	public Result deleteCheckListByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return Result.error("参数ids不能为空");
        }
		String[] idArray = ids.split(",");
		//删除任务
		ActivitiUtil.deleteTask(Constants.Key.CHECK,idArray);
		int count = baseMapper.deleteCheckListBaseByIds(idArray);
		detailMapper.deleteCheckListDetailByParentIds(idArray);
		return count > 0 ? Result.success() : Result.error();
	}

	/**审批**/
	@Override
	@Transactional
	public Result approval(Approval approval) throws Exception{
		approval.setBusinessKey(Constants.Key.CHECK+"."+approval.getId());
		//进行审批 返回一个流程实例
		String processInstanceId = ActivitiUtil.approval(approval);

		CheckListBase base = new CheckListBase();
		Integer id = approval.getId();
		base.setId(id);
		//审批动作判断是否为驳回
		String action = approval.getAction();
		if(Constants.ApprovalAction.REJECT.equals(action))
		{
			base.setApprovalStatus(Constants.ApprovalStatus.REJECT_INT);
			baseMapper.updateCheckListBase(base);
		}
		else
		{
			boolean approvalStatus = ActivitiUtil.processInstanceEnd(processInstanceId);
			//如果审批完则更新审批状态为3
			if (approvalStatus) {
				base.setEffectiveTime(new Date());
				base.setApprovalStatus(Constants.ApprovalStatus.ADOPT_INT);
				baseMapper.updateCheckListBase(base);
			}
		}
		return Result.success();
	}

	/**
	 * 获取响应数据
	 * @return 响应数据
	 */
	private CheckListResp getResponseData(CheckListBaseResp BaseResp,List<CheckListDetailResp> detailedList)
	{
		//添加到结果集
		CheckListResp resp = new CheckListResp();
		resp.setBase(BaseResp);
		resp.setDetail(detailedList);
		return resp;
	}

	/**
	 * 插入基础信息，并返回id
	 * @param baseReq
	 * @return id
	 */
	@Transactional
	public Integer insertBase( CheckListBaseReq baseReq) throws Exception
	{
		CheckListBase base = BeanUtils.reqToEntity(baseReq, CheckListBase.class);
		BaseUtil.setCreateBy(base);
		baseMapper.insertCheckListBase(base);
		return  base.getId();
	}

	/**
	 * 插入明细
	 * @param detailReqList 明细
	 * @param id 父类id
	 * @return
	 */
	@Transactional
	public Boolean insertDetail( List<CheckListDetailReq> detailReqList,Integer id) throws Exception
	{
		if(ObjectUtils.isEmpty(id))
		{
			return false;
		}
		List<CheckListDetail> detailList = new ArrayList<CheckListDetail>();
		//插入明细
		if (null != detailReqList &&detailReqList.size()>0)
		{
			//拷贝
			for (CheckListDetailReq detailReq: detailReqList)
			{
				CheckListDetail detail = BeanUtils.reqToEntity(detailReq, CheckListDetail.class);
				detail.setParentId(id);
				BaseUtil.setCreateBy(detail);
				detailList.add(detail);
			}
			detailMapper.batchInsertOrUpdate(detailList);
		}
		return true;
	}

}
