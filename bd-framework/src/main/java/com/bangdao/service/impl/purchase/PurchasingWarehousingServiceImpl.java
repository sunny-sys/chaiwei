package com.bangdao.service.impl.purchase;

import com.bangdao.common.constant.Constants;
import com.bangdao.common.utils.ActivitiUtil;
import com.bangdao.common.utils.BaseUtil;
import com.bangdao.common.utils.UUID;
import com.bangdao.common.utils.bean.BeanUtils;
import com.bangdao.common.utils.security.ShiroUtils;
import com.bangdao.domain.approval.Approval;
import com.bangdao.domain.purchase.PurchasingWarehousingBase;
import com.bangdao.domain.purchase.PurchasingWarehousingDetailed;
import com.bangdao.domain.system.Warehouse;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.framework.web.page.TableSupport;
import com.bangdao.mapper.purchase.PurchasingWarehousingBaseMapper;
import com.bangdao.mapper.purchase.PurchasingWarehousingDetailedMapper;
import com.bangdao.mapper.system.WarehouseMapper;
import com.bangdao.requestVo.purchase.PurchasingWarehousingBaseRequest;
import com.bangdao.requestVo.purchase.PurchasingWarehousingDetailedRequest;
import com.bangdao.requestVo.purchase.PurchasingWarehousingRequest;
import com.bangdao.requestVo.purchase.PurchasingWarehousingSearchReq;
import com.bangdao.responseVo.approval.ApprovalResp;
import com.bangdao.responseVo.purchasing.PurchasingWarehousingBaseResponse;
import com.bangdao.responseVo.purchasing.PurchasingWarehousingDetailedResponse;
import com.bangdao.responseVo.purchasing.PurchasingWarehousingResponse;
import com.bangdao.service.purchase.IPurchasingWarehousingService;
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
 * 采购入库 服务层实现
 * 
 * @author chaiwei
 * @date 2018-10-25
 */
@Slf4j
@Service
public class PurchasingWarehousingServiceImpl implements IPurchasingWarehousingService {

    @Autowired
	private PurchasingWarehousingBaseMapper purchasingWarehousingBaseMapper;

	@Autowired
	private PurchasingWarehousingDetailedMapper purchasingWarehousingDetailedMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;
	/**
     * 查询采购入库信息
     * @author chaiwei
     * @param id 采购入库ID
     * @return 采购入库信息
     */
    @Override
	public Result selectPurchasingWarehousingById(Integer id) throws Exception {
        if (ObjectUtils.isEmpty(id)){
            return Result.error("参数id不能为空");
        }
        //查询基本信息
		PurchasingWarehousingBaseResponse purchasingWarehousingBase = purchasingWarehousingBaseMapper.selectRelationById(id);
		//查询明细
		PurchasingWarehousingDetailedRequest purchasingWarehousingDetailedRequest = new PurchasingWarehousingDetailedRequest();
		purchasingWarehousingDetailedRequest.setParentId(id);
		purchasingWarehousingDetailedRequest.setStatus(Constants.Status.NORMAL_INT);
		List<PurchasingWarehousingDetailedResponse> purchasingWarehousingDetailedList = purchasingWarehousingDetailedMapper.selectRelationList(purchasingWarehousingDetailedRequest);

		PurchasingWarehousingResponse responseData = getResponseData(purchasingWarehousingBase, purchasingWarehousingDetailedList);

		String businessKey = Constants.Key.PURCHASE_WAREHOUSE+"."+id;
		//查询审批记录
		List<ApprovalResp> approvalHistory = ActivitiUtil.getApprovalHistory(businessKey);
		return Result.success().put("purchasingWarehousing", responseData).put("approvalHistory",approvalHistory);
	}

	/**
     * 查询当前用户任务列表
     * @author chaiwei
     * @param baseRequest 采购入库信息
     * @return 采购入库集合
     */
	@Override
	public List<PurchasingWarehousingBaseResponse> selectBaseList(PurchasingWarehousingBaseRequest baseRequest) throws Exception {

		List<Integer> ids = ActivitiUtil.getTaskListByName(Constants.Key.PURCHASE_WAREHOUSE);
		if(null == ids || ids.size()==0)
		{
			return new ArrayList<PurchasingWarehousingBaseResponse>();
		}
		baseRequest.setIds(ids);
		return purchasingWarehousingBaseMapper.selectRelationList(baseRequest);
	}

	/**
	 * 获取当前用户订单列表
	 * @param baseRequest
	 * @return
	 */
	@Override
	public List<PurchasingWarehousingBaseResponse> selectBillList(PurchasingWarehousingBaseRequest baseRequest) {
		baseRequest.setCreateBy(""+ShiroUtils.getUserId());
		baseRequest.setStatus(Constants.Status.NORMAL_INT);
		return purchasingWarehousingBaseMapper.selectRelationList(baseRequest);
	}

