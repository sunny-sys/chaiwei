package com.bangdao.service.impl.system;

import com.bangdao.common.exception.base.BaseException;
import com.bangdao.common.utils.BaseUtil;
import com.bangdao.common.utils.bean.BeanUtils;
import com.bangdao.domain.system.Warehouse;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.mapper.system.WarehouseMapper;
import com.bangdao.requestVo.system.WarehouseReq;
import com.bangdao.responseVo.system.WarehouseResp;
import com.bangdao.service.system.IWarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 仓库 服务层实现
 * 
 * @author chaiwei
 * @date 2018-12-18
 */
@Slf4j
@Service
public class WarehouseServiceImpl implements IWarehouseService {

    @Autowired
	private WarehouseMapper warehouseMapper;

	/**
     * 查询仓库信息
     * @author chaiwei
     * @param id 仓库ID
     * @return 仓库信息
     */
    @Override
	public Result selectWarehouseById(String id) throws Exception {
        if (ObjectUtils.isEmpty(id)){
            return Result.error("参数pkId不能为空");
        }
		WarehouseResp data = warehouseMapper.selectRelationById(id);
	    return Result.success().put("warehouse", data);
	}
	
	/**
     * 查询仓库列表
     * @author chaiwei
     * @param warehouse 仓库信息
     * @return 仓库集合
     */
	@Override
	public List<WarehouseResp> selectWarehouseList(WarehouseReq warehouse) throws Exception {
        List<WarehouseResp> warehouseList = warehouseMapper.selectRelationList(warehouse);
	    return warehouseList;
	}
	
    /**
     * 新增仓库
     * @author chaiwei
     * @param warehouse 仓库信息
     * @return 结果
     */
	@Override
    @Transactional
	public Result insertWarehouse(WarehouseReq warehouse) throws Exception {
		Warehouse temp = new Warehouse();
        BeanUtils.copyProperties(warehouse,temp);
        int count = warehouseMapper.insertWarehouse(temp);
	    return count > 0 ? Result.success() : Result.error();
	}
	
	/**
     * 修改仓库
     * @author chaiwei
     * @param warehouse 仓库信息
     * @return 结果
     */
	@Override
    @Transactional
	public Result updateWarehouse(WarehouseReq warehouse) throws Exception {
		Warehouse temp = new Warehouse();
        BeanUtils.copyProperties(warehouse,temp);
        int count = warehouseMapper.updateWarehouse(temp);
	    return count > 0 ? Result.success() : Result.error();
	}

	/**
     * 删除仓库对象
     * @author chaiwei
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
    @Transactional
	public Result deleteWarehouseByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return Result.error("参数ids不能为空");
        }
		int count = warehouseMapper.deleteWarehouseByIds(ids.split(","));
		return count > 0 ? Result.success() : Result.error();
	}

    /**
     * 增加库存
     */
    @Override
    @Transactional
	public void increaseInventory(String id, BigDecimal num)
    {
        if(ObjectUtils.isEmpty(id))
        {
            throw new BaseException("id不能为空");
        }
        if(!ObjectUtils.isEmpty(num) && num.compareTo(BigDecimal.ZERO) != 0)
        {
            Warehouse warehouse = warehouseMapper.selectWarehouseById(id);
            BigDecimal number = warehouse.getNumber();//获取库存数量
            Warehouse temp = new Warehouse();
            temp.setNumber(num.add(number));
            temp.setWarehouseId(id);
            BaseUtil.setUpdateBy(temp);
            warehouseMapper.updateWarehouse(temp);
        }
    }

    /**
     * 减少库存
     */
    @Override
    @Transactional
    public void reduceInventory(String id, BigDecimal num)
    {
        if(ObjectUtils.isEmpty(id))
        {
            throw new BaseException("id不能为空");
        }
        if(!ObjectUtils.isEmpty(num) && num.compareTo(BigDecimal.ZERO) != 0)
        {
            synchronized(this)
            {
                WarehouseResp warehouseResp = warehouseMapper.selectRelationById(id);
                BigDecimal number = warehouseResp.getNumber();//获取库存数量
                if(num.compareTo(number)>0)
                {
                    throw new BaseException("\""+warehouseResp.getCommodityName()+"\""+"库存数量不足，并且系统不允许负库存");
                }
                Warehouse temp = new Warehouse();
                temp.setNumber(number.subtract(num));
                temp.setWarehouseId(id);
                BaseUtil.setUpdateBy(temp);
                warehouseMapper.updateWarehouse(temp);
            }
        }
    }
}
