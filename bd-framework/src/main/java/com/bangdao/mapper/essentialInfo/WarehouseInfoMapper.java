package com.bangdao.mapper.essentialInfo;

import com.bangdao.domain.essentialInfo.WarehouseInfo;
import com.bangdao.requestVo.essentialInfo.WarehouseInfoRequest;
import java.util.List;	

/**
 * 仓库 数据层
 * 
 * @author chenshao
 * @date 2018-10-16
 */
public interface WarehouseInfoMapper {
	/**
     * 查询仓库信息
     * @author chenshao
     * @param id 仓库ID
     * @return 仓库信息
     */
	public WarehouseInfo selectWarehouseInfoById(Integer id);

	/**
     * 查询仓库列表
     * @author chenshao
     * @param warehouseInfo 仓库信息
     * @return 仓库集合
     */
	public List<WarehouseInfo> selectWarehouseInfoList(WarehouseInfoRequest warehouseInfo);
	
	/**
     * 新增仓库
     * @author chenshao
     * @param warehouseInfo 仓库信息
     * @return 结果
     */
	public int insertWarehouseInfo(WarehouseInfo warehouseInfo);
	
	/**
     * 修改仓库
     * @author chenshao
     * @param warehouseInfo 仓库信息
     * @return 结果
     */
	public int updateWarehouseInfo(WarehouseInfo warehouseInfo);
	
	/**
     * 删除仓库
     * @author chenshao
     * @param id 仓库ID
     * @return 结果
     */
	public int deleteWarehouseInfoById(Integer id);
	
	/**
     * 批量删除仓库
     * @author chenshao
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWarehouseInfoByIds(String[] ids);
	
}