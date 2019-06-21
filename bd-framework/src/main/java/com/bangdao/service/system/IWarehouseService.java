package com.bangdao.service.system;

import com.bangdao.framework.web.domain.Result;
import com.bangdao.requestVo.system.WarehouseReq;
import com.bangdao.responseVo.system.WarehouseResp;

import java.math.BigDecimal;
import java.util.List;

/**
 * 仓库 服务层
 * 
 * @author chaiwei
 * @date 2018-12-18
 */
public interface IWarehouseService {
	/**
     * 查询仓库信息
     * @author chaiwei
     * @param pkId 仓库ID
     * @return 仓库信息
     */
	public Result selectWarehouseById(String pkId) throws Exception;
	
	/**
     * 查询仓库列表
     * @author chaiwei
     * @param warehouse 仓库信息
     * @return 仓库集合
     */
	public List<WarehouseResp> selectWarehouseList(WarehouseReq warehouse) throws Exception;
	
	/**
     * 新增仓库
     * @author chaiwei
     * @param warehouse 仓库信息
     * @return 结果
     */
	public Result insertWarehouse(WarehouseReq warehouse) throws Exception;
	
	/**
     * 修改仓库
     * @author chaiwei
     * @param warehouse 仓库信息
     * @return 结果
     */
	public Result updateWarehouse(WarehouseReq warehouse) throws Exception;
		
	/**
     * 删除仓库信息
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteWarehouseByIds(String ids) throws Exception;

	/**
	 * 增加库存
	 * @param id
	 * @param num 数量
	 * @return
	 */
	public void increaseInventory(String id, BigDecimal num);

	/**
	 * 减少库存
	 * @param id
	 * @param num 数量
	 * @return
	 */
	public void reduceInventory(String id, BigDecimal num);
}
