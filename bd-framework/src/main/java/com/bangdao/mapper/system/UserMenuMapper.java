package com.bangdao.mapper.system;

import java.util.List;

/**
 * 用户和菜单关系 数据层
 */
public interface UserMenuMapper {
    public List<Long> selectMenuByUserId(Long userId);
}
