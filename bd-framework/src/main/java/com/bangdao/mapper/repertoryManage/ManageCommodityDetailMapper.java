package com.bangdao.mapper.repertoryManage;

import com.bangdao.domain.repertoryManage.ManageCommodityDetail;
import com.bangdao.requestVo.repertoryManage.ManageCommodityDetailReq;
import com.bangdao.responseVo.repertoryManage.ManageCommodityDetailResp;

import java.util.List;	

/**
 * 调拨单明细详情 数据层
 * 
 * @author chenshao
 * @date 2018-11-08
 */
public interface ManageCommodityDetailMapper {
	/**
     * 查询调拨单明细详情信息
     * @author chenshao
     * @param id 调拨单明细详情ID
     * @return 调拨单明细详情信息
     */
	public ManageCommodityDetail selectManageCommodityDetailById(Integer id);

	/**
	 * 查询调拨单明细详情信息(关联商品，仓库表)
	 * @author chaiwei
	 * @param id
	 * @return
	 */
	public ManageCommodityDetail selectRelationById(Integer id);


	/**
     * 查询调拨单明细详情列表
     * @author chenshao
     * @param manageCommodityDetail 调拨单明细详情信息
     * @return 调拨单明细详情集合
     */
	public List<ManageCommodityDetail> selectManageCommodityDetailList(ManageCommodityDetailReq manageCommodityDetail);
	
	/**
     * 新增调拨单明细详情
     * @author chenshao
     * @param manageCommodityDetail 调拨单明细详情信息
     * @return 结果
     */
	public int insertManageCommodityDetail(ManageCommodityDetail manageCommodityDetail);
	
	/**
     * 修改调拨单明细详情
     * @author chenshao
     * @param manageCommodityDetail 调拨单明细详情信息
     * @return 结果
     */
	public int updateManageCommodityDetail(ManageCommodityDetail manageCommodityDetail);
	
	/**
     * 删除调拨单明细详情
     * @author chenshao
     * @param id 调拨单明细详情ID
     * @return 结果
     */
	public int deleteManageCommodityDetailById(Integer id);

	/**
     * 删除调拨单明细详情
     * @author chenshao
     * @param parentId 调拨单明细详情ID
     * @return 结果
     */
	public int deleteManageCommodityDetailByParentIds(String[] parentId);

	/**
     * 批量删除调拨单明细详情
     * @author chenshao
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteManageCommodityDetailByIds(String[] ids);

	public void batchInsertOrUpdate(List<ManageCommodityDetail> manageCommodityDetailList);

	/**查询详情（关联查询）**/
	public List<ManageCommodityDetailResp> selectRelationList(ManageCommodityDetailReq manageCommodityDetailReq);
}