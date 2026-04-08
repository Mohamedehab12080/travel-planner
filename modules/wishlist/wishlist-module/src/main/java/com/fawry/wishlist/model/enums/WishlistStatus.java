package com.fawry.wishlist.model.enums;

import lombok.Getter;

@Getter
public enum WishlistStatus {
    WANT_TO_VISIT("Want to Visit"),
    PLANNING("Planning"),
    VISITED("Visited");

    private final String description;

    WishlistStatus(String description) {
        this.description = description;
    }

}