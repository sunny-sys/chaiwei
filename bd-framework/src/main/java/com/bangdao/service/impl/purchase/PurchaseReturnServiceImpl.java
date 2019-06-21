package com.bangdao.service.impl.purchase;

import com.bangdao.common.constant.Constants;
import com.bangdao.common.utils.ActivitiUtil;
import com.bangdao.common.utils.BaseUtil;
import com.bangdao.common.utils.UUID;
import com.bangdao.common.utils.bean.BeanUtils;
import com.bangdao.common.utils.security.ShiroUtils;
import com.bangdao.domain.approval.Approval;
import com.bangdao.domain.purchase.PurchaseReturnBase;
import com.bangdao.domain.purchase.PurchaseReturnDetailed;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.framework.web.page.TableSupport;
import com.bangdao.mapper.purchase.PurchaseReturnBaseMapper;
import com.bangdao.mapper.purchase.PurchaseReturnDetailedMapper;
import com.bangdao.requestVo.purchase.*;
import com.bangdao.responseVo.approval.ApprovalResp;
import com.bangdao.responseVo.purchasing.PurchaseReturnBaseResp;
import com.bangdao.responseVo.purchasing.PurchaseReturnDetailedResp;
import com.bangdao.responseVo.purchasing.PurchaseReturnResp;
import com.bangdao.service.purchase.IPurchaseReturnService;
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
 * 采购退货 服务层实现
 *
 * @author chaiwei
 * @date 2018-11-07
 */
@Slf4j
@Service
public class PurchaseReturnServiceImpl implements IPurchaseReturnService {

    @Autowired
    private PurchaseReturnBaseMapper purchaseReturnBaseMapper;
    @Autowired
    private PurchaseReturnDetailedMapper purchaseReturnDetailedMapper;
    @Autowired
    private IWarehouseService warehouseService;

    /**
     * 查询采购退货信息
     *
     * @param id 采购退货ID
     * @return 采购退货信息
     * @author chaiwei
     */
    @Override
    public Result selectPurchaseReturnById(Integer id) throws Exception {
        if (ObjectUtils.isEmpty(id)) {
            return Result.error("参数id不能为空");
        }
        //查询基本信息
        PurchaseReturnBaseResp purchaseReturnBaseResp = purchaseReturnBaseMapper.selectRelationById(id);

        //查询明细
        PurchaseReturnDetailedReq purchaseReturnDetailedReq = new PurchaseReturnDetailedReq();
        purchaseReturnDetailedReq.setParentId(id);
        purchaseReturnDetailedReq.setStatus(Constants.Status.NORMAL_INT);
        List<PurchaseReturnDetailedResp> purchaseReturnDetailedResps = purchaseReturnDetailedMapper.selectRelationList(purchaseReturnDetailedReq);

        String businessKey = Constants.Key.PURCHASE_RETURN+"."+id;
        //查询审批记录
        List<ApprovalResp> approvalHistory = ActivitiUtil.getApprovalHistory(businessKey);
        PurchaseReturnResp responseData = getResponseData(purchaseReturnBaseResp, purchaseReturnDetailedResps);
        return Result.success().put("purchaseReturn", responseData).put("approvalHistory", approvalHistory);
    }

    /**
     * 获取当前用户任务列表
     *
     * @param baseReq 采购退货信息
     * @return 采购退货集合
     * @author chaiwei
     */
    @Override
    public List<PurchaseReturnBaseResp> selectBaseList(PurchaseReturnBaseReq baseReq) throws Exception {
        List<Integer> ids = ActivitiUtil.getTaskListByName(Constants.Key.PURCHASE_RETURN);
        if(null == ids || ids.size()==0)
        {
            return new ArrayList<PurchaseReturnBaseResp>();
        }
        baseReq.setIds(ids);
        return purchaseReturnBaseMapper.selectRelationList(baseReq);
    }

    /**
     * 获取当前用户订单列表
     *
     * @param baseReq 采购退货信息
     * @return 采购退货集合
     * @author chaiwei
     */
    @Override
    public List<PurchaseReturnBaseResp> selectBillList(PurchaseReturnBaseReq baseReq) {
        baseReq.setCreateBy("" + ShiroUtils.getUserId());
        baseReq.setStatus(Constants.Status.NORMAL_INT);
        return purchaseReturnBaseMapper.selectRelationList(baseReq);
    }

