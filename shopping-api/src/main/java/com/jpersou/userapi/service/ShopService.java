package com.jpersou.userapi.service;

import com.jpersou.userapi.dto.ItemDTO;
import com.jpersou.userapi.dto.ShopDTO;
import com.jpersou.userapi.dto.ShopReportDTO;
import com.jpersou.userapi.model.Shop;
import com.jpersou.userapi.repository.ReportRepository;
import com.jpersou.userapi.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    private final ReportRepository reportRepository;
    public List<ShopDTO> getAll(){
        List<Shop> shops = shopRepository.findAll();
        return shops
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier){
        List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);
        return shops
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO){
        List<Shop> shops = shopRepository.findAllByDateGreaterThan(shopDTO.getDate());
        return shops
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public ShopDTO findById(long shopId){
        Optional<Shop> shops = shopRepository.findById(shopId);
        if
        (shops.isPresent()){
            return ShopDTO.convert(shops.get());
        }
        return null;
    }

    public ShopDTO save(ShopDTO shopDTO){
        shopDTO.setTotal(shopDTO.getItems()
                .stream()
                .map(x -> x.getPrice())
                .reduce((float) 0, Float::sum));

        Shop shop = Shop.convert(shopDTO);
        shop.setDate(LocalDateTime.now());

        shop = shopRepository.save(shop);
        return ShopDTO.convert(shop);
    }

    public List<ShopDTO> getShopByFilters(LocalDate startDate, LocalDate endDate, Float minimumValue){
        List<Shop> shops = reportRepository.getShopByFilters(startDate, endDate, minimumValue);
        return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(LocalDate startDate, LocalDate endDate){
        return reportRepository.getReportByDate(startDate,endDate);
    }

}
