package com.bangdao.service.essentialInfo;

import com.bangdao.domain.essentialInfo.DictionaryItem;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.requestVo.essentialInfo.DictionaryItemRequest;

import java.util.List;

/**
 * 字典明细 服务层
 * 
 * @author xupj
 * @date 2018-09-14
 */
public interface IDictionaryItemService {
	/**
     * 查询字典明细信息
     * 
     * @param id 字典明细ID
     * @return 字典明细信息
     */
	public Result selectDictionaryItemById(Integer id);
	
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
	public Result insertDictionaryItem(DictionaryItemRequest dictionaryItem);
	
	/**
     * 修改字典明细
     * 
     * @param dictionaryItem 字典明细信息
     * @return 结果
     */
	public Result updateDictionaryItem(DictionaryItemRequest dictionaryItem);
		
	/**
     * 删除字典明细信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteDictionaryItemByIds(String ids);

	/**
	 * 根据字典类型查询字典明细详情数据
	 * @param dicType 字典类型
	 */
	List<DictionaryItem> selectDictionaryItemByDicType(String dicType);
}
