package org.tripgain.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "flight_info")
@Data
public class FlightInfo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gw_flight_key")
    private String gwFlightKey;

    private String carrier;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "fare_type")
    private String fareType;

    @Column(name = "total_amount")
    private Double totalAmount;
}
