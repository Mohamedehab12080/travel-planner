package com.fawry.destination.api.service;

import com.fawry.common.db.model.dto.OrderDirections;
import com.fawry.destination.model.dto.*;
import com.fawry.destination.model.enums.DestinationStatus;
import com.fawry.destination.model.vto.*;

import java.time.LocalDate;
import java.util.List;

public interface DestinationService {
    DestinationVTO getUserDestinationById(Long id);
    AdminDestinationVTO getAdminDestinationById(Long id);
    DestinationVTO createDestination(DestinationDTO request);
    List<DestinationVTO> createDestinationsList(BulkDestinationDTO request);
    void updateDestination(Long id, DestinationDTO request);
    void delete(Long id);
    void approveDestination(Long id, ApproveDestinationDTO request);
    void rejectDestination(Long id, ApproveDestinationDTO request);
    DestinationResultSet getAllByFilters(String quickSearchQuery, DestinationStatus destinationStatus, LocalDate createdOnFrom, LocalDate createdOnTo, String currencyQuickSearchQuery,List<Long> createdByIds, String orderBy, OrderDirections orderDir, Integer pageNum, Integer pageSize);
    DestinationSummaryVTO getSummary();
}