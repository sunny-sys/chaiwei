package com.bangdao.service.impl.essentialInfo;

import com.bangdao.domain.essentialInfo.Enclosure;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.mapper.essentialInfo.EnclosureMapper;
import com.bangdao.requestVo.essentialInfo.EnclosureRequest;
import com.bangdao.service.essentialInfo.IEnclosureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 附件 服务层实现
 * 
 * @author xml
 * @date 2018-09-18
 */
@Slf4j
@Service
public class EnclosureServiceImpl implements IEnclosureService {

    @Autowired
	private EnclosureMapper enclosureMapper;

	/**
     * 查询附件信息
     * @author xml
     * @param id 附件ID
     * @return 附件信息
     */
    @Override
	public Result selectEnclosureById(Integer id) throws Exception {
        if (ObjectUtils.isEmpty(id)){
            return Result.error("参数id不能为空");
        }
		Enclosure data = enclosureMapper.selectEnclosureById(id);
	    return Result.success().put("enclosure", data);
	}
	
	/**
     * 查询附件列表
     * @author xml
     * @param enclosure 附件信息
     * @return 附件集合
     */
	@Override
	public List<Enclosure> selectEnclosureList(EnclosureRequest enclosure) throws Exception {
	    return enclosureMapper.selectEnclosureList(enclosure);
	}
	
    /**
     * 新增附件
     * @author xml
     * @param enclosure 附件信息
     * @return 结果
     */
	@Override
	public Result insertEnclosure(EnclosureRequest enclosure) throws Exception {
		Enclosure temp = new Enclosure();
        BeanUtils.copyProperties(enclosure,temp);
        int count = enclosureMapper.insertEnclosure(temp);
	    return count > 0 ? Result.success() : Result.error();
	}
	
	/**
     * 修改附件
     * @author xml
     * @param enclosure 附件信息
     * @return 结果
     */
	@Override
	public Result updateEnclosure(EnclosureRequest enclosure) throws Exception {
		Enclosure temp = new Enclosure();
        BeanUtils.copyProperties(enclosure,temp);
        int count = enclosureMapper.updateEnclosure(temp);
	    return count > 0 ? Result.success() : Result.error();
	}

	/**
     * 删除附件对象
     * @author xml
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public Result deleteEnclosureByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return Result.error("参数ids不能为空");
        }
		int count = enclosureMapper.deleteEnclosureByIds(ids.split(","));
		return count > 0 ? Result.success() : Result.error();
	}
	
}
