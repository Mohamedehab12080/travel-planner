package com.fawry.destination.model.vto;

import com.fawry.destination.model.enums.DestinationStatus;
import com.fawry.user.model.vto.LightUserVTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DestinationListItemVTO {
    private Long id;
    private String countryCode;
    private String countryName;
    private String capital;
    private String region;
    private Long population;
    private String flagUrl;
    private String currencyName;
    private String currencyCode;
    private DestinationStatus status;
    private LightUserVTO createdBy;
    private LightUserVTO lastModifiedBy;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime createdOn;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime lastModifiedOn;
    private Boolean inWishlist;
}