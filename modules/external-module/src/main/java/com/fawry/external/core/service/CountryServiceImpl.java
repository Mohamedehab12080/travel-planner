package com.fawry.external.core.service;


import com.fawry.destination.api.repository.DestinationRepository;
import com.fawry.external.api.rest.CountryApiClient;
import com.fawry.external.api.service.CountryService;
import com.fawry.external.core.mapper.CountryMapper;
import com.fawry.external.model.dto.CountryDTO;
import com.fawry.external.model.vto.CountrySuggestionVTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryApiClient countryApiClient;
    private final CountryMapper countryMapper;
    private final DestinationRepository destinationRepository;

    @Override
    public List<CountrySuggestionVTO> fetchCountries(String region, String search) {
        log.info("Fetching countries from external API - region: {}, search: {}", region, search);

        List<CountryDTO> countries;

        if (StringUtils.hasText(search)) {
            countries = countryApiClient.searchCountriesByName(search);
        } else if (StringUtils.hasText(region) && !"ALL".equalsIgnoreCase(region)) {
            countries = countryApiClient.getCountriesByRegion(region);
        } else {
            countries = countryApiClient.getAllCountries();
        }

        if (countries == null || countries.isEmpty()) {
            log.warn("No countries fetched from external API");
            return List.of();
        }

        return countries.stream()
                .map(country -> {
                    boolean exists = destinationRepository.existsByCountryCode(country.getCca2());
                    return countryMapper.toCountrySuggestionVTO(country,exists);
                })
                .collect(Collectors.toList());
    }

    @Override
    public CountrySuggestionVTO fetchCountryByCode(String code) {
        log.info("Fetching country by code: {}", code);

        CountryDTO country = countryApiClient.getCountryByCode(code);

        if (country == null) {
            log.warn("No country found for code: {}", code);
            return null;
        }

        boolean exists = destinationRepository.existsByCountryCode(country.getCca2());
        return countryMapper.toCountrySuggestionVTO(country, exists);
    }

    @Override
    public List<String> getAllRegions() {
        log.info("Getting all regions from external API");

        List<CountryDTO> countries = countryApiClient.getAllCountries();

        if (countries == null || countries.isEmpty()) {
            return List.of();
        }

        return countries.stream()
                .map(CountryDTO::getRegion)
                .filter(StringUtils::hasText)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}