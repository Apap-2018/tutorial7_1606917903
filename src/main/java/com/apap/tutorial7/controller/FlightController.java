package com.apap.tutorial7.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.service.FlightService;

/**
 * FlightController
 */
@RestController
@RequestMapping("/flight")
public class FlightController {
	@Autowired 
	FlightService flightService;
	
	/**
	 * POST add flight
	 */
	@PostMapping(value = "/add")
	public FlightModel addFlightSubmit(@RequestBody FlightModel flight) {
		return flightService.addFlight(flight);
	}
	
	/**
	 * PUT update flight
	 */
	@PutMapping(value = "/update/{flightId}")
	public String updateFlightSubmit(@PathVariable("flightId") long flightId,
									 @RequestParam(value = "destination", required=false) String destination,
									 @RequestParam(value = "origin", required=false) String origin,
									 @RequestParam(value = "time", required=false) Date time) {
		FlightModel flight = flightService.getFlightById(flightId).get();
		if (destination != null) {
			flight.setDestination(destination);
		}
		if (origin != null) {
			flight.setOrigin(origin);
		}
		if (time != null) {
			flight.setTime(time);
		}
		flightService.updateFlight(flight);
		return "flight update success";
	}
	
	/**
	 * GET flight
	 */
	@GetMapping(value = "/view/{flightNumber}")
	public Optional<FlightModel> viewFlight(@PathVariable("flightNumber") String flightNumber) {
		return flightService.getFlightDetailByFlightNumber(flightNumber);
	}
	
	/**
	 * GET all flight 
	 */
	@GetMapping(value = "/all")
	public List<FlightModel> viewAllFlight(){
		return flightService.getAllFlight();
	}
	
	/**
	 * Delete flight 
	 */
	@DeleteMapping(value = "/{flightId}")
	public String deleteFlight(@PathVariable("flightId") long flightId) {
		FlightModel flight = flightService.getFlightById(flightId).get();
		flightService.deleteFlight(flight);
		return "flight has been deleted";
	}
}