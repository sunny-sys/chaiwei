package com.bangdao.requestVo.essentialInfo;

import com.bangdao.framework.web.requestVo.RequestEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 科目表 sys_subject 请求实体
 *
 * @author chenshao
 * @date 2018-10-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectRequest extends RequestEntity {

    private static final long serialVersionUID = 1L;

    /**  */
    private Integer id;
    /** 科目代码 */
    private String subjectCode;
    /** 科目名称 */
    private String subjectName;
    /** 上级科目代码 */
    private String parentSubjectCode;
    /** 是否标准科目（1:是;0:否） */
    private Integer standardSubject;
    /** 数据状态（-1:无效;1:有效） */
    private Integer status;
    /** 是否同级新增（0:否;1:是） */
    private Integer isEquative;
}
