package com.bangdao.service.impl.outWarehouse;

import com.bangdao.common.constant.Constants;
import com.bangdao.common.utils.ActivitiUtil;
import com.bangdao.common.utils.BaseUtil;
import com.bangdao.common.utils.bean.BeanUtils;
import com.bangdao.common.utils.security.ShiroUtils;
import com.bangdao.domain.approval.Approval;
import com.bangdao.domain.outWarehouse.OutWarehouseBase;
import com.bangdao.domain.outWarehouse.OutWarehouseDetailed;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.framework.web.page.TableSupport;
import com.bangdao.mapper.outWarehouse.OutWarehouseBaseMapper;
import com.bangdao.mapper.outWarehouse.OutWarehouseDetailedMapper;
import com.bangdao.requestVo.outWarehouse.OutWarehouseBaseReq;
import com.bangdao.requestVo.outWarehouse.OutWarehouseDetailedReq;
import com.bangdao.requestVo.outWarehouse.OutWarehouseReq;
import com.bangdao.responseVo.approval.ApprovalResp;
import com.bangdao.responseVo.outWarehouse.OutWarehouseBaseResp;
import com.bangdao.responseVo.outWarehouse.OutWarehouseDetailedResp;
import com.bangdao.responseVo.outWarehouse.OutWarehouseResp;
import com.bangdao.service.outWarehouse.IOutWarehouseService;
import com.bangdao.service.system.IWarehouseService;
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
 * 出库 服务层实现
 * 
 * @author chaiwei
 * @date 2018-11-07
 */
@Slf4j
@Service
public class OutWarehouseServiceImpl implements IOutWarehouseService {

    @Autowired
	private OutWarehouseBaseMapper outWarehouseBaseMapper;
	@Autowired
	private OutWarehouseDetailedMapper outWarehouseDetailedMapper;
	@Autowired
	private IWarehouseService warehouseService;
	/**
     * 查询出库信息
     * @author chaiwei
     * @param id 出库ID
     * @return 出库信息
     */
    @Override
	public Result selectOutWarehouseById(Integer id) throws Exception {
        if (ObjectUtils.isEmpty(id)){
            return Result.error("参数id不能为空");
        }
		//查询基本信息
		OutWarehouseBaseResp outWarehouseBaseResp = outWarehouseBaseMapper.selectRelationById(id);

		//查询明细
		OutWarehouseDetailedReq outWarehouseDetailedReq = new OutWarehouseDetailedReq();
		outWarehouseDetailedReq.setParentId(id);
		outWarehouseDetailedReq.setStatus(Constants.Status.NORMAL_INT);
		List<OutWarehouseDetailedResp> outWarehouseDetailedResps = outWarehouseDetailedMapper.selectRelationList(outWarehouseDetailedReq);

		OutWarehouseResp responseData = getResponseData(outWarehouseBaseResp, outWarehouseDetailedResps);

		String businessKey = Constants.Key.OUT_WAREHOUSE+"."+id;
		//查询审批记录
		List<ApprovalResp> approvalHistory = ActivitiUtil.getApprovalHistory(businessKey);
		return Result.success().put("outWarehouse", responseData).put("approvalHistory", approvalHistory);
	}
	
	/**
     * 查询出库个人任务列表
     * @author chaiwei
     * @param baseReq 出库信息
     * @return 出库集合
     */
	@Override
	public List<OutWarehouseBaseResp> selectBaseList(OutWarehouseBaseReq baseReq) throws Exception {
		List<Integer> ids = ActivitiUtil.getTaskListByName(Constants.Key.OUT_WAREHOUSE);
		if(null == ids || ids.size()==0)
		{
			return new ArrayList<OutWarehouseBaseResp>();
		}
		baseReq.setIds(ids);
		return outWarehouseBaseMapper.selectRelationList(baseReq);
	}

