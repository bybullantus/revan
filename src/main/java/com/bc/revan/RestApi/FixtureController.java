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

import com.bc.revan.Business.IEventsForFixtureService;
import com.bc.revan.Business.IFixtureMainNodeService;
import com.bc.revan.Business.IFixtureService;
import com.bc.revan.Business.ILineupForFixtureService;
import com.bc.revan.Business.IMainOfPlayersForFixtureService;
import com.bc.revan.Business.IStatisticForFixtureService;
import com.bc.revan.Entities.Fixture;
import com.bc.revan.Entities.StatisticsForFixture;
import com.bc.revan.Entities.Dto.EventsForFixtureDto;
import com.bc.revan.Entities.Dto.FixtureDto;
import com.bc.revan.Entities.Dto.FixtureMainNodeDto;
import com.bc.revan.Entities.Dto.LineupForFixtureDto;
import com.bc.revan.Entities.Dto.MainOfPlayersForFixtureDto;
import com.bc.revan.Entities.Dto.StatisticsForFixtureDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController()
@RequestMapping("api")
@Tag(name = "Fixture")
@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
public class FixtureController {
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	IFixtureService fixtureManager;
	@Autowired
	IFixtureMainNodeService fixtureMainNodeManager;
	@Autowired
	IStatisticForFixtureService statisticsForFixtureManager;
	@Autowired
	IEventsForFixtureService eventsForFixtureManager;
	@Autowired
	ILineupForFixtureService lineupForFixtureManager;
	@Autowired
	IMainOfPlayersForFixtureService mainOfPlayersForFixtureManager;

	@GetMapping("/fixture")
	public ResponseEntity<List<FixtureDto>> get() throws ContextedException, ExecutionException, InterruptedException {

		List<FixtureDto> returnValue =  modelMapper.map(fixtureManager.getAll().get(),
				new TypeToken<List<FixtureDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);
	}

	@GetMapping("/fixture/getbyId/{id}")
	public ResponseEntity<FixtureDto> getById(@PathVariable long id)
			throws ContextedException, ExecutionException, InterruptedException {

		FixtureDto returnValue =modelMapper.map( fixtureManager.getById(id).get(),
				FixtureDto.class);
		return new ResponseEntity(returnValue, HttpStatus.OK);
	}

	@GetMapping("/fixture/mainnode/getbyId/{id}")
	public ResponseEntity<FixtureMainNodeDto> getMainNodeById(@PathVariable long id)
			throws ContextedException, ExecutionException, InterruptedException {

		FixtureMainNodeDto returnValue = modelMapper.map(fixtureMainNodeManager.getById(id).get(),
				FixtureMainNodeDto.class);
		return new ResponseEntity(returnValue, HttpStatus.OK);
	}
	@GetMapping("/fixture/mainnode/getbyFixtureId/{id}")
	public ResponseEntity<FixtureMainNodeDto> getMainNodeByFixtureId(@PathVariable long id)
			throws ContextedException, ExecutionException, InterruptedException {

		FixtureMainNodeDto returnValue = modelMapper.map(fixtureMainNodeManager.getByFixtureId(id).get(),
				FixtureMainNodeDto.class);
		return new ResponseEntity(returnValue, HttpStatus.OK);
	}
	@GetMapping("/fixture/mainnode/getbyStatus/{status}")
	public ResponseEntity<List<FixtureMainNodeDto>> getMainNodeByStatus(@PathVariable String status)
			throws ContextedException, ExecutionException, InterruptedException {

		List<FixtureMainNodeDto> returnValue = modelMapper.map(fixtureMainNodeManager.getByStatus(status).get(),
				new TypeToken<List<FixtureMainNodeDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);
	}

