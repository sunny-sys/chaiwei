package com.bangdao.service.impl.outWarehouse;

import com.bangdao.common.constant.Constants;
import com.bangdao.common.utils.BaseUtil;
import com.bangdao.common.utils.bean.BeanUtils;
import com.bangdao.domain.outWarehouse.ReceiptBase;
import com.bangdao.domain.outWarehouse.ReceiptDetail;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.framework.web.page.TableSupport;
import com.bangdao.mapper.outWarehouse.ReceiptBaseMapper;
import com.bangdao.mapper.outWarehouse.ReceiptDetailMapper;
import com.bangdao.mapper.outWarehouse.OutWarehouseBaseMapper;
import com.bangdao.mapper.outWarehouse.ReturnBaseMapper;
import com.bangdao.requestVo.outWarehouse.*;
import com.bangdao.responseVo.outWarehouse.*;
import com.bangdao.service.outWarehouse.IReceiptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 收款单 服务层实现
 *
 * @author chaiwei
 * @date 2018-11-13
 */
@Slf4j
@Service
public class ReceiptServiceImpl implements IReceiptService {

	/**
	 * 基本表
	 */
    @Autowired
	private ReceiptBaseMapper baseMapper;

	/**
	 * 明细
	 */
	@Autowired
	private ReceiptDetailMapper detailMapper;

	/**
	 * 退货
	 */
	@Autowired
	private ReturnBaseMapper saleReturnBaseMapper;

	/**
	 * 出库
	 */
	@Autowired
	private OutWarehouseBaseMapper saleOutWarehouseBaseMapper;
	/**
     * 查询收款单信息
     * @author chaiwei
     * @param id 收款单ID
     * @return 收款单信息
     */
    @Override
	public Result selectReceiptById(Integer id) throws Exception {
		if (ObjectUtils.isEmpty(id)){
			return Result.error("参数id不能为空");
		}
		//查询基本信息
		ReceiptBaseResp baseResp = baseMapper.selectRelationById(id);

		//查询明细
		List<ReceiptDetail> detailsList = this.getDetailList(baseResp.getId());

		//拷贝数据
		List<ReceiptDetailResp> detailsResp = new ArrayList<ReceiptDetailResp>();
		BeanUtils.copyListBeanProp(detailsList,detailsResp,ReceiptDetailResp.class);

		ReceiptResp responseData = this.getResponseData(baseResp, detailsResp);
		return Result.success().put("billPayment", responseData);
	}

	/**
	 * 根据供应商查询收款单（包含出库，还退货）
	 * @author chaiwei
	 * @param receipt 供应商id
	 * @return 出库，退货
	 */
    @Override
	public Result getReturnAndWarehouse(ReceiptReq receipt) throws Exception {
		if (ObjectUtils.isEmpty(receipt.getSupplierId())){
			return Result.error("参数supplierId不能为空");
		}
		/**
		 * 查询退货
		 */
		ReturnBaseReq saleReturnBaseReq= new ReturnBaseReq();
		saleReturnBaseReq.setSupplierId(receipt.getSupplierId());
		saleReturnBaseReq.setStartDate(receipt.getStartDate());
		saleReturnBaseReq.setEndDate(receipt.getEndDate());
		List<ReturnBaseResp> saleReturnBaseResps = saleReturnBaseMapper.selectRelationList(saleReturnBaseReq);

		/**
		 * 查询出库
		 */
		OutWarehouseBaseReq outWarehouseBaseReq = new OutWarehouseBaseReq();
		outWarehouseBaseReq.setSupplierId(receipt.getSupplierId());
		outWarehouseBaseReq.setStartDate(receipt.getStartDate());
		outWarehouseBaseReq.setEndDate(receipt.getEndDate());
		List<OutWarehouseBaseResp> outWarehouseBaseResps = saleOutWarehouseBaseMapper.selectRelationList(outWarehouseBaseReq);

		return Result.success().put("purchaseReturn", saleReturnBaseResps).put("purchasingWarehousing", outWarehouseBaseResps);
	}

