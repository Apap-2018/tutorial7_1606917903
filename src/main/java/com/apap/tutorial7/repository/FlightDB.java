package com.apap.tutorial7.repository;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * FlightDB
 */
@Repository
public interface FlightDB extends JpaRepository<FlightModel, Long> {
	FlightModel findById(long id);
}
