package com.bangdao.service.impl.system;

import com.bangdao.common.support.Convert;
import com.bangdao.common.utils.StringUtils;
import com.bangdao.domain.system.Role;
import com.bangdao.domain.system.User;
import com.bangdao.domain.system.UserRole;
import com.bangdao.framework.shiro.service.PasswordService;
import com.bangdao.mapper.system.RoleMapper;
import com.bangdao.mapper.system.UserMapper;
import com.bangdao.mapper.system.UserMenuMapper;
import com.bangdao.mapper.system.UserRoleMapper;
import com.bangdao.service.system.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 用户 业务层处理
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PasswordService passwordService;
    @Autowired
    private UserMenuMapper userMenu;
    /**
     * 根据条件分页查询用户对象
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    public List<User> selectUserList(User user) {
        List<User> users = userMapper.selectUserList(user);
        if (null != users)
        {
            for (User u : users)
            {
                List<Long> roleIds = userRoleMapper.selectUserRoleByUserId(u.getUserId());
                if(null != roleIds)
                {
                    Long[] temp = roleIds.toArray(new Long[roleIds.size()]);
                    u.setRoleIds(temp);
                }
            }
        }
        return users;
    }

    /**
     * 通过用户名查询用户
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public User selectUserByLoginName(String userName) {
        User user = userMapper.selectUserByLoginName(userName);
        if(null != user)
        {
            List<Long> roleIds = userRoleMapper.selectUserRoleByUserId(user.getUserId());
            if(null != roleIds)
            {
                Long[] temp = roleIds.toArray(new Long[roleIds.size()]);
                user.setRoleIds(temp);
            }
        }
        return user;
    }

    /**
     * 通过手机号码查询用户
     * @return 用户对象信息
     */
    @Override
    public User selectUserByPhoneNumber(String phoneNumber) {
        User user = userMapper.selectUserByPhoneNumber(phoneNumber);
        if(null != user)
        {
            List<Long> roleIds = userRoleMapper.selectUserRoleByUserId(user.getUserId());
            if(null != roleIds)
            {
                Long[] temp = roleIds.toArray(new Long[roleIds.size()]);
                user.setRoleIds(temp);
            }
        }
        return user;
    }

    /**
     * 通过邮箱查询用户
     * @param email 邮箱
     * @return 用户对象信息
     */
    @Override
    public User selectUserByEmail(String email) {
        User user = userMapper.selectUserByEmail(email);
        if(null != user)
        {
            List<Long> roleIds = userRoleMapper.selectUserRoleByUserId(user.getUserId());
            if(null != roleIds)
            {
                Long[] temp = roleIds.toArray(new Long[roleIds.size()]);
                user.setRoleIds(temp);
            }
        }
        return user;
    }

    /**
     * 通过用户ID查询用户
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public User selectUserById(Long userId) {
        if (null == userId){
            return null;
        }
        User user = userMapper.selectUserById(userId);
        List<Long> roleIds = userRoleMapper.selectUserRoleByUserId(userId);
        if(null != roleIds)
        {
            Long[] temp = roleIds.toArray(new Long[roleIds.size()]);
            user.setRoleIds(temp);
        }
        return user;
    }


    public Long[] selectMenuByUserId(Long userId)
    {
        if(null == userId){
            return new Long[0];
        }
        List<Long> list = userMenu.selectMenuByUserId(userId);
        if(null != list)
        {
            Long[] longs = list.toArray(new Long[list.size()]);
            return longs;
        }
        else {
            return new Long[0];
        }
    }

    /**
     * 通过用户ID删除用户
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteUserById(Long userId) {
        userRoleMapper.deleteUserRoleByUserId(userId);
        return userMapper.deleteUserById(userId);
    }

    /**
     * 批量删除用户信息
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return 0;
        }
        Long[] userIds = Convert.toLongArray(ids);
        for (Long userId : userIds) {
            if (User.isAdmin(userId)) {
                throw new Exception("不允许删除超级管理员用户");
            }
        }
        return userMapper.deleteUserByIds(userIds);
    }

    /**
     * 新增保存用户信息
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertUser(User user) {
        user.randomSalt();
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        //user.setCreateBy(ShiroUtils.getLoginName());
        // 新增用户信息
        int rows = userMapper.insertUser(user);
        // 新增用户与角色管理
        //insertUserRole(user);
        return rows;
    }

    /**
     * 修改保存用户信息
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateUser(User user) {
        Long userId = user.getUserId();
        //user.setUpdateBy(ShiroUtils.getLoginName());
        //userRoleMapper.deleteUserRoleByUserId(userId);
        //insertUserRole(user);
        if(null != user.getPassword())//说明初始化密码
        {
            user.randomSalt();
            user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        }
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户个人详细信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateUserInfo(User user) {
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int resetUserPwd(User user) {
        int temp = 0;
        User u = userMapper.selectUserById(user.getUserId());
        if(u != null)
        {
            //校验原始密码是否正确
            boolean matches = passwordService.matches(u, user.getOldPassword());
            if (matches)
            {
                user.randomSalt();
                user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
                temp = updateUserInfo(user);
            }
        }
        return temp;
    }

    /**
     * 新增用户角色信息
     *
     * @param user 用户对象
     */
    @Transactional
    public int insertUserRole(User user) {
        userRoleMapper.deleteUserRoleByUserId(user.getUserId());
        // 新增用户与角色管理
        List<UserRole> list = new ArrayList<UserRole>();
        for (Long roleId : user.getRoleIds()) {
            UserRole ur = new UserRole();
            ur.setUserId(user.getUserId());
            ur.setRoleId(roleId);
            list.add(ur);
        }
        int rows = 0;
        if (list.size() > 0) {
            rows = userRoleMapper.batchUserRole(list);
        }
        return rows;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param loginName 用户名
     * @return
     */
    @Override
    public boolean checkLoginNameUnique(String loginName) {
        int count = userMapper.checkLoginNameUnique(loginName);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     * 校验用户名称是否唯一
     * @return
     */
    @Override
    public boolean checkPhoneUnique(User user) {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        User info = userMapper.checkPhoneUnique(user.getPhonenumber());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
            return false;
        }
        return true;
    }

    /**
     * 校验email是否唯一
     * @return
     */
    @Override
    public boolean checkEmailUnique(User user) {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        User info = userMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
            return false;
        }
        return true;
    }

    /**
     * 查询用户所属角色组
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(Long userId) {
        List<Role> list = roleMapper.selectRolesByUserId(userId);
        StringBuffer idsStr = new StringBuffer();
        for (Role role : list) {
            idsStr.append(role.getRoleName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString())) {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }
}
