package com.project.busanHere2.domain.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MemberDTO {
    private Long memberId;
    private int type;
    private String name;
    private String nickName;
    private String sex;
    private String passwd;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
