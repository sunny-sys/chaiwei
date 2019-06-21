package com.bangdao.requestVo.essentialInfo;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 仓库表 ess_warehouse_info 请求实体
 *
 * @author chenshao
 * @date 2018-10-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WarehouseInfoRequest extends RequestEntity {

    private static final long serialVersionUID = 1L;

    /**  */
    private Integer id;
    /** 仓库名称 */
    private String name;
    /** 仓库编码 */
    private String code;
    /** 仓库类别id */
    private Integer categoryId;
//    /** 备注 */
//    private Integer remark;
    /** 数据状态（-1:无效;1:有效） */
    private Integer status;
//    /** 创建时间 */
//    private Date createTime;
//    /** 修改时间 */
//    private Date updateTime;
}
