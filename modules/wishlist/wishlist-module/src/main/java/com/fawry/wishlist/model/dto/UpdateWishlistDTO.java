package com.fawry.wishlist.model.dto;

import com.fawry.wishlist.model.enums.WishlistStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWishlistDTO {

    private WishlistStatus status;

    private String notes;

    @Min(1)
    @Max(5)
    private Integer priority;

    private LocalDate visitedDate;

    @Min(1)
    @Max(5)
    private Integer rating;

    private String review;
}