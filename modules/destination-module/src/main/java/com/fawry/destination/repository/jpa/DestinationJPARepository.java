package com.fawry.destination.repository.jpa;

import com.fawry.common.repository.jpa.BaseJPARepository;
import com.fawry.destination.model.entity.Destination;
import com.fawry.destination.model.vto.DestinationSummaryVTO;
import com.fawry.destination.model.vto.DestinationVTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface DestinationJPARepository extends BaseJPARepository<Destination, Long> {

    @Query("SELECT new com.fawry.destination.model.vto.DestinationSummaryVTO(" +
            "COUNT(d), " +
            "SUM(CASE WHEN d.status = 'PENDING' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN d.status = 'APPROVED'THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN d.status = 'REJECTED'THEN 1 ELSE 0 END)) " +
            "FROM Destination d")
    DestinationSummaryVTO countSummaryVTO();

    @Query("SELECT d.id, CASE WHEN w.id IS NOT NULL THEN true ELSE false END " +
            "FROM Destination d " +
            "LEFT JOIN Wishlist w ON w.destination.id = d.id AND w.user.id = :userId " +
            "WHERE d.id IN :ids")
    List<Object[]> findWishlistStatusByIds(@Param("ids") List<Long> ids, @Param("userId") Long userId);

    default Map<Long, Boolean> findWishlistStatusMapByIds(List<Long> ids, Long userId) {
        return findWishlistStatusByIds(ids, userId).stream()
                .collect(Collectors.toMap(
                        arr -> (Long) arr[0],
                        arr -> (Boolean) arr[1]
                ));
    }

    Optional<Destination> findByCountryCode(String countryCode);

    boolean existsByCountryCode(String countryCode);

    @Query("SELECT DISTINCT d.region FROM Destination d WHERE d.region IS NOT NULL ")
    List<String> findAllRegions();

}