package com.bangdao.responseVo.outWarehouse;

import com.bangdao.framework.web.responseVo.ResponseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 收款单详情表 响应实体
 *
 * @author chaiwei
 * @date 2018-11-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ReceiptDetailResp extends ResponseEntity {

    private static final long serialVersionUID = 1L;

    /**  */
    private Integer id;
    /** 收款单基本表id */
    private Integer parentId;
    /** 来源id */
    private Integer sourceId;
    /** 来源（标记来源 1.出库；2.退货） */
    private String source;
}
