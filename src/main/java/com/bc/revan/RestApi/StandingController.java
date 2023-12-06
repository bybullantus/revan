package com.bc.revan.RestApi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

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

import com.bc.revan.Business.IStandingMainNodeService;
import com.bc.revan.Entities.Dto.StandingDetailDto;
import com.bc.revan.Entities.Dto.StandingMainNodeDto;
import com.bc.revan.Entities.Dto.StatisticForTeamDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController()
@RequestMapping("api")
@Tag(name = "Standing")
@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
public class StandingController {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	IStandingMainNodeService standingMainNodeManager;

	@GetMapping("/standing")
	public ResponseEntity<List<StandingMainNodeDto>> getAll() throws InterruptedException, ExecutionException {
		List<StandingMainNodeDto> returnValue = modelMapper.map(standingMainNodeManager.getAll().get(),
				new TypeToken<List<StandingMainNodeDto>>() {
				}.getType());

		List<StandingMainNodeDto> sortedReturnValue = new ArrayList<StandingMainNodeDto>();
		returnValue.forEach(x -> {
			x.getStanding().forEach(y -> {
				List<StandingDetailDto> sortedList = y.getStandingDetail().stream()
						.sorted(Comparator.comparingInt(StandingDetailDto::getRank)).collect(Collectors.toList());
				y.setStandingDetail(sortedList);
			});
			StandingMainNodeDto item = x;
			sortedReturnValue.add(item);
		}

		);

		return new ResponseEntity(sortedReturnValue, HttpStatus.OK);
	}

	@GetMapping("/standing/getbyId/{id}")
	public ResponseEntity<StandingMainNodeDto> getbyId(@PathVariable long id)
			throws InterruptedException, ExecutionException {

		StandingMainNodeDto standing = modelMapper.map(standingMainNodeManager.getById(id).get(),
				StandingMainNodeDto.class);

		StandingMainNodeDto sortedReturnValue = new StandingMainNodeDto();
		standing.getStanding().forEach(x -> {
			List<StandingDetailDto> sortedList = x.getStandingDetail().stream()
					.sorted(Comparator.comparingInt(StandingDetailDto::getRank)).collect(Collectors.toList());
			x.setStandingDetail(sortedList);

		});
		StandingMainNodeDto item = standing;
		sortedReturnValue = standing;

		return new ResponseEntity(standing, HttpStatus.OK);

	}

	@GetMapping("/standing/{year}/{leagueId}/")
	public ResponseEntity<StatisticForTeamDto> getstatisticsBySeasonAndLeagueIdAndTeamId(@PathVariable int year,
			@PathVariable int leagueId) throws InterruptedException, ExecutionException {

		StandingMainNodeDto standing = modelMapper
				.map(standingMainNodeManager.getBySeasonAndLeagueId(year, leagueId).get(), StandingMainNodeDto.class);

		StandingMainNodeDto sortedReturnValue = new StandingMainNodeDto();
		standing.getStanding().forEach(x -> {
			List<StandingDetailDto> sortedList = x.getStandingDetail().stream()
					.sorted(Comparator.comparingInt(StandingDetailDto::getRank)).collect(Collectors.toList());
			x.setStandingDetail(sortedList);

		});
		StandingMainNodeDto item = standing;
		sortedReturnValue = standing;

		return new ResponseEntity(standing, HttpStatus.OK);

	}

}
