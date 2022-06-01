package com.sparta.security0531.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Setter
@Getter
public class UserRequestDto {

    @NotEmpty
    @Pattern(regexp = "(?=.*[0-9])|(?=.*[a-zA-Z]).{3,20}", message = "닉네임 영문자숫자섞고 3자이상으로 지켜서 적")
    private String username;

    @NotEmpty
    @Size(min = 4, message = "4자이상 적어")
    private String password;

    @NotEmpty
    private String password2;
}