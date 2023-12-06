package com.bc.revan.RestApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bc.revan.Business.IVenueService;
import com.bc.revan.Entities.Venue;
import com.bc.revan.Entities.Dto.VenueDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@RequestMapping("api")
@Tag(name = "Venue")
@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
public class VenueController {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	IVenueService venueManager;

	@GetMapping("/venue")
	public ResponseEntity<List<VenueDto>> getAll() throws InterruptedException, ExecutionException {
		List<VenueDto> returnValue = modelMapper.map(venueManager.getAll().get(), new TypeToken<List<VenueDto>>() {
		}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/venue/getbyId/{id}")
	public ResponseEntity<VenueDto> getbyId(@PathVariable long id) throws InterruptedException, ExecutionException {

		VenueDto venue = modelMapper.map(venueManager.getById(id).get(), VenueDto.class);
		return new ResponseEntity(venue, HttpStatus.OK);

	}

	@GetMapping("/venue/getbyName/{name}")
	public ResponseEntity<List<VenueDto>> getbyName(@PathVariable String name)
			throws InterruptedException, ExecutionException {

		List<VenueDto> returnValue = new ArrayList<VenueDto>();

		for (Venue venue : venueManager.getByName(name).get())
			returnValue.add(modelMapper.map(venue, VenueDto.class));

		return new ResponseEntity(returnValue, HttpStatus.OK);

	}
}
