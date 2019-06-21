package com.bangdao.mapper.purchase;

import com.bangdao.domain.purchase.BillPaymentDetail;
import com.bangdao.requestVo.purchase.BillPaymentDetailReq;

import java.util.List;


/**
 * 付款单详情 数据层
 * 
 * @author chaiwei
 * @date 2018-11-13
 */
public interface BillPaymentDetailMapper {
	/**
     * 查询付款单详情信息
     * @author chaiwei
     * @param id 付款单详情ID
     * @return 付款单详情信息
     */
	public BillPaymentDetail selectBillPaymentDetailById(Integer id);

	/**
     * 查询付款单详情列表
     * @author chaiwei
     * @param billPaymentDetail 付款单详情信息
     * @return 付款单详情集合
     */
	public List<BillPaymentDetail> selectBillPaymentDetailList(BillPaymentDetailReq billPaymentDetail);

	/**
	 * 查询付款单详情信息(关联商品，仓库表)
	 * @author chaiwei
	 * @param id 采购退货详情ID
	 * @return 采购退货详情信息
	 */
	public BillPaymentDetail selectRelationById(Integer id);

	/**
	 * 查询付款单详情信息列表(关联商品，仓库表)
	 * @author chaiwei
	 * @param billPaymentDetail 采购退货详情信息
	 * @return 采购退货详情集合
	 */
	public List<BillPaymentDetail> selectRelationList(BillPaymentDetailReq billPaymentDetail);

	/**
	 * 批量新增或更新详情(插入数据的表必须有主键或者是唯一索引)
	 * @author chaiwei
	 * @param detailReqList
	 * @return 结果
	 */
	public int batchInsertOrUpdate(List<BillPaymentDetail> detailReqList);


	/**
     * 新增付款单详情
     * @author chaiwei
     * @param billPaymentDetail 付款单详情信息
     * @return 结果
     */
	public int insertBillPaymentDetail(BillPaymentDetail billPaymentDetail);
	
	/**
     * 修改付款单详情
     * @author chaiwei
     * @param billPaymentDetail 付款单详情信息
     * @return 结果
     */
	public int updateBillPaymentDetail(BillPaymentDetail billPaymentDetail);
	
	/**
     * 删除付款单详情
     * @author chaiwei
     * @param id 付款单详情ID
     * @return 结果
     */
	public int deleteBillPaymentDetailById(Integer id);

	/**
     * 批量删除付款单详情
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBillPaymentDetailByIds(String[] ids);

	/**
     * 根据parent_id批量删除付款单详情
     * @author chaiwei
     * @param parentId 需要删除的数据parentId
     * @return 结果
     */
	public int deleteByParentId(String[] parentId);

}