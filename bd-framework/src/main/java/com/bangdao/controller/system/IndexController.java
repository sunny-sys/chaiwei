package com.bangdao.controller.system;

import com.bangdao.domain.system.Menu;
import com.bangdao.domain.system.User;
import com.bangdao.framework.config.ProjectPropertiesConfig;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.service.system.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 首页 业务处理
 */
@Controller
public class IndexController extends BaseController {
    @Autowired
    private IMenuService menuService;

    @Autowired
    private ProjectPropertiesConfig projectPropertiesConfig;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap) {
        // 取身份信息
        User user = getUser();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUserId(user.getUserId());
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", projectPropertiesConfig.getCopyrightYear());
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap) {
        mmap.put("version", projectPropertiesConfig.getVersion());
        return "main";
    }

}
