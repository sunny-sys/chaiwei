package com.bangdao.framework.web.domain;

import com.bangdao.common.constant.Constants;

import java.util.HashMap;

/**
 * 操作消息提醒
 */
public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 初始化一个新创建的 Message 对象
     */
    public Result() {
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static Result error() {
        return error(Constants.FAIL, "操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 内容
     * @return 错误消息
     */
    public static Result error(String msg) {
        return error(500, msg);
    }

    /**
     * 返回错误消息
     *
     * @param code 错误码
     * @param msg  内容
     * @return 错误消息
     */
    public static Result error(int code, String msg) {
        Result json = new Result();
        json.put("code", code);
        json.put("msg", msg);
        return json;
    }

    /**
     * 返回成功消息
     *
     * @param msg 内容
     * @return 成功消息
     */
    public static Result success(String msg) {
        Result json = new Result();
        json.put("msg", msg);
        json.put("code", Constants.SUCCESS);
        return json;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Result success() {
        return Result.success("操作成功");
    }

    /**
     * 返回成功消息
     *
     * @param key   键值
     * @param value 内容
     * @return 成功消息
     */
    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
