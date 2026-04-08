package com.fawry.external.api.rest;

import com.fawry.external.model.dto.CountryDTO;

import java.util.List;

public interface CountryApiClient {
    List<CountryDTO> getAllCountries();
    List<CountryDTO> getCountriesByRegion(String region);
    List<CountryDTO> searchCountriesByName(String name);
    CountryDTO getCountryByCode(String code);
}