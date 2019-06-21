package com.bangdao.controller.essentialInfo;

import com.bangdao.domain.essentialInfo.LogisticsInfo;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.group.essentialInfo.logisticsInfo.LogisticsInfoAdd;
import com.bangdao.group.essentialInfo.logisticsInfo.LogisticsInfoUpdate;
import com.bangdao.requestVo.essentialInfo.LogisticsInfoRequest;
import com.bangdao.service.essentialInfo.ILogisticsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物流 信息操作处理
 * 
 * @author xupj
 * @date 2018-10-10
 */
@Slf4j
@Controller
@RequestMapping("/bangdao/logisticsInfo")
public class LogisticsInfoController extends BaseController {
    
	@Autowired
	private ILogisticsInfoService logisticsInfoService;

	@RequiresPermissions("bangdao:logisticsInfo:view")
	@GetMapping
	public Result logisticsInfo() {
		return success();
	}

	/**
	 * 查询物流列表
	 * @author xupj
	 */
	//@RequiresPermissions("bangdao:logisticsInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestBody LogisticsInfoRequest logisticsInfo) throws Exception {
		startPage();
        List<LogisticsInfo> list = logisticsInfoService.selectLogisticsInfoList(logisticsInfo);
		return getDataTable(list);
	}

	/**
	 * 新增保存物流
	 * @author xupj
	 */
	//@RequiresPermissions("bangdao:logisticsInfo:add")
	@Log(title = "物流", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result addSave(@RequestBody @Validated({LogisticsInfoAdd.class}) LogisticsInfoRequest logisticsInfo,
						  BindingResult result) throws Exception {
	    if (result.hasErrors()){
	        return Result.error(result.getAllErrors().get(0).getDefaultMessage());
		}
		return logisticsInfoService.insertLogisticsInfo(logisticsInfo);
	}

	/**
	 * 查询物流详情
	 * @author xupj
	 */
	@GetMapping("/edit/{id}")
	@ResponseBody
	public Result edit(@PathVariable("id") Integer id) throws Exception {
		return logisticsInfoService.selectLogisticsInfoById(id);
	}

	/**
	 * 修改保存物流
	 * @author xupj
	 */
    //@RequiresPermissions("bangdao:logisticsInfo:edit")
	@Log(title = "物流", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public Result editSave(@RequestBody @Validated({LogisticsInfoUpdate.class}) LogisticsInfoRequest logisticsInfo,
						   BindingResult result) throws Exception {
        if (result.hasErrors()){
            return Result.error(result.getAllErrors().get(0).getDefaultMessage());
        }
	    return logisticsInfoService.updateLogisticsInfo(logisticsInfo);
	}

	/**
	 * 删除物流
	 * @author xupj
	 */
	//@RequiresPermissions("bangdao:logisticsInfo:remove")
	@Log(title = "物流", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public Result remove(String ids) throws Exception {
		return logisticsInfoService.deleteLogisticsInfoByIds(ids);
	}

}
