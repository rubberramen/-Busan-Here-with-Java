package com.project.busanHere2.domain.shop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ShopDto {

    private Long shopId;
    private String name;
    private String address;
    private String videoUrl;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private char status;

}
