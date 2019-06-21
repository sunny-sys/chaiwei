package com.bangdao.controller.essentialInfo;

import com.bangdao.domain.essentialInfo.ReceivePaymentAccount;
import com.bangdao.domain.essentialInfo.Subject;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.group.essentialInfo.receivePaymentAccount.ReceivePaymentAccountAdd;
import com.bangdao.group.essentialInfo.receivePaymentAccount.ReceivePaymentAccountUpdate;
import com.bangdao.requestVo.essentialInfo.ReceivePaymentAccountRequest;
import com.bangdao.service.essentialInfo.IReceivePaymentAccountService;
import com.bangdao.service.essentialInfo.ISubjectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收付款账户 信息操作处理
 * 
 * @author chenshao
 * @date 2018-10-15
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/receivePaymentAccount")
public class ReceivePaymentAccountController extends BaseController {
    
	@Autowired
	private IReceivePaymentAccountService receivePaymentAccountService;

	@Autowired
	private ISubjectService subjectService;
	
	@RequiresPermissions("bangdao:receivePaymentAccount:view")
	@GetMapping
	public Result receivePaymentAccount() {
		return success();
	}

	/**
	 * 查询收付款账户列表
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:receivePaymentAccount:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody ReceivePaymentAccountRequest receivePaymentAccount) throws Exception {
		startPage();
        List<ReceivePaymentAccount> list = receivePaymentAccountService.selectReceivePaymentAccountList(receivePaymentAccount);
		return getDataTable(list);
	}
	/**
	 * 查询科目（收付款账户1001和1002下对应科目）
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:subject:list")
	@PostMapping("/subject")
	@ResponseBody
	public TableDataInfo subject() throws Exception {
		startPage();
        List<Subject> list = subjectService.selectSubjectToAccount();
		return getDataTable(list);
	}
	/**
	 * 新增保存收付款账户
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:receivePaymentAccount:add")
	@Log(title = "收付款账户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result addSave(@RequestBody @Validated({ReceivePaymentAccountAdd.class}) ReceivePaymentAccountRequest receivePaymentAccount,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return receivePaymentAccountService.insertReceivePaymentAccount(receivePaymentAccount);
	}

	/**
	 * 查询收付款账户详情
	 * @author chenshao
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return receivePaymentAccountService.selectReceivePaymentAccountById(id);
	}

	/**
	 * 修改保存收付款账户
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:receivePaymentAccount:edit")
	@Log(title = "收付款账户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public Result editSave(@RequestBody @Validated({ReceivePaymentAccountUpdate.class}) ReceivePaymentAccountRequest receivePaymentAccount,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
	    return receivePaymentAccountService.updateReceivePaymentAccount(receivePaymentAccount);
	}

	/**
	 * 删除收付款账户
	 * @author chenshao
	 */
	//@RequiresPermissions("bangdao:receivePaymentAccount:remove")
	@Log(title = "收付款账户", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public Result remove(String ids) throws Exception {
		return receivePaymentAccountService.deleteReceivePaymentAccountByIds(ids);
	}

}
