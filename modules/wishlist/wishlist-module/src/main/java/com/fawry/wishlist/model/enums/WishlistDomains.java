package com.fawry.wishlist.model.enums;

import com.fawry.common.model.interfaces.Domains;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum WishlistDomains implements Domains {

    WISHLIST(2301),;
    private final Integer id;

    @Override
    public Integer id() {
        return id;
    }
}