    /**
     * 查询采购退货列表
     *
     * @param purchaseReturn 采购退货信息
     * @return 采购退货集合
     * @author chaiwei
     */
    @Override
    public TableDataInfo selectPurchaseReturnList(PurchaseReturnReq purchaseReturn) throws Exception {
        //基础信息
        PurchaseReturnBaseReq purchaseReturnBaseReq = purchaseReturn.getPurchaseReturnBaseReq();
        if (null == purchaseReturnBaseReq)//默认查询有效数据
        {
            purchaseReturnBaseReq = new PurchaseReturnBaseReq();
            purchaseReturnBaseReq.setStatus(Constants.Status.NORMAL_INT);
        } else if (null == purchaseReturnBaseReq.getStatus()) {
            purchaseReturnBaseReq.setStatus(Constants.Status.NORMAL_INT);
        }

        TableSupport.startPage(purchaseReturn);
        List<PurchaseReturnBaseResp> purchaseReturnBaseResps = purchaseReturnBaseMapper.selectRelationList(purchaseReturnBaseReq);

        //存放结果
        List<PurchaseReturnResp> purchasineReturnRespList = new ArrayList<PurchaseReturnResp>();
        //明细
        for (PurchaseReturnBaseResp purchaseReturnBaseResp : purchaseReturnBaseResps) {
            PurchaseReturnDetailedReq purchaseReturnDetailedReq = new PurchaseReturnDetailedReq();
            purchaseReturnDetailedReq.setParentId(purchaseReturnBaseResp.getId());
            purchaseReturnDetailedReq.setStatus(Constants.Status.NORMAL_INT);
            List<PurchaseReturnDetailedResp> purchaseReturnDetailedResps = purchaseReturnDetailedMapper.selectRelationList(purchaseReturnDetailedReq);

            //添加响应到集合中
            PurchaseReturnResp responseData = getResponseData(purchaseReturnBaseResp, purchaseReturnDetailedResps);
            purchasineReturnRespList.add(responseData);
        }
        return TableDataInfo.success(purchasineReturnRespList, purchaseReturnBaseResps);
    }

    /**
     * 新增采购退货
     *
     * @param purchaseReturn 采购退货信息
     * @return 结果
     * @author chaiwei
     */
    @Override
    @Transactional
    public Result insertPurchaseReturn(PurchaseReturnReq purchaseReturn) throws Exception {
        //基础信息
        PurchaseReturnBaseReq purchaseReturnBaseReq = purchaseReturn.getPurchaseReturnBaseReq();
        //明细
        List<PurchaseReturnDetailedReq> purchaseReturnDetailedReqList = purchaseReturn.getPurchaseReturnDetailedReq();

        //插入基础信息，并返回id
        PurchaseReturnBase purchaseReturnBase = BeanUtils.reqToEntity(purchaseReturnBaseReq, PurchaseReturnBase.class);
        purchaseReturnBase.setApprovalStatus(Constants.ApprovalStatus.DRAFT_INT);
        BaseUtil.setCreateBy(purchaseReturnBase);
        int count = purchaseReturnBaseMapper.insertPurchaseReturnBase(purchaseReturnBase);

        List<PurchaseReturnDetailed> purchaseReturnDetailedList = new ArrayList<PurchaseReturnDetailed>();
        if (count > 0) {
            //插入明细
            if (null != purchaseReturnDetailedReqList && purchaseReturnDetailedReqList.size() > 0) {
                //拷贝
                for (PurchaseReturnDetailedReq purchaseReturnDetailedReq : purchaseReturnDetailedReqList) {
                    PurchaseReturnDetailed purchaseReturnDetailed = BeanUtils.reqToEntity(purchaseReturnDetailedReq, PurchaseReturnDetailed.class);
                    //设置父类主键
                    purchaseReturnDetailed.setParentId(purchaseReturnBase.getId());
                    purchaseReturnDetailed.setId(UUID.getUUID());
                    BaseUtil.setCreateBy(purchaseReturnDetailed);
                    purchaseReturnDetailed.setTotal(purchaseReturnDetailed.getNumber().multiply(purchaseReturnDetailed.getPostDiscountPrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
                    purchaseReturnDetailedList.add(purchaseReturnDetailed);

                    String warehouseId = purchaseReturnDetailed.getWarehouseId();
                    BigDecimal number = purchaseReturnDetailed.getNumber();
                    warehouseService.reduceInventory(warehouseId, number);//减少库存量
                }
                purchaseReturnDetailedMapper.batchInsertOrUpdate(purchaseReturnDetailedList);
            }
        } else {
            return Result.error();
        }

        //启动流程实例
        ActivitiUtil.startProcessInstanceByKey(Constants.Key.PURCHASE_RETURN,purchaseReturnBase.getId());

        Result result = this.selectPurchaseReturnById(purchaseReturnBase.getId());
        return result;
    }

    /**
     * 修改采购退货
     *
     * @param purchaseReturn 采购退货信息
     * @return 结果
     * @author chaiwei
     */
    @Override
    @Transactional
    public Result updatePurchaseReturn(PurchaseReturnReq purchaseReturn) throws Exception {
        //基础信息
        PurchaseReturnBaseReq purchaseReturnBaseReq = purchaseReturn.getPurchaseReturnBaseReq();
        //明细
        List<PurchaseReturnDetailedReq> purchaseReturnDetailedReqList = purchaseReturn.getPurchaseReturnDetailedReq();

        //插入基础信息，并返回id
        PurchaseReturnBase purchaseReturnBase = BeanUtils.reqToEntity(purchaseReturnBaseReq, PurchaseReturnBase.class);
        BaseUtil.setUpdateBy(purchaseReturnBase);
        purchaseReturnBase.setApprovalStatus(Constants.ApprovalStatus.DRAFT_INT);
        int count = purchaseReturnBaseMapper.updatePurchaseReturnBase(purchaseReturnBase);
        if (count > 0) {
            this.batchInsertOrUpdateDetailed(purchaseReturnBase.getId(), purchaseReturnDetailedReqList);
        } else {
            return Result.error();
        }
        Result result = this.selectPurchaseReturnById(purchaseReturnBase.getId());
        return result;
    }

    /**
     * 删除采购退货对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     * @author chaiwei
     */
    @Override
    @Transactional
    public Result deletePurchaseReturnByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)) {
            return Result.error("参数ids不能为空");
        }

