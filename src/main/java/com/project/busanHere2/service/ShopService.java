package com.project.busanHere2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.busanHere2.domain.shop.ShopForm;
import com.project.busanHere2.mapper.ShopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopMapper shopMapper;

    public List<ShopForm> findAllShops() {
        List<ShopForm> allShops = shopMapper.findAllShops();
        return allShops;
    }

    public List<ShopForm> findShopsByCategory(String category) {
        List<ShopForm> shopsByCategory = shopMapper.findShopsByCategory(category);
        return shopsByCategory;
    }

    public List<ShopForm> readShops(String category) {
        if (category.equals("")) {
            List<ShopForm> allShops = shopMapper.findAllShops();
            return allShops;
        } else {
            List<ShopForm> shopsByCategory = shopMapper.findShopsByCategory(category);
            return shopsByCategory;
        }
    }

    public ShopForm oneShop() {
        return shopMapper.oneShop();
    }

    public String toJson(ShopForm shop) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        String shopString = mapper.writeValueAsString(shop);
        return shopString;
    }
}
