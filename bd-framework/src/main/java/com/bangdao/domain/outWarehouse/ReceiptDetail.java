package com.bangdao.domain.outWarehouse;

import com.bangdao.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 收款单详情表
 *
 * @author chaiwei
 * @date 2018-11-13
 */
public class ReceiptDetail extends BaseEntity{
    private static final long serialVersionUID=1L;

    /**  */
    private Integer id;
    /** 收款单基本表id */
    private Integer parentId;
    /** 来源id */
    private Integer sourceId;
    /** 来源（标记来源 1：出库，2：退货） */
    private String source;
    /** 状态（-1：无效，1：有效） */
    private Integer status;

    public void setId(Integer id){
        this.id = id;
    }
    public Integer getId(){
        return id;
    }
    public void setParentId(Integer parentId){
        this.parentId = parentId;
    }
    public Integer getParentId(){
        return parentId;
    }
    public void setSourceId(Integer sourceId){
        this.sourceId = sourceId;
    }
    public Integer getSourceId(){
        return sourceId;
    }
    public void setSource(String source){
        this.source = source;
    }
    public String getSource(){
        return source;
    }
    public void setStatus(Integer status){
        this.status = status;
    }
    public Integer getStatus(){
        return status;
    }

    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id",getId())
                .append("parentId",getParentId())
                .append("sourceId",getSourceId())
                .append("source",getSource())
                .append("remark",getRemark())
                .append("createBy",getCreateBy())
                .append("createTime",getCreateTime())
                .append("updateBy",getUpdateBy())
                .append("updateTime",getUpdateTime())
                .append("status",getStatus())
            .toString();
    }
}
