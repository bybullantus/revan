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

import com.bc.revan.Business.IWeeklyMatchService;
import com.bc.revan.DataAccess.IFakeMatchesDal;
import com.bc.revan.DataAccess.IWeeklyMatchDal;
import com.bc.revan.Entities.Fake;
import com.bc.revan.Entities.FakeMatches;
import com.bc.revan.Entities.WeeklyMatch;
import com.bc.revan.Entities.Dto.StatisticsForFixtureDto;
import com.bc.revan.Entities.Dto.WeeklyMatchDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController()
@RequestMapping("api")
@Tag(name = "Fake")
//@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
public class FakeController {
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	IFakeMatchesDal fakeMatchesDal;
	@Autowired
	IWeeklyMatchService weeklyMatchManager;
	
	@GetMapping("/fakeMatches/{hafta}")
	public ResponseEntity<Fake> getByHafta(@PathVariable int hafta)
			throws ContextedException, ExecutionException, InterruptedException {

		List<FakeMatches> matches=fakeMatchesDal.getByHafta(hafta);
		
		int counter=0;
		for (FakeMatches match: matches) {
		System.out.println(match.getStatu());	
			if (match.getStatu()!=null && match.getStatu()==true) 
				counter++;
			
		}
		Fake fake=Fake.builder().oran(matches.size()+"/"+counter)
				.maclar(matches).build();
		
		return new ResponseEntity(fake, HttpStatus.OK);
	}
	@GetMapping("/weeklyMatches/")
	public ResponseEntity<List<WeeklyMatchDto>> getByWeeklyMatches()
			throws ContextedException, ExecutionException, InterruptedException {
		
		List<WeeklyMatchDto> matches=modelMapper.map(weeklyMatchManager.getAll().get(),new TypeToken<List<WeeklyMatchDto>>() {
		}.getType());;		
		
		return new ResponseEntity(matches, HttpStatus.OK);
	}
	@GetMapping("/weeklyMatches/getYears")
	public List<Integer> getYears()
			throws ContextedException, ExecutionException, InterruptedException {
		
		return weeklyMatchManager.groupByYear().get();
	}
	@GetMapping("/weeklyMatches/getRounds")
	public List<String> getRounds()
			throws ContextedException, ExecutionException, InterruptedException {
		
		return weeklyMatchManager.groupByRound().get();
	}
	@GetMapping("/weeklyMatches/getWeekNumbers")
	public List<Long> getWeekNumbers()
			throws ContextedException, ExecutionException, InterruptedException {
		
		return weeklyMatchManager.groupByWeekNumber().get();
	}
	@GetMapping("/weeklyMatches/getBySeasonAndWeekNumber/{year}/{week}")
	public ResponseEntity<List<WeeklyMatchDto>> getBySeasonAndWeekNumber(@PathVariable int year,@PathVariable long week)
			throws ContextedException, ExecutionException, InterruptedException {
		
		List<WeeklyMatchDto> matches=modelMapper.map(weeklyMatchManager.getBySeasonAndWeek(year, week).get(),new TypeToken<List<WeeklyMatchDto>>() {
		}.getType());;		
		
		return new ResponseEntity(matches, HttpStatus.OK);
	}

}
