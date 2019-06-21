package com.bangdao.service.essentialInfo;

import com.bangdao.domain.essentialInfo.ReceivePaymentAccount;
import com.bangdao.requestVo.essentialInfo.ReceivePaymentAccountRequest;
import com.bangdao.framework.web.domain.Result;

import java.util.List;

/**
 * 收付款账户 服务层
 * 
 * @author chenshao
 * @date 2018-10-15
 */
public interface IReceivePaymentAccountService {
	/**
     * 查询收付款账户信息
     * @author chenshao
     * @param id 收付款账户ID
     * @return 收付款账户信息
     */
	public Result selectReceivePaymentAccountById(Integer id) throws Exception;
	
	/**
     * 查询收付款账户列表
     * @author chenshao
     * @param receivePaymentAccount 收付款账户信息
     * @return 收付款账户集合
     */
	public List<ReceivePaymentAccount> selectReceivePaymentAccountList(ReceivePaymentAccountRequest receivePaymentAccount) throws Exception;
	
	/**
     * 新增收付款账户
     * @author chenshao
     * @param receivePaymentAccount 收付款账户信息
     * @return 结果
     */
	public Result insertReceivePaymentAccount(ReceivePaymentAccountRequest receivePaymentAccount) throws Exception;
	
	/**
     * 修改收付款账户
     * @author chenshao
     * @param receivePaymentAccount 收付款账户信息
     * @return 结果
     */
	public Result updateReceivePaymentAccount(ReceivePaymentAccountRequest receivePaymentAccount) throws Exception;
		
	/**
     * 删除收付款账户信息
     * @author chenshao
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public Result deleteReceivePaymentAccountByIds(String ids) throws Exception;
	
}
