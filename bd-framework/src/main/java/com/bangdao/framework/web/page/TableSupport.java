package com.bangdao.framework.web.page;

import com.bangdao.common.utils.ServletUtils;
import com.bangdao.common.constant.Constants;
import com.bangdao.common.utils.StringUtils;
import com.bangdao.framework.web.domain.BaseEntity;
import com.github.pagehelper.PageHelper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 表格数据处理
 */
public class TableSupport {
    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain(Object obj) {
        PageDomain pageDomain = new PageDomain();
        BeanUtils.copyProperties(obj,pageDomain);
        return pageDomain;
    }

    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(Constants.PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest(Object obj) {
        return getPageDomain(obj);
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }


    /**
     * 设置请求分页数据
     */
    public static void startPage(Object obj) {
        PageDomain pageDomain = TableSupport.buildPageRequest(obj);
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = pageDomain.getOrderBy();
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }
}
