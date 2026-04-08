package com.fawry.wishlist.model.vto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishlistResultSet {

    @Valid
    private List<@Valid WishlistItemVTO> _list = new ArrayList<>();

    private Long total;

}
