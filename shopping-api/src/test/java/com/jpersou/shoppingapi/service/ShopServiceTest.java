package com.jpersou.shoppingapi.service;

import com.jpersou.shoppingapi.model.Shop;
import com.jpersou.shoppingapi.repository.ShopRepository;
import com.jpersou.shoppingclient.dto.ItemDTO;
import com.jpersou.shoppingclient.dto.ProductDTO;
import com.jpersou.shoppingclient.dto.ShopDTO;
import com.jpersou.shoppingclient.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ShopServiceTest {

    @InjectMocks
    private ShopService shopService;
    @Mock
    private UserService userService;
    @Mock
    private ProductService productService;
    @Mock
    private ShopRepository shopRepository;


    @Test
    public void testSaveShop(){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductIdentifier("123");
        itemDTO.setPrice(100F);

        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier("123");
        shopDTO.setItems(new ArrayList<>());
        shopDTO.getItems().add(itemDTO);
        shopDTO.setTotal(100F);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductIdentifier("123");
        productDTO.setPrice(100F);

        Mockito.when(userService.getUserByDocument("123", "123"))
                .thenReturn(new UserDTO());
        Mockito.when(productService.getProductByIdentifier("123"))
                .thenReturn(productDTO);
        Mockito.when(shopRepository.save(Mockito.any()))
                .thenReturn(Shop.convert(shopDTO));

        shopDTO = shopService.save(shopDTO, "123");
        Assertions.assertEquals(100F, shopDTO.getTotal());
        Assertions.assertEquals(1, shopDTO.getItems().size());
        Mockito.verify(shopRepository, Mockito.times(1)).save(Mockito.any());
    }

}