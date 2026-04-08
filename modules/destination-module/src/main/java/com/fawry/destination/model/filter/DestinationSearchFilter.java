package com.fawry.destination.model.filter;

import com.fawry.common.db.model.dto.SearchFilter;
import com.fawry.common.db.model.interfaces.OrderAttributes;
import com.fawry.destination.model.enums.DestinationStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DestinationSearchFilter extends SearchFilter<DestinationSearchFilter.OrderByAttributes> {
    private String quickSearchQuery;
    private DestinationStatus status;
    private String currencyQuickSearchQuery;
    private LocalDate createdOnFrom;
    private LocalDate createdOnTo;
    private List<Long> createdByIds;

    @Getter
    @AllArgsConstructor
    public enum OrderByAttributes implements OrderAttributes {
        CREATION_DATE("item.createdOn", false),
        LAST_MODIFICATION_DATE("item.lastModifiedOn", false),
        COUNTRY_NAME("item.countryName", false);

        private final String attributeName;
        private final Boolean isLocalized;
    }
}
