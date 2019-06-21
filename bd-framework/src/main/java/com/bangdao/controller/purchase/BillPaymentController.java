package com.bangdao.controller.purchase;

import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.requestVo.purchase.BillPaymentReq;
import com.bangdao.requestVo.purchase.PurchaseReturnReq;
import com.bangdao.responseVo.purchasing.BillPaymentResp;
import com.bangdao.service.purchase.IBillPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 付款单 信息操作处理
 * 
 * @author chaiwei
 * @date 2018-11-13
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/billPayment")
public class BillPaymentController extends BaseController {
    
	@Autowired
	private IBillPaymentService billPaymentService;

	//@RequiresPermissions("bangdao:billPayment:view")
	@GetMapping
	public Result billPayment() {
		return success();
	}

	/**
	 * 查询付款单列表
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:billPayment:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody BillPaymentReq billPayment) throws Exception {
		TableDataInfo tableDataInfo = billPaymentService.selectBillPaymentList(billPayment);
		return tableDataInfo;
	}

	/**
	 * 获取上一单
	 * @author chaiwei
	 */
	@PostMapping("/getPreRecord")
	@ResponseBody
	public TableDataInfo getPreRecord(@RequestBody BillPaymentReq billPayment) throws Exception {
		billPayment.setPageSize(1);
		billPayment.setOrderByColumn("id");
		billPayment.setIsAsc("desc");
		TableDataInfo tableDataInfo = billPaymentService.selectBillPaymentList(billPayment);
		return tableDataInfo;
	}

	/**
	 * 获取下一单
	 * @author chaiwei
	 */
	@PostMapping("/getNextRecord")
	@ResponseBody
	public TableDataInfo getNextRecord(@RequestBody BillPaymentReq billPayment) throws Exception {
		billPayment.setPageSize(1);
		billPayment.setOrderByColumn("id");
		billPayment.setIsAsc("desc");
		TableDataInfo tableDataInfo = billPaymentService.selectBillPaymentList(billPayment);
		return tableDataInfo;
	}

	/**
	 * 新增保存付款单
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:billPayment:add")
	@Log(title = "付款单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result addSave(@RequestBody BillPaymentReq billPayment,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return billPaymentService.insertBillPayment(billPayment);
	}

	/**
	 * 查询付款单详情
	 * @author chaiwei
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return billPaymentService.selectBillPaymentById(id);
	}

	/**
	 * 根据供应商查询付款单（包含采购入库，还采购退货）
	 * @author chaiwei
	 */
	@PostMapping("/returnAndWarehouse/list")
	@ResponseBody
	public Result getReturnAndWarehouse(@RequestBody BillPaymentReq billPayment) throws Exception {
		return billPaymentService.getReturnAndWarehouse(billPayment);
	}

	/**
	 * 修改保存付款单
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:billPayment:edit")
	@Log(title = "付款单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public Result editSave(@RequestBody BillPaymentReq billPayment,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
	    return billPaymentService.updateBillPayment(billPayment);
	}

	/**
	 * 删除付款单
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:billPayment:remove")
	@Log(title = "付款单", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public Result remove(String ids) throws Exception {
		return billPaymentService.deleteBillPaymentByIds(ids);
	}

}
