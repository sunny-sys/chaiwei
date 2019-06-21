package com.bangdao.mapper.outWarehouse;

import com.bangdao.domain.outWarehouse.ReturnDetailed;
import com.bangdao.requestVo.outWarehouse.ReturnDetailedReq;
import com.bangdao.responseVo.outWarehouse.ReturnDetailedResp;

import java.util.List;

/**
 * 退货详情 数据层
 * 
 * @author chaiwei
 * @date 2018-11-07
 */
public interface ReturnDetailedMapper {
	/**
     * 查询退货详情信息
     * @author chaiwei
     * @param id 退货详情ID
     * @re货详情信息
     */
	public ReturnDetailed selectReturnDetailedById(Integer id);

	/**
     * 查询退货详情信息(关联商品，仓库表)
     * @author chaiwei
     * @param id 退货详情ID
     * @return 退货详情信息
     */
	public ReturnDetailedResp selectRelationById(Integer id);

	/**
     * 查询退货详情列表(关联商品，仓库表)
     * @author chaiwei
     * @param returnDetailed 退货详情信息
     * @return 退货详情集合
     */
	public List<ReturnDetailedResp> selectRelationList(ReturnDetailedReq returnDetailed);
	
	/**
     * 查询退货详情列表
     * @author chaiwei
     * @param returnDetailed 退货详情信息
     * @return 退货详情集合
     */
	public List<ReturnDetailed> selectReturnDetailedList(ReturnDetailedReq returnDetailed);

	/**
     * 新增退货详情
     * @author chaiwei
     * @param returnDetailed 退货详情信息
     * @return 结果
     */
	public int insertPurcrnDetailed(ReturnDetailed returnDetailed);

	/**
	 * 批量新增或更新退货详情(插入数据的表必须有主键或者是唯一索引)
	 * @author chaiwei
	 * @param returnDetailedList 退货详情信息
	 * @return 结果
	 */
	public int batchInsertOrUpdate(List<ReturnDetailed> returnDetailedList);

	/**
     * 修改退货详情
     * @author chaiwei
     * @param returnDetailed 退货详情信息
     * @return 结果
     */
	public int updateReturnDetailed(ReturnDetailed returnDetailed);
	
	/**
     * 删除退货详情
     * @author chaiwei
     * @param id 退货详情ID
     * @return 结果
     */
	public int deleteReturnDetailedById(Integer id);
	
	/**
     * 批量删除退货详情
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteReturnDetailedByIds(String[] ids);

	/**
	 * 批量删除退货详情
	 * @author chaiwei
	 * @param parentId 需要删除的数据parentId
	 * @return 结果
	 */
	public int deleteByParentId(String[] parentId);
	
}