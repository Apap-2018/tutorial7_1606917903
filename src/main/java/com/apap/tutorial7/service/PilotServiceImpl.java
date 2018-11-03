package com.apap.tutorial7.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.repository.PilotDB;

/**
 * PilotServiceImpl
 * @author nasya
 *
 */
@Service
@Transactional
public class PilotServiceImpl implements PilotService{
	@Autowired
	private PilotDB pilotDB;

	@Override
	public Optional<PilotModel> getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDB.findByLicenseNumber(licenseNumber);
	}

	@Override
	public PilotModel addPilot(PilotModel pilot) {
		return pilotDB.save(pilot);
	}

	@Override
	public Optional<PilotModel> getPilotDetailById(long pilotId) {
		return pilotDB.findById(pilotId);
	}

	@Override
	public void deletePilot(PilotModel pilot) {
		pilotDB.delete(pilot);
	}

	@Override
	public void updatePilot(long pilotId, PilotModel pilot) {
		pilotDB.save(pilot);
	}
	
}