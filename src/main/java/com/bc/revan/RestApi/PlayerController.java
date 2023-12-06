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

import com.bc.revan.Business.IPlayerService;
import com.bc.revan.Business.ISquadService;
import com.bc.revan.Business.Impl.PlayerManager;
import com.bc.revan.Entities.Dto.PlayerDto;
import com.bc.revan.Entities.Dto.PlayerWithDetailsDto;
import com.bc.revan.Entities.Dto.SquadDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController()
@RequestMapping("api")
@Tag(name = "Player")
@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
public class PlayerController {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ISquadService squadManager;
	
	@Autowired
	IPlayerService playerManager;

	@GetMapping("/players/squads")
	public ResponseEntity<List<SquadDto>> getSquads()
			throws ContextedException, ExecutionException, InterruptedException {

		List<SquadDto> returnValue = modelMapper.map(squadManager.getAll().get(), new TypeToken<List<SquadDto>>() {
		}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);
	}

	@GetMapping("/players/squads/{team}")
	public ResponseEntity<SquadDto> getSquads(@PathVariable long team)
			throws ContextedException, ExecutionException, InterruptedException {

		SquadDto returnValue = modelMapper.map(squadManager.getByTeamId(team).get(), new TypeToken<SquadDto>() {
		}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);
	}
	
	@GetMapping("/players/{id}")
	public ResponseEntity<PlayerWithDetailsDto> getByPlayerId(@PathVariable long id)
			throws ContextedException, ExecutionException, InterruptedException {

		PlayerWithDetailsDto returnValue = modelMapper.map(playerManager.getById(id).get(), new TypeToken<PlayerWithDetailsDto>() {
		}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);
		
	}
	@GetMapping("/players/{id}/{season}")
	public ResponseEntity<PlayerWithDetailsDto> getByIdAndSeason(@PathVariable long id,@PathVariable int season)
			throws ContextedException, ExecutionException, InterruptedException {

		PlayerWithDetailsDto returnValue = modelMapper.map(playerManager.getByIdAndSeason(id,season).get(), new TypeToken<PlayerWithDetailsDto>() {
		}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);
		
	}
	@GetMapping("/players/{id}/{season}/{league}")
	public ResponseEntity<PlayerWithDetailsDto> getByIdAndSeason(@PathVariable long id,@PathVariable int season,@PathVariable long league)
			throws ContextedException, ExecutionException, InterruptedException {

		PlayerWithDetailsDto returnValue = modelMapper.map(playerManager.getByIdAndSeasonAndLeague(id,season,league).get(), new TypeToken<PlayerWithDetailsDto>() {
		}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);
		
	}
}
