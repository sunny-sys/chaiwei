package com.bangdao.service.impl.essentialInfo;

import com.bangdao.domain.essentialInfo.Areas;
import com.bangdao.domain.essentialInfo.Cities;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.mapper.essentialInfo.AreasMapper;
import com.bangdao.mapper.essentialInfo.CitiesMapper;
import com.bangdao.requestVo.essentialInfo.CitiesRequest;
import com.bangdao.service.essentialInfo.ICitiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 行政区域地州市 服务层实现
 * 
 * @author xupj
 * @date 2018-09-17
 */
@Slf4j
@Service
public class CitiesServiceImpl implements ICitiesService {

    @Autowired
	private CitiesMapper citiesMapper;
    @Autowired
	private AreasMapper areasMapper;

	/**
     * 查询行政区域地州市信息
     * @author xupj
     * @param id 行政区域地州市ID
     * @return 行政区域地州市信息
     */
    @Override
	public Result selectCitiesById(Integer id) throws Exception {
        if (ObjectUtils.isEmpty(id)){
            return Result.error("参数id不能为空");
        }
		Cities data = citiesMapper.selectCitiesById(id);
	    return Result.success().put("cities", data);
	}
	
	/**
     * 查询行政区域地州市列表
     * @author xupj
     * @param cities 行政区域地州市信息
     * @return 行政区域地州市集合
     */
	@Override
	public List<Cities> selectCitiesList(CitiesRequest cities) throws Exception {
	    return citiesMapper.selectCitiesList(cities);
	}
	
    /**
     * 新增行政区域地州市
     * @author xupj
     * @param cities 行政区域地州市信息
     * @return 结果
     */
	@Override
	public Result insertCities(CitiesRequest cities) throws Exception {
		CitiesRequest citiesTemp = new CitiesRequest();
		/*citiesTemp.setCity(cities.getCity());
		List<Cities> citiesList = citiesMapper.selectCitiesList(citiesTemp);
		if (!CollectionUtils.isEmpty(citiesList)){
			return Result.error("行政区域地州市名称已存在");
		}
		citiesTemp.setCity(null);*/
		citiesTemp.setCityid(cities.getCityid());
		List<Cities> citiesList = citiesMapper.selectCitiesList(citiesTemp);
		if (!CollectionUtils.isEmpty(citiesList)){
			return Result.error("行政区域地州市code已存在");
		}
		Cities city = new Cities();
		BeanUtils.copyProperties(cities,city);
        int count = citiesMapper.insertCities(city);
	    return count > 0 ? Result.success() : Result.error();
	}
	
	/**
     * 修改行政区域地州市
     * @author xupj
     * @param cities 行政区域地州市信息
     * @return 结果
     */
	@Transactional
	@Override
	public Result updateCities(CitiesRequest cities) throws Exception {
		Cities citiesById = citiesMapper.selectCitiesById(cities.getId());
		if (ObjectUtils.isEmpty(citiesById)){
			return Result.error("当前修改的数据不存在");
		}
		CitiesRequest citesTemp = new CitiesRequest();
		/*citesTemp.setCity(cities.getCity());
		if (!cities.getCity().equals(citiesById.getCity())) {
			List<Cities> citiesList = citiesMapper.selectCitiesList(citesTemp);
			if (!CollectionUtils.isEmpty(citiesList)) {
				return Result.error("行政区域地州市名称已存在");
			}
		}*/
		if (!cities.getCityid().equals(citiesById.getCityid())) {
			citesTemp.setCity(null);
			citesTemp.setCityid(cities.getCityid());
			List<Cities> citiesList = citiesMapper.selectCitiesList(citesTemp);
			if (!CollectionUtils.isEmpty(citiesList)) {
				return Result.error("行政区域地州市code已存在");
			}
			Map<String,Object> dataMap = new HashMap<>();
			dataMap.put("sourceParentid",citiesById.getCityid());
			dataMap.put("targetParentid",cities.getCityid());
			areasMapper.updateCitiesByParentid(dataMap);
		}
		Cities city = new Cities();
		BeanUtils.copyProperties(cities,city);
        int count = citiesMapper.updateCities(city);
	    return count > 0 ? Result.success() : Result.error();
	}

	/**
     * 删除行政区域地州市对象
     * @author xupj
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public Result deleteCitiesByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return Result.error("参数ids不能为空");
        }
		String[] idArray = ids.split(",");
		List<Cities> citiesList = citiesMapper.selectCitiesByIds(idArray);
		if (CollectionUtils.isEmpty(citiesList)){
			return Result.success();
		}
		List<String> idList = new ArrayList<>();
		citiesList.stream().map(Cities::getCityid).forEach(cityid -> idList.add(cityid));
		List<Areas> areasList = areasMapper.selectAreasByParentids(idList.toArray(new String[]{}));
		if (!CollectionUtils.isEmpty(areasList)){
			return Result.error("行政区域地州市[" + citiesList.get(0).getCity() + "]存在下级，请先删除下级数据");
		}

		int count = citiesMapper.deleteCitiesByIds(ids.split(","));
		return count > 0 ? Result.success() : Result.error();
	}

	/**
	 * 根据省份的code查询该省份下的城市
	 *
	 * @param parentid
	 */
	@Override
	public List<Cities> selectCitiesListByProvinces(String parentid) {
		return citiesMapper.selectCitiesListByProvinces(parentid);
	}

}
