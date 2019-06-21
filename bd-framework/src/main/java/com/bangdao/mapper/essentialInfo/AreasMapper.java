package com.bangdao.mapper.essentialInfo;

import com.bangdao.domain.essentialInfo.Areas;
import com.bangdao.requestVo.essentialInfo.AreasRequest;

import java.util.List;
import java.util.Map;

/**
 * 行政区域县区 数据层
 * 
 * @author xupj
 * @date 2018-09-16
 */
public interface AreasMapper {
	/**
     * 查询行政区域县区信息
     * @author xupj
     * @param id 行政区域县区ID
     * @return 行政区域县区信息
     */
	public Areas selectAreasById(Integer id);

	/**
     * 查询行政区域县区列表
     * @author xupj
     * @param areas 行政区域县区信息
     * @return 行政区域县区集合
     */
	public List<Areas> selectAreasList(AreasRequest areas);
	
	/**
     * 新增行政区域县区
     * @author xupj
     * @param areas 行政区域县区信息
     * @return 结果
     */
	public int insertAreas(Areas areas);
	
	/**
     * 修改行政区域县区
     * @author xupj
     * @param areas 行政区域县区信息
     * @return 结果
     */
	public int updateAreas(Areas areas);
	
	/**
     * 删除行政区域县区
     * @author xupj
     * @param id 行政区域县区ID
     * @return 结果
     */
	public int deleteAreasById(Integer id);
	
	/**
     * 批量删除行政区域县区
     * @author xupj
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAreasByIds(String[] ids);

	/**
	 * 查询行政区域县区信息集合
	 *
	 * @param parentids 字典类型数组
	 */
	List<Areas> selectAreasByParentids(String[] parentids);

	/**
	 * 更新行政区域县区的城市code
	 * @param dataMap
	 * @return
	 */
	public int updateCitiesByParentid(Map<String, Object> dataMap);

	/**
	 * 根据城市的code查询该城市下的县区
	 * @param parentid
	 */
    List<Areas> selectAreasListByParentid(String parentid);
}