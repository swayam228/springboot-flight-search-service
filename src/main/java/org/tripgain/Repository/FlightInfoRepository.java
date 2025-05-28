package org.tripgain.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tripgain.Model.FlightInfo;

public interface FlightInfoRepository extends JpaRepository<FlightInfo, Long> {
    List<FlightInfo> findByFlightNumber(String flightNumber);
    
    List<FlightInfo> findAllByOrderByTotalAmountAsc();

    Optional<FlightInfo> findTopByOrderByTotalAmountDesc();

    Optional<FlightInfo> findTopByOrderByTotalAmountAsc();
}

