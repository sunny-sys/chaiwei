package com.bangdao.mapper.essentialInfo;

import com.bangdao.domain.essentialInfo.Enclosure;
import com.bangdao.requestVo.essentialInfo.EnclosureRequest;
import java.util.List;	

/**
 * 附件 数据层
 * 
 * @author xml
 * @date 2018-09-18
 */
public interface EnclosureMapper {
	/**
     * 查询附件信息
     * @author xml
     * @param id 附件ID
     * @return 附件信息
     */
	public Enclosure selectEnclosureById(Integer id);

	/**
     * 查询附件列表
     * @author xml
     * @param enclosure 附件信息
     * @return 附件集合
     */
	public List<Enclosure> selectEnclosureList(EnclosureRequest enclosure);
	
	/**
     * 新增附件
     * @author xml
     * @param enclosure 附件信息
     * @return 结果
     */
	public int insertEnclosure(Enclosure enclosure);
	
	/**
     * 修改附件
     * @author xml
     * @param enclosure 附件信息
     * @return 结果
     */
	public int updateEnclosure(Enclosure enclosure);
	
	/**
     * 删除附件
     * @author xml
     * @param id 附件ID
     * @return 结果
     */
	public int deleteEnclosureById(Integer id);
	
	/**
     * 批量删除附件
     * @author xml
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteEnclosureByIds(String[] ids);
	
}