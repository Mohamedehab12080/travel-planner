package com.fawry.wishlist.repository.jpa;

import com.fawry.common.repository.jpa.BaseJPARepository;
import com.fawry.wishlist.model.entity.Wishlist;
import org.hibernate.annotations.processing.HQL;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface WishlistJPARepository extends BaseJPARepository<Wishlist,Long> {

    boolean existsWishlistByUserIdAndDestinationId(Long userId, Long destinationId);

    @Query("SELECT w.destination.id FROM Wishlist w " +
            "WHERE w.user.id = :userId AND w.destination.id IN :destinationIds")
    List<Long> findExistingDestinationIds(@Param("userId") Long userId,
                                          @Param("destinationIds") List<Long> destinationIds);

}
