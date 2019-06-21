package com.bangdao.requestVo.essentialInfo;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.bangdao.group.essentialInfo.areas.AreasAdd;
import com.bangdao.group.essentialInfo.areas.AreasUpdate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 行政区域县区表 sys_areas 请求实体
 *
 * @author xupj
 * @date 2018-09-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AreasRequest extends RequestEntity {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "行政区域县区id不能为空",groups = {AreasUpdate.class})
    private Integer id;
    @NotEmpty(message = "行政区域县区code不能为空",groups = {AreasUpdate.class, AreasAdd.class})
    private String areaid;
    @NotEmpty(message = "行政区域县区名称不能为空",groups = {AreasUpdate.class, AreasAdd.class})
    private String area;
    @NotEmpty(message = "行政区域地州市code不能为空",groups = {AreasUpdate.class, AreasAdd.class})
    private String parentid;
    private Integer sort;
}
