package com.fawry.wishlist.repository.custom;

import com.fawry.common.db.api.service.AbstractQueryBuilderV2;
import com.fawry.common.db.model.dto.QBCondition;
import com.fawry.wishlist.model.entity.Wishlist;
import com.fawry.wishlist.model.filter.WishlistSearchFilter;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishlistQueryBuilder extends AbstractQueryBuilderV2<Wishlist, WishlistSearchFilter> {

    public WishlistQueryBuilder(EntityManager em) {
        super(em);
    }

    @Override
    public List<QBCondition> evaluateWhereConditions(WishlistSearchFilter filters) {
        List<QBCondition> conditions = new ArrayList<>();

        if (filters.getUserId() != null) {
            conditions.add(QBCondition.builder()
                    .placeHolder("userId")
                    .value(filters.getUserId())
                    .condition("item.user.id = :PH")
                    .build());
        }

        if (filters.getDestinationsIds() != null && !filters.getDestinationsIds().isEmpty()) {
            conditions.add(QBCondition.builder()
                    .placeHolder("destinationIds")
                    .value(filters.getDestinationsIds())
                    .condition("item.destination.id IN (:PH)")
                    .build());
        }

        if (filters.getStatus() != null) {
            conditions.add(QBCondition.builder()
                    .placeHolder("status")
                    .value(filters.getStatus())
                    .condition("item.status = :PH")
                    .build());
        }

        if (filters.getPriorityFrom() != null) {
            conditions.add(QBCondition.builder()
                    .placeHolder("priorityFrom")
                    .value(filters.getPriorityFrom())
                    .condition("item.priority >= :PH")
                    .build());
        }

        if (filters.getPriorityTo() != null) {
            conditions.add(QBCondition.builder()
                    .placeHolder("priorityTo")
                    .value(filters.getPriorityTo())
                    .condition("item.priority <= :PH")
                    .build());
        }

        if (filters.getRateFrom() != null) {
            conditions.add(QBCondition.builder()
                    .placeHolder("rateFrom")
                    .value(filters.getRateFrom())
                    .condition("item.rating >= :PH")
                    .build());
        }

        if (filters.getRateTo() != null) {
            conditions.add(QBCondition.builder()
                    .placeHolder("rateTo")
                    .value(filters.getRateTo())
                    .condition("item.rating <= :PH")
                    .build());
        }

        if (StringUtils.hasText(filters.getDestinationQuickSearch())) {
            conditions.add(QBCondition.builder()
                    .placeHolder("destinationQuickSearch")
                    .value("%" + filters.getDestinationQuickSearch().toLowerCase() + "%")
                    .condition("(LOWER(item.destination.countryName) LIKE LOWER(:PH) OR " +
                            "LOWER(item.destination.countryCode) LIKE LOWER(:PH) OR " +
                            "LOWER(item.destination.capital) LIKE LOWER(:PH) OR " +
                            "LOWER(item.destination.region) LIKE LOWER(:PH) OR " +
                            "LOWER(item.destination.subRegion) LIKE LOWER(:PH))")
                    .build());
        }

        if (StringUtils.hasText(filters.getDestinationCurrencyQuickSearchQuery())) {
            conditions.add(QBCondition.builder()
                    .placeHolder("currencyQuickSearch")
                    .value("%" + filters.getDestinationCurrencyQuickSearchQuery().toLowerCase() + "%")
                    .condition("(LOWER(item.destination.currencyName) LIKE LOWER(:PH) OR " +
                            "LOWER(item.destination.currencyCode) LIKE LOWER(:PH))")
                    .build());
        }

        if (filters.getVisitedDateFrom() != null) {
            conditions.add(QBCondition.builder()
                    .placeHolder("visitedDateFrom")
                    .value(filters.getVisitedDateFrom().atStartOfDay())
                    .condition("item.visitedDate >= :PH")
                    .build());
        }

        if (filters.getVisitedDateTo() != null) {
            conditions.add(QBCondition.builder()
                    .placeHolder("visitedDateTo")
                    .value(filters.getVisitedDateTo().atTime(LocalTime.MAX))
                    .condition("item.visitedDate <= :PH")
                    .build());
        }

        if (filters.getCreatedOnFrom() != null) {
            conditions.add(QBCondition.builder()
                    .placeHolder("createdOnFrom")
                    .value(filters.getCreatedOnFrom().atStartOfDay())
                    .condition("item.createdOn >= :PH")
                    .build());
        }

        if (filters.getCreatedOnTo() != null) {
            conditions.add(QBCondition.builder()
                    .placeHolder("createdOnTo")
                    .value(filters.getCreatedOnTo().atTime(LocalTime.MAX))
                    .condition("item.createdOn <= :PH")
                    .build());
        }

        return conditions;
    }
}