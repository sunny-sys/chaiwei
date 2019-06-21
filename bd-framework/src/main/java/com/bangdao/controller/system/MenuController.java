package com.bangdao.controller.system;

import com.bangdao.domain.system.Menu;
import com.bangdao.domain.system.Role;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.service.system.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 菜单信息
 */
@Controller
@RequestMapping("/system/menu")
@Api(tags = {"菜单接口"})
public class MenuController extends BaseController {

    private String prefix = "system/menu";

    @Autowired
    private IMenuService menuService;

    //@RequiresPermissions("system:menu:view")
    @GetMapping()
    public String menu() {
        return prefix + "/menu";
    }

    //@RequiresPermissions("system:menu:list")
    @PostMapping("/list")
    @ResponseBody
    @ApiOperation(value="列表", notes="菜单列表")
    @ApiImplicitParam(name = "menu", value = "查询实体",required = true, dataType = "Menu")
    public List<Menu> list(@RequestBody Menu menu) {
        List<Menu> menuList = menuService.selectMenuList(menu);
        return menuList;
    }

    /**
     * 删除菜单
     */
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    //@RequiresPermissions("system:menu:remove")
    @PostMapping("/remove/{menuId}")
    @ResponseBody
    @ApiOperation(value="删除", notes="删除")
    @ApiImplicitParam(name = "menuId", value = "菜单menuId",required = true, dataType = "long")
    public Result remove(@PathVariable("menuId") Long menuId) {
        if (menuService.selectCountMenuByParentId(menuId) > 0) {
            return error(1, "存在子菜单,不允许删除");
        }
        if (menuService.selectCountRoleMenuByMenuId(menuId) > 0) {
            return error(1, "菜单已分配,不允许删除");
        }
        return toAjax(menuService.deleteMenuById(menuId));
    }

    /**
     * 新增
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap) {
        Menu menu = null;
        if (0L != parentId) {
            menu = menuService.selectMenuById(parentId);
        } else {
            menu = new Menu();
            menu.setMenuId(0L);
            menu.setMenuName("主目录");
        }
        mmap.put("menu", menu);
        return prefix + "/add";
    }

    /**
     * 新增保存菜单
     */
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    //@RequiresPermissions("system:menu:add")
    @PostMapping("/add")
    @ResponseBody
    @ApiOperation(value="新增", notes="新增")
    @ApiImplicitParam(name = "menu", value = "新增",required = true, dataType = "Menu")
    public Result addSave(@RequestBody Menu menu) {
        return toAjax(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @GetMapping("/edit/{menuId}")
    public String edit(@PathVariable("menuId") Long menuId, ModelMap mmap) {
        mmap.put("menu", menuService.selectMenuById(menuId));
        return prefix + "/edit";
    }

    /**
     * 菜单详情
     */
    @GetMapping("/details/{menuId}")
    @ResponseBody
    @ApiOperation(value="详情", notes="菜单详情")
    @ApiImplicitParam(name = "menuId", value = "基本信息id",required = true, dataType = "long")
    public Result details(@PathVariable("menuId") Long menuId) {
        Menu menu = menuService.selectMenuById(menuId);
        return Result.success().put("menu", menu);
    }

    /**
     * 修改保存菜单
     */
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    //@RequiresPermissions("system:menu:edit")
    @PostMapping("/edit")
    @ResponseBody
    @ApiOperation(value="修改", notes="修改")
    @ApiImplicitParam(name = "menu", value = "修改",required = true, dataType = "Menu")
    public Result editSave(@RequestBody Menu menu) {
        return toAjax(menuService.updateMenu(menu));
    }

    /**
     * 选择菜单图标
     */
    @GetMapping("/icon")
    public String icon() {
        return prefix + "/icon";
    }

    /**
     * 校验菜单名称
     */
    @PostMapping("/checkMenuNameUnique")
    @ResponseBody
    public boolean checkMenuNameUnique(@RequestBody Menu menu) {
        return menuService.checkMenuNameUnique(menu);
    }

    /**
     * 加载角色菜单列表树
     */
    @PostMapping("/roleMenuTreeData")
    @ResponseBody
    @ApiOperation(value="加载角色菜单列表树", notes="根据用户角色来查询菜单树")
    @ApiImplicitParam(name = "role", value = "角色实体",required = true, dataType = "Role")
    public List<Map<String, Object>> roleMenuTreeData(@RequestBody Role role) {
        List<Map<String, Object>> tree = menuService.roleMenuTreeData(role);
        return tree;
    }

    /**
     * 加载所有菜单列表树
     */
    @GetMapping("/menuTreeData")
    @ResponseBody
    @ApiOperation(value="获取菜单树", notes="获取所有菜单树")
    public List<Map<String, Object>> menuTreeData() {
        List<Map<String, Object>> tree = menuService.menuTreeData();
        return tree;
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree/{menuId}")
    public String selectMenuTree(@PathVariable("menuId") Long menuId, ModelMap mmap) {
        mmap.put("menu", menuService.selectMenuById(menuId));
        return prefix + "/tree";
    }
}