package com.bangdao.mapper.essentialInfo;

import com.bangdao.domain.essentialInfo.IntercourseUnit;
import com.bangdao.requestVo.essentialInfo.IntercourseUnitRequest;
import com.bangdao.responseVo.essentialInfo.IntercourseUnitResp;

import java.util.List;

/**
 * 往来单位基本 数据层
 * 
 * @author chaiwei
 * @date 2018-10-12
 */
public interface IntercourseUnitMapper {
	/**
     * 查询往来单位基本信息
     * @author chaiwei
     * @param id 往来单位基本ID
     * @return 往来单位基本信息
     */
	public IntercourseUnit selectIntercourseUnitById(Integer id);

	/**
     * 查询往来单位基本列表
     * @author chaiwei
     * @param intercourseUnit 往来单位基本信息
     * @return 往来单位基本集合
     */
	public List<IntercourseUnit> selectIntercourseUnitList(IntercourseUnitRequest intercourseUnit);

	/**
     * 关联查询单位基本列表
     * @author chaiwei
     * @param intercourseUnit 往来单位基本信息
     * @return 往来单位基本集合
     */
	public List<IntercourseUnitResp> selectRelationList(IntercourseUnitRequest intercourseUnit);

	/**
     * 新增往来单位基本
     * @author chaiwei
     * @param intercourseUnit 往来单位基本信息
     * @return 结果
     */
	public int insertIntercourseUnit(IntercourseUnit intercourseUnit);
	
	/**
     * 修改往来单位基本
     * @author chaiwei
     * @param intercourseUnit 往来单位基本信息
     * @return 结果
     */
	public int updateIntercourseUnit(IntercourseUnit intercourseUnit);
	
	/**
     * 删除往来单位基本
     * @author chaiwei
     * @param id 往来单位基本ID
     * @return 结果
     */
	public int deleteIntercourseUnitById(Integer id);
	
	/**
     * 批量删除往来单位基本
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteIntercourseUnitByIds(String[] ids);
	
}