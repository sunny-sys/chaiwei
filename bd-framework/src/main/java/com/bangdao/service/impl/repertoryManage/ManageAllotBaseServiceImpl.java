package com.bangdao.service.impl.repertoryManage;

import com.bangdao.common.constant.Constants;
import com.bangdao.common.utils.ActivitiUtil;
import com.bangdao.common.utils.BaseUtil;
import com.bangdao.common.utils.UUID;
import com.bangdao.common.utils.bean.BeanUtils;
import com.bangdao.common.utils.security.ShiroUtils;
import com.bangdao.domain.approval.Approval;
import com.bangdao.domain.repertoryManage.ManageAllotBase;
import com.bangdao.domain.repertoryManage.ManageCommodityDetail;
import com.bangdao.domain.system.Warehouse;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.mapper.purchase.PurchasingWarehousingDetailedMapper;
import com.bangdao.mapper.repertoryManage.ManageAllotBaseMapper;
import com.bangdao.mapper.repertoryManage.ManageCommodityDetailMapper;
import com.bangdao.mapper.system.WarehouseMapper;
import com.bangdao.requestVo.repertoryManage.ManageAllotBaseReq;
import com.bangdao.requestVo.repertoryManage.ManageCommodityDetailReq;
import com.bangdao.requestVo.repertoryManage.RepertoryManageRequest;
import com.bangdao.responseVo.approval.ApprovalResp;
import com.bangdao.responseVo.repertoryManage.ManageAllotBaseResp;
import com.bangdao.responseVo.repertoryManage.ManageCommodityDetailResp;
import com.bangdao.responseVo.repertoryManage.RepertoryManageResponse;
import com.bangdao.service.repertoryManage.IManageAllotBaseService;
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
 * 调拨单基本 服务层实现
 * 
 * @author chenshao
 * @date 2018-11-08
 */
@Slf4j
@Service
public class ManageAllotBaseServiceImpl implements IManageAllotBaseService {

    @Autowired
	private ManageAllotBaseMapper manageAllotBaseMapper;

    @Autowired
	private ManageCommodityDetailMapper manageCommodityDetailMapper;
    
	@Autowired
	private PurchasingWarehousingDetailedMapper purchasingWarehousingDetailedMapper;
	@Autowired
	private IWarehouseService warehouseService;
	@Autowired
	private WarehouseMapper warehouseMapper;

	/**
     * 查询详情
     * @author chenshao
     * @param id 调拨单基本ID
     * @return 调拨单基本信息
     */
    @Override
	public Result selectManageAllotBaseById(Integer id) throws Exception {
        if (ObjectUtils.isEmpty(id)){
            return Result.error("参数id不能为空");
        }
        //基本信息
        ManageAllotBaseResp manageAllotBaseResp = manageAllotBaseMapper.selectManageAllotBaseById(id);
        //明细
        ManageCommodityDetailReq manageCommodityDetailReq = new ManageCommodityDetailReq();
  		manageCommodityDetailReq.setParentId(id);
  		manageCommodityDetailReq.setStatus(Constants.Status.NORMAL_INT);
  		List<ManageCommodityDetailResp> manageCommodityDetailList = manageCommodityDetailMapper.selectRelationList(manageCommodityDetailReq);
  	    //添加响应到集合中
  		RepertoryManageResponse responseData = getResponseData(manageAllotBaseResp, manageCommodityDetailList);

		String businessKey = Constants.Key.ALLOT+"."+id;
		//查询审批记录
		List<ApprovalResp> approvalHistory = ActivitiUtil.getApprovalHistory(businessKey);
	    return Result.success().put("manageAllotDetail", responseData).put("approvalHistory", approvalHistory);
	}
	
