package com.bangdao.service.impl.essentialInfo;

import com.bangdao.common.utils.Pin4jUtils;
import com.bangdao.domain.essentialInfo.DictionaryItem;
import com.bangdao.domain.essentialInfo.DictionaryType;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.mapper.essentialInfo.DictionaryItemMapper;
import com.bangdao.mapper.essentialInfo.DictionaryTypeMapper;
import com.bangdao.requestVo.essentialInfo.DictionaryTypeRequest;
import com.bangdao.service.essentialInfo.IDictionaryTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典类型 服务层实现
 * 
 * @author xupj
 * @date 2018-09-14
 */
@Slf4j
@Service
public class DictionaryTypeServiceImpl implements IDictionaryTypeService {

    @Autowired
	private DictionaryTypeMapper dictionaryTypeMapper;
    @Autowired
	private DictionaryItemMapper dictionaryItemMapper;

	/**
     * 查询字典类型信息
     * 
     * @param id 字典类型ID
     * @return 字典类型信息
     */
    @Override
	public Result selectDictionaryTypeById(Integer id) {
    	if (ObjectUtils.isEmpty(id)){
			return Result.error("参数id不能为空");
		}
		DictionaryType data = dictionaryTypeMapper.selectDictionaryTypeById(id);
		return Result.success().put("dictionaryType", data);
	}
	
	/**
     * 查询字典类型列表
     * 
     * @param dictionaryType 字典类型信息
     * @return 字典类型集合
     */
	@Override
	public List<DictionaryType> selectDictionaryTypeList(DictionaryTypeRequest dictionaryType) {
	    return dictionaryTypeMapper.selectDictionaryTypeList(dictionaryType);
	}
	
    /**
     * 新增字典类型
     * 
     * @param dictionaryType 字典类型信息
     * @return 结果
     */
	@Override
	public Result insertDictionaryType(DictionaryTypeRequest dictionaryType) throws Exception {
		DictionaryType dicNameData = dictionaryTypeMapper.selectDictionaryByDicName(dictionaryType.getDicName());
		if (!ObjectUtils.isEmpty(dicNameData)){
			return Result.error("字典类型名称[" + dicNameData + "]已存在");
		}
		DictionaryType dicTypeData = dictionaryTypeMapper.selectDictionaryByDicType(dictionaryType.getDicType());
		if (!ObjectUtils.isEmpty(dicTypeData)){
			return Result.error("字典类型[" + dicTypeData + "]已存在");
		}
		dictionaryType.setDicNameSpell(Pin4jUtils.getFirstLetters(dictionaryType.getDicName()));
		DictionaryType dt = new DictionaryType();
		BeanUtils.copyProperties(dictionaryType,dt);
		int type = dictionaryTypeMapper.insertDictionaryType(dt);
		return type > 0 ? Result.success() : Result.error();
	}
	
	/**
     * 修改字典类型
     * 
     * @param dictionaryType 字典类型信息
     * @return 结果
     */
	@Transactional
	@Override
	public Result updateDictionaryType(DictionaryTypeRequest dictionaryType) throws Exception {
		DictionaryType dicType = dictionaryTypeMapper.selectDictionaryTypeById(dictionaryType.getId());
		if (ObjectUtils.isEmpty(dicType)){
			return Result.error("当前需要修改的字典类型不存在");
		}
		if (!dicType.getDicName().equals(dictionaryType.getDicName())) {
			DictionaryType dicNameData = dictionaryTypeMapper.selectDictionaryByDicName(dictionaryType.getDicName());
			if (!ObjectUtils.isEmpty(dicNameData)) {
				return Result.error("字典类型名称[" + dicNameData + "]已存在");
			}
			dictionaryType.setDicNameSpell(Pin4jUtils.getFirstLetters(dictionaryType.getDicName().trim()));
		}
		if (!dicType.getDicType().equals(dictionaryType.getDicType())) {
			DictionaryType dicTypeData = dictionaryTypeMapper.selectDictionaryByDicType(dictionaryType.getDicType());
			if (!ObjectUtils.isEmpty(dicTypeData)) {
				return Result.error("字典类型[" + dicTypeData + "]已存在");
			}
			Map<String,Object> dataMap = new HashMap<>();
			dataMap.put("sourceDicType",dicType.getDicType());
			dataMap.put("targetDicType",dictionaryType.getDicType());
			dictionaryItemMapper.updateDictionaryItemByDicType(dataMap);
		}
		DictionaryType dt = new DictionaryType();
		BeanUtils.copyProperties(dictionaryType,dt);
		dictionaryTypeMapper.updateDictionaryType(dt);
		return  Result.success();
	}

	/**
     * 删除字典类型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public Result deleteDictionaryTypeByIds(String ids) {
		if (StringUtils.isEmpty(ids)){
			return Result.error("参数ids不能为空");
		}
		String[] idArray = ids.split(",");
		List<DictionaryType> dicTypeList = dictionaryTypeMapper.selectDictionaryTypeByIds(idArray);
		if (CollectionUtils.isEmpty(dicTypeList)){
			return Result.success();
		}
		List<String> idList = new ArrayList<>();
		dicTypeList.stream().map(DictionaryType::getDicType).forEach(dicType -> idList.add(dicType));
		List<DictionaryItem> dictionaryItemList = dictionaryItemMapper.selectDictionaryItemByDicTypes(idList.toArray(new String[]{}));
		if (!CollectionUtils.isEmpty(dictionaryItemList)){
			return Result.error("字典类型[" + dictionaryItemList.get(0).getDicType() + "]存在字典明细，请先删除明细数据");
		}
		int type = dictionaryTypeMapper.deleteDictionaryTypeByIds(idList.toArray(new String[]{}));
		return type > 0 ? Result.success() : Result.error();
	}
	
}