	/**
     * 查询收款单列表
     * @author chaiwei
     * @param billPayment 收款单信息
     * @return 收款单集合
     */
	@Override
	public TableDataInfo selectReceiptList(ReceiptReq billPayment) throws Exception {

		ReceiptBaseReq baseReq = billPayment.getBaseReq();
		if (null == baseReq)
		{
			baseReq = new ReceiptBaseReq();
			baseReq.setStatus(Constants.Status.NORMAL_INT);
		}

		TableSupport.startPage(billPayment);
		List<ReceiptBaseResp> BaseRespList = baseMapper.selectRelationList(baseReq);

		//存放结果集
		List<ReceiptResp> temp = new ArrayList<ReceiptResp>();
		for (ReceiptBaseResp baseResp:BaseRespList)
		{
			List<ReceiptDetail> detailsList = this.getDetailList(baseResp.getId());

			//拷贝数据
			List<ReceiptDetailResp> detailsResp = new ArrayList<ReceiptDetailResp>();
			BeanUtils.copyListBeanProp(detailsList,detailsResp,ReceiptDetailResp.class);
			ReceiptResp responseData = this.getResponseData(baseResp, detailsResp);
			temp.add(responseData);
		}
	    return TableDataInfo.success(temp,BaseRespList);
	}

    /**
     * 新增收款单
     * @author chaiwei
     * @param billPayment 收款单信息
     * @return 结果
     */
	@Override
	@Transactional
	public Result insertReceipt(ReceiptReq billPayment) throws Exception {
		//保存基本信息
		ReceiptBaseReq baseReq = billPayment.getBaseReq();
		ReceiptBase base = BeanUtils.reqToEntity(baseReq, ReceiptBase.class);
		int count = baseMapper.insertReceiptBase(base);
		if(count>0)
		{
			/*保存明细*/
			List<ReceiptDetailReq> detailReqList = billPayment.getDetailReq();
			if (null != detailReqList && detailReqList.size()>0)
			{
				List<ReceiptDetail> detailList = new ArrayList<ReceiptDetail>();
				for (ReceiptDetailReq detailReq:detailReqList)
				{
					ReceiptDetail detail = BeanUtils.reqToEntity(detailReq, ReceiptDetail.class);
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
		Result result = this.selectReceiptById(base.getId());
		return result;
	}

	/**
     * 修改收款单
     * @author chaiwei
     * @param billPayment 收款单信息
     * @return 结果
     */
	@Override
	@Transactional
	public Result updateReceipt(ReceiptReq billPayment) throws Exception {
		//更新基本信息
		ReceiptBaseReq baseReq = billPayment.getBaseReq();
		ReceiptBase base = BeanUtils.reqToEntity(baseReq, ReceiptBase.class);
		int count = baseMapper.updateReceiptBase(base);
		if(count>0)
		{
			/*保存明细*/
			List<ReceiptDetailReq> detailReqList = billPayment.getDetailReq();
			if (null != detailReqList && detailReqList.size()>0)
			{
				List<ReceiptDetail> detailList = new ArrayList<ReceiptDetail>();
				for (ReceiptDetailReq detailReq:detailReqList)
				{
					ReceiptDetail detail = BeanUtils.reqToEntity(detailReq, ReceiptDetail.class);
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
		Result result = this.selectReceiptById(base.getId());
		return result;
	}

	/**
     * 删除收款单对象
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@Transactional
	public Result deleteReceiptByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return Result.error("参数ids不能为空");
        }
		int count = baseMapper.deleteReceiptBaseByIds(ids.split(","));
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
	private  List<ReceiptDetail> getDetailList(Integer parentId)
	{
		if (ObjectUtils.isEmpty(parentId)){
			return null;
		}
		//查询明细
		ReceiptDetailReq detailReq = new ReceiptDetailReq();
		detailReq.setStatus(Constants.Status.NORMAL_INT);
		detailReq.setParentId(parentId);
		List<ReceiptDetail> detailsList = detailMapper.selectReceiptDetailList(detailReq);
		return detailsList;
	}


	/**
	 * 获取响应数据
	 * @return 响应数据
	 */
	private ReceiptResp getResponseData(ReceiptBaseResp baseResp, List<ReceiptDetailResp> detailResp)
	{
		//添加到结果集
		ReceiptResp billPaymentResp = new ReceiptResp();
		billPaymentResp.setBaseResp(baseResp);
		billPaymentResp.setDetailResp(detailResp);
		return billPaymentResp;
	}
}