	/**
     * 查询采购入库列表
     * @author chaiwei
     * @param purchasingWarehousing 采购入库信息
     * @return 采购入库集合
     */
	@Override
	public TableDataInfo selectPurchasingWarehousingList(PurchasingWarehousingRequest purchasingWarehousing) throws Exception {
		//基础信息
		PurchasingWarehousingBaseRequest purchasingWarehousingBaseRequest = purchasingWarehousing.getPurchasingWarehousingBase();
		if (null == purchasingWarehousingBaseRequest)//默认查询有效数据
		{
			purchasingWarehousingBaseRequest = new PurchasingWarehousingBaseRequest();
			purchasingWarehousingBaseRequest.setStatus(Constants.Status.NORMAL_INT);
		}

		TableSupport.startPage(purchasingWarehousing);
		List<PurchasingWarehousingBaseResponse> purchasingWarehousingBasesList = purchasingWarehousingBaseMapper.selectRelationList(purchasingWarehousingBaseRequest);

		//存放结果
		List<PurchasingWarehousingResponse> purchasingWarehousingResponseList = new ArrayList<PurchasingWarehousingResponse>();
		//明细
		for (PurchasingWarehousingBaseResponse purchasingWarehousingBaseResponse : purchasingWarehousingBasesList)
		{
			PurchasingWarehousingDetailedRequest purchasingWarehousingDetailedRequest = new PurchasingWarehousingDetailedRequest();
			purchasingWarehousingDetailedRequest.setParentId(purchasingWarehousingBaseResponse.getId());
			purchasingWarehousingDetailedRequest.setStatus(Constants.Status.NORMAL_INT);
			List<PurchasingWarehousingDetailedResponse> purchasingWarehousingDetailedList = purchasingWarehousingDetailedMapper.selectRelationList(purchasingWarehousingDetailedRequest);

			//添加响应到集合中
			PurchasingWarehousingResponse responseData = getResponseData(purchasingWarehousingBaseResponse, purchasingWarehousingDetailedList);
			purchasingWarehousingResponseList.add(responseData);
		}
		return TableDataInfo.success(purchasingWarehousingResponseList,purchasingWarehousingBasesList);
	}

    /**
     * 新增采购入库
     * @author chaiwei
     * @param purchasingWarehousing 采购入库信息
     * @return 结果
     */
	@Override
	@Transactional
	public Result insertPurchasingWarehousing(PurchasingWarehousingRequest purchasingWarehousing) throws Exception {
		//基础信息
		PurchasingWarehousingBaseRequest purchasingWarehousingBaseRequest = purchasingWarehousing.getPurchasingWarehousingBase();
		//明细
		List<PurchasingWarehousingDetailedRequest> purchasingWarehousingDetailedRequestList = purchasingWarehousing.getPurchasingWarehousingDetailed();
		//设置审批状态---草稿状态
		purchasingWarehousingBaseRequest.setApprovalStatus(Constants.ApprovalStatus.DRAFT_INT);
		Integer id = this.batchInsertOrUpdateBase(purchasingWarehousingBaseRequest);
		if (null != id)
		{
			this.batchInsertOrUpdateDetailed(id,purchasingWarehousingDetailedRequestList);
		}
		else
		{
			return Result.error();
		}
		Result result = this.selectPurchasingWarehousingById(id);
		return result;
	}

	/**
	 * 提交采购入库
	 * @param purchasingWarehousing
	 * @return
	 */
	@Override
	@Transactional
	public Result submitPurchasingWarehousing(PurchasingWarehousingRequest purchasingWarehousing) throws Exception {
		//基础信息
		PurchasingWarehousingBaseRequest purchasingWarehousingBaseRequest = purchasingWarehousing.getPurchasingWarehousingBase();
		//明细
		List<PurchasingWarehousingDetailedRequest> purchasingWarehousingDetailedRequestList = purchasingWarehousing.getPurchasingWarehousingDetailed();

		//设置审批状态为审批中
		purchasingWarehousingBaseRequest.setApprovalStatus(Constants.ApprovalStatus.IN_APPROVAL_INT);
		purchasingWarehousingBaseRequest.setSubmitTime(new Date());
		purchasingWarehousingBaseRequest.setSubmitBy(ShiroUtils.getUserId()+"");
		Integer id = this.batchInsertOrUpdateBase(purchasingWarehousingBaseRequest);

		if (null != id)
		{
			this.batchInsertOrUpdateDetailed(id,purchasingWarehousingDetailedRequestList);
		}
		else
		{
			return Result.error();
		}
        //完成提交任务
        ActivitiUtil.finishTask(Constants.Key.PURCHASE_WAREHOUSE+"."+id);
        return Result.success();
	}

