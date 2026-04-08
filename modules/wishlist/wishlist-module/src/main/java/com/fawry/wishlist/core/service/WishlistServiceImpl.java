package com.fawry.wishlist.core.service;

import com.fawry.common.db.model.dto.OrderDirections;
import com.fawry.common.db.model.dto.PaginationInfo;
import com.fawry.common.db.model.dto.SortingInfo;
import com.fawry.common.model.exception.BusinessException;
import com.fawry.common.model.vto.NewRecordVTO;
import com.fawry.common.security.api.service.SecurityUtilsService;
import com.fawry.destination.model.entity.Destination;
import com.fawry.common.model.vto.InWishlistVTO;
import com.fawry.user.model.entity.User;
import com.fawry.wishlist.api.repository.WishlistRepository;
import com.fawry.wishlist.api.service.WishlistService;
import com.fawry.wishlist.core.mapper.WishlistMapper;
import com.fawry.wishlist.model.dto.UpdateWishlistDTO;
import com.fawry.wishlist.model.dto.WishlistDTO;
import com.fawry.wishlist.model.entity.Wishlist;
import com.fawry.wishlist.model.enums.WishlistStatus;
import com.fawry.wishlist.model.filter.WishlistSearchFilter;
import com.fawry.wishlist.model.vto.WishlistResultSet;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.fawry.wishlist.model.enums.WishlistErrors.*;

@Slf4j
@Service
@AllArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final WishlistMapper wishlistMapper;
    private final SecurityUtilsService securityUtilsService;

    @Override
    @Transactional
    public NewRecordVTO addWishListItem(Long destinationId, WishlistDTO wishlistDTO) {
        Long userId=securityUtilsService.getCurrentUserId();
        boolean exist =wishlistRepository.existsByUserIdAndDestinationId(userId, destinationId);
        if(exist) {
            throw new BusinessException(DESTINATION_ALREADY_IN_WISHLIST,destinationId);
        }
        User user=User.builder().id(userId).build();
        Destination destination=Destination.builder().id(destinationId).build();
        Wishlist wishlist=Wishlist.builder()
                .user(user)
                .destination(destination)
                .notes(wishlistDTO.getNotes())
                .priority(wishlistDTO.getPriority())
                .status(WishlistStatus.WANT_TO_VISIT)
                .build();
        Wishlist insertedWishlistItem= wishlistRepository.insert(wishlist);
        return NewRecordVTO.builder().id(insertedWishlistItem.getId()).build();
    }

    @Override
    @Transactional
    public void updateWishlistItem(Long wishlistId,UpdateWishlistDTO updateWishlistDTO) {
        Wishlist wishlist=wishlistRepository.selectById(wishlistId).orElseThrow(()-> new BusinessException(WISHLIST_ITEM_NOT_FOUND, wishlistId));
        Long userId=securityUtilsService.getCurrentUserId();
        if(userId!=null && !wishlist.getCreatedBy().getId().equals(userId)) {
            throw new BusinessException(NOT_ALLOWED_TO_UPDATE_THIS_WISHLIST,userId);
        }
        if(updateWishlistDTO.getPriority()!=null) wishlist.setPriority(updateWishlistDTO.getPriority());
        if(updateWishlistDTO.getStatus()!=null) wishlist.setStatus(updateWishlistDTO.getStatus());
        if(updateWishlistDTO.getReview()!=null) wishlist.setReview(updateWishlistDTO.getReview());
        if(updateWishlistDTO.getRating()!=null) wishlist.setRating(updateWishlistDTO.getRating());
        if(updateWishlistDTO.getVisitedDate() !=null) wishlist.setVisitedDate(updateWishlistDTO.getVisitedDate());
        wishlistRepository.update(wishlist);
    }

    @Override
    public WishlistResultSet getAllByFilters(Long userId, List<Long> destinationsIds, Integer priorityFrom, Integer priorityTo, Integer rateFrom, Integer rateTo, String destinationQuickSearch, WishlistStatus status, String destinationCurrencyQuickSearchQuery, LocalDate visitedDateFrom, LocalDate visitedDateTo, LocalDate createdOnFrom, LocalDate createdOnTo, String orderBy, OrderDirections orderDir, Integer pageNum, Integer pageSize) {
        Long currentUserId=userId;
        if(currentUserId==null){
                currentUserId= securityUtilsService.getCurrentUserId();
        }
        WishlistSearchFilter wishlistSearchFilter=WishlistSearchFilter.builder()
                .userId(currentUserId)
                .destinationsIds(destinationsIds)
                .destinationQuickSearch(destinationQuickSearch)
                .destinationCurrencyQuickSearchQuery(destinationCurrencyQuickSearchQuery)
                .visitedDateFrom(visitedDateFrom)
                .visitedDateTo(visitedDateTo)
                .createdOnFrom(createdOnFrom)
                .createdOnTo(createdOnTo)
                .priorityFrom(priorityFrom)
                .priorityTo(priorityTo)
                .rateFrom(rateFrom)
                .rateTo(rateTo)
                .status(status)
                .sorting(new SortingInfo<>(orderBy, orderDir))
                .defaultSorting(new SortingInfo<>(WishlistSearchFilter.OrderByAttributes.PRIORITY,OrderDirections.DESC))
                .pagination(PaginationInfo.builder().pageNum(pageNum).pageSize(pageSize).build())
                .build();
        List<Wishlist> wishlistItems=wishlistRepository.selectAllByFilters(wishlistSearchFilter);
        Long total=wishlistRepository.countAllByFilters(wishlistSearchFilter);
       log.info("Total Wishlists filtered {}",total);
        return WishlistResultSet.builder()._list(wishlistMapper.toWishlistItemVTOs(wishlistItems)).total(total).build();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Wishlist wishlist=wishlistRepository.selectById(id).orElseThrow(()-> new BusinessException(WISHLIST_ITEM_NOT_FOUND,id));
        wishlistRepository.deleteById(id);
    }

    public List<Long> getWishlistStatusByDestinationIds(Long userId, List<Long> destinationIds) {
        List<Long> result = new ArrayList<>();

        if (destinationIds == null || destinationIds.isEmpty()) {
            return result;
        }
        List<Long> existingIds = wishlistRepository.existsByUserIdAndDestinationsIds(userId, destinationIds);
        Set<Long> existingIdSet = new HashSet<>(existingIds);
        for (Long destinationId : destinationIds) {
            if(existingIdSet.contains(destinationId)) result.add(destinationId);
        }
        return result;
    }

}
