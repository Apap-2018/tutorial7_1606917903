package com.apap.tutorial7.controller;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.service.FlightService;
import com.apap.tutorial7.service.PilotService;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * FlightController
 * @author nasya
 */
@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable("licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber).get();
		ArrayList<FlightModel> flightList = new ArrayList<>();
		pilot.setPilotFlight(flightList);
		flightList.add(new FlightModel());
		model.addAttribute("pilot", pilot);
		model.addAttribute("title", "APAP | Add Flight");
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.POST, params={"save"})
	private String addFligthSubmit(@ModelAttribute PilotModel pilot, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(pilot.getLicenseNumber()).get();
		for(FlightModel flight : pilot.getPilotFlight()) {
			flight.setPilot(archive);
			flightService.addFlight(flight);
		}
		model.addAttribute("title", "APAP");
		return "add";
	}
	
	@RequestMapping(value="/flight/add/{licenseNumber}", params={"addRow"}, method = RequestMethod.POST)
	public String addRow(PilotModel pilot, BindingResult bindingResult, Model model) {
		if (pilot.getPilotFlight() ==  null) {
			pilot.setPilotFlight(new ArrayList<FlightModel>());
		}
		pilot.getPilotFlight().add(new FlightModel());
	    model.addAttribute("pilot", pilot);
	    return "addFlight";
	}
	
	@RequestMapping(value="/flight/add/{licenseNumber}", params={"removeRow"}, method = RequestMethod.POST)
	public String removeRow(PilotModel pilot, BindingResult bindingResult, HttpServletRequest req, Model model) {
	   Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
	   pilot.getPilotFlight().remove(rowId.intValue());
	   model.addAttribute("pilot", pilot);
	   return "addFlight";
	}
	
	@RequestMapping(value = "/flight/delete", method = RequestMethod.POST)
	private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
		for(FlightModel flight : pilot.getPilotFlight()) {
			flightService.deleteFlightById(flight.getId());
		}
		model.addAttribute("title", "APAP");
		return "delete";
	}
	
	@RequestMapping(value = "/flight/update/{id}", method = RequestMethod.GET)
	private String updateFligt(@PathVariable(value = "id") long id, Model model) {
		model.addAttribute("flight", flightService.getFlightById(id));
		model.addAttribute("title", "APAP | Update Flight");
		return "updateFlight";
	}
	
	@RequestMapping(value = "/flight/update", method = RequestMethod.POST)
	private String updateFlightSubmit(@ModelAttribute FlightModel flight, Model model) {
		FlightModel archive = flightService.getFlightById(flight.getId());
		archive.setDestination(flight.getDestination());
		archive.setFlightNumber(flight.getFlightNumber());
		archive.setOrigin(flight.getOrigin());
		archive.setTime(flight.getTime());
		flightService.addFlight(archive);
		model.addAttribute("title", "APAP");
		return "update";
	}
	
	@RequestMapping("/flight/view/{id}")
	private String viewFlightById(@PathVariable("id") long id, Model model) {
		FlightModel archive = flightService.getFlightById(id);
		model.addAttribute("flight", archive);
		model.addAttribute("title", "APAP | View Flight");
		return "view-flight";
	}
}