	/**
     * 修改采购入库
     * @author chaiwei
     * @param purchasingWarehousing 采购入库信息
     * @return 结果
     */
	@Override
	@Transactional
	public Result updatePurchasingWarehousing(PurchasingWarehousingRequest purchasingWarehousing) throws Exception {
		//基础信息
		PurchasingWarehousingBaseRequest purchasingWarehousingBaseRequest = purchasingWarehousing.getPurchasingWarehousingBase();
		//明细
		List<PurchasingWarehousingDetailedRequest> purchasingWarehousingDetailedRequestList = purchasingWarehousing.getPurchasingWarehousingDetailed();
		//设置审批状态
		purchasingWarehousingBaseRequest.setApprovalStatus(Constants.ApprovalStatus.DRAFT_INT);
		Integer id = this.batchInsertOrUpdateBase(purchasingWarehousingBaseRequest);
		if (null != id)
		{
			this.batchInsertOrUpdateDetailed(id,purchasingWarehousingDetailedRequestList);
		}
		else
		{
			return Result.error();
		}
		Result result = this.selectPurchasingWarehousingById(id);
		return result;
	}

	/**
     * 删除采购入库对象
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@Transactional
	public Result deletePurchasingWarehousingByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return Result.error("参数ids不能为空");
        }
		String[] idArray = ids.split(",");

		//删除个人任务
		ActivitiUtil.deleteTask(Constants.Key.PURCHASE_WAREHOUSE,idArray);

		int count = purchasingWarehousingBaseMapper.deletePurchasingWarehousingBaseByIds(idArray);

        if (count>0)
        {
        	purchasingWarehousingDetailedMapper.deleteByParentId(idArray);
		}
		else
		{
			return Result.error();
		}
		return Result.success();
	}

	/**
	 * @param req
	 * @Des: 采购入库查询
	 * @Author: xupj
	 * @Date: 2018/11/7 17:48
	 */
	@Override
	public TableDataInfo selectCgrkByCondition(PurchasingWarehousingSearchReq req) {
		TableSupport.startPage(req);
		List<PurchasingWarehousingBaseResponse> respList =
				purchasingWarehousingBaseMapper.selectCgrkByCondition(req);
		return TableDataInfo.success(respList);
	}


	/**
	 * 获取响应数据
	 * @return 响应数据
	 */
	private PurchasingWarehousingResponse getResponseData(PurchasingWarehousingBaseResponse baseResp, List<PurchasingWarehousingDetailedResponse> detailedRespList)
	{
		//添加到结果集
		PurchasingWarehousingResponse purchasingWarehousingResp = new PurchasingWarehousingResponse();
		purchasingWarehousingResp.setPurchasingWarehousingBaseResp(baseResp);
		purchasingWarehousingResp.setPurchasingWarehousingDetailedResp(detailedRespList);
		return purchasingWarehousingResp;
	}


	/**
	 * 插入或者更新采购入库明细
	 * @param detailedRequestList
	 * @return
	 */
	@Transactional
	public boolean batchInsertOrUpdateDetailed(Integer parentId,List<PurchasingWarehousingDetailedRequest> detailedRequestList)
	{
		if(null == parentId || null == detailedRequestList || detailedRequestList.size()==0)
		{
			return false;
		}
		//插入明细
		List<PurchasingWarehousingDetailed> purchasingWarehousingDetailedList = new ArrayList<PurchasingWarehousingDetailed>();
		//拷贝
		for (PurchasingWarehousingDetailedRequest purchasingWarehousingDetailedRequest: detailedRequestList)
		{
			PurchasingWarehousingDetailed purchasingWarehousingDetailed = BeanUtils.reqToEntity(purchasingWarehousingDetailedRequest, PurchasingWarehousingDetailed.class);
			//设置父类id
			purchasingWarehousingDetailed.setParentId(parentId);
			Integer isDelete = purchasingWarehousingDetailedRequest.getIsDelete();
			if (null != isDelete && Constants.Status.DELETED_INT == isDelete)
			{
				purchasingWarehousingDetailed.setStatus(Constants.Status.DELETED_INT);
			}
			else
			{
				purchasingWarehousingDetailed.setStatus(Constants.Status.NORMAL_INT);
			}
			//设置创建人或者更新人
			if(null == purchasingWarehousingDetailed.getId())
			{
				BaseUtil.setCreateBy(purchasingWarehousingDetailed);
			}
			else
			{
				BaseUtil.setUpdateBy(purchasingWarehousingDetailed);
			}
			//计算折后价和总价
			BigDecimal number = purchasingWarehousingDetailed.getNumber();
			BigDecimal unitPrice = purchasingWarehousingDetailed.getUnitPrice();
			String discount = purchasingWarehousingDetailed.getDiscount();
			BigDecimal postDiscountPrice = unitPrice.multiply(new BigDecimal(discount).divide(new BigDecimal("10"))).setScale(2,BigDecimal.ROUND_HALF_UP);
			BigDecimal total = postDiscountPrice.multiply(number).setScale(2,BigDecimal.ROUND_HALF_UP);
			purchasingWarehousingDetailed.setPostDiscountPrice(postDiscountPrice);
			purchasingWarehousingDetailed.setTotal(total);
			purchasingWarehousingDetailedList.add(purchasingWarehousingDetailed);
		}
		int i = purchasingWarehousingDetailedMapper.batchInsertOrUpdate(purchasingWarehousingDetailedList);
		return i>0? true : false;
	}

