package com.fawry.wishlist.core.controller.api;

import com.fawry.common.db.model.dto.OrderDirections;
import com.fawry.common.model.vto.ErrorVTO;
import com.fawry.common.model.vto.NewRecordVTO;
import com.fawry.wishlist.model.dto.UpdateWishlistDTO;
import com.fawry.wishlist.model.dto.WishlistDTO;
import com.fawry.wishlist.model.enums.WishlistStatus;
import com.fawry.wishlist.model.vto.WishlistResultSet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Wishlist Management", description = "Endpoints for managing user wishlist")
@RequestMapping("/api/wishlist")
public interface WishlistController {

    @PostMapping("/{destinationId}")
    @Operation(summary = "Add to wishlist", description = "Adds a destination to user's wishlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = NewRecordVTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorVTO.class))
            }),
    })
    ResponseEntity<NewRecordVTO> addToWishlist(
            @PathVariable Long destinationId,
            @Valid @RequestBody(required = false) WishlistDTO request);

    @PutMapping("/{wishlistId}")
    @Operation(summary = "Update wishlist item", description = "Updates wishlist item details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorVTO.class))
            })
    })
    ResponseEntity<Void> updateWishlistItem(
            @PathVariable Long wishlistId,
            @Valid @RequestBody UpdateWishlistDTO request);

    @DeleteMapping("/{wishlistId}")
    @Operation(summary = "Remove from wishlist", description = "Removes a destination from user's wishlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorVTO.class))
            })
    })
    ResponseEntity<Void> removeFromWishlist(@PathVariable Long wishlistId);

    @GetMapping
    @Operation(summary = "Get user wishlist", description = "Returns all destinations in user's wishlist with filters and pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = WishlistResultSet.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorVTO.class))
            })
    })
    ResponseEntity<WishlistResultSet> getUserWishlist(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) List<Long> destinationsIds,
            @RequestParam(required = false) Integer priorityFrom,
            @RequestParam(required = false) Integer priorityTo,
            @RequestParam(required = false) Integer rateFrom,
            @RequestParam(required = false) Integer rateTo,
            @RequestParam(required = false) String destinationQuickSearch,
            @RequestParam(required = false) WishlistStatus status,
            @RequestParam(required = false) String destinationCurrencyQuickSearchQuery,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate visitedDateFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate visitedDateTo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdOnFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdOnTo,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) OrderDirections orderDir,
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "20") Integer pageSize);

}