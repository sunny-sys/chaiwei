package com.bangdao.service.impl.essentialInfo;

import com.bangdao.common.utils.StringUtils;
import com.bangdao.domain.essentialInfo.DictionaryItem;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.mapper.essentialInfo.DictionaryItemMapper;
import com.bangdao.requestVo.essentialInfo.DictionaryItemRequest;
import com.bangdao.service.essentialInfo.IDictionaryItemService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 字典明细 服务层实现
 * 
 * @author xupj
 * @date 2018-09-14
 */
@Slf4j
@Service
public class DictionaryItemServiceImpl implements IDictionaryItemService {

    @Autowired
	private DictionaryItemMapper dictionaryItemMapper;

	/**
     * 查询字典明细信息
     * 
     * @param id 字典明细ID
     * @return 字典明细信息
     */
    @Override
	public Result selectDictionaryItemById(Integer id) {
		if (ObjectUtils.isEmpty(id)){
			return Result.error("参数id不能为空");
		}
		DictionaryItem data = dictionaryItemMapper.selectDictionaryItemById(id);
	    return Result.success().put("dictionaryItem", data);
	}
	
	/**
     * 查询字典明细列表
     * 
     * @param dictionaryItem 字典明细信息
     * @return 字典明细集合
     */
	@Override
	public List<DictionaryItem> selectDictionaryItemList(DictionaryItemRequest dictionaryItem) {
	    return dictionaryItemMapper.selectDictionaryItemList(dictionaryItem);
	}
	
    /**
     * 新增字典明细
     * 
     * @param dictionaryItem 字典明细信息
     * @return 结果
     */
	@Override
	public Result insertDictionaryItem(DictionaryItemRequest dictionaryItem) {
		DictionaryItem item = new DictionaryItem();
		BeanUtils.copyProperties(dictionaryItem,item);
		//查询该类型下是否存在这个名称对应的这个值
		DictionaryItem itemData = dictionaryItemMapper.selectDictionaryItemByDicType(item);
		if (!ObjectUtils.isEmpty(itemData)){
			return Result.error("数据已存在,无法新增");
		}
		int count = dictionaryItemMapper.insertDictionaryItem(item);
	    return count > 0 ? Result.success() : Result.error();
	}
	
	/**
     * 修改字典明细
     * 
     * @param dictionaryItem 字典明细信息
     * @return 结果
     */
	@Override
	public Result updateDictionaryItem(DictionaryItemRequest dictionaryItem) {
		DictionaryItem item = new DictionaryItem();
		BeanUtils.copyProperties(dictionaryItem,item);
		//查询该类型下是否存在这个名称对应的这个值
		DictionaryItem itemData = dictionaryItemMapper.selectDictionaryItemByDicType(item);
		if (!ObjectUtils.isEmpty(itemData)){
			return Result.error("数据已存在,无法修改");
		}
		int count = dictionaryItemMapper.updateDictionaryItem(item);
	    return count > 0 ? Result.success() : Result.error();
	}

	/**
     * 删除字典明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public Result deleteDictionaryItemByIds(String ids) {
		if (StringUtils.isEmpty(ids)){
			return Result.error("参数id不能为空");
		}
		int count = dictionaryItemMapper.deleteDictionaryItemByIds(ids.split(","));
		return count > 0 ? Result.success() : Result.error();
	}

	@Override
	public List<DictionaryItem> selectDictionaryItemByDicType(String dicType) {
		return dictionaryItemMapper.selectDictionaryItemByDt(dicType);
	}

}
