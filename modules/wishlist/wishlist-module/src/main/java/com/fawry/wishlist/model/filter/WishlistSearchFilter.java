package com.fawry.wishlist.model.filter;

import com.fawry.common.db.model.dto.SearchFilter;
import com.fawry.common.db.model.interfaces.OrderAttributes;
import com.fawry.destination.model.enums.DestinationStatus;
import com.fawry.wishlist.model.enums.WishlistStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WishlistSearchFilter extends SearchFilter<WishlistSearchFilter.OrderByAttributes> {

    private Long userId;
    private List<Long> destinationsIds;
    private Integer priorityFrom;
    private Integer priorityTo;
    private Integer rateFrom;
    private Integer rateTo;
    private String destinationQuickSearch;
    private WishlistStatus status;
    private String destinationCurrencyQuickSearchQuery;
    private LocalDate visitedDateFrom;
    private LocalDate visitedDateTo;
    private LocalDate createdOnFrom;
    private LocalDate createdOnTo;

    @Getter
    @AllArgsConstructor
    public enum OrderByAttributes implements OrderAttributes {
        CREATION_DATE("item.createdOn", false),
        LAST_MODIFICATION_DATE("item.lastModifiedOn", false),
        COUNTRY_NAME("item.destination.countryName", false),
        VISITED_DATE("item.visitedDate", false),
        RATE("item.rating", false),
        PRIORITY("item.priority", false);

        private final String attributeName;
        private final Boolean isLocalized;
    }
}
