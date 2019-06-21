package com.bangdao.mapper.outWarehouse;

import com.bangdao.domain.outWarehouse.OutWarehouseBase;
import com.bangdao.requestVo.outWarehouse.OutWarehouseBaseReq;
import com.bangdao.responseVo.outWarehouse.OutWarehouseBaseResp;

import java.util.List;

/**
 * 出库基本 数据层
 * 
 * @author chaiwei
 * @date 2018-11-07
 */
public interface OutWarehouseBaseMapper {
	/**
     * 查询出库基本信息
     * @author chaiwei
     * @param id 出库基本ID
     * @return 出库基本信息
     */
	public OutWarehouseBase selectOutWarehouseBaseById(Integer id);

	/**
     * 查询出库关联后的基本信息（关联员工，往来单位表）
     * @author chaiwei
     * @param id 出库基本ID
     * @return 出库基本信息
     */
	public OutWarehouseBaseResp selectRelationById(Integer id);

	/**
     * 查询出库基本列表
     * @author chaiwei
     * @param outWarehouseBase 出库基本信息
     * @return 出库基本集合
     */
	public List<OutWarehouseBase> selectOutWarehouseBaseList(OutWarehouseBaseReq outWarehouseBase);

	/**
     * 查询出库关联后的基本信息列表（关联员工，往来单位表）
     * @author chaiwei
     * @param outWarehouseBase 出库基本信息
     * @return 出库基本集合
     */
	public List<OutWarehouseBaseResp> selectRelationList(OutWarehouseBaseReq outWarehouseBase);

	/**
     * 新增出库基本
     * @author chaiwei
     * @param outWarehouseBase 出库基本信息
     * @return 结果
     */
	public int insertOutWarehouseBase(OutWarehouseBase outWarehouseBase);
	
	/**
     * 修改出库基本
     * @author chaiwei
     * @param outWarehouseBase 出库基本信息
     * @return 结果
     */
	public int updateOutWarehouseBase(OutWarehouseBase outWarehouseBase);
	
	/**
     * 删除出库基本
     * @author chaiwei
     * @param id 出库基本ID
     * @return 结果
     */
	public int deleteOutWarehouseBaseById(Integer id);
	
	/**
     * 批量删除出库基本
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOutWarehouseBaseByIds(String[] ids);
}