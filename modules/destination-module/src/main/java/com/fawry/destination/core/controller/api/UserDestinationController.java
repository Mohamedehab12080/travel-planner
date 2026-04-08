package com.fawry.destination.core.controller.api;

import com.fawry.common.db.model.dto.OrderDirections;
import com.fawry.destination.model.vto.DestinationListItemVTO;
import com.fawry.destination.model.vto.DestinationResultSet;
import com.fawry.destination.model.vto.DestinationVTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "User Destination Management", description = "Endpoints for regular users to view destinations")
@RequestMapping("/api/users/destinations")
public interface UserDestinationController {

    @GetMapping
    @Secured("ROLE_USER")
    @Operation(summary = "Get all approved destinations", description = "Returns a list of approved destinations with pagination and filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            })
    })
    ResponseEntity<DestinationResultSet> getApprovedDestinations(
            @RequestParam(required = false) String quickSearch,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdOnFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdOnTo,
            @RequestParam(required = false) String currencyQuickSearch,
            @RequestParam(required = false) List<Long> createdByIds,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) OrderDirections orderDir,
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "20") Integer pageSize);

    @GetMapping("/{id}")
    @Secured("ROLE_USER")
    @Operation(summary = "Get destination by ID", description = "Returns destination details for a specific ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            })
    })
    ResponseEntity<DestinationVTO> getDestinationById(@PathVariable Long id);
}