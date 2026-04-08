package com.fawry.destination.model.vto;

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
public class DestinationVTO {
    private Long id;
    private String countryCode;
    private String countryName;
    private String capital;
    private String region;
    private String subregion;
    private Long population;
    private String currencyName;
    private String currencyCode;
    private String flagUrl;
    private String languages;
    private String timezones;
    private String status;
    private String note;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime createdOn;
    private Boolean inWishlist;
}
