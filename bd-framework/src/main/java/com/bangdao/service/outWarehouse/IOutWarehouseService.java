package com.bangdao.service.outWarehouse;

import com.bangdao.domain.approval.Approval;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.requestVo.outWarehouse.OutWarehouseBaseReq;
import com.bangdao.requestVo.outWarehouse.OutWarehouseReq;
import com.bangdao.responseVo.outWarehouse.OutWarehouseBaseResp;

import java.util.List;

/**
 * 出库 服务层
 * 
 * @author chaiwei
 * @date 2018-11-07
 */
public interface IOutWarehouseService {
	/**
     * 查询出库信息
     * @author chaiwei
     * @param id 出库ID
     * @return 出库信息
     */
	public Result selectOutWarehouseById(Integer id) throws Exception;
	
	/**
     * 查询出库个人任务列表
     * @author chaiwei
     * @param baseReq 出库信息
     * @return 出库集合
     */
	public List<OutWarehouseBaseResp> selectBaseList(OutWarehouseBaseReq baseReq) throws Exception;
	
	/**
     * 查询出库列表
     * @author chaiwei
     * @param saleOutWarehouse 出库信息
     * @return 出库集合
     */
	public TableDataInfo selectOutWarehouseList(OutWarehouseReq saleOutWarehouse) throws Exception;
	
	/**
     * 新增出库
     * @author chaiwei
     * @param saleOutWarehouse 出库信息
     * @return 结果
     */
	public Result insertOutWarehouse(OutWarehouseReq saleOutWarehouse) throws Exception;
	
	/**
     * 修改出库
     * @author chaiwei
     * @param saleOutWarehouse 出库信息
     * @return 结果
     */
	public Result updateOutWarehouse(OutWarehouseReq saleOutWarehouse) throws Exception;
		
	/**
     * 删除出库信息
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteOutWarehouseByIds(String ids) throws Exception;

	/**
	 * 查询出库个人订单列表
	 * @author chaiwei
	 */
    List<OutWarehouseBaseResp> billList(OutWarehouseBaseReq base) throws Exception;

    /**提交**/
	Result submit(OutWarehouseReq outWarehouse) throws Exception;

	/**审批**/
	Result approval(Approval approval);
}
