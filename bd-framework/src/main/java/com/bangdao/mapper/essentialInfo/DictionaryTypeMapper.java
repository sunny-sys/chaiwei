package com.bangdao.mapper.essentialInfo;

import com.bangdao.domain.essentialInfo.DictionaryType;
import com.bangdao.requestVo.essentialInfo.DictionaryTypeRequest;

import java.util.List;

/**
 * 字典类型 数据层
 * 
 * @author xupj
 * @date 2018-09-14
 */
public interface DictionaryTypeMapper {
	/**
     * 查询字典类型信息
     * 
     * @param id 字典类型ID
     * @return 字典类型信息
     */
	public DictionaryType selectDictionaryTypeById(Integer id);

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
	public int insertDictionaryType(DictionaryType dictionaryType);
	
	/**
     * 修改字典类型
     * 
     * @param dictionaryType 字典类型信息
     * @return 结果
     */
	public int updateDictionaryType(DictionaryType dictionaryType);
	
	/**
     * 删除字典类型
     * 
     * @param id 字典类型ID
     * @return 结果
     */
	public int deleteDictionaryTypeById(Integer id);
	
	/**
     * 批量删除字典类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDictionaryTypeByIds(String[] ids);

	/**
	 * 根据字典类型名称查询字典类型数据
	 * @param dicName
	 * @return
	 */
	DictionaryType selectDictionaryByDicName(String dicName);

	/**
	 * 根据字典类型查询字典类型数据
	 * @param dicType
	 * @return
	 */
	DictionaryType selectDictionaryByDicType(String dicType);

	/**
	 * 查询字典类型信息集合
	 *
	 * @param ids 字典类型数组
	 * @return 字典类型信息
	 */
	public List<DictionaryType> selectDictionaryTypeByIds(String[] ids);

	
}