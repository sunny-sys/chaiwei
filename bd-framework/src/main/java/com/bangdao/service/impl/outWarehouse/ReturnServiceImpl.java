package com.bangdao.service.impl.outWarehouse;

import com.bangdao.common.constant.Constants;
import com.bangdao.common.utils.ActivitiUtil;
import com.bangdao.common.utils.BaseUtil;
import com.bangdao.common.utils.UUID;
import com.bangdao.common.utils.bean.BeanUtils;
import com.bangdao.common.utils.security.ShiroUtils;
import com.bangdao.domain.approval.Approval;
import com.bangdao.domain.outWarehouse.ReturnBase;
import com.bangdao.domain.outWarehouse.ReturnDetailed;
import com.bangdao.domain.system.Warehouse;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.framework.web.page.TableSupport;
import com.bangdao.mapper.outWarehouse.ReturnBaseMapper;
import com.bangdao.mapper.outWarehouse.ReturnDetailedMapper;
import com.bangdao.mapper.system.WarehouseMapper;
import com.bangdao.requestVo.outWarehouse.ReturnBaseReq;
import com.bangdao.requestVo.outWarehouse.ReturnDetailedReq;
import com.bangdao.requestVo.outWarehouse.ReturnReq;
import com.bangdao.responseVo.approval.ApprovalResp;
import com.bangdao.responseVo.outWarehouse.*;
import com.bangdao.service.outWarehouse.IReturnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 退货 服务层实现
 * 
 * @author chaiwei
 * @date 2018-11-07
 */
@Slf4j
@Service
public class ReturnServiceImpl implements IReturnService {

    @Autowired
	private ReturnBaseMapper returnBaseMapper;
	@Autowired
	private ReturnDetailedMapper returnDetailedMapper;
	@Autowired
	private WarehouseMapper warehouseMapper;
	/**
     * 查询退货信息
     * @author chaiwei
     * @param id 退货ID
     * @return 退货信息
     */
    @Override
	public Result selectReturnById(Integer id) throws Exception {
        if (ObjectUtils.isEmpty(id)){
            return Result.error("参数id不能为空");
        }
		//查询基本信息
		ReturnBaseResp returnBaseResp = returnBaseMapper.selectRelationById(id);

		//查询明细
		ReturnDetailedReq returnDetailedReq = new ReturnDetailedReq();
		returnDetailedReq.setParentId(id);
		returnDetailedReq.setStatus(Constants.Status.NORMAL_INT);
		List<ReturnDetailedResp> returnDetailedResps = returnDetailedMapper.selectRelationList(returnDetailedReq);

		ReturnResp responseData = getResponseData(returnBaseResp, returnDetailedResps);

		String businessKey = Constants.Key.OUT_RETURN+"."+id;
		//查询审批记录
		List<ApprovalResp> approvalHistory = ActivitiUtil.getApprovalHistory(businessKey);
		return Result.success().put("return", responseData).put("approvalHistory",approvalHistory);
	}
	
	/**
     * 个人任务列表
     * @author chaiwei
     * @param baseReq 退货信息
     * @return 退货集合
     */
	@Override
	public List<ReturnBaseResp> selectBaseList(ReturnBaseReq baseReq) throws Exception {
		List<Integer> ids = ActivitiUtil.getTaskListByName(Constants.Key.OUT_RETURN);
		if(null == ids || ids.size()==0)
		{
			return new ArrayList<ReturnBaseResp>();
		}
		baseReq.setIds(ids);
		List<ReturnBaseResp> baseResps = returnBaseMapper.selectRelationList(baseReq);
		return baseResps;
	}

	/**
	 * 个人订单列表
	 * @param base
	 * @return
	 */
	@Override
	public List<ReturnBaseResp> billList(ReturnBaseReq base) {
		base.setStatus(Constants.Status.NORMAL_INT);
		List<ReturnBaseResp> resps = returnBaseMapper.selectRelationList(base);
		return resps;
	}

	/**
	 * 提交
	 * @param returnReq
	 * @return
	 */
	@Override
	@Transactional
	public Result submit(ReturnReq returnReq) throws Exception {
		ReturnBaseReq baseReq = returnReq.getReturnReq();
		Integer id = baseReq.getId();
		baseReq.setApprovalStatus(Constants.ApprovalStatus.IN_APPROVAL_INT);
		baseReq.setSubmitTime(new Date());
		baseReq.setSubmitBy(ShiroUtils.getUserId()+"");
		if(ObjectUtils.isEmpty(id))//保存
		{
			Result result = this.insertReturn(returnReq);
			ReturnResp returnResp = (ReturnResp) result.get("return");
			id = returnResp.getReturnBaseResp().getId();
		}
		else
		{
			this.updateReturn(returnReq);
		}
		//完成任务
		ActivitiUtil.finishTask(Constants.Key.OUT_RETURN+"."+id);
		return Result.success();
	}

