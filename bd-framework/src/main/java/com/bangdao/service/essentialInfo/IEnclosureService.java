package com.bangdao.service.essentialInfo;

import com.bangdao.domain.essentialInfo.Enclosure;
import com.bangdao.requestVo.essentialInfo.EnclosureRequest;
import com.bangdao.framework.web.domain.Result;

import java.util.List;

/**
 * 附件 服务层
 * 
 * @author xml
 * @date 2018-09-18
 */
public interface IEnclosureService {
	/**
     * 查询附件信息
     * @author xml
     * @param id 附件ID
     * @return 附件信息
     */
	public Result selectEnclosureById(Integer id) throws Exception;
	
	/**
     * 查询附件列表
     * @author xml
     * @param enclosure 附件信息
     * @return 附件集合
     */
	public List<Enclosure> selectEnclosureList(EnclosureRequest enclosure) throws Exception;
	
	/**
     * 新增附件
     * @author xml
     * @param enclosure 附件信息
     * @return 结果
     */
	public Result insertEnclosure(EnclosureRequest enclosure) throws Exception;
	
	/**
     * 修改附件
     * @author xml
     * @param enclosure 附件信息
     * @return 结果
     */
	public Result updateEnclosure(EnclosureRequest enclosure) throws Exception;
		
	/**
     * 删除附件信息
     * @author xml
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteEnclosureByIds(String ids) throws Exception;
	
}
