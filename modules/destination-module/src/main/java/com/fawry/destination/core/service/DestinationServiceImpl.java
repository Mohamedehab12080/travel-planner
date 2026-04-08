package com.fawry.destination.core.service;

import com.fawry.common.db.model.dto.OrderDirections;
import com.fawry.common.db.model.dto.PaginationInfo;
import com.fawry.common.db.model.dto.SortingInfo;
import com.fawry.common.model.exception.BusinessException;
import com.fawry.common.model.vto.InWishlistVTO;
import com.fawry.common.security.api.service.SecurityUtilsService;
import com.fawry.destination.api.repository.DestinationRepository;
import com.fawry.destination.api.service.DestinationService;
import com.fawry.destination.core.mapper.DestinationMapper;
import com.fawry.destination.model.dto.ApproveDestinationDTO;
import com.fawry.destination.model.dto.BulkDestinationDTO;
import com.fawry.destination.model.dto.DestinationDTO;
import com.fawry.destination.model.entity.Destination;
import com.fawry.destination.model.enums.DestinationStatus;
import com.fawry.destination.model.filter.DestinationSearchFilter;
import com.fawry.destination.model.vto.*;
import com.fawry.user.model.enums.Role;
import com.fawry.wishlist.api.service.WishlistAdapterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.fawry.common.db.model.dto.OrderDirections.DESC;
import static com.fawry.destination.model.enums.DestinationErrors.*;