	@GetMapping("/fixture/headtohead/{teamId1}/{teamId2}/{season}")
	public ResponseEntity<List<FixtureMainNodeDto>> headtohead(@PathVariable long teamId1, @PathVariable long teamId2,
			@PathVariable int season) throws ContextedException, ExecutionException, InterruptedException {

		List<FixtureMainNodeDto> returnValue = modelMapper.map(
				fixtureMainNodeManager.getBySeasonAndH2h(season, teamId1, teamId2).get(),
				new TypeToken<List<FixtureMainNodeDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/fixture/headtohead/{teamId1}/{teamId2}/{season}/{leagueId}")
	public ResponseEntity<List<FixtureMainNodeDto>> headtoheadWithLeague(@PathVariable long teamId1,
			@PathVariable long teamId2, @PathVariable int season, @PathVariable long leagueId)
			throws ContextedException, ExecutionException, InterruptedException {

		List<FixtureMainNodeDto> returnValue = modelMapper.map(
				fixtureMainNodeManager.getBySeasonAndLeagueIdAndH2h(season, leagueId, teamId1, teamId2).get(),
				new TypeToken<List<FixtureMainNodeDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/fixture/statistics")
	public ResponseEntity<List<StatisticsForFixtureDto>> statistics()
			throws ContextedException, ExecutionException, InterruptedException {

		List<StatisticsForFixtureDto> returnValue = modelMapper.map(statisticsForFixtureManager.getAll().get(),
				new TypeToken<List<StatisticsForFixtureDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/fixture/statistics/{fixture}")
	public ResponseEntity<List<StatisticsForFixtureDto>> statistics(@PathVariable long fixture)
			throws ContextedException, ExecutionException, InterruptedException {

		List<StatisticsForFixtureDto> returnValue = modelMapper.map(
				statisticsForFixtureManager.getByFixture(fixture).get(),
				new TypeToken<List<StatisticsForFixtureDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/fixture/statistics/{fixture}/{team}")
	public ResponseEntity<List<StatisticsForFixtureDto>> statistics(@PathVariable long fixture, @PathVariable long team)
			throws ContextedException, ExecutionException, InterruptedException {

		List<StatisticsForFixtureDto> returnValue = modelMapper.map(
				statisticsForFixtureManager.getByFixtureAndTeam(fixture, team).get(),
				new TypeToken<List<StatisticsForFixtureDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/fixture/events")
	public ResponseEntity<List<EventsForFixtureDto>> events()
			throws ContextedException, ExecutionException, InterruptedException {

		List<EventsForFixtureDto> returnValue = modelMapper.map(eventsForFixtureManager.getAll().get(),
				new TypeToken<List<EventsForFixtureDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/fixture/events/{fixture}")
	public ResponseEntity<List<EventsForFixtureDto>> events(@PathVariable long fixture)
			throws ContextedException, ExecutionException, InterruptedException {

		List<EventsForFixtureDto> returnValue = modelMapper.map(eventsForFixtureManager.getByFixture(fixture).get(),
				new TypeToken<List<EventsForFixtureDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/fixture/lineups")
	public ResponseEntity<List<LineupForFixtureDto>> lineups()
			throws ContextedException, ExecutionException, InterruptedException {

		List<LineupForFixtureDto> returnValue = modelMapper.map(lineupForFixtureManager.getAll().get(),
				new TypeToken<List<LineupForFixtureDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/fixture/lineups/{fixture}")
	public ResponseEntity<List<LineupForFixtureDto>> lineups(@PathVariable long fixture)
			throws ContextedException, ExecutionException, InterruptedException {

		List<LineupForFixtureDto> returnValue = modelMapper.map(lineupForFixtureManager.getByFixture(fixture).get(),
				new TypeToken<List<LineupForFixtureDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/fixture/players")
	public ResponseEntity<List<MainOfPlayersForFixtureDto>> players()
			throws ContextedException, ExecutionException, InterruptedException {

		List<MainOfPlayersForFixtureDto> returnValue = modelMapper.map(mainOfPlayersForFixtureManager.getAll().get(),
				new TypeToken<List<MainOfPlayersForFixtureDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/fixture/players/{fixture}")
	public ResponseEntity<List<MainOfPlayersForFixtureDto>> players(@PathVariable long fixture)
			throws ContextedException, ExecutionException, InterruptedException {

		List<MainOfPlayersForFixtureDto> returnValue = modelMapper.map(
				mainOfPlayersForFixtureManager.getByFixture(fixture).get(),
				new TypeToken<List<MainOfPlayersForFixtureDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}
	
}
