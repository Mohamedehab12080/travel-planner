package com.fawry.common.db.model.dto;

import com.fawry.common.db.model.interfaces.OrderAttributes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class SearchFilter<S extends Enum<S> & OrderAttributes> {
    @Builder.Default
    private PaginationInfo pagination = PaginationInfo.builder().build();
    private SortingInfo<S> sorting;
    private SortingInfo<S> defaultSorting;
}
