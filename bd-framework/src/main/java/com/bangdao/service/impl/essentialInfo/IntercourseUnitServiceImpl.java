package com.bangdao.service.impl.essentialInfo;

import com.bangdao.domain.essentialInfo.IntercourseUnit;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.mapper.essentialInfo.IntercourseUnitMapper;
import com.bangdao.requestVo.essentialInfo.IntercourseUnitRequest;
import com.bangdao.responseVo.essentialInfo.IntercourseUnitResp;
import com.bangdao.service.essentialInfo.IIntercourseUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 往来单位基本 服务层实现
 * 
 * @author xml
 * @date 2018-09-18
 */
@Slf4j
@Service
public class IntercourseUnitServiceImpl implements IIntercourseUnitService {

    @Autowired
	private IntercourseUnitMapper intercourseUnitMapper;

	/**
     * 查询往来单位基本信息
     * @author xml
     * @param id 往来单位基本ID
     * @return 往来单位基本信息
     */
    @Override
	public Result selectIntercourseUnitById(Integer id) throws Exception {
        if (ObjectUtils.isEmpty(id)){
            return Result.error("参数id不能为空");
        }
		IntercourseUnit data = intercourseUnitMapper.selectIntercourseUnitById(id);
	    return Result.success().put("intercourseUnit", data);
	}
	
	/**
     * 查询往来单位基本列表
     * @author xml
     * @param intercourseUnit 往来单位基本信息
     * @return 往来单位基本集合
     */
	@Override
	public List<IntercourseUnit> selectIntercourseUnitList(IntercourseUnitRequest intercourseUnit) throws Exception {
		if(null == intercourseUnit.getStatus())
		{
			intercourseUnit.setStatus(1);
		}
	    return intercourseUnitMapper.selectIntercourseUnitList(intercourseUnit);
	}


	/**
     * 关联查询单位基本列表
     * @author xml
     * @param intercourseUnit 往来单位基本信息
     * @return 往来单位基本集合
     */
	@Override
	public List<IntercourseUnitResp> selectRelationList(IntercourseUnitRequest intercourseUnit) throws Exception {
		if(null == intercourseUnit.getStatus())
		{
			intercourseUnit.setStatus(1);
		}
	    return intercourseUnitMapper.selectRelationList(intercourseUnit);
	}

    /**
     * 新增往来单位基本
     * @author xml
     * @param intercourseUnit 往来单位基本信息
     * @return 结果
     */
	@Override
	public Result insertIntercourseUnit(IntercourseUnitRequest intercourseUnit) throws Exception {
		IntercourseUnit temp = new IntercourseUnit();
        BeanUtils.copyProperties(intercourseUnit,temp);
        int count = intercourseUnitMapper.insertIntercourseUnit(temp);
	    return count > 0 ? Result.success() : Result.error();
	}
	
	/**
     * 修改往来单位基本
     * @author xml
     * @param intercourseUnit 往来单位基本信息
     * @return 结果
     */
	@Override
	public Result updateIntercourseUnit(IntercourseUnitRequest intercourseUnit) throws Exception {
		IntercourseUnit temp = new IntercourseUnit();
        BeanUtils.copyProperties(intercourseUnit,temp);
        int count = intercourseUnitMapper.updateIntercourseUnit(temp);
	    return count > 0 ? Result.success() : Result.error();
	}

	/**
     * 删除往来单位基本对象
     * @author xml
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public Result deleteIntercourseUnitByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return Result.error("参数ids不能为空");
        }
		int count = intercourseUnitMapper.deleteIntercourseUnitByIds(ids.split(","));
		return count > 0 ? Result.success() : Result.error();
	}
	
}
