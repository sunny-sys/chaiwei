package com.bangdao.service.essentialInfo;

import com.bangdao.domain.essentialInfo.DictionaryType;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.requestVo.essentialInfo.DictionaryTypeRequest;

import java.util.List;

/**
 * 字典类型 服务层
 * 
 * @author xupj
 * @date 2018-09-14
 */
public interface IDictionaryTypeService {
	/**
     * 查询字典类型信息
     * 
     * @param id 字典类型ID
     * @return 字典类型信息
     */
	public Result selectDictionaryTypeById(Integer id);
	
	/**
     * 查询字典类型列表
     * 
     * @param dictionaryType 字典类型信息
     * @return 字典类型集合
     */
	public List<DictionaryType> selectDictionaryTypeList(DictionaryTypeRequest dictionaryType);
	
	/**
     * 新增字典类型
     * 
     * @param dictionaryType 字典类型信息
     * @return 结果
     */
	public Result insertDictionaryType(DictionaryTypeRequest dictionaryType) throws Exception;
	
	/**
     * 修改字典类型
     * 
     * @param dictionaryType 字典类型信息
     * @return 结果
     */
	public Result updateDictionaryType(DictionaryTypeRequest dictionaryType) throws Exception;

	/**
     * 删除字典类型信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteDictionaryTypeByIds(String ids);
	
}
