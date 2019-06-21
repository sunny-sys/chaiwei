package com.bangdao.common.utils.bean;

import com.bangdao.common.exception.base.BaseException;
import com.bangdao.framework.web.domain.BaseEntity;
import com.bangdao.framework.web.requestVo.RequestEntity;
import com.bangdao.framework.web.responseVo.ResponseEntity;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Bean 工具类
 */
public class BeanUtils {
    /**
     * Bean方法名中属性名开始的下标
     */
    private static final int BEAN_METHOD_PROP_INDEX = 3;

    /**
     * 匹配getter方法的正则表达式
     */
    private static final Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)");

    /**
     * 匹配setter方法的正则表达式
     */
    private static final Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)");

    /**
     * Bean属性复制工具方法。
     *
     * @param dest 目标对象
     * @param src  源对象
     */
    public static void copyProperties(Object src,Object dest) {
        List<Method> destSetters = getSetterMethods(dest);
        List<Method> srcGetters = getGetterMethods(src);
        try {
            for (Method setter : destSetters) {
                for (Method getter : srcGetters) {
                    if (isMethodPropEquals(setter.getName(), getter.getName())
                            && setter.getParameterTypes()[0].equals(getter.getReturnType())) {
                        setter.invoke(dest, getter.invoke(src));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 拷贝list集合中的每一个对象的每个属性
     */
    public static <T,R extends Object> void copyListBeanProp(List<T> tlist, List<R> rlist, Class<R> clazz){
        if (CollectionUtils.isEmpty(tlist)){
            return;
        }
        if (null == rlist){
            throw new BaseException("BeanUtils.copyListBeanProp","copy failed");
        }
        tlist.forEach(t -> {
            try {
                rlist.add(copy(t, clazz));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * entity类转换成Response类
     */
    public static <T extends BaseEntity,R extends ResponseEntity> R entityToResp(T t, Class<R> clazz) {
        return copy(t, clazz);
    }

    /**
     * request转换成entity
     */
    public static <T extends RequestEntity,R extends BaseEntity> R reqToEntity(T t, Class<R> clazz) {
        return copy(t, clazz);
    }

    private static <T,R> R copy(T t, Class<R> clazz) {
        try {
            R r = clazz.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(t, r);
            return r;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("BeanUtils.copy","Transformation abnormity");
        }
    }

    /**
     * 获取对象的setter方法。
     *
     * @param obj 对象
     * @return 对象的setter方法列表
     */
    public static List<Method> getSetterMethods(Object obj) {
        // setter方法列表
        List<Method> setterMethods = new ArrayList<Method>();

        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();

        // 查找setter方法

        for (Method method : methods) {
            Matcher m = SET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 1)) {
                setterMethods.add(method);
            }
        }
        // 返回setter方法列表
        return setterMethods;
    }

    /**
     * 获取对象的getter方法。
     *
     * @param t 对象
     * @return 对象的getter方法列表
     */

    public static List<Method> getGetterMethods(T t) {
        // getter方法列表
        List<Method> getterMethods = new ArrayList<Method>();
        // 获取所有方法
        Method[] methods = t.getClass().getMethods();
        // 查找getter方法
        for (Method method : methods) {
            Matcher m = GET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 0)) {
                getterMethods.add(method);
            }
        }
        // 返回getter方法列表
        return getterMethods;
    }

    /**
     * 获取对象的getter方法。
     *
     * @param obj 对象
     * @return 对象的getter方法列表
     */

    public static List<Method> getGetterMethods(Object obj) {
        // getter方法列表
        List<Method> getterMethods = new ArrayList<Method>();
        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();
        // 查找getter方法
        for (Method method : methods) {
            Matcher m = GET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 0)) {
                getterMethods.add(method);
            }
        }
        // 返回getter方法列表
        return getterMethods;
    }

    /**
     * 检查Bean方法名中的属性名是否相等。<br>
     * 如getName()和setName()属性名一样，getName()和setAge()属性名不一样。
     *
     * @param m1 方法名1
     * @param m2 方法名2
     * @return 属性名一样返回true，否则返回false
     */

    public static boolean isMethodPropEquals(String m1, String m2) {
        return m1.substring(BEAN_METHOD_PROP_INDEX).equals(m2.substring(BEAN_METHOD_PROP_INDEX));
    }
}
