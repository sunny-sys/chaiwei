package com.bangdao.responseVo.repertoryManage;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CheckListResp {
    private static final long serialVersionUID = 1L;

    private CheckListBaseResp base;

    private List<CheckListDetailResp> detail;
}