@Slf4j
@Service
@AllArgsConstructor
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;
    private final DestinationMapper destinationMapper;
    private final SecurityUtilsService securityUtilsService;
    private final WishlistAdapterService wishlistAdapterService;

    @Override
    public DestinationVTO getUserDestinationById(Long id) {
        String currentRole=securityUtilsService.getCurrentUserRole();
        Destination destination = destinationRepository.selectById(id).orElseThrow(()-> new BusinessException(DESTINATION_NOT_FOUND,id));
        if(!destination.getStatus().equals(DestinationStatus.APPROVED)) throw new BusinessException(DESTINATION_NOT_FOUND,id);
        DestinationVTO destinationVTO = destinationMapper.toDestinationVTO(destination);
        if(currentRole.equals(Role.ROLE_USER.name())) {
            Long currentUserId= securityUtilsService.getCurrentUserId();
            destinationVTO.setInWishlist(!wishlistAdapterService.inWishlistCheck(currentUserId,List.of(destinationVTO.getId())).isEmpty());
        }
        return destinationVTO;
    }

    @Override
    public AdminDestinationVTO getAdminDestinationById(Long id) {
        Destination destination = destinationRepository.selectById(id).orElseThrow(()-> new BusinessException(DESTINATION_NOT_FOUND,id));
        AdminDestinationVTO adminDestinationVTO = destinationMapper.toAdminDestinationVTO(destination);
        return adminDestinationVTO;
    }

    @Override
    @Transactional
    public DestinationVTO createDestination(DestinationDTO request) {
        log.info("Creating destination with country code: {}", request.getCountryCode());
        boolean exist = destinationRepository.existsByCountryCode(request.getCountryCode());
        if(exist) {
            throw new BusinessException(DESTINATION_ALREADY_EXIST,request.getCountryCode());
        }
        Destination destination=destinationMapper.toEntity(request);
        destination.setStatus(DestinationStatus.PENDING);
        Destination inserted= destinationRepository.insert(destination);
        log.info("Destination created successfully with id: {}", inserted.getId());
        return destinationMapper.toDestinationVTO(inserted);
    }

    @Override
    @Transactional
    public List<DestinationVTO> createDestinationsList(BulkDestinationDTO request) {
        log.info("Bulk creating {} destinations", request.getDestinations().size());
        for (DestinationDTO destinationDTO : request.getDestinations()) {
            boolean exist = destinationRepository.existsByCountryCode(destinationDTO.getCountryCode());
            if (exist) throw new BusinessException(DESTINATION_ALREADY_EXIST,destinationDTO.getCountryCode());
        }
        List<Destination> destinationsToBeInserted=destinationMapper.toEntities(request.getDestinations());
        destinationsToBeInserted.forEach(dest -> {
            if (request.getAutoApprove()) {
                dest.setStatus(DestinationStatus.APPROVED);
            } else {
                dest.setStatus(DestinationStatus.PENDING);
            }
        });
        List<Destination> savedDestinations=destinationRepository.bulkInsert(destinationsToBeInserted);
        List<DestinationVTO> destinationVTOS=destinationMapper.toDestinationsVTOs(savedDestinations);
        log.info("Bulk created {} destinations successfully", savedDestinations.size());
        return destinationVTOS;
    }

    @Override
    @Transactional
    public void updateDestination(Long id, DestinationDTO request) {
        log.info("Updating destination with id: {}", id);
        Destination destination = destinationRepository.selectById(id).orElseThrow(()-> new BusinessException(DESTINATION_NOT_FOUND,id));
        if(request.getCountryCode()!=null) {
            destination.setCountryCode(request.getCountryCode());
        }
        if(request.getCountryName()!=null) {
            destination.setCountryName(request.getCountryName());
        }
        if(request.getCapital()!=null){
            destination.setCapital(request.getCapital());
        }
        if(request.getLanguages()!=null){
            destination.setLanguages(request.getLanguages());
        }
        if(request.getCurrencyCode()!=null){
            destination.setCurrencyCode(request.getCurrencyCode());
        }
        if(request.getCurrencyName()!=null){
            destination.setCurrencyName(request.getCurrencyName());
        }
        if(request.getTimezones()!=null){
            destination.setTimezones(request.getTimezones());
        }
        destinationRepository.update(destination);
        log.info("Destination updated successfully with id: {}", id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting destination with id: {}", id);
        boolean exist = destinationRepository.existsById(id);
        if(exist)
            throw new BusinessException(DESTINATION_NOT_FOUND,id);
        destinationRepository.deleteById(id);
        log.info("Destination deleted successfully with id: {}", id);
    }

    @Override
    @Transactional
    public void approveDestination(Long id, ApproveDestinationDTO request) {
        log.info("Approving destination with id: {}", id);
        Destination destination = destinationRepository.selectById(id).orElseThrow(()-> new BusinessException(DESTINATION_NOT_FOUND,id));
        if(destination.getStatus().equals(DestinationStatus.APPROVED))
            throw new BusinessException(DESTINATION_ALREADY_APPROVED,destination.getCountryName());
        destination.setNote(request.getNotes());
        destination.setStatus(DestinationStatus.APPROVED);
        destinationRepository.update(destination);
        log.info("Destination approved successfully with id: {}", id);
    }

    @Override
    @Transactional
    public void rejectDestination(Long id, ApproveDestinationDTO request) {
        log.info("Rejecting destination with id: {}", id);
        Destination destination = destinationRepository.selectById(id).orElseThrow(()-> new BusinessException(DESTINATION_NOT_FOUND,id));
        if(destination.getStatus().equals(DestinationStatus.REJECTED))
            throw new BusinessException(DESTINATION_ALREADY_REJECTED,destination.getCountryName());
        destination.setNote(request.getNotes());
        destination.setStatus(DestinationStatus.REJECTED);
        destinationRepository.update(destination);
        log.info("Destination rejected successfully with id: {}", id);
    }

    @Override
    public DestinationResultSet getAllByFilters(String quickSearchQuery, DestinationStatus destinationStatus, LocalDate createdOnFrom, LocalDate createdOnTo, String currencyQuickSearchQuery,List<Long> createdByIds, String orderBy, OrderDirections orderDir, Integer pageNum, Integer pageSize) {
        String currentUserRole=securityUtilsService.getCurrentUserRole();
        if(currentUserRole.equals(Role.ROLE_ADMIN.name())) {
            if(createdByIds==null||createdByIds.isEmpty()){
                Long currentUserId= securityUtilsService.getCurrentUserId();
                createdByIds=new ArrayList<>();
                createdByIds.add(currentUserId);
            }
        }

        DestinationSearchFilter destinationSearchFilter =DestinationSearchFilter.builder()
                .quickSearchQuery(quickSearchQuery)
                .status(destinationStatus)
                .currencyQuickSearchQuery(currencyQuickSearchQuery)
                .createdOnFrom(createdOnFrom)
                .createdOnTo(createdOnTo)
                .sorting(new SortingInfo<>(orderBy, orderDir))
                .defaultSorting(new SortingInfo<>(DestinationSearchFilter.OrderByAttributes.CREATION_DATE, DESC))
                .pagination(PaginationInfo.builder().pageNum(pageNum).pageSize(pageSize).build())
                .createdByIds(createdByIds)
                .build();
        List<Destination> destinations=destinationRepository.selectAllByFilters(destinationSearchFilter);
        List<DestinationListItemVTO> destinationListItemVTOs=destinationMapper.toDestinationListItemVTOs(destinations);
        if(currentUserRole.equals(Role.ROLE_USER.name())) {
            List<Long> destinationIds=destinations.stream().map(Destination::getId).toList();
            Long currentUserId= securityUtilsService.getCurrentUserId();
            List<Long> destinationsInWishlist=wishlistAdapterService.inWishlistCheck(currentUserId, destinationIds);
            destinationListItemVTOs.forEach(destinationListItemVTO->{
                destinationListItemVTO.setInWishlist(destinationsInWishlist.contains(destinationListItemVTO.getId()));
            });
        }
        Long total=destinationRepository.countAllByFilters(destinationSearchFilter);
        log.info("Total number of destinations: {}", total);
        return DestinationResultSet.builder()._list(destinationListItemVTOs).total(total).build();
    }

    @Override
    public DestinationSummaryVTO getSummary() {
        return destinationRepository.countSummaryVTO();
    }
}
