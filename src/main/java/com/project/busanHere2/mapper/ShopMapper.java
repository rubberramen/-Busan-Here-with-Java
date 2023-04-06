package com.project.busanHere2.mapper;

import com.project.busanHere2.domain.shop.ShopForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopMapper {

    List<ShopForm> findAllShops();
    List<ShopForm> findShopsByCategory(String category);

    ShopForm oneShop();
}
