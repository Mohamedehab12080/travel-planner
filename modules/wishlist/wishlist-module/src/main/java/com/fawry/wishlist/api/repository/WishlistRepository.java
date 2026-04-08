package com.fawry.wishlist.api.repository;

import com.fawry.common.api.repository.BaseRepository;
import com.fawry.wishlist.model.entity.Wishlist;
import com.fawry.wishlist.model.filter.WishlistSearchFilter;

import java.util.List;

public interface WishlistRepository extends BaseRepository<Wishlist,Long> {

    Wishlist insert(Wishlist wishlist);
    void update(Wishlist wishlist);
    List<Wishlist> selectAllByFilters(WishlistSearchFilter filters);
    Long countAllByFilters(WishlistSearchFilter filters);
    boolean existsByUserIdAndDestinationId(Long userId, Long destinationId);
    List<Long> existsByUserIdAndDestinationsIds(Long userId, List<Long> destinationId);
}
