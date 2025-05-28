package org.tripgain.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.tripgain.Model.FlightInfo;
import org.tripgain.Repository.FlightInfoRepository;
import org.tripgain.utility.FlightJsonParser;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class FlightService {

    @Autowired
    private FlightInfoRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public List<FlightInfo> fetchAndSaveFlights() {
        String url = "https://flightapigateway.iweensoft.com/api/flights/fake-search";

        HttpHeaders headers = new HttpHeaders();
        headers.set("gw-flightapi-key", "JAVA-ASSIGMENT20250524");
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = """
            {
              "numAdults": 1,
              "numChildren": 0,
              "numInfants": 0,
              "journeyType": "OneWay",
              "odPairs": [
                {
                  "origin": "BOM",
                  "destination": "DEL",
                  "jDate": "2025-05-28",
                  "cabinClass": "E"
                }
              ]
            }
        """;

        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            String json = response.getBody();
            System.out.println("API Response JSON: " + json);
            List<FlightInfo> flights = FlightJsonParser.parseFlightsFromJson(json);
            return repository.saveAll(flights);

        } catch (RestClientException e) {
            throw new RuntimeException("Flight API call failed", e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse flight data", e);
        }
    }
}

