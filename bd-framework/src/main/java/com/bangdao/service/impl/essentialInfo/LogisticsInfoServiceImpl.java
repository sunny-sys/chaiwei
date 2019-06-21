package com.bangdao.service.impl.essentialInfo;

import com.bangdao.domain.essentialInfo.LogisticsInfo;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.mapper.essentialInfo.LogisticsInfoMapper;
import com.bangdao.requestVo.essentialInfo.LogisticsInfoRequest;
import com.bangdao.service.essentialInfo.ILogisticsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 物流 服务层实现
 * 
 * @author xupj
 * @date 2018-10-10
 */
@Slf4j
@Service
public class LogisticsInfoServiceImpl implements ILogisticsInfoService {

    @Autowired
	private LogisticsInfoMapper logisticsInfoMapper;

	/**
     * 查询物流信息
     * @author xupj
     * @param id 物流ID
     * @return 物流信息
     */
    @Override
	public Result selectLogisticsInfoById(Integer id) throws Exception {
        if (ObjectUtils.isEmpty(id)){
            return Result.error("参数id不能为空");
        }
		LogisticsInfo data = logisticsInfoMapper.selectLogisticsInfoById(id);
	    return Result.success().put("logisticsInfo", data);
	}
	
	/**
     * 查询物流列表
     * @author xupj
     * @param logisticsInfo 物流信息
     * @return 物流集合
     */
	@Override
	public List<LogisticsInfo> selectLogisticsInfoList(LogisticsInfoRequest logisticsInfo) throws Exception {
	    return logisticsInfoMapper.selectLogisticsInfoList(logisticsInfo);
	}
	
    /**
     * 新增物流
     * @author xupj
     * @param logisticsInfo 物流信息
     * @return 结果
     */
	@Override
	public Result insertLogisticsInfo(LogisticsInfoRequest logisticsInfo) throws Exception {
		LogisticsInfo temp = new LogisticsInfo();
        BeanUtils.copyProperties(logisticsInfo,temp);
        int count = logisticsInfoMapper.insertLogisticsInfo(temp);
	    return count > 0 ? Result.success() : Result.error();
	}
	
	/**
     * 修改物流
     * @author xupj
     * @param logisticsInfo 物流信息
     * @return 结果
     */
	@Override
	public Result updateLogisticsInfo(LogisticsInfoRequest logisticsInfo) throws Exception {
		LogisticsInfo temp = new LogisticsInfo();
        BeanUtils.copyProperties(logisticsInfo,temp);
        int count = logisticsInfoMapper.updateLogisticsInfo(temp);
	    return count > 0 ? Result.success() : Result.error();
	}

	/**
     * 删除物流对象
     * @author xupj
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public Result deleteLogisticsInfoByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return Result.error("参数ids不能为空");
        }
		int count = logisticsInfoMapper.deleteLogisticsInfoByIds(ids.split(","));
		return count > 0 ? Result.success() : Result.error();
	}
	
}
