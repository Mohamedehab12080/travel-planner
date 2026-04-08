package com.fawry.destination.repository.jpa;

import com.fawry.common.repository.jpa.BaseJPARepository;
import com.fawry.destination.model.entity.Destination;
import com.fawry.destination.model.vto.DestinationSummaryVTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DestinationJPARepository extends BaseJPARepository<Destination, Long> {

    @Query("SELECT new com.fawry.destination.model.vto.DestinationSummaryVTO(" +
            "COUNT(d), " +
            "SUM(CASE WHEN d.status = 'PENDING' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN d.status = 'APPROVED'THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN d.status = 'REJECTED'THEN 1 ELSE 0 END)) " +
            "FROM Destination d")
    DestinationSummaryVTO countSummaryVTO();

    Optional<Destination> findByCountryCode(String countryCode);

    boolean existsByCountryCode(String countryCode);

    @Query("SELECT DISTINCT d.region FROM Destination d WHERE d.region IS NOT NULL ")
    List<String> findAllRegions();

}