	/**
     * 个人任务列表
     * @author chenshao
     * @param base 调拨单基本信息
     * @return 调拨单基本列表
     */
	@Override
	public List<ManageAllotBaseResp> selectBaseList(ManageAllotBaseReq base) throws Exception {
		List<Integer> ids = ActivitiUtil.getTaskListByName(Constants.Key.ALLOT);
		if(null == ids || ids.size()==0)
		{
			return new ArrayList<ManageAllotBaseResp>();
		}
		base.setIds(ids);
		List<ManageAllotBaseResp> manageAllotBaseList = manageAllotBaseMapper.selectManageAllotBaseList(base);
		return manageAllotBaseList;
	}

	/**
	 * 个人订单列表
	 * @param base
	 * @return
	 */
	@Override
	public List<ManageAllotBaseResp> billList(ManageAllotBaseReq base) {
		base.setStatus(Constants.Status.NORMAL_INT);
		return manageAllotBaseMapper.selectManageAllotBaseList(base);
	}

	/**提交**/
	@Override
	@Transactional
	public Result submit(RepertoryManageRequest manageAllot) throws Exception{
		ManageAllotBaseReq baseReq = manageAllot.getBase();
		baseReq.setApprovalStatus(Constants.ApprovalStatus.IN_APPROVAL_INT);
		baseReq.setSubmitTime(new Date());
		baseReq.setSubmitBy(ShiroUtils.getUserId()+"");
		Integer id = baseReq.getId();
		if(ObjectUtils.isEmpty(id))
		{
			id = this.insertBase(baseReq);
			List<ManageCommodityDetailReq> detail = manageAllot.getDetail();
			this.insertDetailed(id,detail);
		}
		else
		{
			this.updateManageAllotBase(manageAllot);
		}
		ActivitiUtil.finishTask(Constants.Key.ALLOT+"."+id);
		return Result.success();
	}

	/**审批**/
	@Override
	@Transactional
	public Result approval(Approval approval) throws Exception{
		approval.setBusinessKey(Constants.Key.ALLOT+"."+approval.getId());
		//进行审批 返回一个流程实例
		String processInstanceId = ActivitiUtil.approval(approval);

		ManageAllotBase base = new ManageAllotBase();
		Integer id = approval.getId();
		base.setId(id);
		//审批动作判断是否为驳回
		String action = approval.getAction();
		if(Constants.ApprovalAction.REJECT.equals(action))
		{
			base.setApprovalStatus(Constants.ApprovalStatus.REJECT_INT);
			//驳回需要将退货单从新入库
			ManageCommodityDetailReq detailedReq = new ManageCommodityDetailReq();
			detailedReq.setParentId(id);
			List<ManageCommodityDetail> details = manageCommodityDetailMapper.selectManageCommodityDetailList(detailedReq);
			for (ManageCommodityDetail detailed : details) {
				warehouseService.increaseInventory(detailed.getWarehouseId(), detailed.getNumber());
			}
			manageAllotBaseMapper.updateManageAllotBase(base);
		}
		else
		{
			boolean approvalStatus = ActivitiUtil.processInstanceEnd(processInstanceId);
			//如果审批完则更新审批状态为3
			if (approvalStatus) {
				base.setEffectiveTime(new Date());
				base.setApprovalStatus(Constants.ApprovalStatus.ADOPT_INT);
				manageAllotBaseMapper.updateManageAllotBase(base);
				//插入调拨明细到仓库表
				Result result = this.selectManageAllotBaseById(id);
				RepertoryManageResponse response = (RepertoryManageResponse) result.get("manageAllotDetail");
				List<Warehouse> warehouse = this.getWarehouse(response);
				warehouseMapper.batchInsertOrUpdate(warehouse);
			}
		}
		return Result.success();
	}

	/**
     * 新增
     * @author chenshao
     * @param repertoryManageRequest 调拨单基本信息
     * @return 结果
     */
	@Override
	@Transactional
	public Result insertManageAllotBase(RepertoryManageRequest repertoryManageRequest) throws Exception {
        //基础信息
        ManageAllotBaseReq manageAllotBaseReq = repertoryManageRequest.getBase();
  		//明细
  		List<ManageCommodityDetailReq> manageCommodityDetailReqList = repertoryManageRequest.getDetail();
		Integer id = this.insertBase(manageAllotBaseReq);
  		if (null != id)
  		{
			//插入明细
			if (null != manageCommodityDetailReqList &&manageCommodityDetailReqList.size()>0)
			{
				this.insertDetailed(id,manageCommodityDetailReqList);
			}
  		}
  		else
		{
			return Result.error();
		}
		ActivitiUtil.startProcessInstanceByKey(Constants.Key.ALLOT,id);
		return Result.success();
	}
	
