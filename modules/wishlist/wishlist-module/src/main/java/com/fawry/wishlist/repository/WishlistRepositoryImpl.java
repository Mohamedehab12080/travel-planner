package com.fawry.wishlist.repository;

import com.fawry.common.repository.BaseRepositoryImpl;
import com.fawry.common.security.api.service.SecurityUtilsService;
import com.fawry.user.model.entity.User;
import com.fawry.wishlist.api.repository.WishlistRepository;
import com.fawry.wishlist.model.entity.Wishlist;
import com.fawry.wishlist.model.filter.WishlistSearchFilter;
import com.fawry.wishlist.repository.custom.WishlistQueryBuilder;
import com.fawry.wishlist.repository.jpa.WishlistJPARepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class WishlistRepositoryImpl extends BaseRepositoryImpl<Wishlist,Long> implements WishlistRepository {

    private final WishlistJPARepository wishlistJPARepository;
    private final SecurityUtilsService securityUtilsService;
    private final WishlistQueryBuilder queryBuilder;
    public WishlistRepositoryImpl(WishlistJPARepository jpaRepository,SecurityUtilsService securityUtilsService,WishlistQueryBuilder queryBuilder) {
        super(jpaRepository);
        this.wishlistJPARepository =jpaRepository;
        this.securityUtilsService = securityUtilsService;
        this.queryBuilder = queryBuilder;
    }

    @Override
    public Wishlist insert(Wishlist wishlist) {
        Long currentUserId = securityUtilsService.getCurrentUserId();
        User user = User.builder().id(currentUserId).build();
        wishlist.setCreatedBy(user);
        wishlist.setCreatedOn(LocalDateTime.now());
        return wishlistJPARepository.save(wishlist);
    }

    @Override
    public void update(Wishlist wishlist) {
        Long currentUserId = securityUtilsService.getCurrentUserId();
        User user = User.builder().id(currentUserId).build();
        wishlist.setLastModifiedBy(user);
        wishlist.setLastModifiedOn(LocalDateTime.now());
        wishlistJPARepository.save(wishlist);
    }

    @Override
    public List<Wishlist> selectAllByFilters(WishlistSearchFilter filters) {
        return queryBuilder.selectAllByFilters(filters);
    }

    @Override
    public Long countAllByFilters(WishlistSearchFilter filters) {
        return queryBuilder.countAllByFilters(filters);
    }

    @Override
    public boolean existsByUserIdAndDestinationId(Long userId, Long destinationId) {
        return wishlistJPARepository.existsWishlistByUserIdAndDestinationId(userId,destinationId);
    }

    @Override
    public List<Long> existsByUserIdAndDestinationsIds(Long userId, List<Long> destinationId) {
        return wishlistJPARepository.findExistingDestinationIds(userId,destinationId);
    }

}
