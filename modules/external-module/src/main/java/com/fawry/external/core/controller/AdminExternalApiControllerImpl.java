package com.fawry.external.core.controller;

import com.fawry.common.security.api.service.SecurityUtilsService;
import com.fawry.external.api.service.CountryService;
import com.fawry.external.core.controller.api.AdminExternalApiController;
import com.fawry.external.model.vto.CountrySuggestionVTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminExternalApiControllerImpl implements AdminExternalApiController {

    private final CountryService countryService;
    private final SecurityUtilsService securityUtilsService;

    @Override
    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    public ResponseEntity<List<CountrySuggestionVTO>> fetchCountries(String region, String search) {
        log.info("GET /api/admin/external/countries - Admin {} fetching countries - region: {}, search: {}",
                securityUtilsService.getCurrentUserEmail(), region, search);

        List<CountrySuggestionVTO> countries = countryService.fetchCountries(region, search);
        return ResponseEntity.ok(countries);
    }

    @Override
    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    public ResponseEntity<List<String>> getAllRegions() {
        log.info("GET /api/admin/external/regions - Admin {} fetching all regions",
                securityUtilsService.getCurrentUserEmail());

        List<String> regions = countryService.getAllRegions();
        return ResponseEntity.ok(regions);
    }

    @Override
    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    public ResponseEntity<CountrySuggestionVTO> getCountryByCode(String code) {
        log.info("GET /api/admin/external/country - Admin {} fetching country by code: {}",
                securityUtilsService.getCurrentUserEmail(), code);

        CountrySuggestionVTO country = countryService.fetchCountryByCode(code);
        return ResponseEntity.ok(country);
    }
}
