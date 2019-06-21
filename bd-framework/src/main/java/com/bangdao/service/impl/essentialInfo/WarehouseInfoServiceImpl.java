package com.bangdao.service.impl.essentialInfo;

import com.bangdao.domain.essentialInfo.WarehouseInfo;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.mapper.essentialInfo.WarehouseInfoMapper;
import com.bangdao.requestVo.essentialInfo.WarehouseInfoRequest;
import com.bangdao.service.essentialInfo.IWarehouseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 仓库 服务层实现
 * 
 * @author chenshao
 * @date 2018-10-16
 */
@Slf4j
@Service
public class WarehouseInfoServiceImpl implements IWarehouseInfoService {

    @Autowired
	private WarehouseInfoMapper warehouseInfoMapper;

	/**
     * 查询仓库信息
     * @author chenshao
     * @param id 仓库ID
     * @return 仓库信息
     */
    @Override
	public Result selectWarehouseInfoById(Integer id) throws Exception {
        if (ObjectUtils.isEmpty(id)){
            return Result.error("参数id不能为空");
        }
		WarehouseInfo data = warehouseInfoMapper.selectWarehouseInfoById(id);
	    return Result.success().put("warehouseInfo", data);
	}
	
	/**
     * 查询仓库列表
     * @author chenshao
     * @param warehouseInfo 仓库信息
     * @return 仓库集合
     */
	@Override
	public List<WarehouseInfo> selectWarehouseInfoList(WarehouseInfoRequest warehouseInfo) throws Exception {
	    return warehouseInfoMapper.selectWarehouseInfoList(warehouseInfo);
	}
	
    /**
     * 新增仓库
     * @author chenshao
     * @param warehouseInfo 仓库信息
     * @return 结果
     */
	@Override
	public Result insertWarehouseInfo(WarehouseInfoRequest warehouseInfo) throws Exception {
		WarehouseInfo temp = new WarehouseInfo();
        BeanUtils.copyProperties(warehouseInfo,temp);
        int count = warehouseInfoMapper.insertWarehouseInfo(temp);
	    return count > 0 ? Result.success() : Result.error();
	}
	
	/**
     * 修改仓库
     * @author chenshao
     * @param warehouseInfo 仓库信息
     * @return 结果
     */
	@Override
	public Result updateWarehouseInfo(WarehouseInfoRequest warehouseInfo) throws Exception {
		WarehouseInfo temp = new WarehouseInfo();
        BeanUtils.copyProperties(warehouseInfo,temp);
        int count = warehouseInfoMapper.updateWarehouseInfo(temp);
	    return count > 0 ? Result.success() : Result.error();
	}

	/**
     * 删除仓库对象
     * @author chenshao
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public Result deleteWarehouseInfoByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return Result.error("参数ids不能为空");
        }
		int count = warehouseInfoMapper.deleteWarehouseInfoByIds(ids.split(","));
		return count > 0 ? Result.success() : Result.error();
	}
	
}
