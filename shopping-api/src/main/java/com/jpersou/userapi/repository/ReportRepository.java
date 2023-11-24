package com.jpersou.userapi.repository;

import com.jpersou.userapi.dto.ShopReportDTO;
import com.jpersou.userapi.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository{
    public List<Shop> getShopByFilters(LocalDate startDate, LocalDate endDate, Float minimumValue);
    public ShopReportDTO getReportByDate(LocalDate startDate, LocalDate endDate);
}
