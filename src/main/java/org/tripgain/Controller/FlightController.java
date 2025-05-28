package org.tripgain.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.tripgain.Exception.InvalidFlightNumberException;
import org.tripgain.Model.FlightInfo;
import org.tripgain.Repository.FlightInfoRepository;
import org.tripgain.Service.FlightService;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService service;

    @Autowired
    private FlightInfoRepository repository;

    @PostMapping("/fetch-and-store")
    public ResponseEntity<List<FlightInfo>> fetchAndStore() {
        return ResponseEntity.ok(service.fetchAndSaveFlights());
    }

    @GetMapping("/{flightNumber}")
    public ResponseEntity<List<FlightInfo>> getByFlightNumber(@PathVariable(required = false) String flightNumber) {
    	if (!flightNumber.matches("\\d+")) {
            throw new InvalidFlightNumberException("Flight number must contain only digits.");
        }

        List<FlightInfo> flightInfo = repository.findByFlightNumber(flightNumber);
        return new ResponseEntity<>(flightInfo, HttpStatus.OK);
    }

    @GetMapping
    public List<FlightInfo> getAllSortedByPrice() {
        return repository.findAllByOrderByTotalAmountAsc();
    }

    @GetMapping("/max-price")
    public ResponseEntity<FlightInfo> getMaxPriceFlight() {
        return repository.findTopByOrderByTotalAmountDesc()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/min-price")
    public ResponseEntity<FlightInfo> getMinPriceFlight() {
        return repository.findTopByOrderByTotalAmountAsc()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

