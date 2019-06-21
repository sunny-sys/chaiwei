package com.bangdao.service.essentialInfo;

import com.bangdao.domain.essentialInfo.Provinces;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.requestVo.essentialInfo.ProvincesRequest;

import java.util.List;

/**
 * 省份 服务层
 * 
 * @author xupj
 * @date 2018-09-16
 */
public interface IProvincesService {
	/**
     * 查询省份信息
     * @author xupj
     * @param id 省份ID
     * @return 省份信息
     */
	public Result selectProvincesById(Integer id) throws Exception;
	
	/**
     * 查询省份列表
     * @author xupj
     * @param provinces 省份信息
     * @return 省份集合
     */
	public List<Provinces> selectProvincesList(ProvincesRequest provinces) throws Exception;
	
	/**
     * 新增省份
     * @author xupj
     * @param provinces 省份信息
     * @return 结果
     */
	public Result insertProvinces(ProvincesRequest provinces) throws Exception;
	
	/**
     * 修改省份
     * @author xupj
     * @param provinces 省份信息
     * @return 结果
     */
	public Result updateProvinces(ProvincesRequest provinces) throws Exception;
		
	/**
     * 删除省份信息
     * @author xupj
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteProvincesByIds(String ids) throws Exception;
	
}
