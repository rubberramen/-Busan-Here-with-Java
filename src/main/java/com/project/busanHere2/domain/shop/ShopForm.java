package com.project.busanHere2.domain.shop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShopForm {

    private Long shopId;
    private String name;
    private String address;
    private String videoUrl;
    private String category;
}
