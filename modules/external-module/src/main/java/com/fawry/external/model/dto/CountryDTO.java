package com.fawry.external.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDTO {

    private NameDTO name;
    private String cca2;      // 2-letter country code
    private String cca3;      // 3-letter country code

    @JsonProperty("capital")
    private List<String> capitals;

    private String region;
    private String subregion;
    private Long population;
    private Map<String, CurrencyDTO> currencies;
    private FlagsDTO flags;
    private Map<String, String> languages;
    private List<String> timezones;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NameDTO {
        private String common;
        private String official;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CurrencyDTO {
        private String name;
        private String symbol;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FlagsDTO {
        private String png;
        private String svg;
        @JsonProperty("alt")
        private String altText;
    }
}