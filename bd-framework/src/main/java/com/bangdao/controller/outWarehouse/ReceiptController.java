package com.bangdao.controller.outWarehouse;

import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.requestVo.outWarehouse.ReceiptReq;
import com.bangdao.service.outWarehouse.IReceiptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * 收款单 信息操作处理
 * 
 * @author chaiwei
 * @date 2018-11-13
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/receipt")
public class ReceiptController extends BaseController {
    
	@Autowired
	private IReceiptService receiptService;

	//@RequiresPermissions("bangdao:receipt:view")
	@GetMapping
	public Result receipt() {
		return success();
	}

	/**
	 * 查询收款单列表
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:receipt:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody ReceiptReq receipt) throws Exception {
		TableDataInfo tableDataInfo = receiptService.selectReceiptList(receipt);
		return tableDataInfo;
	}

	/**
	 * 获取上一单
	 * @author chaiwei
	 */
	@PostMapping("/getPreRecord")
	@ResponseBody
	public TableDataInfo getPreRecord(@RequestBody ReceiptReq receipt) throws Exception {
		receipt.setPageSize(1);
		receipt.setOrderByColumn("id");
		receipt.setIsAsc("desc");
		TableDataInfo tableDataInfo = receiptService.selectReceiptList(receipt);
		return tableDataInfo;
	}

	/**
	 * 获取下一单
	 * @author chaiwei
	 */
	@PostMapping("/getNextRecord")
	@ResponseBody
	public TableDataInfo getNextRecord(@RequestBody ReceiptReq receipt) throws Exception {
		receipt.setPageSize(1);
		receipt.setOrderByColumn("id");
		receipt.setIsAsc("desc");
		TableDataInfo tableDataInfo = receiptService.selectReceiptList(receipt);
		return tableDataInfo;
	}

	/**
	 * 新增保存收款单
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:receipt:add")
	@Log(title = "收款单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result addSave(@RequestBody ReceiptReq receipt,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return receiptService.insertReceipt(receipt);
	}

	/**
	 * 查询收款单详情
	 * @author chaiwei
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return receiptService.selectReceiptById(id);
	}

	/**
	 * 根据供应商查询收款单（包含出库，退货）
	 * @author chaiwei
	 */
	@PostMapping("/returnAndWarehouse/list")
	@ResponseBody
	public Result getReturnAndWarehouse(@RequestBody ReceiptReq receipt) throws Exception {
		return receiptService.getReturnAndWarehouse(receipt);
	}

	/**
	 * 修改保存收款单
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:receipt:edit")
	@Log(title = "收款单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public Result editSave(@RequestBody ReceiptReq receipt,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
	    return receiptService.updateReceipt(receipt);
	}

	/**
	 * 删除收款单
	 * @author chaiwei
	 */
	//@RequiresPermissions("bangdao:receipt:remove")
	@Log(title = "收款单", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public Result remove(String ids) throws Exception {
		return receiptService.deleteReceiptByIds(ids);
	}

}
