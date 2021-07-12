package net.jinyiyun.framework.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author dongshixiao
 */
@Data
public class ChangePasswordDTO {
    @NotBlank(message = "旧密码必填")
    String oldPwd;
    @NotBlank(message = "新密码必填")
    String newPwd;
    @NotBlank(message = "确认密码必填")
    String confirmPwd;
}
