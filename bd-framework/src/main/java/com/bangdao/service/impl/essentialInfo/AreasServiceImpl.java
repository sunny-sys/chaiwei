package com.bangdao.service.impl.essentialInfo;

import com.bangdao.domain.essentialInfo.Areas;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.mapper.essentialInfo.AreasMapper;
import com.bangdao.requestVo.essentialInfo.AreasRequest;
import com.bangdao.service.essentialInfo.IAreasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 行政区域县区 服务层实现
 *
 * @author xupj
 * @date 2018-09-17
 */
@Slf4j
@Service
public class AreasServiceImpl implements IAreasService {

    @Autowired
    private AreasMapper areasMapper;

    /**
     * 查询行政区域县区信息
     *
     * @param id 行政区域县区ID
     * @return 行政区域县区信息
     * @author xupj
     */
    @Override
    public Result selectAreasById(Integer id) throws Exception {
        if (ObjectUtils.isEmpty(id)) {
            return Result.error("参数id不能为空");
        }
        Areas data = areasMapper.selectAreasById(id);
        return Result.success().put("areas", data);
    }

    /**
     * 查询行政区域县区列表
     *
     * @param areas 行政区域县区信息
     * @return 行政区域县区集合
     * @author xupj
     */
    @Override
    public List<Areas> selectAreasList(AreasRequest areas) throws Exception {
        return areasMapper.selectAreasList(areas);
    }

    /**
     * 新增行政区域县区
     *
     * @param areas 行政区域县区信息
     * @return 结果
     * @author xupj
     */
    @Override
    public Result insertAreas(AreasRequest areas) throws Exception {
        AreasRequest areasTemp = new AreasRequest();
		/*areasTemp.setArea(areas.getArea());
		areasTemp.setParentid(areas.getParentid());
		List<Areas> areasList = areasMapper.selectAreasList(areasTemp);
		if (!CollectionUtils.isEmpty(areasList)){
			return Result.error("当前行政区域县区名称已存在");
		}*/
        areasTemp.setArea(null);
        areasTemp.setAreaid(areas.getAreaid());
        List<Areas> areasList = areasMapper.selectAreasList(areasTemp);
        if (!CollectionUtils.isEmpty(areasList)) {
            return Result.error("当前行政区域县区code已存在");
        }
        Areas temp = new Areas();
        BeanUtils.copyProperties(areas, temp);
        int count = areasMapper.insertAreas(temp);
        return count > 0 ? Result.success() : Result.error();
    }

    /**
     * 修改行政区域县区
     *
     * @param areas 行政区域县区信息
     * @return 结果
     * @author xupj
     */
    @Override
    public Result updateAreas(AreasRequest areas) throws Exception {
        Areas areasById = areasMapper.selectAreasById(areas.getId());
        if (ObjectUtils.isEmpty(areasById)) {
            return Result.error("当前修改的数据不存在");
        }
        AreasRequest areasTemp = new AreasRequest();
		/*areasTemp.setArea(areas.getArea());
		areasTemp.setParentid(areas.getParentid());
		if (!areas.getArea().equals(areasById.getArea())){
			List<Areas> areasList = areasMapper.selectAreasList(areasTemp);
			if (!CollectionUtils.isEmpty(areasList)){
				return Result.error("当前行政区域县区名称已存在");
			}
		}*/
        if (!areas.getAreaid().equals(areasById.getAreaid())) {
            areasTemp.setArea(null);
            areasTemp.setAreaid(areas.getAreaid());
            List<Areas> areasList = areasMapper.selectAreasList(areasTemp);
            if (!CollectionUtils.isEmpty(areasList)) {
                return Result.error("当前行政区域县区code已存在");
            }
        }
        Areas temp = new Areas();
        BeanUtils.copyProperties(areas, temp);
        int count = areasMapper.updateAreas(temp);
        return count > 0 ? Result.success() : Result.error();
    }

    /**
     * 删除行政区域县区对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     * @author xupj
     */
    @Override
    public Result deleteAreasByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)) {
            return Result.error("参数ids不能为空");
        }
        int count = areasMapper.deleteAreasByIds(ids.split(","));
        return count > 0 ? Result.success() : Result.error();
    }

    /**
     * 根据城市的code查询该城市下的县区
     *
     * @param parentid
     */
    @Override
    public List<Areas> selectAreasListByCityid(String parentid) {
        return areasMapper.selectAreasListByParentid(parentid);
    }

}
