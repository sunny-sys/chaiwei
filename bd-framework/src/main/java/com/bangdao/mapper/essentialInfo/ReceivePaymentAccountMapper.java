package com.bangdao.mapper.essentialInfo;

import com.bangdao.domain.essentialInfo.ReceivePaymentAccount;
import com.bangdao.requestVo.essentialInfo.ReceivePaymentAccountRequest;
import java.util.List;	

/**
 * 收付款账户 数据层
 * 
 * @author chenshao
 * @date 2018-10-15
 */
public interface ReceivePaymentAccountMapper {
	/**
     * 查询收付款账户信息
     * @author chenshao
     * @param id 收付款账户ID
     * @return 收付款账户信息
     */
	public ReceivePaymentAccount selectReceivePaymentAccountById(Integer id);

	/**
     * 查询收付款账户列表
     * @author chenshao
     * @param receivePaymentAccount 收付款账户信息
     * @return 收付款账户集合
     */
	public List<ReceivePaymentAccount> selectReceivePaymentAccountList(ReceivePaymentAccountRequest receivePaymentAccount);
	
	/**
     * 新增收付款账户
     * @author chenshao
     * @param receivePaymentAccount 收付款账户信息
     * @return 结果
     */
	public int insertReceivePaymentAccount(ReceivePaymentAccount receivePaymentAccount);
	
	/**
     * 修改收付款账户
     * @author chenshao
     * @param receivePaymentAccount 收付款账户信息
     * @return 结果
     */
	public int updateReceivePaymentAccount(ReceivePaymentAccount receivePaymentAccount);
	
	/**
     * 删除收付款账户
     * @author chenshao
     * @param id 收付款账户ID
     * @return 结果
     */
	public int deleteReceivePaymentAccountById(Integer id);
	
	/**
     * 批量删除收付款账户
     * @author chenshao
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteReceivePaymentAccountByIds(String[] ids);
	
}