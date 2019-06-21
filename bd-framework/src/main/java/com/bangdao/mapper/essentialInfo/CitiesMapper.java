package com.bangdao.mapper.essentialInfo;

import com.bangdao.domain.essentialInfo.Cities;
import com.bangdao.requestVo.essentialInfo.CitiesRequest;

import java.util.List;
import java.util.Map;

/**
 * 行政区域地州市 数据层
 * 
 * @author xupj
 * @date 2018-09-17
 */
public interface CitiesMapper {
	/**
     * 查询行政区域地州市信息
     * @author xupj
     * @param id 行政区域地州市ID
     * @return 行政区域地州市信息
     */
	public Cities selectCitiesById(Integer id);

	/**
     * 查询行政区域地州市列表
     * @author xupj
     * @param cities 行政区域地州市信息
     * @return 行政区域地州市集合
     */
	public List<Cities> selectCitiesList(CitiesRequest cities);
	
	/**
     * 新增行政区域地州市
     * @author xupj
     * @param cities 行政区域地州市信息
     * @return 结果
     */
	public int insertCities(Cities cities);
	
	/**
     * 修改行政区域地州市
     * @author xupj
     * @param cities 行政区域地州市信息
     * @return 结果
     */
	public int updateCities(Cities cities);
	
	/**
     * 删除行政区域地州市
     * @author xupj
     * @param id 行政区域地州市ID
     * @return 结果
     */
	public int deleteCitiesById(Integer id);
	
	/**
     * 批量删除行政区域地州市
     * @author xupj
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCitiesByIds(String[] ids);

	/**
	 * 查询行政区域地州市信息集合
	 *
	 * @param parentids 字典类型数组
	 */
	public List<Cities> selectCitiesByParentids(String[] parentids);

	/**
	 * 更新查询行政区域地州市中的省份code
	 * @param dataMap
	 * @return
	 */
	public int updateCitiesByParentid(Map<String, Object> dataMap);

	/**
	 * 查询行政区域地州市信息集合
	 *
	 * @param ids 字典类型数组
	 */
	public List<Cities> selectCitiesByIds(String[] ids);

	/**
	 * 根据省份的code查询该省份下的城市
	 */
    List<Cities> selectCitiesListByProvinces(String provinceid);
}