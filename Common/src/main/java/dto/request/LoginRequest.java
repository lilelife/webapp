package dto.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@ApiModel
@Data
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "请输入手机号")
    @Pattern(regexp = "((16[0-9])|(13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))[0-9]{8}$", message = "必须是一个手机号")
    private String phone;
    @NotBlank(message = "请输入密码")
    private String pwd;
}
