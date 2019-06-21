package com.bangdao.controller.essentialInfo;

import com.bangdao.domain.essentialInfo.Contacts;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.group.essentialInfo.contacts.ContactsAdd;
import com.bangdao.group.essentialInfo.contacts.ContactsUpdate;
import com.bangdao.requestVo.essentialInfo.ContactsRequest;
import com.bangdao.service.essentialInfo.IContactsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 联系人 信息操作处理
 * 
 * @author xml
 * @date 2018-09-18
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/contacts")
public class ContactsController extends BaseController {
    
	@Autowired
	private IContactsService contactsService;

	@RequiresPermissions("bangdao:contacts:view")
	@GetMapping
	public Result contacts() {
		return success();
	}

	/**
	 * 查询联系人列表
	 * @author xml
	 */
	@RequiresPermissions("bangdao:contacts:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody ContactsRequest contacts) throws Exception {
		startPage();
        List<Contacts> list = contactsService.selectContactsList(contacts);
		return getDataTable(list);
	}

	/**
	 * 新增保存联系人
	 * @author xml
	 */
	@RequiresPermissions("bangdao:contacts:add")
	@Log(title = "联系人", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result addSave(@RequestBody @Validated({ContactsAdd.class}) ContactsRequest contacts,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return contactsService.insertContacts(contacts);
	}

	/**
	 * 查询联系人详情
	 * @author xml
	 */
	@GetMapping("/edit/{id}")
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return contactsService.selectContactsById(id);
	}

	/**
	 * 修改保存联系人
	 * @author xml
	 */
	@RequiresPermissions("bangdao:contacts:edit")
	@Log(title = "联系人", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public Result editSave(@RequestBody @Validated({ContactsUpdate.class}) ContactsRequest contacts,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
	    return contactsService.updateContacts(contacts);
	}

	/**
	 * 删除联系人
	 * @author xml
	 */
	@RequiresPermissions("bangdao:contacts:remove")
	@Log(title = "联系人", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public Result remove(String ids) throws Exception {
		return contactsService.deleteContactsByIds(ids);
	}

}
