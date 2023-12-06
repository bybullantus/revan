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

import com.bc.revan.Business.IStatisticForTeamService;
import com.bc.revan.Business.ITeamService;
import com.bc.revan.Entities.Team;
import com.bc.revan.Entities.Dto.BiggestOfStatisticsDto;
import com.bc.revan.Entities.Dto.CardsOfStatisticsDto;
import com.bc.revan.Entities.Dto.CleanSheetOfStatisticsDto;
import com.bc.revan.Entities.Dto.FailedToScoreOfStatisticsDto;
import com.bc.revan.Entities.Dto.FixtureOfStatisticsDto;
import com.bc.revan.Entities.Dto.GoalsOfStatisticsDto;
import com.bc.revan.Entities.Dto.LineupOfStatisticsDto;
import com.bc.revan.Entities.Dto.PenaltyOfStatisticsDto;
import com.bc.revan.Entities.Dto.StatisticForTeamDto;
import com.bc.revan.Entities.Dto.TeamDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController()
@RequestMapping("api")
@Tag(name = "Team")
@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
public class TeamController {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ITeamService teamManager;

	@Autowired
	IStatisticForTeamService statisticForTeamManager;

	@GetMapping("/team")
	public ResponseEntity<List<TeamDto>> getAll() throws InterruptedException, ExecutionException {
		List<TeamDto> returnValue = modelMapper.map(teamManager.getAll().get(), new TypeToken<List<TeamDto>>() {
		}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/team/getbyId/{id}")
	public ResponseEntity<TeamDto> getbyId(@PathVariable long id) throws InterruptedException, ExecutionException {

		TeamDto team = modelMapper.map(teamManager.getById(id).get(), TeamDto.class);
		return new ResponseEntity(team, HttpStatus.OK);

	}

	@GetMapping("/team/getbyName/{name}")
	public ResponseEntity<List<TeamDto>> getbyName(@PathVariable String name)
			throws InterruptedException, ExecutionException {

		List<TeamDto> returnValue = new ArrayList<TeamDto>();

		for (Team team : teamManager.getByName(name).get())
			returnValue.add(modelMapper.map(team, TeamDto.class));

		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	// region Statistics

	@GetMapping("/team/statistics/")
	public ResponseEntity<List<StatisticForTeamDto>> getAllForStatistics()
			throws InterruptedException, ExecutionException {
		List<StatisticForTeamDto> returnValue = modelMapper.map(statisticForTeamManager.getAll().get(),
				new TypeToken<List<StatisticForTeamDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/team/statistics/{year}/{leagueId}/{teamId}")
	public ResponseEntity<StatisticForTeamDto> getstatisticsBySeasonAndLeagueIdAndTeamId(@PathVariable int year,
			@PathVariable int leagueId, @PathVariable int teamId) throws InterruptedException, ExecutionException {
		StatisticForTeamDto returnValue = modelMapper.map(
				statisticForTeamManager.getBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId).get(),
				StatisticForTeamDto.class);
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/team/statistics/fixture/{year}/{leagueId}/{teamId}")
	public ResponseEntity<FixtureOfStatisticsDto> getFixtureOfStatisticsBySeasonAndLeagueIdAndTeamId(
			@PathVariable int year, @PathVariable int leagueId, @PathVariable int teamId)
			throws InterruptedException, ExecutionException {
		FixtureOfStatisticsDto returnValue = modelMapper.map(statisticForTeamManager
				.getFixtureOfStatisticsBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId).get(),
				FixtureOfStatisticsDto.class);
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/team/statistics/biggest/{year}/{leagueId}/{teamId}")
	public ResponseEntity<BiggestOfStatisticsDto> getBiggestOfStatisticsBySeasonAndLeagueIdAndTeamId(
			@PathVariable int year, @PathVariable int leagueId, @PathVariable int teamId)
			throws InterruptedException, ExecutionException {
		BiggestOfStatisticsDto returnValue = modelMapper.map(statisticForTeamManager
				.getBiggestOfStatisticsBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId).get(),
				BiggestOfStatisticsDto.class);
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/team/statistics/goals/{year}/{leagueId}/{teamId}")
	public ResponseEntity<GoalsOfStatisticsDto> getGoalsOfStatisticsBySeasonAndLeagueIdAndTeamId(@PathVariable int year,
			@PathVariable int leagueId, @PathVariable int teamId) throws InterruptedException, ExecutionException {
		GoalsOfStatisticsDto returnValue = modelMapper.map(
				statisticForTeamManager.getGoalsOfStatisticsBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId).get(),
				GoalsOfStatisticsDto.class);
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/team/statistics/form/{year}/{leagueId}/{teamId}")
	public ResponseEntity<String> getFormOfStatisticsBySeasonAndLeagueIdAndTeamId(@PathVariable int year,
			@PathVariable int leagueId, @PathVariable int teamId) throws InterruptedException, ExecutionException {
		String returnValue = modelMapper.map(
				statisticForTeamManager.getFormOfStatisticsBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId).get(),
				String.class);
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/team/statistics/cleansheet/{year}/{leagueId}/{teamId}")
	public ResponseEntity<CleanSheetOfStatisticsDto> getCleanSheetOfStatisticsBySeasonAndLeagueIdAndTeamId(
			@PathVariable int year, @PathVariable int leagueId, @PathVariable int teamId)
			throws InterruptedException, ExecutionException {
		CleanSheetOfStatisticsDto returnValue = modelMapper.map(statisticForTeamManager
				.getCleanSheetOfStatisticsBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId).get(),
				CleanSheetOfStatisticsDto.class);
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/team/statistics/failedtoscore/{year}/{leagueId}/{teamId}")
	public ResponseEntity<FailedToScoreOfStatisticsDto> getFailedToScoreOfStatisticsBySeasonAndLeagueIdAndTeamId(
			@PathVariable int year, @PathVariable int leagueId, @PathVariable int teamId)
			throws InterruptedException, ExecutionException {
		FailedToScoreOfStatisticsDto returnValue = modelMapper.map(statisticForTeamManager
				.getFailedToScoreOfStatisticsBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId).get(),
				FailedToScoreOfStatisticsDto.class);
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/team/statistics/penalty/{year}/{leagueId}/{teamId}")
	public ResponseEntity<PenaltyOfStatisticsDto> getPenaltyOfStatisticsBySeasonAndLeagueIdAndTeamId(
			@PathVariable int year, @PathVariable int leagueId, @PathVariable int teamId)
			throws InterruptedException, ExecutionException {
		PenaltyOfStatisticsDto returnValue = modelMapper.map(statisticForTeamManager
				.getPenaltyOfStatisticsBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId).get(),
				PenaltyOfStatisticsDto.class);
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/team/statistics/lineup/{year}/{leagueId}/{teamId}")
	public ResponseEntity<List<LineupOfStatisticsDto>> getLineupOfStatisticsBySeasonAndLeagueIdAndTeamId(
			@PathVariable int year, @PathVariable int leagueId, @PathVariable int teamId)
			throws InterruptedException, ExecutionException {
		List<LineupOfStatisticsDto> returnValue = modelMapper.map(
				statisticForTeamManager.getLineupOfStatisticsBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId).get(),
				new TypeToken<List<LineupOfStatisticsDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

	@GetMapping("/team/statistics/cards/{year}/{leagueId}/{teamId}")
	public ResponseEntity<CardsOfStatisticsDto> getCardsOfStatisticsBySeasonAndLeagueIdAndTeamId(@PathVariable int year,
			@PathVariable int leagueId, @PathVariable int teamId) throws InterruptedException, ExecutionException {
		CardsOfStatisticsDto returnValue = modelMapper.map(
				statisticForTeamManager.getCardsOfStatisticsBySeasonAndLeagueIdAndTeamId(year, leagueId, teamId).get(),
				CardsOfStatisticsDto.class);
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}
	// endregion
}
