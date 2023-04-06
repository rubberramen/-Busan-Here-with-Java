package com.project.busanHere2.service;

import com.project.busanHere2.domain.shop.ShopForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShopServiceTest {

    @Autowired
    ShopService shopService;
    
    @Test
    public void readShops() throws Exception {
        // given
        List<ShopForm> shopForms = shopService.readShops("");
        for (ShopForm shopForm : shopForms) {
            System.out.println("shopForm.toString() = " + shopForm.toString());
        }
        System.out.println();
        List<ShopForm> fires = shopService.readShops("구이");
        for (ShopForm fire : fires) {
            System.out.println("fire.toString() = " + fire.toString());
        }
    }
    
}