	/**
     * 修改调拨单基本
     * @author chenshao
     * @param repertoryManageRequest 调拨单基本信息
     * @return 结果
     */
	@Override
	@Transactional
	public Result updateManageAllotBase(RepertoryManageRequest repertoryManageRequest) throws Exception {
	    //基础信息
        ManageAllotBaseReq manageAllotBaseReq = repertoryManageRequest.getBase();
  		//明细
  		List<ManageCommodityDetailReq> manageCommodityDetailReqList = repertoryManageRequest.getDetail();

  		//插入基础信息，并返回id
  		ManageAllotBase manageAllotBase = BeanUtils.reqToEntity(manageAllotBaseReq, ManageAllotBase.class);
  		BaseUtil.setUpdateBy(manageAllotBase);
  		int count = manageAllotBaseMapper.updateManageAllotBase(manageAllotBase);

  		List<ManageCommodityDetail> manageCommodityDetailList = new ArrayList<ManageCommodityDetail>();
  		if (count<=0)
  		{
  			return Result.error();
  		}
  		 //插入明细
		if (null != manageCommodityDetailReqList &&manageCommodityDetailReqList.size()>0)
		{
  			//拷贝
  			for (ManageCommodityDetailReq manageCommodityDetailReq: manageCommodityDetailReqList)
  			{
  				ManageCommodityDetail manageCommodityDetail = BeanUtils.reqToEntity(manageCommodityDetailReq, ManageCommodityDetail.class);
  				manageCommodityDetail.setParentId(manageAllotBase.getId());
  				manageCommodityDetail.setTotal(manageCommodityDetail.getNumber().multiply(manageCommodityDetail.getPostDiscountPrice()));

				String warehouseId = manageCommodityDetail.getWarehouseId();//仓库表id
				BigDecimal numberNew = manageCommodityDetail.getNumber();//调拨单数量
  				Integer isDelete = manageCommodityDetailReq.getIsDelete();
				if (null != isDelete && Constants.Status.DELETED_INT == isDelete)
				{
					manageCommodityDetail.setStatus(Constants.Status.DELETED_INT);
					warehouseService.increaseInventory(warehouseId, numberNew);//增加库存量
				}

				manageCommodityDetail.setStatus(Constants.Status.NORMAL_INT);
				BaseUtil.setUpdateBy(manageCommodityDetail);
				manageCommodityDetailList.add(manageCommodityDetail);
				Integer id = manageCommodityDetail.getId();//明细id
				//新增的数据
				if (ObjectUtils.isEmpty(id)) {
					warehouseService.reduceInventory(warehouseId, numberNew);//减少库存量
				} else//修改的数据
				{
					//对比前后调拨数量是否有变化 ，如果变化则 相应的增加减少库存
					ManageCommodityDetail detail = manageCommodityDetailMapper.selectManageCommodityDetailById(id);
					BigDecimal numberOld = detail.getNumber();//旧的退货数量
					//退货数量差值
					BigDecimal subtract = numberOld.subtract(numberNew);
					if (subtract.compareTo(BigDecimal.ZERO) > 0) {
						warehouseService.increaseInventory(warehouseId, subtract.abs());
					} else {
						warehouseService.reduceInventory(warehouseId, subtract.abs());
					}
				}
  			}
  			manageCommodityDetailMapper.batchInsertOrUpdate(manageCommodityDetailList);
		}
	 	return Result.success();
	}

