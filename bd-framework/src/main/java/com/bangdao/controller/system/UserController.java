package com.bangdao.controller.system;

import com.bangdao.common.utils.StringUtils;
import com.bangdao.common.utils.poi.ExcelUtil;
import com.bangdao.domain.system.User;
import com.bangdao.framework.aspectj.lang.annotation.Log;
import com.bangdao.framework.aspectj.lang.enums.BusinessType;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.framework.web.page.TableDataInfo;
import com.bangdao.service.system.IRoleService;
import com.bangdao.service.system.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 用户信息
 */
@Controller
@RequestMapping("/system/user")
@Api(tags = {"用户接口"})
public class UserController extends BaseController {
    private String prefix = "system/user";

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    //@RequiresPermissions("system:user:view")
    @GetMapping()
    @ApiIgnore
    public String user() {
        return prefix + "/user";
    }

    //@RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    @ApiOperation(value="列表", notes="获取用户列表")
    @ApiImplicitParam(name = "user", value = "用户查询实体",required = true, dataType = "User")
    public TableDataInfo list(@RequestBody User user) {
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
   // @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    @ResponseBody
    public Result export(@RequestBody User user) throws Exception {
        try {
            List<User> list = userService.selectUserList(user);
            ExcelUtil<User> util = new ExcelUtil<User>(User.class);
            return util.exportExcel(list, "user");
        } catch (Exception e) {
            return error("导出Excel失败，请联系网站管理员！");
        }
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    @ApiIgnore
    public String add(ModelMap mmap) {
        mmap.put("roles", roleService.selectRoleAll());
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
   // @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    @ApiOperation(value="新增", notes="新增用户")
    @ApiImplicitParam(name = "user", value = "新增实体",required = true, dataType = "User")
    public Result addSave(@RequestBody User user) {
        if (StringUtils.isNotNull(user.getUserId()) && User.isAdmin(user.getUserId())) {
            return error("不允许修改超级管理员用户");
        }
        return toAjax(userService.insertUser(user));
    }


    /**
     * 新增保存用户
     */
   // @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/addUserRole")
    @ResponseBody
    @ApiOperation(value="新增用户角色关系", notes="新增用户角色关系")
    @ApiImplicitParam(name = "user", value = "新增实体",required = true, dataType = "User")
    public Result addSaveUserRole(@RequestBody User user) {
        if (StringUtils.isNotNull(user.getUserId()) && User.isAdmin(user.getUserId())) {
            return error("不允许修改超级管理员用户");
        }
        return toAjax(userService.insertUserRole(user));
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("user", userService.selectUserById(userId));
        mmap.put("roles", roleService.selectRolesByUserId(userId));
        return prefix + "/edit";
    }

    /**
     * 查询用户详情
     */
    @GetMapping("/details/{userId}")
    @ResponseBody
    @ApiOperation(value="用户详情", notes="用户详情")
    @ApiImplicitParam(name = "userId", value = "基本信息id",required = true, dataType = "long")
    public Result details(@PathVariable("userId") Long userId) {
        User user = userService.selectUserById(userId);
        return Result.success().put("user", user);
    }

    /**
     * 修改保存用户
     */
   // @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @ApiOperation(value="修改", notes="修改用户")
    @ApiImplicitParam(name = "user", value = "修改实体",required = true, dataType = "User")
    public Result editSave(@RequestBody User user) {
        if (StringUtils.isNotNull(user.getUserId()) && User.isAdmin(user.getUserId())) {
            return error("不允许修改超级管理员用户");
        }
        return toAjax(userService.updateUser(user));
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/resetPwd";
    }

    //@RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    @ApiOperation(value="重置密码", notes="重置密码")
    @ApiImplicitParam(name = "user", value = "重置密码实体",required = true, dataType = "User")
    public Result resetPwd(@RequestBody User user) {
        return toAjax(userService.resetUserPwd(user));
    }

   // @RequiresPermissions("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    @ApiOperation(value="删除", notes="删除用户")
    @ApiImplicitParam(name = "ids", value = "用户id集合",required = true, dataType = "String")
    public Result remove(String ids) {
        try {
            return toAjax(userService.deleteUserByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    @ApiOperation(value="校验登录名", notes="校验登录名是否重复")
    @ApiImplicitParam(name = "user", value = "校验实体",required = true, dataType = "User")
    public boolean checkLoginNameUnique(@RequestBody User user) {
        return userService.checkLoginNameUnique(user.getLoginName());
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public boolean checkPhoneUnique(@RequestBody User user) {
        return userService.checkPhoneUnique(user);
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public boolean checkEmailUnique(@RequestBody User user) {
        return userService.checkEmailUnique(user);
    }
}