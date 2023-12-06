package com.bc.revan.RestApi;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.exception.ContextedException;
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

import com.bc.revan.Business.ICoachService;
import com.bc.revan.Entities.Dto.CareerOfCoachDto;
import com.bc.revan.Entities.Dto.CoachsDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController()
@RequestMapping("api")
@Tag(name = "Coach")
@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
public class CoachController {
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ICoachService coachManager;

	@GetMapping("/coachs")
	public ResponseEntity<List<CoachsDto>> get() throws ContextedException, ExecutionException, InterruptedException {

		List<CoachsDto> returnValue = modelMapper.map(coachManager.getAll().get(), new TypeToken<List<CoachsDto>>() {
		}.getType());

		return new ResponseEntity(returnValue, HttpStatus.OK);
	}

	@GetMapping("/coachs/getCareer/{coach}")
	public ResponseEntity<List<CareerOfCoachDto>> getCareer(@PathVariable long coach)
			throws ContextedException, ExecutionException, InterruptedException {

		List<CareerOfCoachDto> returnValue = modelMapper.map(coachManager.getById(coach).get().getCareers(),
				new TypeToken<List<CareerOfCoachDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);
	}

	@GetMapping("/coachs/{coach}")
	public ResponseEntity<CoachsDto> get(@PathVariable long coach)
			throws ContextedException, ExecutionException, InterruptedException {

		CoachsDto returnValue = modelMapper.map(coachManager.getById(coach).get(), CoachsDto.class);
		return new ResponseEntity(returnValue, HttpStatus.OK);
	}

	@GetMapping("/coachs/getByTeam/{team}")
	public ResponseEntity<CoachsDto> getByTeam(@PathVariable long team)
			throws ContextedException, ExecutionException, InterruptedException {

		List<CoachsDto> returnValue = modelMapper.map(coachManager.getByTeam(team).get(),
				new TypeToken<List<CoachsDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);
	}
}
