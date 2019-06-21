package com.bangdao.requestVo.essentialInfo;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.bangdao.group.essentialInfo.cities.CitiesAdd;
import com.bangdao.group.essentialInfo.cities.CitiesUpdate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 行政区域地州市表 sys_cities 请求实体
 *
 * @author xupj
 * @date 2018-09-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CitiesRequest extends RequestEntity {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "行政区域地州市id不能为空",groups = {CitiesUpdate.class})
    private Integer id;
    @NotEmpty(message = "行政区域地州市code不能为空",groups = {CitiesUpdate.class, CitiesAdd.class})
    private String cityid;
    @NotEmpty(message = "行政区域地州市名称不能为空",groups = {CitiesUpdate.class, CitiesAdd.class})
    private String city;
    @NotEmpty(message = "省份code不能为空",groups = {CitiesUpdate.class, CitiesAdd.class})
    private String parentid;
    private Integer sort;
}
