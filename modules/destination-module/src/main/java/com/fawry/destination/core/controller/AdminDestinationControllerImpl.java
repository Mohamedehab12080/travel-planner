package com.fawry.destination.core.controller;

import com.fawry.common.db.model.dto.OrderDirections;
import com.fawry.common.security.api.service.SecurityUtilsService;
import com.fawry.destination.api.service.DestinationService;
import com.fawry.destination.core.controller.api.AdminDestinationController;
import com.fawry.destination.model.dto.ApproveDestinationDTO;
import com.fawry.destination.model.dto.BulkDestinationDTO;
import com.fawry.destination.model.dto.DestinationDTO;
import com.fawry.destination.model.enums.DestinationStatus;
import com.fawry.destination.model.vto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminDestinationControllerImpl implements AdminDestinationController {

    private final DestinationService destinationService;
    private final SecurityUtilsService securityUtilsService;

    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    @Override
    public ResponseEntity<DestinationVTO> createDestination(DestinationDTO request) {
        log.info("POST /api/admin/destinations - Admin {} creating destination: {}",
                securityUtilsService.getCurrentUserEmail(), request.getCountryCode());

        DestinationVTO created = destinationService.createDestination(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    @Override
    public ResponseEntity<List<DestinationVTO>> bulkCreateDestinations(BulkDestinationDTO request) {
        log.info("POST /api/admin/destinations/bulk - Admin {} creating {} destinations",
                securityUtilsService.getCurrentUserEmail(), request.getDestinations().size());

        List<DestinationVTO> created = destinationService.createDestinationsList(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    @Override
    public ResponseEntity<Void> updateDestination(Long id, DestinationDTO request) {
        log.info("PUT /api/admin/destinations/{} - Admin {} updating destination",
                id, securityUtilsService.getCurrentUserEmail());

        destinationService.updateDestination(id, request);
        return ResponseEntity.noContent().build();
    }

    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    @Override
    public ResponseEntity<Void> deleteDestination(Long id) {
        log.info("DELETE /api/admin/destinations/{} - Admin {} deleting destination",
                id, securityUtilsService.getCurrentUserEmail());

        destinationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    @Override
    public ResponseEntity<Void> approveDestination(Long id, ApproveDestinationDTO request) {
        log.info("POST /api/admin/destinations/{}/approve - Admin {} approving destination",
                id, securityUtilsService.getCurrentUserEmail());

        destinationService.approveDestination(id, request);
        return ResponseEntity.noContent().build();
    }

    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    @Override
    public ResponseEntity<Void> rejectDestination(Long id, ApproveDestinationDTO request) {
        log.info("POST /api/admin/destinations/{}/reject - Admin {} rejecting destination",
                id, securityUtilsService.getCurrentUserEmail());

        destinationService.rejectDestination(id, request);
        return ResponseEntity.noContent().build();
    }

    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    @Override
    public ResponseEntity<DestinationResultSet> getAllDestinations(
            String quickSearch,
            DestinationStatus status,
            LocalDate createdOnFrom,
            LocalDate createdOnTo,
            String currencyQuickSearch,
            List<Long> createdById,
            String orderBy,
            OrderDirections orderDir,
            Integer pageNum,
            Integer pageSize) {

        log.info("GET /api/admin/destinations - Admin {} viewing all destinations",
                securityUtilsService.getCurrentUserEmail());

        DestinationResultSet destinations = destinationService.getAllByFilters(
                quickSearch,
                status,
                createdOnFrom,
                createdOnTo,
                currencyQuickSearch,
                createdById,
                orderBy,
                orderDir,
                pageNum,
                pageSize);

        return ResponseEntity.ok(destinations);
    }

    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    @Override
    public ResponseEntity<AdminDestinationVTO> getAdminDestinationById(Long id) {
        log.info("GET /api/admin/destinations/admin-view/{} - Admin {} viewing destination details",
                id, securityUtilsService.getCurrentUserEmail());

        AdminDestinationVTO destination = destinationService.getAdminDestinationById(id);
        return ResponseEntity.ok(destination);
    }

    @Secured({"ROLE_ADMIN","ROLE_SUPER_ADMIN"})
    @Override
    public ResponseEntity<DestinationSummaryVTO> getDestinationSummary() {
        log.info("GET /api/admin/destinations/summary - Admin {} viewing summary",
                securityUtilsService.getCurrentUserEmail());

        DestinationSummaryVTO summary = destinationService.getSummary();
        return ResponseEntity.ok(summary);
    }
}