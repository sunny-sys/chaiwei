package com.bangdao.requestVo.system;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = -7952921565172760546L;

    @ApiModelProperty(value = "登录名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "是否记住我")
    private Boolean rememberMe;
    @ApiModelProperty(value = "验证码")
    private String validateCode;
}
