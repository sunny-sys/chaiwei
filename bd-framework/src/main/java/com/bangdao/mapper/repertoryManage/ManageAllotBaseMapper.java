package com.bangdao.mapper.repertoryManage;

import com.bangdao.domain.repertoryManage.ManageAllotBase;
import com.bangdao.requestVo.repertoryManage.*;
import com.bangdao.responseVo.repertoryManage.ManageAllotBaseResp;

import java.util.List;	

/**
 * 库存管理调拨单基本 数据层
 * 
 * @author chenshao
 * @date 2018-11-08
 */
public interface ManageAllotBaseMapper {
	/**
     * 查询库存管理调拨单基本信息
     * @author chenshao
     * @param id 库存管理调拨单基本ID
     * @return 库存管理调拨单基本信息
     */
	public ManageAllotBaseResp selectManageAllotBaseById(Integer id);

	/**
     * 查询库存管理调拨单基本列表
     * @author chenshao
     * @param manageAllotBase 库存管理调拨单基本信息
     * @return 库存管理调拨单基本集合
     */
	public List<ManageAllotBaseResp> selectManageAllotBaseList(ManageAllotBaseReq manageAllotBase);
	
	/**
     * 新增库存管理调拨单基本
     * @author chenshao
     * @param manageAllotBase 库存管理调拨单基本信息
     * @return 结果
     */
	public int insertManageAllotBase(ManageAllotBase manageAllotBase);
	
	/**
     * 修改库存管理调拨单基本
     * @author chenshao
     * @param manageAllotBase 库存管理调拨单基本信息
     * @return 结果
     */
	public int updateManageAllotBase(ManageAllotBase manageAllotBase);
	
	/**
     * 删除库存管理调拨单基本
     * @author chenshao
     * @param id 库存管理调拨单基本ID
     * @return 结果
     */
	public int deleteManageAllotBaseById(Integer id);
	
	/**
     * 批量删除库存管理调拨单基本
     * @author chenshao
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteManageAllotBaseByIds(String[] ids);
	
}