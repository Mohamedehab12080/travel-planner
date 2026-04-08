package com.fawry.destination.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DestinationDTO {

    @NotBlank(message = "Country code is required")
    @Pattern(regexp = "^[A-Z]{2,3}$", message = "Country code must be 2-3 uppercase letters")
    private String countryCode;

    @NotBlank(message = "Country name is required")
    @Size(min = 2, max = 150, message = "Country name must be between 2 and 150 characters")
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
}