package com.fawry.destination.api.repository;


import com.fawry.common.api.repository.BaseRepository;
import com.fawry.destination.model.entity.Destination;
import com.fawry.destination.model.filter.DestinationSearchFilter;
import com.fawry.destination.model.vto.DestinationSummaryVTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DestinationRepository extends BaseRepository<Destination, Long> {
    Destination insert(Destination destination);
    Optional<Destination> selectByCountryCode(String countryCode);
    boolean existsByCountryCode(String countryCode);
    List<Destination> selectAllByFilters(DestinationSearchFilter searchFilter);
    Long countAllByFilters(DestinationSearchFilter searchFilter);
    List<String> findAllRegions();
    DestinationSummaryVTO countSummaryVTO();
    List<Destination> bulkInsert(List<Destination> destinations);
    void update(Destination destination);
    Map<Long,Boolean> checkInWishlistJoin(List<Long> ids,Long userId);
}