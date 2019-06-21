package com.bangdao.service.essentialInfo;

import com.bangdao.domain.essentialInfo.CommodityBase;
import com.bangdao.requestVo.essentialInfo.CommodityBaseRequest;
import com.bangdao.framework.web.domain.Result;

import java.util.List;

/**
 * 商品基本 服务层
 * 
 * @author xupj
 * @date 2018-10-10
 */
public interface ICommodityBaseService {
	/**
     * 查询商品基本信息
     * @author xupj
     * @param id 商品基本ID
     * @return 商品基本信息
     */
	public Result selectCommodityBaseById(Integer id) throws Exception;
	
	/**
     * 查询商品基本列表
     * @author xupj
     * @param commodityBase 商品基本信息
     * @return 商品基本集合
     */
	public List<CommodityBase> selectCommodityBaseList(CommodityBaseRequest commodityBase) throws Exception;
	
	/**
     * 新增商品基本
     * @author xupj
     * @param commodityBase 商品基本信息
     * @return 结果
     */
	public Result insertCommodityBase(CommodityBaseRequest commodityBase) throws Exception;
	
	/**
     * 修改商品基本
     * @author xupj
     * @param commodityBase 商品基本信息
     * @return 结果
     */
	public Result updateCommodityBase(CommodityBaseRequest commodityBase) throws Exception;
		
	/**
     * 删除商品基本信息
     * @author xupj
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteCommodityBaseByIds(String ids) throws Exception;
	
}
