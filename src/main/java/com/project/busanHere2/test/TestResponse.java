package com.project.busanHere2.test;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TestResponse {

    private Long shopId;
    private String name;
    private String address;
    private String videoUrl;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private char status;

    @Override
    public String

    toString() {
        return "ShopResponse{" +
                "shopId=" + shopId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", category='" + category + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", status=" + status +
                '}';
    }
}
