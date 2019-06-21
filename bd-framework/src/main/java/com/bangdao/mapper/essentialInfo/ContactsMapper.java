package com.bangdao.mapper.essentialInfo;

import com.bangdao.domain.essentialInfo.Contacts;
import com.bangdao.requestVo.essentialInfo.ContactsRequest;
import java.util.List;	

/**
 * 联系人 数据层
 * 
 * @author xml
 * @date 2018-09-18
 */
public interface ContactsMapper {
	/**
     * 查询联系人信息
     * @author xml
     * @param id 联系人ID
     * @return 联系人信息
     */
	public Contacts selectContactsById(Integer id);

	/**
     * 查询联系人列表
     * @author xml
     * @param contacts 联系人信息
     * @return 联系人集合
     */
	public List<Contacts> selectContactsList(ContactsRequest contacts);
	
	/**
     * 新增联系人
     * @author xml
     * @param contacts 联系人信息
     * @return 结果
     */
	public int insertContacts(Contacts contacts);
	
	/**
     * 修改联系人
     * @author xml
     * @param contacts 联系人信息
     * @return 结果
     */
	public int updateContacts(Contacts contacts);
	
	/**
     * 删除联系人
     * @author xml
     * @param id 联系人ID
     * @return 结果
     */
	public int deleteContactsById(Integer id);
	
	/**
     * 批量删除联系人
     * @author xml
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteContactsByIds(String[] ids);
	
}