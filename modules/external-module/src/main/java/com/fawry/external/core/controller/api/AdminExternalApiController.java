package com.fawry.external.core.controller.api;

import com.fawry.common.model.vto.ErrorVTO;
import com.fawry.external.model.vto.CountrySuggestionVTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Admin External API", description = "Admin endpoints for fetching countries from external API")
@RequestMapping("/api/admin/external")
public interface AdminExternalApiController {

    @GetMapping("/countries")
    @Operation(summary = "Fetch countries from external API",
            description = "Fetches countries from REST Countries API. Returns empty list if no results found.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorVTO.class))
            })
    })
    ResponseEntity<List<CountrySuggestionVTO>> fetchCountries(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String search);

    @GetMapping("/regions")
    @Operation(summary = "Get all regions", description = "Returns list of all available regions from external API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorVTO.class))
            })
    })
    ResponseEntity<List<String>> getAllRegions();

    @GetMapping("/country")
    @Operation(summary = "Get country by code", description = "Fetches a single country by its 2-letter country code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorVTO.class))
            })
    })
    ResponseEntity<CountrySuggestionVTO> getCountryByCode(@RequestParam String code);
}