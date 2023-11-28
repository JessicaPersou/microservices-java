package com.jpersou.userapi.repository.impl;

import com.jpersou.userapi.model.Shop;
import com.jpersou.userapi.repository.ReportRepository;
import com.jpersou.shoppingclient.dto.ShopReportDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepository {

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public List<Shop> getShopByFilters(LocalDate startDate, LocalDate endDate, Float minimumValue){
        StringBuilder sb = new StringBuilder();
        sb.append("select s");
        sb.append("from shop s");
        sb.append("where s.date >= :startDate");

        if(endDate != null){
            sb.append("and s.date <= :endDate");
        }

        if(minimumValue != null){
            sb.append("and s.total <= :minimumValue");
        }

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("statDate", startDate.atTime(0,0));

        if(endDate != null){
            query.setParameter("endDate", endDate.atTime(23,59));
        }

        if(minimumValue != null){
            query.setParameter("minimumValue", minimumValue);
        }
        return query.getResultList();
    }
    @Override
    public ShopReportDTO getReportByDate(LocalDate startDate, LocalDate endDate){
        StringBuilder sb = new StringBuilder();
        sb.append("select count(sp.id), sum(sp.total), avg(sp.total)");
        sb.append("from shopping.shop sp");
        sb.append("where sp.date >= :startDate");
        sb.append("where sp.date <= :endDate");

        if(endDate != null){
            sb.append("and s.date <= :endDate");
        }

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("statDate", startDate.atTime(0,0));
        query.setParameter("endDate", endDate.atTime(23,59));

        Object[] result = (Object[]) query.getSingleResult();
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setCount(((BigInteger) result[0]).intValue());
        shopReportDTO.setTotal((Double) result[1]);
        shopReportDTO.setTotal((Double) result[2]);

        return shopReportDTO;
    }
}

