package com.apap.tutorial7.service;

import java.util.Optional;

import com.apap.tutorial7.model.PilotModel;

/**
 * 
 * PilotService
 * @author nasya
 *
 */
public interface PilotService {
	Optional<PilotModel> getPilotDetailByLicenseNumber (String licenseNumber);
	PilotModel addPilot (PilotModel pilot);
	void deletePilot(PilotModel pilot);
	Optional<PilotModel> getPilotDetailById(long pilotId);
	void updatePilot(long pilotId, PilotModel pilot);
}