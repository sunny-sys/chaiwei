package com.bangdao.service.impl.system;

import com.bangdao.common.support.Convert;
import com.bangdao.common.utils.StringUtils;
import com.bangdao.common.utils.security.ShiroUtils;
import com.bangdao.domain.system.Menu;
import com.bangdao.domain.system.Role;
import com.bangdao.domain.system.RoleMenu;
import com.bangdao.mapper.system.RoleMapper;
import com.bangdao.mapper.system.RoleMenuMapper;
import com.bangdao.mapper.system.UserRoleMapper;
import com.bangdao.service.system.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 角色 业务层处理
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    @Override
    public List<Role> selectRoleList(Role role) {
        List<Role> roles = roleMapper.selectRoleList(role);
        if(null != roles)
        {
            for (Role r : roles)
            {
                List<Long> list = roleMenuMapper.selectRoleMenuByRoleId(r.getRoleId());
                if(null != list)
                {
                    Long[] longs = list.toArray(new Long[list.size()]);
                    r.setMenuIds(longs);
                }
            }
        }
        return roles;
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRoleKeys(Long userId) {
        List<Role> perms = roleMapper.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (Role perm : perms) {
            if (StringUtils.isNotNull(perms)) {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    @Override
    public List<Role> selectRolesByUserId(Long userId) {
        List<Role> userRoles = roleMapper.selectRolesByUserId(userId);
        List<Role> roles = roleMapper.selectRolesAll();
        for (Role role : roles) {
            for (Role userRole : userRoles) {
                if (role.getRoleId().longValue() == userRole.getRoleId().longValue()) {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    @Override
    public List<Role> selectRoleAll() {
        return roleMapper.selectRolesAll();
    }

    /**
     * 通过角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    @Override
    public Role selectRoleById(Long roleId) {
        Role role = roleMapper.selectRoleById(roleId);
        List<Long> list = roleMenuMapper.selectRoleMenuByRoleId(role.getRoleId());
        if(null != list)
        {
            Long[] longs = list.toArray(new Long[list.size()]);
            role.setMenuIds(longs);
        }
        return role;
    }

    /**
     * 通过角色ID删除角色
     *
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    @Transactional
    public boolean deleteRoleById(Long roleId) {
        return roleMapper.deleteRoleById(roleId) > 0 ? true : false;
    }

    /**
     * 批量删除角色信息
     *
     * @param ids 需要删除的数据ID
     * @throws Exception
     */
    @Override
    @Transactional
    public int deleteRoleByIds(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)){
            return 0;
        }
        Long[] roleIds = Convert.toLongArray(ids);
        for (Long roleId : roleIds) {
            Role role = selectRoleById(roleId);
            if (countUserRoleByRoleId(roleId) > 0) {
                throw new Exception(String.format("%1$s已分配,不能删除", role.getRoleName()));
            }
        }
        return roleMapper.deleteRoleByIds(roleIds);
    }

    /**
     * 新增保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertRole(Role role) {
        //role.setCreateBy(ShiroUtils.getLoginName());
        // 新增角色信息
        //ShiroUtils.clearCachedAuthorizationInfo();
        //return //insertRoleMenu(role);
        return roleMapper.insertRole(role);
    }

    /**
     * 修改保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateRole(Role role) {
        //role.setUpdateBy(ShiroUtils.getLoginName());
        // 修改角色信息

        // 删除角色与菜单关联
        //roleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
        //ShiroUtils.clearCachedAuthorizationInfo();
        //insertRoleMenu(role);
        return roleMapper.updateRole(role);
    }

    /**
     * 新增角色菜单信息
     *
     * @param role 角色对象
     */
    @Transactional
    public int insertRoleMenu(Role role) {
        // 新增角色信息
        roleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
        ShiroUtils.clearCachedAuthorizationInfo();
        int rows = 0;
        // 新增用户与角色管理
        List<RoleMenu> list = new ArrayList<RoleMenu>();
        for (Long menuId : role.getMenuIds()) {
            RoleMenu rm = new RoleMenu();
            rm.setRoleId(role.getRoleId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0) {
            rows = roleMenuMapper.batchRoleMenu(list);
        }
        return rows;
    }

    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public boolean checkRoleNameUnique(Role role) {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        Role info = roleMapper.checkRoleNameUnique(role.getRoleName());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue()) {
            return false;
        }
        return true;
    }

    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public boolean checkRoleKeyUnique(Role role) {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        Role info = roleMapper.checkRoleKeyUnique(role.getRoleKey());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue()) {
            return false;
        }
        return true;
    }

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public int countUserRoleByRoleId(Long roleId) {
        return userRoleMapper.countUserRoleByRoleId(roleId);
    }

}
