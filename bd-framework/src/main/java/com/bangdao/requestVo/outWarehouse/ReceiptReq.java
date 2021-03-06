package com.bangdao.requestVo.outWarehouse;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceiptReq extends RequestEntity {
    private static final long serialVersionUID = 1L;

    private ReceiptBaseReq baseReq;

    private List<ReceiptDetailReq> detailReq;

    /**
     * 客户id（关联往来单位表id）
     */
    private Integer supplierId;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date startDate;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date endDate;
}
