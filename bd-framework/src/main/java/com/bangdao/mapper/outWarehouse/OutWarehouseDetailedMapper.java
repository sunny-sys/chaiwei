package com.bangdao.mapper.outWarehouse;

import com.bangdao.domain.outWarehouse.OutWarehouseDetailed;
import com.bangdao.requestVo.outWarehouse.OutWarehouseDetailedReq;
import com.bangdao.responseVo.outWarehouse.OutWarehouseDetailedResp;

import java.util.List;

/**
 * 出库详情 数据层
 * 
 * @author chaiwei
 * @date 2018-11-07
 */
public interface OutWarehouseDetailedMapper {
	/**
     * 查询出库详情信息
     * @author chaiwei
     * @param id 出库详情ID
     * @re货详情信息
     */
	public OutWarehouseDetailed selectOutWarehouseDetailedById(Integer id);

	/**
     * 查询出库详情信息(关联商品，仓库表)
     * @author chaiwei
     * @param id 出库详情ID
     * @return 出库详情信息
     */
	public OutWarehouseDetailedResp selectRelationById(Integer id);

	/**
     * 查询出库详情列表(关联商品，仓库表)
     * @author chaiwei
     * @param outWarehouseDetailed 出库详情信息
     * @return 出库详情集合
     */
	public List<OutWarehouseDetailedResp> selectRelationList(OutWarehouseDetailedReq outWarehouseDetailed);
	
	/**
     * 查询出库详情列表
     * @author chaiwei
     * @param outWarehouseDetailed 出库详情信息
     * @return 出库详情集合
     */
	public List<OutWarehouseDetailed> selectOutWarehouseDetailedList(OutWarehouseDetailedReq outWarehouseDetailed);

	/**
     * 新增出库详情
     * @author chaiwei
     * @param outWarehouseDetailed 出库详情信息
     * @return 结果
     */
	public int insertPurcrnDetailed(OutWarehouseDetailed outWarehouseDetailed);

	/**
	 * 批量新增或更新出库详情(插入数据的表必须有主键或者是唯一索引)
	 * @author chaiwei
	 * @param outWarehouseDetailedList 出库详情信息
	 * @return 结果
	 */
	public int batchInsertOrUpdate(List<OutWarehouseDetailed> outWarehouseDetailedList);

	/**
     * 修改出库详情
     * @author chaiwei
     * @param outWarehouseDetailed 出库详情信息
     * @return 结果
     */
	public int updateOutWarehouseDetailed(OutWarehouseDetailed outWarehouseDetailed);
	
	/**
     * 删除出库详情
     * @author chaiwei
     * @param id 出库详情ID
     * @return 结果
     */
	public int deleteOutWarehouseDetailedById(Integer id);
	
	/**
     * 批量删除出库详情
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOutWarehouseDetailedByIds(String[] ids);

	/**
	 * 批量删除出库详情
	 * @author chaiwei
	 * @param parentId 需要删除的数据parentId
	 * @return 结果
	 */
	public int deleteByParentId(String[] parentId);
	
}