package com.bangdao.mapper.essentialInfo;

import com.bangdao.domain.essentialInfo.DictionaryItem;
import com.bangdao.requestVo.essentialInfo.DictionaryItemRequest;

import java.util.List;
import java.util.Map;

/**
 * 字典明细 数据层
 * 
 * @author xupj
 * @date 2018-09-14
 */
public interface DictionaryItemMapper {
	/**
     * 查询字典明细信息
     * 
     * @param id 字典明细ID
     * @return 字典明细信息
     */
	public DictionaryItem selectDictionaryItemById(Integer id);

	/**
     * 查询字典明细列表
     * 
     * @param dictionaryItem 字典明细信息
     * @return 字典明细集合
     */
	public List<DictionaryItem> selectDictionaryItemList(DictionaryItemRequest dictionaryItem);
	
	/**
     * 新增字典明细
     * 
     * @param dictionaryItem 字典明细信息
     * @return 结果
     */
	public int insertDictionaryItem(DictionaryItem dictionaryItem);
	
	/**
     * 修改字典明细
     * 
     * @param dictionaryItem 字典明细信息
     * @return 结果
     */
	public int updateDictionaryItem(DictionaryItem dictionaryItem);
	
	/**
     * 删除字典明细
     * 
     * @param id 字典明细ID
     * @return 结果
     */
	public int deleteDictionaryItemById(Integer id);
	
	/**
     * 批量删除字典明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDictionaryItemByIds(String[] ids);

	/**
	 * 查询字典明细信息集合
	 *
	 * @param ids 字典类型数组
	 * @return 字典类型信息
	 */
	public List<DictionaryItem> selectDictionaryItemByDicTypes(String[] ids);

	/**
	 * 根据字典类型、类型名称和类型值唯一确定字典明细查询
	 * @param item
	 * @return
	 */
	public DictionaryItem selectDictionaryItemByDicType(DictionaryItem item);

	/**
	 * 更新字典类型
	 * @param dicTypeMap
	 * @return
	 */
	public int updateDictionaryItemByDicType(Map<String,Object> dicTypeMap);

	/**
	 * 根据字典类型查询字典明细详情数据
	 * @param dicType
	 * @return
	 */
	List<DictionaryItem> selectDictionaryItemByDt(String dicType);
}