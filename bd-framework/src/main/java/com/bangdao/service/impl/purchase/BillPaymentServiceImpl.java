package com.bangdao.service.impl.purchase;

import com.bangdao.common.constant.Constants;
import com.bangdao.common.utils.BaseUtil;
import com.bangdao.common.utils.bean.BeanUtils;
import com.bangdao.domain.purchase.BillPaymentBase;
import com.bangdao.domain.purchase.BillPaymentDetail;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.framework.web.page.TableSupport;
import com.bangdao.mapper.purchase.BillPaymentBaseMapper;
import com.bangdao.mapper.purchase.BillPaymentDetailMapper;
import com.bangdao.mapper.purchase.PurchaseReturnBaseMapper;
import com.bangdao.mapper.purchase.PurchasingWarehousingBaseMapper;
import com.bangdao.requestVo.purchase.*;
import com.bangdao.responseVo.purchasing.*;
import com.bangdao.service.purchase.IBillPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 付款单 服务层实现
 *
 * @author chaiwei
 * @date 2018-11-13
 */
@Slf4j
@Service
public class BillPaymentServiceImpl implements IBillPaymentService {

	/**
	 * 基本表
	 */
    @Autowired
	private BillPaymentBaseMapper baseMapper;

	/**
	 * 明细
	 */
	@Autowired
	private BillPaymentDetailMapper detailMapper;

	/**
	 * 采购退货
	 */
	@Autowired
	private PurchaseReturnBaseMapper purchaseReturnBaseMapper;

	/**
	 * 采购入库
	 */
	@Autowired
	private PurchasingWarehousingBaseMapper purchasingWarehousingBaseMapper;
	/**
     * 查询付款单信息
     * @author chaiwei
     * @param id 付款单ID
     * @return 付款单信息
     */
    @Override
	public Result selectBillPaymentById(Integer id) throws Exception {
		if (ObjectUtils.isEmpty(id)){
			return Result.error("参数id不能为空");
		}
		//查询基本信息
		BillPaymentBaseResp baseResp = baseMapper.selectRelationById(id);

		//查询明细
		List<BillPaymentDetail> detailsList = this.getDetailList(baseResp.getId());

		//拷贝数据
		List<BillPaymentDetailResp> detailsResp = new ArrayList<BillPaymentDetailResp>();
		BeanUtils.copyListBeanProp(detailsList,detailsResp,BillPaymentDetailResp.class);

		BillPaymentResp responseData = this.getResponseData(baseResp, detailsResp);
		return Result.success().put("billPayment", responseData);
	}

	/**
	 * 根据供应商查询付款单（包含采购入库，还采购退货）
	 * @author chaiwei
	 * @param billPayment
	 * @return 采购入库，采购退货
	 */
    @Override
	public Result getReturnAndWarehouse(BillPaymentReq billPayment) throws Exception {
		if (ObjectUtils.isEmpty(billPayment.getSupplierId())){
			return Result.error("参数supplierId不能为空");
		}
		/**
		 * 查询退货
		 */

		PurchaseReturnBaseReq returnBaseReq = new PurchaseReturnBaseReq();
		returnBaseReq.setSupplierId(billPayment.getSupplierId());
		returnBaseReq.setStartDate(billPayment.getStartDate());
		returnBaseReq.setEndDate(billPayment.getEndDate());
		List<PurchaseReturnBaseResp> ReturnBaseResps = purchaseReturnBaseMapper.selectRelationList(returnBaseReq);

		/**
		 * 查询入库
		 */
		PurchasingWarehousingBaseRequest warehousingBaseRequest = new PurchasingWarehousingBaseRequest();
		warehousingBaseRequest.setSupplierId(billPayment.getSupplierId());
		warehousingBaseRequest.setStartDate(billPayment.getStartDate());
		warehousingBaseRequest.setEndDate(billPayment.getEndDate());
		List<PurchasingWarehousingBaseResponse> warehousingBaseResponses = purchasingWarehousingBaseMapper.selectRelationList(warehousingBaseRequest);

		return Result.success().put("purchaseReturn", ReturnBaseResps).put("purchasingWarehousing",warehousingBaseResponses);
	}

	/**
     * 查询付款单列表
     * @author chaiwei
     * @param billPayment 付款单信息
     * @return 付款单集合
     */
	@Override
	public TableDataInfo selectBillPaymentList(BillPaymentReq billPayment) throws Exception {

		BillPaymentBaseReq baseReq = billPayment.getBaseReq();
		if (null == baseReq)
		{
			baseReq = new BillPaymentBaseReq();
			baseReq.setStatus(Constants.Status.NORMAL_INT);
		}

		TableSupport.startPage(billPayment);
		List<BillPaymentBaseResp> BaseRespList = baseMapper.selectRelationList(baseReq);

		//存放结果集
		List<BillPaymentResp> temp = new ArrayList<BillPaymentResp>();
		for (BillPaymentBaseResp baseResp:BaseRespList)
		{
			List<BillPaymentDetail> detailsList = this.getDetailList(baseResp.getId());

			//拷贝数据
			List<BillPaymentDetailResp> detailsResp = new ArrayList<BillPaymentDetailResp>();
			BeanUtils.copyListBeanProp(detailsList,detailsResp,BillPaymentDetailResp.class);
			BillPaymentResp responseData = this.getResponseData(baseResp, detailsResp);
			temp.add(responseData);
		}
	    return TableDataInfo.success(temp,BaseRespList);
	}

