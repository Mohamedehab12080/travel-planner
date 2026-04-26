package com.fawry.destination.repository;

import com.fawry.common.repository.BaseRepositoryImpl;
import com.fawry.common.security.api.service.SecurityUtilsService;
import com.fawry.destination.api.repository.DestinationRepository;
import com.fawry.destination.model.entity.Destination;
import com.fawry.destination.model.filter.DestinationSearchFilter;
import com.fawry.destination.model.vto.DestinationSummaryVTO;
import com.fawry.destination.repository.custom.DestinationQueryBuilder;
import com.fawry.destination.repository.jpa.DestinationJPARepository;
import com.fawry.user.model.entity.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class DestinationRepositoryImpl extends BaseRepositoryImpl<Destination,Long> implements DestinationRepository {

    private final DestinationJPARepository destinationJPARepository;
    private final DestinationQueryBuilder destinationQueryBuilder;
    private final SecurityUtilsService securityUtilsService;

    public DestinationRepositoryImpl(DestinationJPARepository destinationJPARepository,DestinationQueryBuilder destinationQueryBuilder,SecurityUtilsService securityUtilsService) {
        super(destinationJPARepository);
        this.destinationJPARepository = destinationJPARepository;
        this.destinationQueryBuilder = destinationQueryBuilder;
        this.securityUtilsService=securityUtilsService;
    }

    @Override
    public Destination insert(Destination destination) {
        User currentUser=User.builder().id(securityUtilsService.getCurrentUserId()).build();
        destination.setCreatedBy(currentUser);
        destination.setCreatedOn(LocalDateTime.now());
        return destinationJPARepository.save(destination);
    }

    @Override
    public Optional<Destination> selectByCountryCode(String countryCode) {
        return destinationJPARepository.findByCountryCode(countryCode);
    }

    @Override
    public boolean existsByCountryCode(String countryCode) {
        return destinationJPARepository.existsByCountryCode(countryCode);
    }

    @Override
    public List<Destination> selectAllByFilters(DestinationSearchFilter searchFilter) {
        return destinationQueryBuilder.selectAllByFilters(searchFilter);
    }

    @Override
    public Long countAllByFilters(DestinationSearchFilter searchFilter) {
        return destinationQueryBuilder.countAllByFilters(searchFilter);
    }

    @Override
    public List<String> findAllRegions() {
        return destinationJPARepository.findAllRegions();
    }

    @Override
    public DestinationSummaryVTO countSummaryVTO() {
        return destinationJPARepository.countSummaryVTO();
    }

    @Override
    public List<Destination> bulkInsert(List<Destination> destinations) {
        User currentUser=User.builder().id(securityUtilsService.getCurrentUserId()).build();
        destinations.forEach(destination->{destination.setCreatedBy(currentUser);destination.setCreatedOn(LocalDateTime.now());});
        return destinationJPARepository.saveAll(destinations);
    }

    @Override
    public void update(Destination destination) {
        User currentUser=User.builder().id(securityUtilsService.getCurrentUserId()).build();
        destination.setLastModifiedBy(currentUser);
        destination.setLastModifiedOn(LocalDateTime.now());
        destinationJPARepository.save(destination);
    }

    @Override
    public Map<Long, Boolean> checkInWishlistJoin(List<Long> ids, Long userId) {
        return destinationJPARepository.findWishlistStatusMapByIds(ids, userId);
    }
}
