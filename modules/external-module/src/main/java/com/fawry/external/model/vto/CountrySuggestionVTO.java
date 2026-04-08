package com.fawry.external.model.vto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountrySuggestionVTO {
    private String name;
    private String code;
    private String capital;
    private String region;
    private String subregion;
    private Long population;
    private String currencyName;
    private String currencyCode;
    private String flagUrl;
    private String languages;
    private String timezones;
    private Boolean alreadyExists;
}