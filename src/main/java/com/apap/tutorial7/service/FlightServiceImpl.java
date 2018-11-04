package com.apap.tutorial7.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.repository.FlightDB;

/**
 * FlightServiceImp
 * @author nasya
 *
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService{
	@Autowired
	private FlightDB flightDB;
	
	@Override
	public FlightModel addFlight(FlightModel flight) {
		return flightDB.save(flight);
	}

	@Override
	public void deleteFlightById(Long id) {
		flightDB.deleteById(id);
	}
	
	@Override
	public FlightModel getFlightDetailById (Long id){
		return flightDB.getOne(id);
	}

	@Override
	public List<FlightModel> getFlightList() {
		return flightDB.findAll();
	}

	@Override
	public Optional<FlightModel> getFlightDetailByFlightNumber(String flightNumber) {
		Optional<FlightModel> flight = flightDB.findByFlightNumber(flightNumber);
		return flight;
	}

	@Override
	public Optional<FlightModel> getFlightById(long flightId) {
		return flightDB.findById(flightId);
	}

	@Override
	public void updateFlight(FlightModel flight) {
		flightDB.save(flight);
		
	}

	@Override
	public List<FlightModel> getAllFlight() {
		return flightDB.findAll();
	}

	@Override
	public void deleteFlight(FlightModel flight) {
		flightDB.delete(flight);
	}
	
}