	/**
	 * 查询个人订单列表
	 * @param base
	 * @return
	 */
	@Override
	public List<OutWarehouseBaseResp> billList(OutWarehouseBaseReq base) throws Exception{
		base.setStatus(Constants.Status.NORMAL_INT);
		return outWarehouseBaseMapper.selectRelationList(base);
	}

	/**
     * 查询出库列表
     * @author chaiwei
     * @param outWarehouse 出库信息
     * @return 出库集合
     */
	@Override
	public TableDataInfo selectOutWarehouseList(OutWarehouseReq outWarehouse) throws Exception {
		//基础信息
		OutWarehouseBaseReq outWarehouseBaseReq = outWarehouse.getOutWarehouseReq();
		if (null == outWarehouseBaseReq)//默认查询有效数据
		{
			outWarehouseBaseReq = new OutWarehouseBaseReq();
			outWarehouseBaseReq.setStatus(Constants.Status.NORMAL_INT);
		}
		else if (null == outWarehouseBaseReq.getStatus())
		{
			outWarehouseBaseReq.setStatus(Constants.Status.NORMAL_INT);
		}

		TableSupport.startPage(outWarehouse);
		List<OutWarehouseBaseResp> outWarehouseBaseResps = outWarehouseBaseMapper.selectRelationList(outWarehouseBaseReq);

		//存放结果
		List<OutWarehouseResp> purchasineOutWarehouseRespList = new ArrayList<OutWarehouseResp>();
		//明细
		for (OutWarehouseBaseResp outWarehouseBaseResp : outWarehouseBaseResps)
		{
			OutWarehouseDetailedReq outWarehouseDetailedReq = new OutWarehouseDetailedReq();
			outWarehouseDetailedReq.setParentId(outWarehouseBaseResp.getId());
			outWarehouseDetailedReq.setStatus(Constants.Status.NORMAL_INT);
			List<OutWarehouseDetailedResp> outWarehouseDetailedResps = outWarehouseDetailedMapper.selectRelationList(outWarehouseDetailedReq);

			//添加响应到集合中
			OutWarehouseResp responseData = getResponseData(outWarehouseBaseResp, outWarehouseDetailedResps);
			purchasineOutWarehouseRespList.add(responseData);
		}
		return TableDataInfo.success(purchasineOutWarehouseRespList, outWarehouseBaseResps);
	}