	/**
	 * 插入或者更新基本信息
	 * @param purchasingWarehousingBaseRequest
	 * @return 返回主键
	 */
	@Transactional
	public Integer batchInsertOrUpdateBase(PurchasingWarehousingBaseRequest purchasingWarehousingBaseRequest)
	{
		Integer id = purchasingWarehousingBaseRequest.getId();
		//插入基础信息，并返回id
		PurchasingWarehousingBase purchasingWarehousingBase = BeanUtils.reqToEntity(purchasingWarehousingBaseRequest, PurchasingWarehousingBase.class);
		if (null == id)
		{
			BaseUtil.setCreateBy(purchasingWarehousingBase);
			purchasingWarehousingBaseMapper.insertPurchasingWarehousingBase(purchasingWarehousingBase);
			//启动流程实例
			ActivitiUtil.startProcessInstanceByKey(Constants.Key.PURCHASE_WAREHOUSE,purchasingWarehousingBase.getId());
		}
		else
		{
			BaseUtil.setUpdateBy(purchasingWarehousingBase);
			purchasingWarehousingBaseMapper.updatePurchasingWarehousingBase(purchasingWarehousingBase);
		}
		return purchasingWarehousingBase.getId();
	}

	/**
	 * 审批
	 * @param approval 审批实体
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	public Result approvalPurchasingWarehousing (Approval approval) throws Exception
    {
		approval.setBusinessKey(Constants.Key.PURCHASE_WAREHOUSE+"."+approval.getId());
		//进行审批 返回一个流程实例
		String processInstanceId = ActivitiUtil.approval(approval);

		PurchasingWarehousingBase base = new PurchasingWarehousingBase();
		base.setId(approval.getId());
		//审批动作判断是否为驳回
		String action = approval.getAction();
		if(Constants.ApprovalAction.REJECT.equals(action))
		{
			base.setApprovalStatus(Constants.ApprovalStatus.REJECT_INT);
			purchasingWarehousingBaseMapper.updatePurchasingWarehousingBase(base);
		}
		else
		{
			boolean approvalStatus = ActivitiUtil.processInstanceEnd(processInstanceId);
			//如果审批完则更新审批状态为3
			if(approvalStatus)
			{
				base.setEffectiveTime(new Date());
				base.setApprovalStatus(Constants.ApprovalStatus.ADOPT_INT);
				purchasingWarehousingBaseMapper.updatePurchasingWarehousingBase(base);

				//将商品信息插入到库存表中
                Result result = this.selectPurchasingWarehousingById(approval.getId());
				PurchasingWarehousingResponse purchasingWarehousing = (PurchasingWarehousingResponse) result.get("purchasingWarehousing");
				List<Warehouse> warehouse = this.getWarehouse(purchasingWarehousing);
				warehouseMapper.batchInsertOrUpdate(warehouse);
			}
		}
		return Result.success();
	}

    /**
     * 获取仓库信息数据
     * @param purchasingWarehousing
     * @return
     */
	public List<Warehouse> getWarehouse(PurchasingWarehousingResponse purchasingWarehousing)
    {
        PurchasingWarehousingBaseResponse baseResp = purchasingWarehousing.getPurchasingWarehousingBaseResp();
        List<PurchasingWarehousingDetailedResponse> detailedResps = purchasingWarehousing.getPurchasingWarehousingDetailedResp();
        List<Warehouse> warehouses = new ArrayList<Warehouse>();

        for (PurchasingWarehousingDetailedResponse detailed : detailedResps)
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
			warehouse.setDateOfStorage(new Date());
			warehouse.setBillSource(Constants.BillSource.PURCHASE_WAREHOUSE);//标记单据来源
			warehouses.add(warehouse);
        }
        return warehouses;
    }
}
