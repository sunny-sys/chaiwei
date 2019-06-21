package com.bangdao.mapper.outWarehouse;

import com.bangdao.domain.outWarehouse.ReceiptDetail;
import com.bangdao.requestVo.outWarehouse.ReceiptDetailReq;

import java.util.List;


/**
 * 收款单详情 数据层
 * 
 * @author chaiwei
 * @date 2018-11-13
 */
public interface ReceiptDetailMapper {
	/**
     * 查询收款单详情信息
     * @author chaiwei
     * @param id 收款单详情ID
     * @return 收款单详情信息
     */
	public ReceiptDetail selectBillPaymentDetailById(Integer id);

	/**
     * 查询收款单详情列表
     * @author chaiwei
     * @param receiptDetail 收款单详情信息
     * @return 收款单详情集合
     */
	public List<ReceiptDetail> selectReceiptDetailList(ReceiptDetailReq receiptDetail);

	/**
	 * 查询收款单详情信息(关联商品，仓库表)
	 * @author chaiwei
	 * @Receipt收款单详情ID
	 * @return 收款单详情信息
	 */
	public ReceiptDetail selectRelationById(Integer id);

	/**
	 * 查询收款单详情信息列表(关联商品，仓库表)
	 * @author chaiwei
	 * @param receiptDetail 收款单详情信息
	 * @return 收款单详情集合
	 */
	public List<ReceiptDetail> selectRelationList(ReceiptDetailReq receiptDetail);

	/**
	 * 批量新增或更新详情(插入数据的表必须有主键或者是唯一索引)
	 * @author chaiwei
	 * @param detailReqList
	 * @return 结果
	 */
	public int batchInsertOrUpdate(List<ReceiptDetail> detailReqList);


	/**
     * 新增收款单详情
     * @author chaiwei
     * @param receiptDetail 收款单详情信息
     * @return 结果
     */
	public int insertReceiptDetail(ReceiptDetail receiptDetail);
	
	/**
     * 修改收款单详情
     * @author chaiwei
     * @param receiptDetail 收款单详情信息
     * @return 结果
     */
	public int updateReceiptDetail(ReceiptDetail receiptDetail);
	
	/**
     * 删除收款单详情
     * @author chaiwei
     * @param id 收款单详情ID
     * @return 结果
     */
	public int deleteReceiptDetailById(Integer id);

	/**
     * 批量删除收款单详情
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteReceiptDetailByIds(String[] ids);

	/**
     * 根据parent_id批量删除收款单详情
     * @author chaiwei
     * @param parentId 需要删除的数据parentId
     * @return 结果
     */
	public int deleteByParentId(String[] parentId);

}