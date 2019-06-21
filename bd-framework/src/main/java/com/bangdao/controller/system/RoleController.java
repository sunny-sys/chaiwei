package com.bangdao.controller.system;

import com.bangdao.common.utils.poi.ExcelUtil;
import com.bangdao.domain.system.Role;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.service.system.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色信息
 */
@Controller
@RequestMapping("/system/role")
@Api(tags = {"角色接口"})
public class RoleController extends BaseController {

    private String prefix = "system/role";

    @Autowired
    private IRoleService roleService;

    //@RequiresPermissions("system:role:view")
    @GetMapping()
    public String role() {
        return prefix + "/role";
    }

    //@RequiresPermissions("system:role:list")
    @PostMapping("/list")
    @ResponseBody
    @ApiOperation(value="列表", notes="角色列表")
    @ApiImplicitParam(name = "role", value = "查询实体",required = true, dataType = "Role")
    public TableDataInfo list(@RequestBody Role role) {
        startPage();
        List<Role> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }

    @Log(title = "角色管理", businessType = BusinessType.EXPORT)
    //@RequiresPermissions("system:role:export")
    @PostMapping("/export")
    @ResponseBody
    public Result export(@RequestBody Role role) throws Exception {
        try {
            List<Role> list = roleService.selectRoleList(role);
            ExcelUtil<Role> util = new ExcelUtil<Role>(Role.class);
            return util.exportExcel(list, "role");
        } catch (Exception e) {
            return error("导出Excel失败，请联系网站管理员！");
        }
    }

    /**
     * 新增角色
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存角色
     */
    //@RequiresPermissions("system:role:add")
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    @ApiOperation(value="新增", notes="新增")
    @ApiImplicitParam(name = "role", value = "新增实体",required = true, dataType = "Role")
    public Result addSave(@RequestBody Role role) {
        return toAjax(roleService.insertRole(role));

    }

    /**
     * 新增保存角色菜单
     */
    //@RequiresPermissions("system:role:add")
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping("/addRoleMenu")
    @ResponseBody
    @ApiOperation(value="新增角色菜单关系", notes="新增角色菜单关系")
    @ApiImplicitParam(name = "role", value = "新增实体",required = true, dataType = "Role")
    public Result addSaveRoleMenu(@RequestBody Role role) {
        return toAjax(roleService.insertRoleMenu(role));

    }

    /**
     * 修改角色
     */
    @GetMapping("/edit/{roleId}")
    public String edit(@PathVariable("roleId") Long roleId, ModelMap mmap) {
        mmap.put("role", roleService.selectRoleById(roleId));
        return prefix + "/edit";
    }

    /**
     * 角色详情
     */
    @GetMapping("/details/{roleId}")
    @ResponseBody
    @ApiOperation(value="详情", notes="角色详情")
    @ApiImplicitParam(name = "roleId", value = "基本信息id",required = true, dataType = "long")
    public Result details(@PathVariable("roleId") Long roleId) {
        Role role = roleService.selectRoleById(roleId);
        return Result.success().put("role", role);
    }

    /**
     * 修改保存角色
     */
    //@RequiresPermissions("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    @ApiOperation(value="修改", notes="修改")
    @ApiImplicitParam(name = "role", value = "新增实体",required = true, dataType = "Role")
    public Result editSave(@RequestBody Role role) {
        return toAjax(roleService.updateRole(role));
    }

    //@RequiresPermissions("system:role:remove")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    @ApiOperation(value="删除", notes="批量删除")
    @ApiImplicitParam(name = "ids", value = "角色id集合",required = true, dataType = "String")
    public Result remove(String ids) {
        try {
            return toAjax(roleService.deleteRoleByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 校验角色名称
     */
    @PostMapping("/checkRoleNameUnique")
    @ResponseBody
    public boolean checkRoleNameUnique(@RequestBody Role role) {
        return roleService.checkRoleNameUnique(role);
    }

    /**
     * 校验角色权限
     */
    @PostMapping("/checkRoleKeyUnique")
    @ResponseBody
    public boolean checkRoleKeyUnique(@RequestBody Role role) {
        return roleService.checkRoleKeyUnique(role);
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree")
    public String selectMenuTree() {
        return prefix + "/tree";
    }

}