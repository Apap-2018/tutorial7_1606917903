package com.apap.tutorial7.service;


import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.FlightModel;

/**
 * 
 * FlightService
 * @author nasya
 *
 */
public interface FlightService {
	FlightModel getFlightDetailById (Long id);
	Optional<FlightModel> getFlightDetailByFlightNumber (String flightNumber);
	FlightModel addFlight(FlightModel flight);
	void deleteFlightById(Long id);
	List<FlightModel> getFlightList();
	Optional<FlightModel> getFlightById(long flightId);
	void updateFlight(FlightModel flight);
	List<FlightModel> getAllFlight();
	void deleteFlight(FlightModel flight);
}