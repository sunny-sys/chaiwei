package com.bangdao.controller.essentialInfo;

import com.bangdao.domain.essentialInfo.DictionaryItem;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.group.essentialInfo.dictionaryItem.DictionaryItemAdd;
import com.bangdao.group.essentialInfo.dictionaryItem.DictionaryItemUpdate;
import com.bangdao.requestVo.essentialInfo.DictionaryItemRequest;
import com.bangdao.service.essentialInfo.IDictionaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典明细 信息操作处理
 * 
 * @author xupj
 * @date 2018-09-14
 */
@Controller
@RequestMapping("/module/dictionaryItem")
public class DictionaryItemController extends BaseController {

	@Autowired
	private IDictionaryItemService dictionaryItemService;

//	@RequiresPermissions("module:dictionaryItem:view")
	@GetMapping
	public Result dictionaryItem() {
	    return success();
	}

	/**
	 * 查询字典明细列表
	 */
//	@RequiresPermissions("module:dictionaryItem:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody DictionaryItemRequest dictionaryItem) throws Exception {
		startPage();
        List<DictionaryItem> list = dictionaryItemService.selectDictionaryItemList(dictionaryItem);
		return getDataTable(list);
	}

	/**
	 * 新增保存字典明细
	 */
//	@RequiresPermissions("module:dictionaryItem:add")
	@Log(title = "字典明细", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result addSave(@RequestBody @Validated({DictionaryItemAdd.class}) DictionaryItemRequest dictionaryItem,
						  BindingResult result) throws Exception {
		if (result.hasErrors()){
			return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return dictionaryItemService.insertDictionaryItem(dictionaryItem);
	}

	/**
	 * 查询字典明细详情
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
	public Result edit(@PathVariable("id") Integer id) throws Exception {
	    return dictionaryItemService.selectDictionaryItemById(id);
	}

	/**
	 * 修改保存字典明细
	 */
//	@RequiresPermissions("module:dictionaryItem:edit")
	@Log(title = "字典明细", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public Result editSave(@RequestBody @Validated({DictionaryItemUpdate.class})  DictionaryItemRequest dictionaryItem,
						   BindingResult result) throws Exception {
		if (result.hasErrors()){
			return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return dictionaryItemService.updateDictionaryItem(dictionaryItem);
	}
	
	/**
	 * 删除字典明细
	 */
//	@RequiresPermissions("module:dictionaryItem:remove")
	@Log(title = "字典明细", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public Result remove(String ids) throws Exception {
		return dictionaryItemService.deleteDictionaryItemByIds(ids);
	}

    /**
     * 根据字典类型查询字典明细详情数据
     */
    @GetMapping("/data/{dicType}")
    @ResponseBody
    public TableDataInfo dataDicType(@PathVariable("dicType") String dicType) throws Exception {
        startPage();
        List<DictionaryItem> datas = dictionaryItemService.selectDictionaryItemByDicType(dicType);
        return getDataTable(datas);
    }
}
