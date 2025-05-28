package org.tripgain.utility;

import java.util.ArrayList;
import java.util.List;

import org.tripgain.Model.FlightInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FlightJsonParser {
	public static List<FlightInfo> parseFlightsFromJson(String json) throws JsonProcessingException {
	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode root = mapper.readTree(json);
	    List<FlightInfo> flights = new ArrayList<>();

	    JsonNode journeys = root.path("flightJourneys");
	    if (journeys.isArray()) {
	        for (JsonNode journey : journeys) {
	            JsonNode options = journey.path("flightOptions");
	            for (JsonNode option : options) {
	                JsonNode fareNode = option.path("flightFare");
	                double totalAmount = fareNode.path("totalAmount").asDouble(0.0);
	                JsonNode recommendedFlights = option.path("recommendedFlight");
	                for (JsonNode flightNode : recommendedFlights) {
	                    FlightInfo flight = new FlightInfo();
	                    flight.setGwFlightKey(flightNode.path("gwFlightKey").asText());
	                    flight.setFareType(flightNode.path("fareType").asText());
	                    JsonNode leg = flightNode.path("flightLegs").get(0);
	                    if (leg != null) {
	                        flight.setCarrier(leg.path("carrier").asText());
	                        flight.setFlightNumber(leg.path("flightNumber").asText());
	                    }
	                    flight.setTotalAmount(totalAmount);
	                    flights.add(flight);
	                }
	            }
	        }
	    }
	    return flights;
	}
}
