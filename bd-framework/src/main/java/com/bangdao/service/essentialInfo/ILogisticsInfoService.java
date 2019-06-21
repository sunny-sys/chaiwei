package com.bangdao.service.essentialInfo;

import com.bangdao.domain.essentialInfo.LogisticsInfo;
import com.bangdao.requestVo.essentialInfo.LogisticsInfoRequest;
import com.bangdao.framework.web.domain.Result;

import java.util.List;

/**
 * 物流 服务层
 * 
 * @author xupj
 * @date 2018-10-10
 */
public interface ILogisticsInfoService {
	/**
     * 查询物流信息
     * @author xupj
     * @param id 物流ID
     * @return 物流信息
     */
	public Result selectLogisticsInfoById(Integer id) throws Exception;
	
	/**
     * 查询物流列表
     * @author xupj
     * @param logisticsInfo 物流信息
     * @return 物流集合
     */
	public List<LogisticsInfo> selectLogisticsInfoList(LogisticsInfoRequest logisticsInfo) throws Exception;
	
	/**
     * 新增物流
     * @author xupj
     * @param logisticsInfo 物流信息
     * @return 结果
     */
	public Result insertLogisticsInfo(LogisticsInfoRequest logisticsInfo) throws Exception;
	
	/**
     * 修改物流
     * @author xupj
     * @param logisticsInfo 物流信息
     * @return 结果
     */
	public Result updateLogisticsInfo(LogisticsInfoRequest logisticsInfo) throws Exception;
		
	/**
     * 删除物流信息
     * @author xupj
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteLogisticsInfoByIds(String ids) throws Exception;
	
}
