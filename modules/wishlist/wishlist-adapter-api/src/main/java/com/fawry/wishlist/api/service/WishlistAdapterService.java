package com.fawry.wishlist.api.service;

import java.util.List;

public interface WishlistAdapterService {
    List<Long> inWishlistCheck(Long userId, List<Long> destinationIds);
}
