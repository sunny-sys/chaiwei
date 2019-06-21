package com.bangdao.service.impl.essentialInfo;

import com.bangdao.domain.essentialInfo.Contacts;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.mapper.essentialInfo.ContactsMapper;
import com.bangdao.requestVo.essentialInfo.ContactsRequest;
import com.bangdao.service.essentialInfo.IContactsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 联系人 服务层实现
 * 
 * @author xml
 * @date 2018-09-18
 */
@Slf4j
@Service
public class ContactsServiceImpl implements IContactsService {

    @Autowired
	private ContactsMapper contactsMapper;

	/**
     * 查询联系人信息
     * @author xml
     * @param id 联系人ID
     * @return 联系人信息
     */
    @Override
	public Result selectContactsById(Integer id) throws Exception {
        if (ObjectUtils.isEmpty(id)){
            return Result.error("参数id不能为空");
        }
		Contacts data = contactsMapper.selectContactsById(id);
	    return Result.success().put("contacts", data);
	}
	
	/**
     * 查询联系人列表
     * @author xml
     * @param contacts 联系人信息
     * @return 联系人集合
     */
	@Override
	public List<Contacts> selectContactsList(ContactsRequest contacts) throws Exception {
	    return contactsMapper.selectContactsList(contacts);
	}
	
    /**
     * 新增联系人
     * @author xml
     * @param contacts 联系人信息
     * @return 结果
     */
	@Override
	public Result insertContacts(ContactsRequest contacts) throws Exception {
		Contacts temp = new Contacts();
        BeanUtils.copyProperties(contacts,temp);
        int count = contactsMapper.insertContacts(temp);
	    return count > 0 ? Result.success() : Result.error();
	}
	
	/**
     * 修改联系人
     * @author xml
     * @param contacts 联系人信息
     * @return 结果
     */
	@Override
	public Result updateContacts(ContactsRequest contacts) throws Exception {
		Contacts temp = new Contacts();
        BeanUtils.copyProperties(contacts,temp);
        int count = contactsMapper.updateContacts(temp);
	    return count > 0 ? Result.success() : Result.error();
	}

	/**
     * 删除联系人对象
     * @author xml
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public Result deleteContactsByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return Result.error("参数ids不能为空");
        }
		int count = contactsMapper.deleteContactsByIds(ids.split(","));
		return count > 0 ? Result.success() : Result.error();
	}
	
}
