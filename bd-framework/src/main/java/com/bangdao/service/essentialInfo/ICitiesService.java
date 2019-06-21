package com.bangdao.service.essentialInfo;

import com.bangdao.domain.essentialInfo.Cities;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.requestVo.essentialInfo.CitiesRequest;

import java.util.List;

/**
 * 行政区域地州市 服务层
 * 
 * @author xupj
 * @date 2018-09-17
 */
public interface ICitiesService {
	/**
     * 查询行政区域地州市信息
     * @author xupj
     * @param id 行政区域地州市ID
     * @return 行政区域地州市信息
     */
	public Result selectCitiesById(Integer id) throws Exception;
	
	/**
     * 查询行政区域地州市列表
     * @author xupj
     * @param cities 行政区域地州市信息
     * @return 行政区域地州市集合
     */
	public List<Cities> selectCitiesList(CitiesRequest cities) throws Exception;
	
	/**
     * 新增行政区域地州市
     * @author xupj
     * @param cities 行政区域地州市信息
     * @return 结果
     */
	public Result insertCities(CitiesRequest cities) throws Exception;
	
	/**
     * 修改行政区域地州市
     * @author xupj
     * @param cities 行政区域地州市信息
     * @return 结果
     */
	public Result updateCities(CitiesRequest cities) throws Exception;
		
	/**
     * 删除行政区域地州市信息
     * @author xupj
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteCitiesByIds(String ids) throws Exception;

	/**
	 * 根据省份的code查询该省份下的城市
	 */
    List<Cities> selectCitiesListByProvinces(String parentid);
}
