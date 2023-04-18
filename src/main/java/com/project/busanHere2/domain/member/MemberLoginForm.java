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

    private String name;

    private String pw;

    private String nickName;

    private String sex;  //Sex

    public void setting(String name, String pw, String nickName, String sex) {
        this.name = name;
        this.pw = pw;
        this.nickName = nickName;
        this.sex = sex;
    }
}
