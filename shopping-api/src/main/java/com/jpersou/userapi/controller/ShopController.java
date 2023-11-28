package com.jpersou.userapi.controller;

import com.jpersou.userapi.service.ShopService;
import com.jpersou.shoppingclient.dto.ShopDTO;
import com.jpersou.shoppingclient.dto.ShopReportDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/shopping")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping
    public List<ShopDTO> getShops(){
        return shopService.getAll();
    }

    @GetMapping("/shopByUser/{userIdentifier}")
    public List<ShopDTO> getById(@PathVariable String userIdentifier){
        return shopService.getByUser(userIdentifier);
    }

    @GetMapping("/shopByUser")
    public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO){
        return shopService.getByDate(shopDTO);
    }

    @GetMapping("/{id}")
    public ShopDTO findById(@PathVariable Long id){
        return shopService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShopDTO newShop( @RequestBody @Valid ShopDTO shopDTO,
                            @RequestHeader(name = "key", required = true) String key){
        return shopService.save(shopDTO, key);
    }

    @GetMapping("/search")
    public List<ShopDTO> getShopsByFilters(@RequestParam(name = "startDate", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy")LocalDate startDate,
                                           @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endDate,
                                           @RequestParam(name = "minimumValue", required = false) Float minimumValue){
        return shopService.getShopByFilters(startDate, endDate, minimumValue);
    }

    @GetMapping("/search/report")
    public ShopReportDTO getReportByDate(@RequestParam(name = "startDate", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy")LocalDate startDate,
                                         @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endDate){
        return shopService.getReportByDate(startDate, endDate);
    }

}
