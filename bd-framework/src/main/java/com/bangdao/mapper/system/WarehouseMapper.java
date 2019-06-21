package com.bangdao.mapper.system;

import com.bangdao.domain.system.Warehouse;
import com.bangdao.requestVo.system.WarehouseReq;
import com.bangdao.responseVo.system.WarehouseResp;

import java.util.List;

/**
 * 仓库 数据层
 * 
 * @author chaiwei
 * @date 2018-12-18
 */
public interface WarehouseMapper {
	/**
     * 查询仓库信息
     * @author chaiwei
     * @param warehouseId 仓库ID
     * @return 仓库信息
     */
	public Warehouse selectWarehouseById(String warehouseId);

	/**
     * 关联查询仓库信息明细
     * @author chaiwei
     * @param warehouseId 仓库ID
     * @return 仓库信息
     */
	public WarehouseResp selectRelationById(String warehouseId);

	/**
     * 查询仓库列表
     * @author chaiwei
     * @param warehouse 仓库信息
     * @return 仓库集合
     */
	public List<Warehouse> selectWarehouseList(WarehouseReq warehouse);

	/**
     * 关联查询仓库列表
     * @author chaiwei
     * @param warehouse 仓库信息
     * @return 仓库集合
     */
	public List<WarehouseResp> selectRelationList(WarehouseReq warehouse);

	/**
     * 新增仓库
     * @author chaiwei
     * @param warehouse 仓库信息
     * @return 结果
     */
	public int insertWarehouse(Warehouse warehouse);
	
	/**
     * 批量插入或更新
     * @author chaiwei
     * @param warehouse 仓库信息
     * @return 结果
     */
	public int batchInsertOrUpdate(List<Warehouse> warehouse);

	/**
     * 修改仓库
     * @author chaiwei
     * @param warehouse 仓库信息
     * @return 结果
     */
	public int updateWarehouse(Warehouse warehouse);
	
	/**
     * 删除仓库
     * @author chaiwei
     * @param warehouseId 仓库ID
     * @return 结果
     */
	public int deleteWarehouseById(String warehouseId);
	
	/**
     * 批量删除仓库
     * @author chaiwei
     * @param warehouseIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteWarehouseByIds(String[] warehouseIds);
	
}