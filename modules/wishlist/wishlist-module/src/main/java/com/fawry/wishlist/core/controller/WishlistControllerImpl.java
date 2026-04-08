package com.fawry.wishlist.core.controller;

import com.fawry.common.db.model.dto.OrderDirections;
import com.fawry.common.model.vto.NewRecordVTO;
import com.fawry.wishlist.api.service.WishlistService;
import com.fawry.wishlist.core.controller.api.WishlistController;
import com.fawry.wishlist.model.dto.UpdateWishlistDTO;
import com.fawry.wishlist.model.dto.WishlistDTO;
import com.fawry.wishlist.model.enums.WishlistStatus;
import com.fawry.wishlist.model.vto.WishlistResultSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WishlistControllerImpl implements WishlistController {

    private final WishlistService wishlistService;

    @Override
    @Secured({"ROLE_USER"})
    public ResponseEntity<NewRecordVTO> addToWishlist(Long destinationId, WishlistDTO request) {
        log.info("POST /api/wishlist/{} - adding destination to wishlist", destinationId);

        NewRecordVTO response = wishlistService.addWishListItem(destinationId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @Secured({"ROLE_USER"})
    public ResponseEntity<Void> updateWishlistItem(Long wishlistId, UpdateWishlistDTO request) {
        log.info("PUT /api/wishlist/{} - updating wishlist item", wishlistId);

        wishlistService.updateWishlistItem(wishlistId, request);
        return ResponseEntity.noContent().build();
    }

    @Override
    @Secured({"ROLE_USER"})
    public ResponseEntity<Void> removeFromWishlist(Long wishlistId) {
        log.info("DELETE /api/wishlist/{} - removing from wishlist", wishlistId);

        wishlistService.deleteById(wishlistId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    public ResponseEntity<WishlistResultSet> getUserWishlist(
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
            Integer pageSize) {

        log.info("GET /api/wishlist -Viewing wishlist with filters");

        WishlistResultSet wishlist = wishlistService.getAllByFilters(
                userId, destinationsIds, priorityFrom, priorityTo, rateFrom, rateTo,
                destinationQuickSearch, status, destinationCurrencyQuickSearchQuery,
                visitedDateFrom, visitedDateTo, createdOnFrom, createdOnTo,
                orderBy, orderDir, pageNum, pageSize);

        return ResponseEntity.ok(wishlist);
    }

}
