package com.fawry.destination.core.controller.api;

import com.fawry.common.db.model.dto.OrderDirections;
import com.fawry.destination.model.dto.ApproveDestinationDTO;
import com.fawry.destination.model.dto.BulkDestinationDTO;
import com.fawry.destination.model.dto.DestinationDTO;
import com.fawry.destination.model.enums.DestinationStatus;
import com.fawry.destination.model.vto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Admin Destination Management", description = "Admin-only endpoints for managing destinations")
@RequestMapping("/api/admin/destinations")
public interface AdminDestinationController {

    @PostMapping
    @Operation(summary = "Create destination", description = "Creates a new destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            })
    })
    ResponseEntity<DestinationVTO> createDestination(@Valid @RequestBody DestinationDTO request);

    @PostMapping("/bulk")
    @Operation(summary = "Bulk create destinations", description = "Creates multiple destinations at once")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            })
    })
    ResponseEntity<List<DestinationVTO>> bulkCreateDestinations(@Valid @RequestBody BulkDestinationDTO request);

    @PutMapping("/{id}")
    @Operation(summary = "Update destination", description = "Updates an existing destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            })
    })
    ResponseEntity<Void> updateDestination(@PathVariable Long id, @Valid @RequestBody DestinationDTO request);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete destination", description = "Deletes a destination by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            })
    })
    ResponseEntity<Void> deleteDestination(@PathVariable Long id);

    @PostMapping("/{id}/approve")
    @Operation(summary = "Approve destination", description = "Approves a pending destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            })
    })
    ResponseEntity<Void> approveDestination(@PathVariable Long id, @RequestBody(required = false) ApproveDestinationDTO request);

    @PostMapping("/{id}/reject")
    @Operation(summary = "Reject destination", description = "Rejects a pending destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            })
    })
    ResponseEntity<Void> rejectDestination(@PathVariable Long id, @RequestBody(required = false) ApproveDestinationDTO request);

    @GetMapping
    @Operation(summary = "Get all destinations (admin view)", description = "Returns all destinations with filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            })
    })
    ResponseEntity<DestinationResultSet> getAllDestinations(
            @RequestParam(required = false) String quickSearchQuery,
            @RequestParam(required = false) DestinationStatus status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdOnFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdOnTo,
            @RequestParam(required = false) String currencyQuickSearch,
            @RequestParam(required = false) List<Long> createdById,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) OrderDirections orderDir,
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "20") Integer pageSize);

    @GetMapping("/admin-view/{id}")
    @Operation(summary = "Get destination by ID (admin view)", description = "Returns full destination details including audit information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            })
    })
    ResponseEntity<AdminDestinationVTO> getAdminDestinationById(@PathVariable Long id);

    @GetMapping("/summary")
    @Operation(summary = "Get destination summary", description = "Returns destination statistics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = com.fawry.common.model.vto.ErrorVTO.class))
            })
    })
    ResponseEntity<DestinationSummaryVTO> getDestinationSummary();
}