	/**
	 * 审批
	 * @param approval
	 * @return
	 */
	@Override
	@Transactional
	public Result approval(Approval approval) throws Exception {
		approval.setBusinessKey(Constants.Key.OUT_RETURN+"."+approval.getId());
		//进行审批 返回一个流程实例
		String processInstanceId = ActivitiUtil.approval(approval);
		Integer id = approval.getId();
		ReturnBase base = new ReturnBase();
		base.setId(id);
		//审批动作判断是否为驳回
		String action = approval.getAction();
		if(Constants.ApprovalAction.REJECT.equals(action))
		{
			base.setApprovalStatus(Constants.ApprovalStatus.REJECT_INT);
			returnBaseMapper.updateReturnBase(base);
		}
		else
		{
			boolean approvalStatus = ActivitiUtil.processInstanceEnd(processInstanceId);
			//如果审批完则更新审批状态为3
			if(approvalStatus)
			{
				base.setEffectiveTime(new Date());
				base.setApprovalStatus(Constants.ApprovalStatus.ADOPT_INT);
				returnBaseMapper.updateReturnBase(base);

				//将商品信息插入到库存表中
				Result result = this.selectReturnById(id);
				ReturnResp returnResp = (ReturnResp) result.get("return");
				List<Warehouse> warehouse = this.getWarehouse(returnResp);
				warehouseMapper.batchInsertOrUpdate(warehouse);
			}
		}
		return Result.success();
	}

	/**
     * 查询退货列表
     * @author chaiwei
     * @param returnReq 退货信息
     * @return 退货集合
     */
	@Override
	public TableDataInfo selectReturnList(ReturnReq returnReq) throws Exception {
		//基础信息
		ReturnBaseReq returnBaseReq = returnReq.getReturnReq();
		if (null == returnBaseReq)//默认查询有效数据
		{
			returnBaseReq = new ReturnBaseReq();
			returnBaseReq.setStatus(Constants.Status.NORMAL_INT);
		}
		else if (null == returnBaseReq.getStatus())
		{
			returnBaseReq.setStatus(Constants.Status.NORMAL_INT);
		}

		TableSupport.startPage(returnReq);
		List<ReturnBaseResp> returnBaseResps = returnBaseMapper.selectRelationList(returnBaseReq);

		//存放结果
		List<ReturnResp> purchasineReturnRespList = new ArrayList<ReturnResp>();
		//明细
		for (ReturnBaseResp returnBaseResp : returnBaseResps)
		{
			ReturnDetailedReq returnDetailedReq = new ReturnDetailedReq();
			returnDetailedReq.setParentId(returnBaseResp.getId());
			returnDetailedReq.setStatus(Constants.Status.NORMAL_INT);
			List<ReturnDetailedResp> returnDetailedResps = returnDetailedMapper.selectRelationList(returnDetailedReq);

			//添加响应到集合中
			ReturnResp responseData = getResponseData(returnBaseResp, returnDetailedResps);
			purchasineReturnRespList.add(responseData);
		}
		return TableDataInfo.success(purchasineReturnRespList,returnBaseResps);
	}

