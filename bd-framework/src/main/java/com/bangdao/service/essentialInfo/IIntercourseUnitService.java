package com.bangdao.service.essentialInfo;

import com.bangdao.domain.essentialInfo.IntercourseUnit;
import com.bangdao.requestVo.essentialInfo.IntercourseUnitRequest;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.responseVo.essentialInfo.IntercourseUnitResp;

import java.util.List;

/**
 * 往来单位基本 服务层
 * 
 * @author xml
 * @date 2018-09-18
 */
public interface IIntercourseUnitService {
	/**
     * 查询往来单位基本信息
     * @author xml
     * @param id 往来单位基本ID
     * @return 往来单位基本信息
     */
	public Result selectIntercourseUnitById(Integer id) throws Exception;
	
	/**
     * 查询往来单位基本列表
     * @author xml
     * @param intercourseUnit 往来单位基本信息
     * @return 往来单位基本集合
     */
	public List<IntercourseUnit> selectIntercourseUnitList(IntercourseUnitRequest intercourseUnit) throws Exception;
	
	/**
     * 新增往来单位基本
     * @author xml
     * @param intercourseUnit 往来单位基本信息
     * @return 结果
     */
	public Result insertIntercourseUnit(IntercourseUnitRequest intercourseUnit) throws Exception;

	/**
	 * 关联查询单位基本
	 * @author xml
	 * @param intercourseUnit 往来单位基本信息
	 * @return 结果
	 */
	public List<IntercourseUnitResp> selectRelationList(IntercourseUnitRequest intercourseUnit) throws Exception;

	/**
     * 修改往来单位基本
     * @author xml
     * @param intercourseUnit 往来单位基本信息
     * @return 结果
     */
	public Result updateIntercourseUnit(IntercourseUnitRequest intercourseUnit) throws Exception;
		
	/**
     * 删除往来单位基本信息
     * @author xml
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteIntercourseUnitByIds(String ids) throws Exception;
	
}
