package com.bangdao.common.utils;

import com.bangdao.common.utils.security.ShiroUtils;
import com.bangdao.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 基础工具类
 */
public class BaseUtil {
    /**
     * 设置创建人和创建时间
     * @param t
     * @param <T>
     */
    public static <T extends BaseEntity> void setCreateBy(T t)
    {
        //备注：登录占时没做 当前人先注销
        if(null == t)
        {
            return;
        }
        Long userId = ShiroUtils.getUserId();
        t.setCreateBy(""+userId);
        t.setCreateTime(new Date());
    }

    /**
     * 设置更新人，更新时间
     * @param t
     * @param <T>
     */
    public static <T extends BaseEntity> void setUpdateBy(T t)
    {
        //备注：登录占时没做 当前人先注销
        if(null == t)
        {
            return;
        }
        Long userId = ShiroUtils.getUserId();
        t.setUpdateBy(""+userId);
        t.setUpdateTime(new Date());
    }
}
