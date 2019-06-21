package com.bangdao.framework.shiro.service;

import com.bangdao.common.constant.ShiroConstants;
import com.bangdao.common.constant.UserConstants;
import com.bangdao.common.exception.user.CaptchaException;
import com.bangdao.common.exception.user.UserBlockedException;
import com.bangdao.common.exception.user.UserNotExistsException;
import com.bangdao.common.utils.DateUtils;
import com.bangdao.common.utils.ServletUtils;
import com.bangdao.common.utils.security.ShiroUtils;
import com.bangdao.domain.system.User;
import com.bangdao.common.constant.UserStatus;
import com.bangdao.service.system.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 登录service
 */
@Component
public class LoginService {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private IUserService userService;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA))) {
            throw new CaptchaException();
        }
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new UserNotExistsException();
        }
        User user = userService.selectUserByLoginName(username);

        if (user == null && username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN)) {
            user = userService.selectUserByPhoneNumber(username);
        }
        if (user == null && username.matches(UserConstants.EMAIL_PATTERN)) {
            user = userService.selectUserByEmail(username);
        }
        if (user == null || UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            throw new UserNotExistsException();
        }
        passwordService.validate(user, password);
        if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            throw new UserBlockedException(user.getRemark());
        }
        recordLoginInfo(user);
        return user;
    }

    /**
     * 记录登录信息
     * @param user
     */
    public void recordLoginInfo(User user) {
        user.setLoginIp(ShiroUtils.getIp());
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserInfo(user);
    }

}
