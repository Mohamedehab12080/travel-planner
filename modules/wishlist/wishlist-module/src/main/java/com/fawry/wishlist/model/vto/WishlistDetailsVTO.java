package com.fawry.wishlist.model.vto;

import com.fawry.common.model.vto.LookupVTO;
import com.fawry.user.model.vto.LightUserVTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishlistDetailsVTO {
    private Long id;
    private LookupVTO destination;
    private LightUserVTO user;
    private String status;
    private Integer priority;
    private String notes;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime createdOn;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime lastModifiedOn;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate visitedDate;
    private LightUserVTO createdBy;
    private LightUserVTO lastModifiedBy;
    private Integer rating;
    private String review;
}