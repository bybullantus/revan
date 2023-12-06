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

import com.bc.revan.Business.ILeagueService;
import com.bc.revan.Entities.League;
import com.bc.revan.Entities.Dto.LeagueDto;
import com.bc.revan.Entities.Dto.LeagueForGetAllDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController()
@RequestMapping("api")
@Tag(name = "League")
@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
public class LeagueController {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ILeagueService leagueManager;

	@GetMapping("/league")
	public ResponseEntity<List<LeagueForGetAllDto>> getAll() throws InterruptedException, ExecutionException {
		List<LeagueForGetAllDto> returnValue = modelMapper.map(leagueManager.getAll().get(),
				new TypeToken<List<LeagueForGetAllDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/league/getbyId/{id}")
	public ResponseEntity<LeagueDto> getbyId(@PathVariable long id) throws InterruptedException, ExecutionException {

		LeagueDto league = modelMapper.map(leagueManager.getById(id).get(), LeagueDto.class);
		return new ResponseEntity(league, HttpStatus.OK);

	}

	@GetMapping("/league/getbyName/{name}")
	public ResponseEntity<List<LeagueDto>> getbyName(@PathVariable String name)
			throws InterruptedException, ExecutionException {

		List<LeagueDto> returnValue = new ArrayList<LeagueDto>();

		for (League league : leagueManager.getByName(name).get())
			returnValue.add(modelMapper.map(league, LeagueDto.class));

		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

}
