package com.project.busanHere2.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestDto {

    private Long a;
    private String b;

    public TestDto() {
        this.a = 10L;
        this.b = "Hello World";
    }
}
