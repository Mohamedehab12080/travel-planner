package com.fawry.destination.core.controller;

import com.fawry.common.db.model.dto.OrderDirections;
import com.fawry.destination.api.service.DestinationService;
import com.fawry.destination.core.controller.api.UserDestinationController;
import com.fawry.destination.model.enums.DestinationStatus;
import com.fawry.destination.model.vto.DestinationListItemVTO;
import com.fawry.destination.model.vto.DestinationResultSet;
import com.fawry.destination.model.vto.DestinationVTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserDestinationControllerImpl implements UserDestinationController {

    private final DestinationService destinationService;

    @Override
    public ResponseEntity<DestinationResultSet> getApprovedDestinations(
            String quickSearch,
            LocalDate createdOnFrom,
            LocalDate createdOnTo,
            String currencyQuickSearch,
            List<Long> createdByIds,
            String orderBy,
            OrderDirections orderDir,
            Integer pageNum,
            Integer pageSize) {

        log.info("GET /api/destinations - User viewing approved destinations");

        DestinationResultSet destinations = destinationService.getAllByFilters(
                quickSearch,
                DestinationStatus.APPROVED,
                createdOnFrom,
                createdOnTo,
                currencyQuickSearch,
                createdByIds,
                orderBy,
                orderDir,
                pageNum,
                pageSize);

        return ResponseEntity.ok(destinations);
    }

    @Override
    public ResponseEntity<DestinationVTO> getDestinationById(Long id) {
        log.info("GET /api/destinations/{} - User viewing destination details", id);

        DestinationVTO destination = destinationService.getUserDestinationById(id);
        return ResponseEntity.ok(destination);
    }
}