        String[] idArray = ids.split(",");
        //删除个人任务
        ActivitiUtil.deleteTask(Constants.Key.PURCHASE_WAREHOUSE,idArray);

        int count = purchaseReturnBaseMapper.deletePurchaseReturnBaseByIds(idArray);

        if (count > 0) {
            purchaseReturnDetailedMapper.deleteByParentId(idArray);
        } else {
            return Result.error();
        }
       return Result.success();
    }

    /**
     * @param request
     * @Des: 采购退货查询
     * @Author: xupj
     * @Date: 2018/11/7 17:55
     */
    @Override
    public TableDataInfo selectCgthByCondition(PurchaseReturnBaseSearchReq request) {
        TableSupport.startPage(request);
        List<PurchaseReturnBaseResp> respList = purchaseReturnBaseMapper.selectCgthByCondition(request);
        return TableDataInfo.success(respList);
    }

    /**
     * 审批
     *
     * @param approval
     * @return
     */
    @Override
    @Transactional
    public Result approval(Approval approval) {
        approval.setBusinessKey(Constants.Key.PURCHASE_RETURN + "." + approval.getId());
        //进行审批 返回一个流程实例
        String processInstanceId = ActivitiUtil.approval(approval);
        PurchaseReturnBase base = new PurchaseReturnBase();
        base.setId(approval.getId());
        //审批动作判断是否为驳回
        String action = approval.getAction();
        if (Constants.ApprovalAction.REJECT.equals(action)) {
            base.setApprovalStatus(Constants.ApprovalStatus.REJECT_INT);
            //驳回需要将退货单从新入库
            PurchaseReturnDetailedReq detailedReq = new PurchaseReturnDetailedReq();
            detailedReq.setParentId(base.getId());
            detailedReq.setStatus(Constants.Status.NORMAL_INT);
            List<PurchaseReturnDetailed> detaileds = purchaseReturnDetailedMapper.selectPurchaseReturnDetailedList(detailedReq);
            for (PurchaseReturnDetailed detailed : detaileds) {
                warehouseService.increaseInventory(detailed.getWarehouseId(), detailed.getNumber());
            }
            purchaseReturnBaseMapper.updatePurchaseReturnBase(base);
        }
        else
        {
            //判断审批是否结束
            boolean approvalStatus = ActivitiUtil.processInstanceEnd(processInstanceId);
            //如果审批完则更新审批状态为3
            if (approvalStatus) {
                base.setEffectiveTime(new Date());
                base.setApprovalStatus(Constants.ApprovalStatus.ADOPT_INT);
                purchaseReturnBaseMapper.updatePurchaseReturnBase(base);
            }
        }
        return Result.success();
    }

    /**
     * 提交
     *
     * @param purchaseReturn
     * @return
     */
    @Override
    @Transactional
    public Result submit(PurchaseReturnReq purchaseReturn) {
        //基础信息
        PurchaseReturnBaseReq purchaseReturnBaseReq = purchaseReturn.getPurchaseReturnBaseReq();
        //明细
        List<PurchaseReturnDetailedReq> purchaseReturnDetailedReqList = purchaseReturn.getPurchaseReturnDetailedReq();

        //设置审批状态为审批中
        PurchaseReturnBase base = BeanUtils.reqToEntity(purchaseReturnBaseReq, PurchaseReturnBase.class);
        base.setApprovalStatus(Constants.ApprovalStatus.IN_APPROVAL_INT);
        base.setSubmitTime(new Date());
        base.setSubmitBy(ShiroUtils.getUserId()+"");
        Integer id = this.batchInsertOrUpdateBase(base);
        if (null != id) {
            this.batchInsertOrUpdateDetailed(id, purchaseReturnDetailedReqList);
        } else {
            return Result.error();
        }
        //完成提交任务
        ActivitiUtil.finishTask(Constants.Key.PURCHASE_RETURN+"."+id);
        return Result.success();
    }

    /**
     * 插入或更新基本信息
     *
     * @param base
     * @return
     */
    @Transactional
    public Integer batchInsertOrUpdateBase(PurchaseReturnBase base) {
        Integer id = base.getId();
        if (null == id)//说明新增
        {
            //插入基础信息，并返回id
            id = purchaseReturnBaseMapper.insertPurchaseReturnBase(base);
        } else {
            purchaseReturnBaseMapper.updatePurchaseReturnBase(base);
        }
        return id;
    }

    /**
     * 插入或更新明细
     */
    @Transactional
    public void batchInsertOrUpdateDetailed(Integer parentId, List<PurchaseReturnDetailedReq> purchaseReturnDetailedReqList) {
        //插入明细
        if (null != parentId && null != purchaseReturnDetailedReqList && purchaseReturnDetailedReqList.size() > 0) {
            //插入明细
            List<PurchaseReturnDetailed> purchaseReturnDetailedList = new ArrayList<PurchaseReturnDetailed>();
            //拷贝
            for (PurchaseReturnDetailedReq purchaseReturnDetailedReq : purchaseReturnDetailedReqList) {
                PurchaseReturnDetailed purchaseReturnDetailed = BeanUtils.reqToEntity(purchaseReturnDetailedReq, PurchaseReturnDetailed.class);
                //设置父类id
                purchaseReturnDetailed.setParentId(parentId);

                String warehouseId = purchaseReturnDetailed.getWarehouseId();//仓库表id
                BigDecimal numberNew = purchaseReturnDetailed.getNumber();//新的退货数量
                Integer isDelete = purchaseReturnDetailedReq.getIsDelete();//判断是否删除
                //删除的数据
                if (null != isDelete && Constants.Status.DELETED_INT == isDelete) {
                    purchaseReturnDetailed.setStatus(Constants.Status.DELETED_INT);
                    warehouseService.increaseInventory(warehouseId, numberNew);//增加库存量
                } else //修改或者新增的数据
                {
                    purchaseReturnDetailed.setStatus(Constants.Status.NORMAL_INT);
                    //新增的数据
                    if (ObjectUtils.isEmpty(purchaseReturnDetailed.getId())) {
                        purchaseReturnDetailed.setId(UUID.getUUID());
                        warehouseService.reduceInventory(warehouseId, numberNew);//减少库存量
                    } else//修改的数据
                    {
                        //对比前后退货数量是否有变化 ，如果变化则 相应的增加减少库存
                        PurchaseReturnDetailed detailed = purchaseReturnDetailedMapper.selectPurchaseReturnDetailedById(purchaseReturnDetailed.getId());
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
                BaseUtil.setUpdateBy(purchaseReturnDetailed);
                purchaseReturnDetailed.setTotal(purchaseReturnDetailed.getNumber()
                        .multiply(purchaseReturnDetailed.getPostDiscountPrice())
                        .setScale(2,BigDecimal.ROUND_HALF_UP));
                purchaseReturnDetailedList.add(purchaseReturnDetailed);
            }
            purchaseReturnDetailedMapper.batchInsertOrUpdate(purchaseReturnDetailedList);
        }
    }

    /**
     * 获取响应数据
     *
     * @return 响应数据
     */
    private PurchaseReturnResp getResponseData(PurchaseReturnBaseResp baseResp, List<PurchaseReturnDetailedResp> detailedRespList) {
        //添加到结果集
        PurchaseReturnResp purchaseReturnResp = new PurchaseReturnResp();
        purchaseReturnResp.setPurchaseReturnBaseResp(baseResp);
        purchaseReturnResp.setPurchaseReturnDetailedResp(detailedRespList);
        return purchaseReturnResp;
    }
}