    /**
     * 新增付款单
     * @author chaiwei
     * @param billPayment 付款单信息
     * @return 结果
     */
	@Override
	@Transactional
	public Result insertBillPayment(BillPaymentReq billPayment) throws Exception {
		//保存基本信息
		BillPaymentBaseReq baseReq = billPayment.getBaseReq();
		BillPaymentBase base = BeanUtils.reqToEntity(baseReq, BillPaymentBase.class);
		int count = baseMapper.insertBillPaymentBase(base);
		if(count>0)
		{
			/*保存明细*/
			List<BillPaymentDetailReq> detailReqList = billPayment.getDetailReq();
			if (null != detailReqList && detailReqList.size()>0)
			{
				List<BillPaymentDetail> detailList = new ArrayList<BillPaymentDetail>();
				for (BillPaymentDetailReq detailReq:detailReqList)
				{
					BillPaymentDetail detail = BeanUtils.reqToEntity(detailReq, BillPaymentDetail.class);
					detail.setParentId(base.getId());
					BaseUtil.setCreateBy(detail);
					detailList.add(detail);
				}
				detailMapper.batchInsertOrUpdate(detailList);
			}
		}
		else
		{
			return Result.error();
		}
		Result result = this.selectBillPaymentById(base.getId());
		return result;
	}

	/**
     * 修改付款单
     * @author chaiwei
     * @param billPayment 付款单信息
     * @return 结果
     */
	@Override
	@Transactional
	public Result updateBillPayment(BillPaymentReq billPayment) throws Exception {
		//更新基本信息
		BillPaymentBaseReq baseReq = billPayment.getBaseReq();
		BillPaymentBase base = BeanUtils.reqToEntity(baseReq, BillPaymentBase.class);
		int count = baseMapper.updateBillPaymentBase(base);
		if(count>0)
		{
			/*保存明细*/
			List<BillPaymentDetailReq> detailReqList = billPayment.getDetailReq();
			if (null != detailReqList && detailReqList.size()>0)
			{
				List<BillPaymentDetail> detailList = new ArrayList<BillPaymentDetail>();
				for (BillPaymentDetailReq detailReq:detailReqList)
				{
					BillPaymentDetail detail = BeanUtils.reqToEntity(detailReq, BillPaymentDetail.class);
					detail.setParentId(base.getId());
					Integer isDelete = detailReq.getIsDelete();
					if (null != isDelete && Constants.Status.DELETED_INT == isDelete)//判断是否为删除
					{
						detail.setStatus(Constants.Status.DELETED_INT);
					}
					BaseUtil.setUpdateBy(detail);
					detailList.add(detail);
				}
				detailMapper.batchInsertOrUpdate(detailList);
			}
		}
		else
		{
			return Result.error();
		}
		Result result = this.selectBillPaymentById(base.getId());
		return result;
	}

	/**
     * 删除付款单对象
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@Transactional
	public Result deleteBillPaymentByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return Result.error("参数ids不能为空");
        }
		int count = baseMapper.deleteBillPaymentBaseByIds(ids.split(","));
        if(count>0)
        {
			detailMapper.deleteByParentId(ids.split(","));
		}
		else
		{
			return Result.error();
		}
		return Result.success();
	}

	/**
	 * 查询明细集合
	 * @param parentId
	 * @return
	 */
	private  List<BillPaymentDetail> getDetailList(Integer parentId)
	{
		if (ObjectUtils.isEmpty(parentId)){
			return null;
		}
		//查询明细
		BillPaymentDetailReq detailReq = new BillPaymentDetailReq();
		detailReq.setStatus(Constants.Status.NORMAL_INT);
		detailReq.setParentId(parentId);
		List<BillPaymentDetail> detailsList = detailMapper.selectBillPaymentDetailList(detailReq);
		return detailsList;
	}


	/**
	 * 获取响应数据
	 * @return 响应数据
	 */
	private BillPaymentResp getResponseData(BillPaymentBaseResp baseResp, List<BillPaymentDetailResp> detailResp)
	{
		//添加到结果集
		BillPaymentResp billPaymentResp = new BillPaymentResp();
		billPaymentResp.setBaseResp(baseResp);
		billPaymentResp.setDetailResp(detailResp);
		return billPaymentResp;
	}
}
