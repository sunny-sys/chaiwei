package com.bangdao.framework.web.page;

import com.bangdao.common.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.ObjectUtils;

/**
 * 分页数据
 */
@ApiModel(description = "分页实体")
public class PageDomain {
    /**
     * 当前记录起始索引
     */
    @ApiModelProperty(value="当前记录起始索引",name="pageNum")
    private Integer pageNum;
    /**
     * 每页显示记录数
     */
    @ApiModelProperty(value="每页显示记录数",name="pageSize")
    private Integer pageSize;
    /**
     * 排序列
     */
    @ApiModelProperty(value="排序列",name="orderByColumn")
    private String orderByColumn;
    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    @ApiModelProperty(value="排序的方向",name="isAsc")
    private String isAsc;

    public String getOrderBy() {
        if (StringUtils.isEmpty(orderByColumn)) {
            return "";
        }
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        this.isAsc = isAsc;
    }

}