    /**
     * 新增出库
     * @author chaiwei
     * @param outWarehouse 出库信息
     * @return 结果
     */
	@Override
	@Transactional
	public Result insertOutWarehouse(OutWarehouseReq outWarehouse) throws Exception {
		//基础信息
		OutWarehouseBaseReq outWarehouseBaseReq = outWarehouse.getOutWarehouseReq();
		//明细
		List<OutWarehouseDetailedReq> outWarehouseDetailedReqList = outWarehouse.getOutWarehouseDetailedReq();

		//插入基础信息，并返回id
		OutWarehouseBase outWarehouseBase = BeanUtils.reqToEntity(outWarehouseBaseReq, OutWarehouseBase.class);
		BaseUtil.setCreateBy(outWarehouseBase);
		int count = outWarehouseBaseMapper.insertOutWarehouseBase(outWarehouseBase);

		List<OutWarehouseDetailed> outWarehouseDetailedList = new ArrayList<OutWarehouseDetailed>();
		if (count>0)
		{
			//插入明细
			if (null != outWarehouseDetailedReqList && outWarehouseDetailedReqList.size()>0)
			{
				//拷贝
				for (OutWarehouseDetailedReq outWarehouseDetailedReq : outWarehouseDetailedReqList)
				{
					OutWarehouseDetailed outWarehouseDetailed = BeanUtils.reqToEntity(outWarehouseDetailedReq, OutWarehouseDetailed.class);
					//设置父类主键
					outWarehouseDetailed.setParentId(outWarehouseBase.getId());

					BaseUtil.setCreateBy(outWarehouseDetailed);
					outWarehouseDetailed.setTotal(outWarehouseDetailed.getNumber().multiply(outWarehouseDetailed.getPostDiscountPrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
					outWarehouseDetailedList.add(outWarehouseDetailed);

					String warehouseId = outWarehouseDetailed.getWarehouseId();
					BigDecimal number = outWarehouseDetailed.getNumber();
					warehouseService.reduceInventory(warehouseId, number);//减少库存量
				}
				outWarehouseDetailedMapper.batchInsertOrUpdate(outWarehouseDetailedList);
			}
		}
		else
		{
			return Result.error();
		}
		//启动流程实例
		ActivitiUtil.startProcessInstanceByKey(Constants.Key.OUT_WAREHOUSE,outWarehouseBase.getId());

		Result result = this.selectOutWarehouseById(outWarehouseBase.getId());
		return result;
	}

	/**
	 * 提交
	 * @param outWarehouse
	 * @return
	 */
	@Override
	public Result submit(OutWarehouseReq outWarehouse) throws Exception{
		OutWarehouseBaseReq baseReq = outWarehouse.getOutWarehouseReq();
		baseReq.setApprovalStatus(Constants.ApprovalStatus.IN_APPROVAL_INT);
		baseReq.setSubmitTime(new Date());
		baseReq.setSubmitBy(ShiroUtils.getUserId()+"");
		Integer id = baseReq.getId();
		if(null == id)
		{
			Result result = this.insertOutWarehouse(outWarehouse);
			OutWarehouseResp resp = (OutWarehouseResp) result.get("outWarehouse");
			id = resp.getOutWarehouseBaseResp().getId();
		}
		else
		{
			this.updateOutWarehouse(outWarehouse);
		}
		//完成任务
		ActivitiUtil.finishTask(Constants.Key.OUT_WAREHOUSE+"."+id);
		return Result.success();
	}

	@Override
	public Result approval(Approval approval) {
		approval.setBusinessKey(Constants.Key.OUT_WAREHOUSE+"."+approval.getId());
		//进行审批 返回一个流程实例
		String processInstanceId = ActivitiUtil.approval(approval);

		OutWarehouseBase base = new OutWarehouseBase();
		base.setId(approval.getId());

		//审批动作判断是否为驳回
		String action = approval.getAction();
		if(Constants.ApprovalAction.REJECT.equals(action))
		{
			base.setApprovalStatus(Constants.ApprovalStatus.REJECT_INT);
			//驳回需要将退货单从新入库
			OutWarehouseDetailedReq detailedReq = new OutWarehouseDetailedReq();
			detailedReq.setParentId(base.getId());
			detailedReq.setStatus(Constants.Status.NORMAL_INT);
			List<OutWarehouseDetailed> detaileds = outWarehouseDetailedMapper.selectOutWarehouseDetailedList(detailedReq);
			for (OutWarehouseDetailed detailed : detaileds) {
				warehouseService.increaseInventory(detailed.getWarehouseId(), detailed.getNumber());
			}
			outWarehouseBaseMapper.updateOutWarehouseBase(base);
		}
		else
		{
			boolean approvalStatus = ActivitiUtil.processInstanceEnd(processInstanceId);
			//如果审批完则更新审批状态为3
			if (approvalStatus) {
				base.setEffectiveTime(new Date());
				base.setApprovalStatus(Constants.ApprovalStatus.ADOPT_INT);
				outWarehouseBaseMapper.updateOutWarehouseBase(base);
			}
		}
		return Result.success();
	}

	/**
     * 修改出库
     * @author chaiwei
     * @param outWarehouse 出库信息
     * @return 结果
     */
	@Override
	@Transactional
	public Result updateOutWarehouse(OutWarehouseReq outWarehouse) throws Exception {
		//基础信息
		OutWarehouseBaseReq outWarehouseBaseReq = outWarehouse.getOutWarehouseReq();
		//明细
		List<OutWarehouseDetailedReq> outWarehouseDetailedReqList = outWarehouse.getOutWarehouseDetailedReq();

		//插入基础信息，并返回id
		OutWarehouseBase outWarehouseBase = BeanUtils.reqToEntity(outWarehouseBaseReq, OutWarehouseBase.class);
		BaseUtil.setUpdateBy(outWarehouseBase);
		int count = outWarehouseBaseMapper.updateOutWarehouseBase(outWarehouseBase);
		if (count>0)
		{
			//插入明细
			List<OutWarehouseDetailed> outWarehouseDetailedList = new ArrayList<OutWarehouseDetailed>();
			if (null != outWarehouseDetailedReqList && outWarehouseDetailedReqList.size()>0)
			{
				//拷贝
				for (OutWarehouseDetailedReq outWarehouseDetailedReq : outWarehouseDetailedReqList)
				{
					OutWarehouseDetailed outWarehouseDetailed = BeanUtils.reqToEntity(outWarehouseDetailedReq, OutWarehouseDetailed.class);
					//设置父类id
					outWarehouseDetailed.setParentId(outWarehouseBase.getId());

					String warehouseId = outWarehouseDetailed.getWarehouseId();//仓库表id
					BigDecimal numberNew = outWarehouseDetailed.getNumber();//新的退货数量
					Integer isDelete = outWarehouseDetailedReq.getIsDelete();//判断是否删除
					//删除的数据
					if (null != isDelete && Constants.Status.DELETED_INT == isDelete) {
						outWarehouseDetailed.setStatus(Constants.Status.DELETED_INT);

						warehouseService.increaseInventory(warehouseId, numberNew);//增加库存量
					} else //修改或者新增的数据
					{
						outWarehouseDetailed.setStatus(Constants.Status.NORMAL_INT);
						Integer id = outWarehouseDetailed.getId();//明细id
						//新增的数据
						if (ObjectUtils.isEmpty(id)) {
							warehouseService.reduceInventory(warehouseId, numberNew);//减少库存量
						} else//修改的数据
						{
							//对比前后退货数量是否有变化 ，如果变化则 相应的增加减少库存
							OutWarehouseDetailed detailed = outWarehouseDetailedMapper.selectOutWarehouseDetailedById(id);
							BigDecimal numberOld = detailed.getNumber();//旧的退货数量
							//退货数量差值
							BigDecimal subtract = numberOld.subtract(numberNew);
							if (subtract.compareTo(BigDecimal.ZERO) > 0) {
								warehouseService.increaseInventory(warehouseId, subtract.abs());
							} else {
								warehouseService.reduceInventory(warehouseId, subtract.abs());
							}
						}
					}
					BaseUtil.setUpdateBy(outWarehouseDetailed);
					outWarehouseDetailed.setTotal(outWarehouseDetailed.getNumber().multiply(outWarehouseDetailed.getPostDiscountPrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
					outWarehouseDetailedList.add(outWarehouseDetailed);
				}
				outWarehouseDetailedMapper.batchInsertOrUpdate(outWarehouseDetailedList);
			}
		}
		else
		{
			return Result.error();
		}
		Result result = this.selectOutWarehouseById(outWarehouseBase.getId());
		return result;
	}

	/**
     * 删除出库对象
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@Transactional
	public Result deleteOutWarehouseByIds(String ids) throws Exception {
		if (StringUtils.isEmpty(ids)){
			return Result.error("参数ids不能为空");
		}
		String[] idArray = ids.split(",");
		//删除个人任务
		ActivitiUtil.deleteTask(Constants.Key.OUT_WAREHOUSE,idArray);

		int count = outWarehouseBaseMapper.deleteOutWarehouseBaseByIds(idArray);

		if (count>0)
		{
			outWarehouseDetailedMapper.deleteByParentId(idArray);
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
	private OutWarehouseResp getResponseData(OutWarehouseBaseResp baseResp, List<OutWarehouseDetailedResp> detailedRespList)
	{
		//添加到结果集
		OutWarehouseResp outWarehouseResp = new OutWarehouseResp();
		outWarehouseResp.setOutWarehouseBaseResp(baseResp);
		outWarehouseResp.setOutWarehouseDetailedResp(detailedRespList);
		return outWarehouseResp;
	}
}
