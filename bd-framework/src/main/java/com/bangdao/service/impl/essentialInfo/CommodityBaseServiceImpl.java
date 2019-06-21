package com.bangdao.service.impl.essentialInfo;

import com.bangdao.domain.essentialInfo.CommodityBase;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.mapper.essentialInfo.CommodityBaseMapper;
import com.bangdao.requestVo.essentialInfo.CommodityBaseRequest;
import com.bangdao.service.essentialInfo.ICommodityBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 商品基本 服务层实现
 * 
 * @author xupj
 * @date 2018-10-10
 */
@Slf4j
@Service
public class CommodityBaseServiceImpl implements ICommodityBaseService {

    @Autowired
	private CommodityBaseMapper commodityBaseMapper;

	/**
     * 查询商品基本信息
     * @author xupj
     * @param id 商品基本ID
     * @return 商品基本信息
     */
    @Override
	public Result selectCommodityBaseById(Integer id) throws Exception {
        if (ObjectUtils.isEmpty(id)){
            return Result.error("参数id不能为空");
        }
		CommodityBase data = commodityBaseMapper.selectCommodityBaseById(id);
	    return Result.success().put("commodityBase", data);
	}
	
	/**
     * 查询商品基本列表
     * @author xupj
     * @param commodityBase 商品基本信息
     * @return 商品基本集合
     */
	@Override
	public List<CommodityBase> selectCommodityBaseList(CommodityBaseRequest commodityBase) throws Exception {
		if(null == commodityBase.getStatus())
		{
			commodityBase.setStatus(1);
		}
	    return commodityBaseMapper.selectCommodityBaseList(commodityBase);
	}
	
    /**
     * 新增商品基本
     * @author xupj
     * @param commodityBase 商品基本信息
     * @return 结果
     */
	@Override
	@Transactional
	public Result insertCommodityBase(CommodityBaseRequest commodityBase) throws Exception {
		CommodityBase temp = new CommodityBase();
        BeanUtils.copyProperties(commodityBase,temp);
        int count = commodityBaseMapper.insertCommodityBase(temp);
	    return count > 0 ? Result.success() : Result.error();
	}
	
	/**
     * 修改商品基本
     * @author xupj
     * @param commodityBase 商品基本信息
     * @return 结果
     */
	@Override
	@Transactional
	public Result updateCommodityBase(CommodityBaseRequest commodityBase) throws Exception {
		CommodityBase temp = new CommodityBase();
        BeanUtils.copyProperties(commodityBase,temp);
        int count = commodityBaseMapper.updateCommodityBase(temp);
	    return count > 0 ? Result.success() : Result.error();
	}

	/**
     * 删除商品基本对象
     * @author xupj
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@Transactional
	public Result deleteCommodityBaseByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return Result.error("参数ids不能为空");
        }
		int count = commodityBaseMapper.deleteCommodityBaseByIds(ids.split(","));
		return count > 0 ? Result.success() : Result.error();
	}
}
