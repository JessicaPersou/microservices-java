package com.jpersou.shoppingapi.service;

import com.jpersou.shoppingapi.converter.DTOconverter;
import com.jpersou.shoppingapi.model.Shop;
import com.jpersou.shoppingapi.repository.ShopRepository;
import com.jpersou.shoppingclient.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    public List<ShopDTO> getAll(){
        List<Shop> shops = shopRepository.findAll();
        return shops
                .stream()
                .map(DTOconverter::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier){
        List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);
        return shops
                .stream()
                .map(DTOconverter::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO){
        List<Shop> shops = shopRepository.findAllByDateGreaterThan(shopDTO.getDate());
        return shops
                .stream()
                .map(DTOconverter::convert)
                .collect(Collectors.toList());
    }

    public ShopDTO findById(long shopId){
        Optional<Shop> shops = shopRepository.findById(shopId);
        if
        (shops.isPresent()){
            return DTOconverter.convert(shops.get());
        }
        return null;
    }

    public ShopDTO save(ShopDTO shopDTO, String key){
        UserDTO userDTO = userService.getUserByDocument(shopDTO.getUserIdentifier(), key);
        validateProducts(shopDTO.getItems());

        shopDTO.setTotal(shopDTO.getItems()
                .stream()
                .map(x -> x.getPrice())
                .reduce((float) 0, Float::sum));

        Shop shop = Shop.convert(shopDTO);
        shop.setDate(LocalDateTime.now());

        shop = shopRepository.save(shop);
        return DTOconverter.convert(shop);
    }

    private boolean validateProducts(List<ItemDTO> items){
        for(ItemDTO item: items){
            ProductDTO productDTO = productService
                    .getProductByIdentifier(item.getProductIdentifier());
            if(productDTO == null){
                return false;
            }
            item.setPrice(productDTO.getPrice());
        }
        return true;
    }

    public List<ShopDTO> getShopByFilters(LocalDate startDate, LocalDate endDate, Float minimumValue){
        List<Shop> shops = shopRepository.getShopByFilters(startDate, endDate, minimumValue);
        return shops.stream().map(DTOconverter::convert).collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(LocalDate startDate, LocalDate endDate){
        return shopRepository.getReportByDate(startDate,endDate);
    }

}
