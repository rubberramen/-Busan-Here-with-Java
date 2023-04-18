package com.project.busanHere2.domain.member;

import com.project.busanHere2.constant.Sex;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class MemberForm {

    @NotBlank(message = "이름은 필수 값입니다.")
    private String name;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=4, max=16, message = "비밀번호는 4자 이상, 16자 이하로 입력해주세요")
    private String pw;

    @NotEmpty(message = "닉네임은 필수 입력 값입니다.")
    private String nickName;

    @NotEmpty(message = "성별은 필수값")
    private String sex;  //Sex

    public void setting(String name, String pw, String nickName, String sex) {
        this.name = name;
        this.pw = pw;
        this.nickName = nickName;
        this.sex = sex;
    }
}
