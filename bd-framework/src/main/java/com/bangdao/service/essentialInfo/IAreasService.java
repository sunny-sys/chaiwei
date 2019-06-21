package com.bangdao.service.essentialInfo;

import com.bangdao.domain.essentialInfo.Areas;
import com.bangdao.requestVo.essentialInfo.AreasRequest;
import com.bangdao.framework.web.domain.Result;

import java.util.List;

/**
 * 行政区域县区 服务层
 * 
 * @author xupj
 * @date 2018-09-17
 */
public interface IAreasService {
	/**
     * 查询行政区域县区信息
     * @author xupj
     * @param id 行政区域县区ID
     * @return 行政区域县区信息
     */
	public Result selectAreasById(Integer id) throws Exception;
	
	/**
     * 查询行政区域县区列表
     * @author xupj
     * @param areas 行政区域县区信息
     * @return 行政区域县区集合
     */
	public List<Areas> selectAreasList(AreasRequest areas) throws Exception;
	
	/**
     * 新增行政区域县区
     * @author xupj
     * @param areas 行政区域县区信息
     * @return 结果
     */
	public Result insertAreas(AreasRequest areas) throws Exception;
	
	/**
     * 修改行政区域县区
     * @author xupj
     * @param areas 行政区域县区信息
     * @return 结果
     */
	public Result updateAreas(AreasRequest areas) throws Exception;
		
	/**
     * 删除行政区域县区信息
     * @author xupj
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteAreasByIds(String ids) throws Exception;

	/**
	 * 根据城市的code查询该城市下的县区
	 */
    List<Areas> selectAreasListByCityid(String parentid);
}
