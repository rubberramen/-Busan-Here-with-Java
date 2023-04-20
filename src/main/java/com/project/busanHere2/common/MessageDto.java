package com.project.busanHere2.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Getter
@AllArgsConstructor
public class MessageDto {

    private String message;
    private String redirectUri;
    private RequestMethod method;
    private Map<String, Object> data;
}
