package com.fawry.wishlist.api.service;

import com.fawry.common.db.model.dto.OrderDirections;
import com.fawry.common.model.vto.NewRecordVTO;
import com.fawry.common.model.vto.InWishlistVTO;
import com.fawry.wishlist.model.dto.UpdateWishlistDTO;
import com.fawry.wishlist.model.dto.WishlistDTO;
import com.fawry.wishlist.model.enums.WishlistStatus;
import com.fawry.wishlist.model.vto.WishlistResultSet;

import java.time.LocalDate;
import java.util.List;

public interface WishlistService {

    NewRecordVTO addWishListItem(Long destinationId,WishlistDTO wishlistDTO);
    void updateWishlistItem(Long wishlistId, UpdateWishlistDTO updateWishlistDTO);
    WishlistResultSet getAllByFilters(
            Long userId,
            List<Long> destinationsIds,
            Integer priorityFrom,
            Integer priorityTo,
            Integer rateFrom,
            Integer rateTo,
            String destinationQuickSearch,
            WishlistStatus status,
            String destinationCurrencyQuickSearchQuery,
            LocalDate visitedDateFrom,
            LocalDate visitedDateTo,
            LocalDate createdOnFrom,
            LocalDate createdOnTo,
            String orderBy,
            OrderDirections orderDir,
            Integer pageNum,
            Integer pageSize
    );
    void deleteById(Long id);

    List<Long> getWishlistStatusByDestinationIds(Long userId, List<Long> destinationIds);

}
