package com.bangdao.requestVo.outWarehouse;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * 收款单详情表  请求实体
 *
 * @author chaiwei
 * @date 2018-11-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceiptDetailReq extends RequestEntity {

    private static final long serialVersionUID = 1L;

    /**  */
    private Integer id;
    /** 收款单基本表id */
    private Integer parentId;
    /** 来源id */
    private Integer sourceId;
    /** 来源（标记来源 1.出库；2.退货） */
    private String source;
    /**
     * 删除标志
     */
    private Integer IsDelete;
}
