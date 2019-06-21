package com.bangdao.service.essentialInfo;

import com.bangdao.domain.essentialInfo.WarehouseInfo;
import com.bangdao.requestVo.essentialInfo.WarehouseInfoRequest;
import com.bangdao.framework.web.domain.Result;

import java.util.List;

/**
 * 仓库 服务层
 * 
 * @author chenshao
 * @date 2018-10-16
 */
public interface IWarehouseInfoService {
	/**
     * 查询仓库信息
     * @author chenshao
     * @param id 仓库ID
     * @return 仓库信息
     */
	public Result selectWarehouseInfoById(Integer id) throws Exception;
	
	/**
     * 查询仓库列表
     * @author chenshao
     * @param warehouseInfo 仓库信息
     * @return 仓库集合
     */
	public List<WarehouseInfo> selectWarehouseInfoList(WarehouseInfoRequest warehouseInfo) throws Exception;
	
	/**
     * 新增仓库
     * @author chenshao
     * @param warehouseInfo 仓库信息
     * @return 结果
     */
	public Result insertWarehouseInfo(WarehouseInfoRequest warehouseInfo) throws Exception;
	
	/**
     * 修改仓库
     * @author chenshao
     * @param warehouseInfo 仓库信息
     * @return 结果
     */
	public Result updateWarehouseInfo(WarehouseInfoRequest warehouseInfo) throws Exception;
		
	/**
     * 删除仓库信息
     * @author chenshao
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteWarehouseInfoByIds(String ids) throws Exception;
	
}
