package com.fawry.wishlist.core.mapper;

import com.fawry.common.model.vto.LookupVTO;
import com.fawry.destination.model.entity.Destination;
import com.fawry.user.model.entity.User;
import com.fawry.user.model.vto.LightUserVTO;
import com.fawry.wishlist.model.dto.WishlistDTO;
import com.fawry.wishlist.model.entity.Wishlist;
import com.fawry.wishlist.model.vto.WishlistDetailsVTO;
import com.fawry.wishlist.model.vto.WishlistItemVTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface WishlistMapper {

    Wishlist toEntity(WishlistDTO requestDTO);

    List<Wishlist> toEntities(List<WishlistDTO> wishlistItems);

    @Mapping(target = "status", expression = "java(wishlist.getStatus().getDescription())")
    WishlistDetailsVTO toWishlistDetailsVTO(Wishlist wishlist);

    @Mapping(target = "status", expression = "java(wishlist.getStatus().getDescription())")
    WishlistItemVTO toWishlistItemVTO(Wishlist wishlist);

    List<WishlistItemVTO> toWishlistItemVTOs(List<Wishlist> wishlists);

    LightUserVTO toLightUserVTO(User user);

    @Mapping(target = "title", source = "destination.countryName")
    @Mapping(target = "imageUrl", source = "destination.flagUrl")
    LookupVTO toLookupVTO(Destination destination);

}