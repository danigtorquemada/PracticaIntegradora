package com.dgomezt.practicaintegradora.utilities;

import com.dgomezt.practicaintegradora.validations.MatchValues;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@MatchValues(
        field = "password",
        fieldMatch = "confirmPwd",
        message = "{error.matchPwds}"
)
public class UserForm {
    @NotEmpty
    @Pattern(regexp = "^([a-zA-Z0-9_-]+@[a-z]+(\\.[a-z]{2,}))?$", message = "{error.emailPattern}")
    private String username;
    @NotEmpty
    @Pattern(regexp = "^.{2,8}$", message = "{error.pwdLength}")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!#$&])([A-Za-z0-9!#$%&]*)$", message = "{error.pwdPattern}")
    private String password;
    @NotEmpty
    @Pattern(regexp = "^.{2,8}$", message = "{error.pwdLength}")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!#$&])([A-Za-z0-9!#$%&]*)$", message = "{error.pwdPattern}")
    private String confirmPwd;
    private boolean errorMatchPwds;
    private boolean globalErrors;
}