	@Override
	@Transactional
	public Result deleteManageAllotByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return Result.error("参数ids不能为空");
        }
		String[] idArray = ids.split(",");
		//删除任务
		ActivitiUtil.deleteTask(Constants.Key.ALLOT,idArray);
		int count = manageAllotBaseMapper.deleteManageAllotBaseByIds(idArray);
		if (count>0)
		{
			manageCommodityDetailMapper.deleteManageCommodityDetailByParentIds(idArray);
		}
		return count > 0 ? Result.success() : Result.error();
	}

	/**
	 * 获取响应数据
	 * @return 响应数据
	 */
	private RepertoryManageResponse getResponseData(ManageAllotBaseResp manageAllotBaseResp,List<ManageCommodityDetailResp> detailedList)
	{
		//添加到结果集
		RepertoryManageResponse repertoryManageResponse = new RepertoryManageResponse();
		repertoryManageResponse.setManageAllotBaseResp(manageAllotBaseResp);
		repertoryManageResponse.setManageCommodityDetailRespList(detailedList);
		return repertoryManageResponse;
	}

	/**
	 * 插入基本信息
	 * @param manageAllotBaseReq
	 * @return 返回主键id
	 */
	public Integer insertBase(ManageAllotBaseReq manageAllotBaseReq)
	{
		//插入基础信息，并返回id
		ManageAllotBase manageAllotBase = BeanUtils.reqToEntity(manageAllotBaseReq, ManageAllotBase.class);
		BaseUtil.setCreateBy(manageAllotBase);
		manageAllotBaseMapper.insertManageAllotBase(manageAllotBase);
		return manageAllotBase.getId();
	}

	/**
	 * 插入明细
	 * @param detailReq
	 */
	public void insertDetailed(Integer parentId,List<ManageCommodityDetailReq> detailReq)
	{
		List<ManageCommodityDetail> manageCommodityDetailList = new ArrayList<ManageCommodityDetail>();
		if (null != parentId && null != detailReq && detailReq.size()>0)
		{
			//拷贝
			for (ManageCommodityDetailReq manageCommodityDetailReq: detailReq)
			{
				ManageCommodityDetail manageCommodityDetail = BeanUtils.reqToEntity(manageCommodityDetailReq, ManageCommodityDetail.class);
				manageCommodityDetail.setParentId(parentId);
				manageCommodityDetail.setTotal(manageCommodityDetail.getNumber().multiply(manageCommodityDetail.getPostDiscountPrice()).setScale(2, BigDecimal.ROUND_HALF_UP));
				BaseUtil.setCreateBy(manageCommodityDetail);
				manageCommodityDetailList.add(manageCommodityDetail);
				//减少库存
				warehouseService.reduceInventory(manageCommodityDetail.getWarehouseId(),manageCommodityDetail.getNumber());
			}
			manageCommodityDetailMapper.batchInsertOrUpdate(manageCommodityDetailList);
		}
	}

	/**
	 * 获取仓库信息数据
	 * @param resp
	 * @return
	 */
	public List<Warehouse> getWarehouse(RepertoryManageResponse resp)
	{
		ManageAllotBaseResp baseResp = resp.getManageAllotBaseResp();
		List<ManageCommodityDetailResp> detailRespList = resp.getManageCommodityDetailRespList();
		List<Warehouse> warehouses = new ArrayList<Warehouse>();

		for (ManageCommodityDetailResp detailed : detailRespList)
		{
			Warehouse warehouse = warehouseMapper.selectWarehouseById(detailed.getWarehouseId());
			warehouse.setWarehouseId(UUID.getUUID());
			warehouse.setNumber(detailed.getNumber());
			warehouse.setPositionId(baseResp.getInWarehouseId());
			warehouse.setSourceDetailedId(detailed.getId());
			warehouse.setDateOfStorage(new Date());
			warehouse.setCreateTime(new Date());
			BaseUtil.setCreateBy(warehouse);
			warehouse.setUpdateBy(null);
			warehouse.setUpdateTime(null);
			warehouse.setBillSource(Constants.BillSource.ALLOT);//标记单据来源
			warehouses.add(warehouse);
		}
		return warehouses;
	}
}
