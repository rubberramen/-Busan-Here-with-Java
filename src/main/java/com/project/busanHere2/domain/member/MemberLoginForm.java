package com.project.busanHere2.domain.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class MemberLoginForm {

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    private String pw;

    @NotEmpty(message = "닉네임은 필수 입력 값입니다.")
    private String nickName;

}
