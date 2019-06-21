package com.bangdao.service.impl.essentialInfo;

import com.bangdao.domain.essentialInfo.Cities;
import com.bangdao.domain.essentialInfo.Provinces;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.mapper.essentialInfo.CitiesMapper;
import com.bangdao.mapper.essentialInfo.ProvincesMapper;
import com.bangdao.requestVo.essentialInfo.ProvincesRequest;
import com.bangdao.service.essentialInfo.IProvincesService;
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
 * 省份 服务层实现
 * 
 * @author xupj
 * @date 2018-09-16
 */
@Slf4j
@Service
public class ProvincesServiceImpl implements IProvincesService {

    @Autowired
	private ProvincesMapper provincesMapper;
    @Autowired
	private CitiesMapper citiesMapper;

	/**
     * 查询省份信息
     * @author xupj
     * @param id 省份ID
     * @return 省份信息
     */
    @Override
	public Result selectProvincesById(Integer id) throws Exception {
        if (ObjectUtils.isEmpty(id)){
            return Result.error("参数id不能为空");
        }
		Provinces data = provincesMapper.selectProvincesById(id);
	    return Result.success().put("provinces", data);
	}
	
	/**
     * 查询省份列表
     * @author xupj
     * @param provinces 省份信息
     * @return 省份集合
     */
	@Override
	public List<Provinces> selectProvincesList(ProvincesRequest provinces) throws Exception {
	    return provincesMapper.selectProvincesList(provinces);
	}
	
    /**
     * 新增省份
     * @author xupj
     * @param provinces 省份信息
     * @return 结果
     */
	@Override
	public Result insertProvinces(ProvincesRequest provinces) throws Exception {
		ProvincesRequest provincesTemp = new ProvincesRequest();
		/*provincesTemp.setProvince(provinces.getProvince().trim());
		List<Provinces> provincesList = provincesMapper.selectProvincesList(provincesTemp);
		if (!CollectionUtils.isEmpty(provincesList)){
			return Result.error("省份名称已存在");
		}*/
		provincesTemp.setProvince(null);
		provincesTemp.setProvinceid(provinces.getProvinceid().trim());
		List<Provinces> provincesList = provincesMapper.selectProvincesList(provincesTemp);
		if (!CollectionUtils.isEmpty(provincesList)){
			return Result.error("省份code已存在");
		}
		Provinces province = new Provinces();
		BeanUtils.copyProperties(provinces,province);
		int count = provincesMapper.insertProvinces(province);
	    return count > 0 ? Result.success() : Result.error();
	}
	
	/**
     * 修改省份
     * @author xupj
     * @param provinces 省份信息
     * @return 结果
     */
	@Transactional
	@Override
	public Result updateProvinces(ProvincesRequest provinces) throws Exception {
		Provinces provincesById = provincesMapper.selectProvincesById(provinces.getId());
		if (ObjectUtils.isEmpty(provincesById)){
			return Result.error("当前修改的数据不存在");
		}
		ProvincesRequest provincesTemp = new ProvincesRequest();
		/*provincesTemp.setProvince(provinces.getProvince());
		if (!provinces.getProvince().equals(provincesById.getProvince())) {
			List<Provinces> provincesList = provincesMapper.selectProvincesList(provincesTemp);
			if (!CollectionUtils.isEmpty(provincesList)) {
				return Result.error("省份名称已存在");
			}
		}*/
		if (!provinces.getProvinceid().equals(provincesById.getProvinceid())) {
			provincesTemp.setProvince(null);
			provincesTemp.setProvinceid(provinces.getProvinceid());
			List<Provinces> provincesList = provincesMapper.selectProvincesList(provincesTemp);
			if (!CollectionUtils.isEmpty(provincesList)) {
				return Result.error("省份code已存在");
			}
			Map<String,Object> dataMap = new HashMap<>();
			dataMap.put("sourceParentid",provincesById.getProvinceid());
			dataMap.put("targetParentid",provinces.getProvinceid());
			citiesMapper.updateCitiesByParentid(dataMap);
		}
		Provinces province = new Provinces();
		BeanUtils.copyProperties(provinces,province);
        int count = provincesMapper.updateProvinces(province);
	    return count > 0 ? Result.success() : Result.error();
	}

	/**
     * 删除省份对象
     * @author xupj
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public Result deleteProvincesByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return Result.error("参数ids不能为空");
        }
		String[] idArray = ids.split(",");
		List<Provinces> provincesList = provincesMapper.selectProvincesByIds(idArray);
		if (CollectionUtils.isEmpty(provincesList)){
			return Result.success();
		}
		List<String> idList = new ArrayList<>();
		provincesList.stream().map(Provinces::getProvinceid).forEach(provinceid -> idList.add(provinceid));
		List<Cities> citiesList = citiesMapper.selectCitiesByParentids(idList.toArray(new String[]{}));
		if (!CollectionUtils.isEmpty(citiesList)){
			return Result.error("省份[" + provincesList.get(0).getProvince() + "]存在下级城市，请先删除下级城市");
		}
		int count = provincesMapper.deleteProvincesByIds(ids.split(","));
		return count > 0 ? Result.success() : Result.error();
	}
	
}
