package com.bangdao.mapper.essentialInfo;

import com.bangdao.domain.essentialInfo.CommodityBase;
import com.bangdao.requestVo.essentialInfo.CommodityBaseRequest;
import java.util.List;	

/**
 * 商品基本 数据层
 * 
 * @author xupj
 * @date 2018-10-10
 */
public interface CommodityBaseMapper {
	/**
     * 查询商品基本信息
     * @author xupj
     * @param id 商品基本ID
     * @return 商品基本信息
     */
	public CommodityBase selectCommodityBaseById(Integer id);

	/**
     * 查询商品基本列表
     * @author xupj
     * @param commodityBase 商品基本信息
     * @return 商品基本集合
     */
	public List<CommodityBase> selectCommodityBaseList(CommodityBaseRequest commodityBase);
	
	/**
     * 新增商品基本
     * @author xupj
     * @param commodityBase 商品基本信息
     * @return 结果
     */
	public int insertCommodityBase(CommodityBase commodityBase);
	
	/**
     * 修改商品基本
     * @author xupj
     * @param commodityBase 商品基本信息
     * @return 结果
     */
	public int updateCommodityBase(CommodityBase commodityBase);
	
	/**
     * 删除商品基本
     * @author xupj
     * @param id 商品基本ID
     * @return 结果
     */
	public int deleteCommodityBaseById(Integer id);
	
	/**
     * 批量删除商品基本
     * @author xupj
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCommodityBaseByIds(String[] ids);
	
}