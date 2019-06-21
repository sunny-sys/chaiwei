package com.bangdao.service.essentialInfo;

import com.bangdao.domain.essentialInfo.Contacts;
import com.bangdao.requestVo.essentialInfo.ContactsRequest;
import com.bangdao.framework.web.domain.Result;

import java.util.List;

/**
 * 联系人 服务层
 * 
 * @author xml
 * @date 2018-09-18
 */
public interface IContactsService {
	/**
     * 查询联系人信息
     * @author xml
     * @param id 联系人ID
     * @return 联系人信息
     */
	public Result selectContactsById(Integer id) throws Exception;
	
	/**
     * 查询联系人列表
     * @author xml
     * @param contacts 联系人信息
     * @return 联系人集合
     */
	public List<Contacts> selectContactsList(ContactsRequest contacts) throws Exception;
	
	/**
     * 新增联系人
     * @author xml
     * @param contacts 联系人信息
     * @return 结果
     */
	public Result insertContacts(ContactsRequest contacts) throws Exception;
	
	/**
     * 修改联系人
     * @author xml
     * @param contacts 联系人信息
     * @return 结果
     */
	public Result updateContacts(ContactsRequest contacts) throws Exception;
		
	/**
     * 删除联系人信息
     * @author xml
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteContactsByIds(String ids) throws Exception;
	
}
