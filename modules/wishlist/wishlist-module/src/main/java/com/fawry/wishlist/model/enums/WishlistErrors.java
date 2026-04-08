package com.fawry.wishlist.model.enums;

import com.fawry.common.model.interfaces.Domains;
import com.fawry.common.model.interfaces.Errors;
import lombok.AllArgsConstructor;

import static com.fawry.wishlist.model.enums.WishlistDomains.WISHLIST;

@AllArgsConstructor
public enum WishlistErrors implements Errors {
    WISHLIST_ITEM_NOT_FOUND(WISHLIST,"0001","Wishlist {0} item not found"),
    DESTINATION_ALREADY_IN_WISHLIST(WISHLIST,"0002","Destination {0} already in wishlist"),
    INVALID_WISHLIST_STATUS(WISHLIST,"0003","Invalid wishlist status"),
    NOT_ALLOWED_TO_UPDATE_THIS_WISHLIST(WISHLIST,"0004","User {0} Not allowed to update this wishlist"),;

    private final Domains domain;
    private final String code;
    private final String messageEn;

    @Override
    public Domains domain() {
        return domain;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String messageEn() {
        return messageEn;
    }
}
