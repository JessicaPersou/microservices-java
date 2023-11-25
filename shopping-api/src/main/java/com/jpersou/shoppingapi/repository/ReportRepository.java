package com.jpersou.shoppingapi.repository;

import com.jpersou.shoppingapi.model.Shop;
import com.jpersou.shoppingclient.dto.ShopReportDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository{
    public List<Shop> getShopByFilters(LocalDate startDate, LocalDate endDate, Float minimumValue);
    public ShopReportDTO getReportByDate(LocalDate startDate, LocalDate endDate);
}
