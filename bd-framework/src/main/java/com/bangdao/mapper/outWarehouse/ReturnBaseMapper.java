package com.bangdao.mapper.outWarehouse;

import com.bangdao.domain.outWarehouse.ReturnBase;
import com.bangdao.requestVo.outWarehouse.ReturnBaseReq;
import com.bangdao.responseVo.outWarehouse.ReturnBaseResp;

import java.util.List;

/**
 * 退货基本 数据层
 * 
 * @author chaiwei
 * @date 2018-11-07
 */
public interface ReturnBaseMapper {
	/**
     * 查询退货基本信息
     * @author chaiwei
     * @param id 退货基本ID
     * @return 退货基本信息
     */
	public ReturnBase selectReturnBaseById(Integer id);

	/**
     * 查询退货关联后的基本信息（关联员工，往来单位表）
     * @author chaiwei
     * @param id 退货基本ID
     * @return 退货基本信息
     */
	public ReturnBaseResp selectRelationById(Integer id);

	/**
     * 查询退货基本列表
     * @author chaiwei
     * @param returnBase 退货基本信息
     * @return 退货基本集合
     */
	public List<ReturnBase> selectReturnBaseList(ReturnBaseReq returnBase);

	/**
     * 查询退货关联后的基本信息列表（关联员工，往来单位表）
     * @author chaiwei
     * @param returnBase 退货基本信息
     * @return 退货基本集合
     */
	public List<ReturnBaseResp> selectRelationList(ReturnBaseReq returnBase);

	/**
     * 新增退货基本
     * @author chaiwei
     * @param returnBase 退货基本信息
     * @return 结果
     */
	public int insertReturnBase(ReturnBase returnBase);
	
	/**
     * 修改退货基本
     * @author chaiwei
     * @param returnBase 退货基本信息
     * @return 结果
     */
	public int updateReturnBase(ReturnBase returnBase);
	
	/**
     * 删除退货基本
     * @author chaiwei
     * @param id 退货基本ID
     * @return 结果
     */
	public int deleteReturnBaseById(Integer id);
	
	/**
     * 批量删除退货基本
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteReturnBaseByIds(String[] ids);
}