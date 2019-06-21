package com.bangdao.mapper.essentialInfo;

import com.bangdao.domain.essentialInfo.LogisticsInfo;
import com.bangdao.requestVo.essentialInfo.LogisticsInfoRequest;
import java.util.List;	

/**
 * 物流 数据层
 * 
 * @author xupj
 * @date 2018-10-10
 */
public interface LogisticsInfoMapper {
	/**
     * 查询物流信息
     * @author xupj
     * @param id 物流ID
     * @return 物流信息
     */
	public LogisticsInfo selectLogisticsInfoById(Integer id);

	/**
     * 查询物流列表
     * @author xupj
     * @param logisticsInfo 物流信息
     * @return 物流集合
     */
	public List<LogisticsInfo> selectLogisticsInfoList(LogisticsInfoRequest logisticsInfo);
	
	/**
     * 新增物流
     * @author xupj
     * @param logisticsInfo 物流信息
     * @return 结果
     */
	public int insertLogisticsInfo(LogisticsInfo logisticsInfo);
	
	/**
     * 修改物流
     * @author xupj
     * @param logisticsInfo 物流信息
     * @return 结果
     */
	public int updateLogisticsInfo(LogisticsInfo logisticsInfo);
	
	/**
     * 删除物流
     * @author xupj
     * @param id 物流ID
     * @return 结果
     */
	public int deleteLogisticsInfoById(Integer id);
	
	/**
     * 批量删除物流
     * @author xupj
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteLogisticsInfoByIds(String[] ids);
	
}