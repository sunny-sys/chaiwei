package com.bangdao.mapper.essentialInfo;

import com.bangdao.domain.essentialInfo.Provinces;
import com.bangdao.requestVo.essentialInfo.ProvincesRequest;

import java.util.List;

/**
 * 省份 数据层
 * 
 * @author xupj
 * @date 2018-09-16
 */
public interface ProvincesMapper {
	/**
     * 查询省份信息
     * @author xupj
     * @param id 省份ID
     * @return 省份信息
     */
	public Provinces selectProvincesById(Integer id);

	/**
     * 查询省份列表
     * @author xupj
     * @param provinces 省份信息
     * @return 省份集合
     */
	public List<Provinces> selectProvincesList(ProvincesRequest provinces);
	
	/**
     * 新增省份
     * @author xupj
     * @param provinces 省份信息
     * @return 结果
     */
	public int insertProvinces(Provinces provinces);
	
	/**
     * 修改省份
     * @author xupj
     * @param provinces 省份信息
     * @return 结果
     */
	public int updateProvinces(Provinces provinces);
	
	/**
     * 删除省份
     * @author xupj
     * @param id 省份ID
     * @return 结果
     */
	public int deleteProvincesById(Integer id);
	
	/**
     * 批量删除省份
     * @author xupj
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProvincesByIds(String[] ids);

	/**
	 * 查询省份信息集合
	 *
	 * @param ids 字典类型数组
	 */
	public List<Provinces> selectProvincesByIds(String[] ids);
	
}