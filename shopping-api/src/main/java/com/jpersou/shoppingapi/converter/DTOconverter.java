package com.jpersou.shoppingapi.converter;


import com.jpersou.shoppingapi.model.Item;
import com.jpersou.shoppingapi.model.Shop;
import com.jpersou.shoppingclient.dto.ItemDTO;
import com.jpersou.shoppingclient.dto.ShopDTO;

import java.util.stream.Collectors;

public class DTOconverter {

    public static ItemDTO convert(Item item){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductIdentifier(item.getProductIdentifier());
        itemDTO.setPrice(item.getPrice());

        return itemDTO;
    }
    public static ShopDTO convert(Shop shop){
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        shopDTO.setDate(shop.getDate());
        shopDTO.setItems(shop
                .getItems()
                .stream()
                .map(DTOconverter::convert)
                .collect(Collectors.toList()));
        return shopDTO;
    }



}
