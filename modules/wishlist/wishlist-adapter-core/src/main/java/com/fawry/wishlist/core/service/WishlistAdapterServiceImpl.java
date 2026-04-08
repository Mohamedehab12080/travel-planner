package com.fawry.wishlist.core.service;

import com.fawry.wishlist.api.service.WishlistAdapterService;
import com.fawry.wishlist.api.service.WishlistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WishlistAdapterServiceImpl implements WishlistAdapterService {
    private final WishlistService wishlistService;
    @Override
    public List<Long> inWishlistCheck(Long userId, List<Long> destinationIds) {
        return wishlistService.getWishlistStatusByDestinationIds(userId, destinationIds);
    }
}
