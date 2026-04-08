package com.fawry.external.core.service;

import com.fawry.external.api.rest.CountryApiClient;
import com.fawry.external.model.dto.CountryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class RestCountriesClient implements CountryApiClient {

    private final RestClient restClient;
    private final String baseUrl;

    public RestCountriesClient(@Value("${external.api.countries.url:https://restcountries.com/v3.1}") String baseUrl) {
        this.baseUrl = baseUrl;
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultStatusHandler(HttpStatusCode::isError, (request, response) -> {
                    log.error("External API error: {} - {}", response.getStatusCode(), new String(response.getBody().readAllBytes()));
                })
                .build();
    }

    @Override
    public List<CountryDTO> getAllCountries() {
        log.info("Fetching all countries from REST Countries API");

        try {
            String url = UriComponentsBuilder.fromPath("/all")
                    .queryParam("fields", "name,cca2,cca3,capital,region,subregion,population,currencies,flags,languages")
                    .build()
                    .toUriString();

            List<CountryDTO> countries = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<CountryDTO>>() {});

            log.info("Successfully fetched {} countries", countries != null ? countries.size() : 0);
            return countries != null ? countries : Collections.emptyList();

        } catch (Exception e) {
            log.error("Error fetching all countries: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<CountryDTO> getCountriesByRegion(String region) {
        log.info("Fetching countries by region: {}", region);

        try {
            String url = UriComponentsBuilder.fromPath("/region/{region}")
                    .queryParam("fields", "name,cca2,cca3,capital,region,subregion,population,currencies,flags,languages")
                    .buildAndExpand(region.toLowerCase())
                    .toUriString();

            List<CountryDTO> countries = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<CountryDTO>>() {});

            log.info("Successfully fetched {} countries for region: {}", countries != null ? countries.size() : 0, region);
            return countries != null ? countries : Collections.emptyList();

        } catch (Exception e) {
            log.error("Error fetching countries by region {}: {}", region, e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<CountryDTO> searchCountriesByName(String name) {
        log.info("Searching countries by name: {}", name);

        try {
            String url = UriComponentsBuilder.fromPath("/name/{name}")
                    .queryParam("fields", "name,cca2,cca3,capital,region,subregion,population,currencies,flags,languages")
                    .buildAndExpand(name)
                    .toUriString();

            List<CountryDTO> countries = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<CountryDTO>>() {});

            log.info("Successfully fetched {} countries for name: {}", countries != null ? countries.size() : 0, name);
            return countries != null ? countries : Collections.emptyList();

        } catch (Exception e) {
            log.error("Error searching countries by name {}: {}", name, e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public CountryDTO getCountryByCode(String code) {
        log.info("Fetching country by code: {}", code);

        try {
            String url = UriComponentsBuilder.fromPath("/alpha/{code}")
                    .queryParam("fields", "name,cca2,cca3,capital,region,subregion,population,currencies,flags,languages")
                    .buildAndExpand(code.toUpperCase())
                    .toUriString();

            List<CountryDTO> countries = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<CountryDTO>>() {});

            if (countries != null && !countries.isEmpty()) {
                log.info("Successfully fetched country for code: {}", code);
                return countries.get(0);
            }

            return null;

        } catch (Exception e) {
            log.error("Error fetching country by code {}: {}", code, e.getMessage(), e);
            return null;
        }
    }
}