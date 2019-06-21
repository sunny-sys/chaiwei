package com.bangdao.requestVo.essentialInfo;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.bangdao.group.essentialInfo.dictionaryItem.DictionaryItemAdd;
import com.bangdao.group.essentialInfo.dictionaryItem.DictionaryItemUpdate;
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
 * 字典明细表 sys_dictionary_item 请求实体
 *
 * @author xupj
 * @date 2018-09-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DictionaryItemRequest extends RequestEntity {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "字典明細id不能为空",groups = {DictionaryItemUpdate.class})
    /** 主键id */
    private Integer id;
    @NotEmpty(message = "字典类型名称不能为空",groups = {DictionaryItemUpdate.class, DictionaryItemAdd.class})
    /** 字典名称 */
    private String itemName;
    @NotNull(message = "参数字典值不能为空",groups = {DictionaryItemUpdate.class, DictionaryItemAdd.class})
    /** 字典值 */
    private Integer itemValue;
    /** 排序值 */
    private Integer sort;
    @NotEmpty(message = "字典类型不能为空",groups = {DictionaryItemUpdate.class, DictionaryItemAdd.class})
    /** 字典类型 */
    private String dicType;
}
