package com.bangdao.service.impl.essentialInfo;

import com.bangdao.domain.essentialInfo.DictionaryItem;
import com.bangdao.domain.essentialInfo.ReceivePaymentAccount;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.mapper.essentialInfo.DictionaryItemMapper;
import com.bangdao.mapper.essentialInfo.ReceivePaymentAccountMapper;
import com.bangdao.requestVo.essentialInfo.ReceivePaymentAccountRequest;
import com.bangdao.service.essentialInfo.IReceivePaymentAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 收付款账户 服务层实现
 * 
 * @author chenshao
 * @date 2018-10-15
 */
@Slf4j
@Service
public class ReceivePaymentAccountServiceImpl implements IReceivePaymentAccountService {

    @Autowired
	private ReceivePaymentAccountMapper receivePaymentAccountMapper;

    @Autowired
   	private DictionaryItemMapper dictionaryItemMapper;
	/**
     * 查询收付款账户信息
     * @author chenshao
     * @param id 收付款账户ID
     * @return 收付款账户信息
     */
    @Override
	public Result selectReceivePaymentAccountById(Integer id) throws Exception {
        if (ObjectUtils.isEmpty(id)){
            return Result.error("参数id不能为空");
        }
		ReceivePaymentAccount data = receivePaymentAccountMapper.selectReceivePaymentAccountById(id);
	    return Result.success().put("receivePaymentAccount", data);
	}
	
	/**
     * 查询收付款账户列表
     * @author chenshao
     * @param receivePaymentAccount 收付款账户信息
     * @return 收付款账户集合
     */
	@Override
	public List<ReceivePaymentAccount> selectReceivePaymentAccountList(ReceivePaymentAccountRequest receivePaymentAccount) throws Exception {
	    return receivePaymentAccountMapper.selectReceivePaymentAccountList(receivePaymentAccount);
	}
	
    /**
     * 新增收付款账户
     * @author chenshao
     * @param receivePaymentAccount 收付款账户信息
     * @return 结果
     */
	@Override
	public Result insertReceivePaymentAccount(ReceivePaymentAccountRequest receivePaymentAccount) throws Exception {
		ReceivePaymentAccount temp = new ReceivePaymentAccount();
        BeanUtils.copyProperties(receivePaymentAccount,temp);
        int accountTypeId = temp.getAccountTypeId();
        if(accountTypeId==56){//只有当账户类型为银行账户即id为56时，开户银行、开户名、银行账号这三项才可以填写，否则不可填写
        	if(temp.getBankAccount().isEmpty() ||temp.getBankAccountName().isEmpty() ||temp.getBankName().isEmpty()){
        		return Result.error("开户名或开户银行名称或银行账号不能为空");
        	}
        }else{
        	if(!temp.getBankAccount().isEmpty() ||!temp.getBankAccountName().isEmpty() ||!temp.getBankName().isEmpty()){
        		return Result.error("非银行账户，不可填写银行账户信息");
        	}
        }
        DictionaryItem data = dictionaryItemMapper.selectDictionaryItemById(accountTypeId);
        temp.setAccountType(data.getItemName());
        int count = receivePaymentAccountMapper.insertReceivePaymentAccount(temp);
	    return count > 0 ? Result.success() : Result.error();
	}
	
	/**
     * 修改收付款账户
     * @author chenshao
     * @param receivePaymentAccount 收付款账户信息
     * @return 结果
     */
	@Override
	public Result updateReceivePaymentAccount(ReceivePaymentAccountRequest receivePaymentAccount) throws Exception {
		ReceivePaymentAccount temp = new ReceivePaymentAccount();
        BeanUtils.copyProperties(receivePaymentAccount,temp);
        int accountTypeId = temp.getAccountTypeId();
        if(accountTypeId==56){//只有当账户类型为银行账户即id为56时，开户银行、开户名、银行账号这三项才可以填写，否则不可填写
        	if(temp.getBankAccount().isEmpty() ||temp.getBankAccountName().isEmpty() ||temp.getBankName().isEmpty()){
        		return Result.error("开户名或开户银行名称或银行账号不能为空");
        	}
        }else{
        	if(!temp.getBankAccount().isEmpty() ||!temp.getBankAccountName().isEmpty() ||!temp.getBankName().isEmpty()){
        		return Result.error("非银行账户，不可填写银行账户信息");
        	}
        }
        DictionaryItem data = dictionaryItemMapper.selectDictionaryItemById(accountTypeId);
        temp.setAccountType(data.getItemName());
        int count = receivePaymentAccountMapper.updateReceivePaymentAccount(temp);
	    return count > 0 ? Result.success() : Result.error();
	}

	/**
     * 删除收付款账户对象
     * @author chenshao
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public Result deleteReceivePaymentAccountByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return Result.error("参数ids不能为空");
        }
		int count = receivePaymentAccountMapper.deleteReceivePaymentAccountByIds(ids.split(","));
		return count > 0 ? Result.success() : Result.error();
	}
	
}
