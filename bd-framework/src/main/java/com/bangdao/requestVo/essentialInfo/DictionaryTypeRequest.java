package com.bangdao.requestVo.essentialInfo;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.bangdao.group.essentialInfo.dictionaryType.DictionaryTypeAdd;
import com.bangdao.group.essentialInfo.dictionaryType.DictionaryTypeUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 字典类型表 sys_dictionary_type 请求实体
 *
 * @author xupj
 * @date 2018-09-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DictionaryTypeRequest extends RequestEntity {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "字典类型id不能为空",groups = {DictionaryTypeUpdate.class})
    /** 字典类型id */
    private Integer id;
    @NotEmpty(message = "字典类型名称不能为空",groups = {DictionaryTypeUpdate.class, DictionaryTypeAdd.class})
    /** 字典类型名称 */
    private String dicName;
    @NotEmpty(message = "字典类型不能为空",groups = {DictionaryTypeUpdate.class, DictionaryTypeAdd.class})
    /** 字典类型 */
    private String dicType;
    /** 字典名称 */
    private String dicNameSpell;
}