    /**
     * 新增退货
     * @author chaiwei
     * @param returnReq 退货信息
     * @return 结果
     */
	@Override
	@Transactional
	public Result insertReturn(ReturnReq returnReq) throws Exception {
		//基础信息
		ReturnBaseReq returnBaseReq = returnReq.getReturnReq();
		//明细
		List<ReturnDetailedReq> returnDetailedReqList = returnReq.getReturnDetailedReq();

		//插入基础信息，并返回id
		ReturnBase returnBase = BeanUtils.reqToEntity(returnBaseReq, ReturnBase.class);
		BaseUtil.setCreateBy(returnBase);
		int count = returnBaseMapper.insertReturnBase(returnBase);

		Integer id = returnBase.getId();
		List<ReturnDetailed> returnDetailedList = new ArrayList<ReturnDetailed>();
		if (count>0)
		{
			//插入明细
			if (null != returnDetailedReqList &&returnDetailedReqList.size()>0)
			{
				//拷贝
				for (ReturnDetailedReq returnDetailedReq: returnDetailedReqList)
				{
					ReturnDetailed returnDetailed = BeanUtils.reqToEntity(returnDetailedReq, ReturnDetailed.class);
					//设置父类主键
					returnDetailed.setParentId(id);
					BaseUtil.setCreateBy(returnDetailed);
					BigDecimal number = returnDetailed.getNumber();
					String discount = returnDetailed.getDiscount();
					BigDecimal unitPrice = returnDetailed.getUnitPrice();
					BigDecimal postDiscountPrice = unitPrice.multiply(new BigDecimal(discount).divide(new BigDecimal("10"))).setScale(2,BigDecimal.ROUND_HALF_UP);
					returnDetailed.setPostDiscountPrice(postDiscountPrice);
					BigDecimal total = number.multiply(postDiscountPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
					returnDetailed.setTotal(total);
					returnDetailedList.add(returnDetailed);
				}
				returnDetailedMapper.batchInsertOrUpdate(returnDetailedList);
			}
		}
		else
		{
			return Result.error();
		}
		//启动流程实例
		ActivitiUtil.startProcessInstanceByKey(Constants.Key.OUT_RETURN,id);
		Result result = this.selectReturnById(id);
		return result;
	}
	
	/**
     * 修改退货
     * @author chaiwei
     * @param returnReq 退货信息
     * @return 结果
     */
	@Override
	@Transactional
	public Result updateReturn(ReturnReq returnReq) throws Exception {
		//基础信息
		ReturnBaseReq returnBaseReq = returnReq.getReturnReq();
		//明细
		List<ReturnDetailedReq> returnDetailedReqList = returnReq.getReturnDetailedReq();

		//插入基础信息，并返回id
		ReturnBase returnBase = BeanUtils.reqToEntity(returnBaseReq, ReturnBase.class);
		BaseUtil.setUpdateBy(returnBase);
		int count = returnBaseMapper.updateReturnBase(returnBase);
		if (count>0)
		{
			//插入明细
			List<ReturnDetailed> returnDetailedList = new ArrayList<ReturnDetailed>();
			if (null != returnDetailedReqList && returnDetailedReqList.size()>0)
			{
				//拷贝
				for (ReturnDetailedReq returnDetailedReq: returnDetailedReqList)
				{
					ReturnDetailed returnDetailed = BeanUtils.reqToEntity(returnDetailedReq, ReturnDetailed.class);
					//设置父类id
					returnDetailed.setParentId(returnBase.getId());

					Integer isDelete = returnDetailedReq.getIsDelete();
					if (null != isDelete && Constants.Status.DELETED_INT == returnDetailedReq.getIsDelete())
					{
						returnDetailed.setStatus(Constants.Status.DELETED_INT);
					}
					BaseUtil.setUpdateBy(returnDetailed);
					BigDecimal number = returnDetailed.getNumber();
					String discount = returnDetailed.getDiscount();
					BigDecimal unitPrice = returnDetailed.getUnitPrice();
					BigDecimal postDiscountPrice = unitPrice.multiply(new BigDecimal(discount).divide(new BigDecimal("10"))).setScale(2,BigDecimal.ROUND_HALF_UP);
					returnDetailed.setPostDiscountPrice(postDiscountPrice);
					BigDecimal total = number.multiply(postDiscountPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
					returnDetailed.setTotal(total);
					returnDetailedList.add(returnDetailed);
				}
				returnDetailedMapper.batchInsertOrUpdate(returnDetailedList);
			}
		}
		else
		{
			return Result.error();
		}
		Result result = this.selectReturnById(returnBase.getId());
		return result;
	}

	/**
     * 删除退货对象
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@Transactional
	public Result deleteReturnByIds(String ids) throws Exception {
		if (StringUtils.isEmpty(ids)){
			return Result.error("参数ids不能为空");
		}

		String[] idArray = ids.split(",");
		//删除流程实例
		ActivitiUtil.deleteTask(Constants.Key.OUT_RETURN,idArray);

		int count = returnBaseMapper.deleteReturnBaseByIds(idArray);

		if (count>0)
		{
			returnDetailedMapper.deleteByParentId(idArray);
		}
		else
		{
			return Result.error();
		}
		return Result.success();
	}

	/**
	 * 获取响应数据
	 * @return 响应数据
	 */
	private ReturnResp getResponseData(ReturnBaseResp baseResp, List<ReturnDetailedResp> detailedRespList)
	{
		//添加到结果集
		ReturnResp returnResp = new ReturnResp();
		returnResp.setReturnBaseResp(baseResp);
		returnResp.setReturnDetailedResp(detailedRespList);
		return returnResp;
	}

	/**
	 * 获取仓库信息数据
	 * @param returnResp
	 * @return
	 */
	public List<Warehouse> getWarehouse(ReturnResp returnResp)
	{
		ReturnBaseResp baseResp = returnResp.getReturnBaseResp();
		List<ReturnDetailedResp> detailedResp = returnResp.getReturnDetailedResp();
		List<Warehouse> warehouses = new ArrayList<Warehouse>();

		for (ReturnDetailedResp detailed : detailedResp)
		{
			Warehouse warehouse = new Warehouse();
			BeanUtils.copyProperties(detailed,warehouse);
			BeanUtils.copyProperties(baseResp,warehouse);
			warehouse.setWarehouseId(UUID.getUUID());
			warehouse.setSourceDetailedId(detailed.getId());
			warehouse.setCreateTime(new Date());
			BaseUtil.setCreateBy(warehouse);
			warehouse.setStatus(Constants.Status.NORMAL_INT);
			warehouse.setUpdateBy(null);
			warehouse.setUpdateTime(null);
			warehouse.setBillSource(Constants.BillSource.RETURN);//标记单据来源
			warehouse.setDateOfStorage(new Date());
			warehouses.add(warehouse);
		}
		return warehouses;
	}

}
