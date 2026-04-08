package com.fawry.external.api.service;


import com.fawry.external.model.vto.CountrySuggestionVTO;

import java.util.List;

public interface CountryService {
    List<CountrySuggestionVTO> fetchCountries(String region, String search);
    CountrySuggestionVTO fetchCountryByCode(String code);
    List<String> getAllRegions();
}