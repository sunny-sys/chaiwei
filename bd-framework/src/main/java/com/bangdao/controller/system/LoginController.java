package com.bangdao.controller.system;

import com.bangdao.common.utils.ServletUtils;
import com.bangdao.common.utils.StringUtils;
import com.bangdao.framework.web.controller.BaseController;
import com.bangdao.framework.web.domain.Result;
import com.bangdao.requestVo.system.LoginRequest;
import com.bangdao.service.system.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录验证
 */
@Controller
@Api(tags = {"登录接口"})
public class LoginController extends BaseController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = {"/login",""})
    public String login(HttpServletRequest request, HttpServletResponse response) {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request)) {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
        return "login";
    }

    @PostMapping(value = "/login")
    @ResponseBody
    @ApiOperation(value = "登录",notes = "登录接口")
    @ApiImplicitParam(name = "loginRequest", value = "登录请求",required = true, dataType = "LoginRequest")
    public Result ajaxLogin(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordToken token = new UsernamePasswordToken(loginRequest.getUsername(), loginRequest.getPassword(), loginRequest.getRememberMe());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            Long[] longs = userService.selectMenuByUserId(getUserId());
            return success().put("user",getUser()).put("menuIds",longs);
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage())) {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth() {
        return "/error/unauth";
    }
}
