package com.bangdao.requestVo.essentialInfo;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.bangdao.group.essentialInfo.provinces.ProvincesAdd;
import com.bangdao.group.essentialInfo.provinces.ProvincesUpdate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 省份表 sys_provinces 请求实体
 *
 * @author xupj
 * @date 2018-09-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvincesRequest extends RequestEntity {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "省份id不能为空",groups = {ProvincesUpdate.class})
    private Integer id;
    @NotEmpty(message = "省份code不能为空",groups = {ProvincesUpdate.class, ProvincesAdd.class})
    private String provinceid;
    @NotEmpty(message = "省份名称不能为空",groups = {ProvincesUpdate.class, ProvincesAdd.class})
    private String province;
    private Integer sort;
}
