package com.fawry.destination.repository.custom;

import com.fawry.common.db.api.service.AbstractQueryBuilderV2;
import com.fawry.common.db.model.dto.QBCondition;
import com.fawry.destination.model.entity.Destination;
import com.fawry.destination.model.filter.DestinationSearchFilter;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DestinationQueryBuilder extends AbstractQueryBuilderV2<Destination, DestinationSearchFilter> {

    public DestinationQueryBuilder(EntityManager em) {
        super(em);
    }

    @Override
    public List<QBCondition> evaluateWhereConditions(DestinationSearchFilter filters) {
        List<QBCondition> conditions = new ArrayList<>();

        if (filters.getCreatedByIds() != null && !filters.getCreatedByIds().isEmpty())
            conditions.add(QBCondition.builder().placeHolder("createdByIds").value(filters.getCreatedByIds())
                    .condition("item.createdBy.id IN :PH").build());

        if (filters.getStatus() != null)
            conditions.add(QBCondition.builder().placeHolder("status").value(filters.getStatus())
                    .condition("item.status = :PH").build());

        if (filters.getQuickSearchQuery() != null)
            conditions.add(QBCondition.builder().placeHolder("quickSearchQuery").value("%" + filters.getQuickSearchQuery() + "%")
                    .condition("(LOWER(item.countryName) LIKE LOWER(:PH) OR LOWER(item.countryCode) LIKE LOWER(:PH) OR LOWER(item.capital) LIKE LOWER(:PH) OR LOWER(item.region) LIKE LOWER(:PH) OR LOWER(item.subRegion) LIKE LOWER(:PH))").build());

        if (filters.getCurrencyQuickSearchQuery() != null)
            conditions.add(QBCondition.builder().placeHolder("currencyQuickSearch").value("%" + filters.getCurrencyQuickSearchQuery() + "%")
                    .condition("(LOWER(item.currencyName) LIKE LOWER(:PH) OR LOWER(item.currencyCode) LIKE LOWER(:PH))").build());

        if (filters.getCreatedOnFrom() != null)
            conditions.add(QBCondition.builder().placeHolder("createdOnFrom").value(filters.getCreatedOnFrom().atStartOfDay())
                    .condition("item.createdOn >= :PH").build());

        if (filters.getCreatedOnTo() != null)
            conditions.add(QBCondition.builder().placeHolder("createdOnTo").value(filters.getCreatedOnTo().atTime(LocalTime.MAX))
                    .condition("item.createdOn <= :PH").build());

        return conditions;
    }
}
