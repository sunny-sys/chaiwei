package com.bangdao.framework.web.page;

import com.bangdao.common.constant.Constants;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 */
public class TableDataInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 列表数据
     */
    private List<?> rows;
    /**
     * 消息状态码
     */
    private int code;
    /**
     * 操作说明
     */
    private String msg;

    /**
     * 表格数据对象
     */
    public TableDataInfo() {}

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public TableDataInfo(List<?> list, long total) {
        this.rows = list;
        this.total = total;
    }

    public TableDataInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public TableDataInfo(int code, String msg,List<?> list, long total) {
        this(code,msg);
        this.rows = list;
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @param list 返回前段的数据
     * @param dataList 从数据库中查询出来的列表数据集合
     */
    public static TableDataInfo success(List<?> list, List<?> dataList){
        return new TableDataInfo(Constants.SUCCESS,"操作成功",list, new PageInfo(dataList).getTotal());
    }
    /**
     * 返回前段的数据和从数据库中查询出来的列表集合一致时，使用该方法
     */
    public static TableDataInfo success(List<?> list){
        return new TableDataInfo(Constants.SUCCESS,"操作成功",list, new PageInfo(list).getTotal());
    }
    /**
     * @param list 返回前段的数据
     * @param dataList 从数据库中查询出来的列表数据集合
     */
    public static TableDataInfo fail(List<?> list, List<?> dataList){
        return new TableDataInfo(Constants.FAIL,"操作失败",list, new PageInfo(dataList).getTotal());
    }
}
