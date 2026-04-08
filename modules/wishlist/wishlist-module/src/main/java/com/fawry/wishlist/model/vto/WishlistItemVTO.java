package com.fawry.wishlist.model.vto;

import com.fawry.common.model.vto.LookupVTO;
import com.fawry.destination.model.vto.DestinationListItemVTO;
import com.fawry.user.model.vto.LightUserVTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishlistItemVTO {
    private Long id;
    private DestinationListItemVTO destination;
    private LightUserVTO user;
    private String status;
    private Integer priority;
    private String notes;
    private Integer rating;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate visitedDate;
}