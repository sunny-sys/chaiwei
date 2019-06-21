package com.bangdao.requestVo.essentialInfo;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 类别表 sys_category 请求实体
 *
 * @author chaiwei
 * @date 2018-09-30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryRequest extends RequestEntity {

    private static final long serialVersionUID = 1L;

    /**  类别id */
    private Integer id;
    /** 父标签id */
    private String parentId;
    /**  类别名称 */
    private String commodityCategoryName;
    /**  编码 */
    private String commodityCategoryCode;
    /** 拼音码 */
    private String pinyinCode;
    /** 排序 */
    private String sort;
    /** 状态（-1：无效，1：有效） */
    private Integer status;
    /** 类别分类（ 类别，单位类别等） */
    private String category;
}
