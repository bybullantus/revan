package com.bc.revan.Entegration.Football;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bc.revan.Cache.Redis.ILiveMatchesCache;
import com.bc.revan.DataAccess.IFixtureMainNodeDal;
import com.bc.revan.DataAccess.ITeamDal;
import com.bc.revan.DataAccess.IWeeklyMatchDal;
import com.bc.revan.Entegration.ILiveMatchesScheduledService;
import com.bc.revan.Entities.FixtureMainNode;
import com.bc.revan.Entities.WeeklyMatch;
import com.bc.revan.Entities.Base.BaseResponse;
import com.bc.revan.Entities.Dto.FixtureMainNodeDto;
import com.bc.revan.Entities.Dto.LiveMatchesDto;
import com.bc.revan.Entities.Dto.OddsForLiveMatchesDto;
import com.bc.revan.Entities.Dto.ValuesForLiveMatchesDto;
import com.bc.revan.Entities.Enums.EnumForStatus;

import jakarta.transaction.Transactional;

@Service
@Component
public class LiveMatchesScheduledService extends BaseRequest implements ILiveMatchesScheduledService {

	@Autowired
	ILiveMatchesCache liveMatchesCache;

	@Autowired
	IWeeklyMatchDal weeklyMatchDal;

	@Autowired
	ITeamDal teamDal;
	
	@Autowired
	IFixtureMainNodeDal fixtureMainNodeDal;
	
	@Autowired
	ModelMapper modelMapper;

	public LiveMatchesScheduledService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Transactional
	@Override
	public BaseResponse getLiveMatches() throws InterruptedException, ExecutionException {

		
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.add("x-rapidapi-host", user);
		headers.add("x-rapidapi-key", password);

		ResponseEntity<BaseResponse<LiveMatchesDto>> exchange = null;

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		String url = apiUrl + "odds/live";
		exchange = restTemplate.exchange(url, HttpMethod.GET, entity,
				new ParameterizedTypeReference<BaseResponse<LiveMatchesDto>>() {
				});

		System.out.println(exchange.getBody().getResponseList());
		liveMatchesCache.deleteAll();

		List<WeeklyMatch> weeklyMacths = weeklyMatchDal.getAll();
		
		
		boolean dummyFlag=false;
		for (LiveMatchesDto responseItem : exchange.getBody().getResponseList()) {

			for (WeeklyMatch weeklyMatch : weeklyMacths) {
				if (responseItem.getFixture().getId() == weeklyMatch.getFixtureMainNode().getFixture().getId()) {				
					dummyFlag=true;
					responseItem.setId(responseItem.getFixture().getId());
					responseItem.setFixtureMainNode(modelMapper.map(fixtureMainNodeDal.getByFixture(responseItem.getFixture().getId()),FixtureMainNodeDto.class));
					
				
					responseItem.getTeams().getHome()
							.setName(weeklyMatch.getFixtureMainNode().getTeamsMainNode().getTeamHome().getName());
				
					responseItem.getTeams().getAway()
							.setName(weeklyMatch.getFixtureMainNode().getTeamsMainNode().getTeamAway().getName());

					for (OddsForLiveMatchesDto oddsItems : responseItem.getOdds()) {
						if (oddsItems.getId() == 58) {
							for (ValuesForLiveMatchesDto valuesItem : oddsItems.getValues()) {
								if (valuesItem.getHandicap().equals("1.5")) {
									responseItem.setOdd(Double.parseDouble(valuesItem.getOdd()));

									if (responseItem.getOdd() > 1.3) {
										responseItem.setFire(true);
										break;
									}
								}
							}
						}

					}

					System.out.println(responseItem);
					liveMatchesCache.add(responseItem); 
					break;
				}
			}
			
		}
		
		if (!dummyFlag) {
			
			LiveMatchesDto dummyLive=exchange.getBody().getResponseList().get(0);
			
			dummyLive.setId(exchange.getBody().getResponseList().get(0).getFixture().getId());
			dummyLive.setFixtureMainNode(modelMapper.map(fixtureMainNodeDal.getByFixture(weeklyMacths.get(0).getFixtureMainNode().getFixture().getId()),FixtureMainNodeDto.class));
			
			
			dummyLive.getTeams().getHome()
					.setName(weeklyMacths.get(0).getFixtureMainNode().getTeamsMainNode().getTeamHome().getName());
		
			dummyLive.getTeams().getAway()
					.setName(weeklyMacths.get(0).getFixtureMainNode().getTeamsMainNode().getTeamAway().getName());

			for (OddsForLiveMatchesDto oddsItems : dummyLive.getOdds()) {
				if (oddsItems.getId() == 58) {
					for (ValuesForLiveMatchesDto valuesItem : oddsItems.getValues()) {
						if (valuesItem.getHandicap().equals("1.5")) {
							dummyLive.setOdd(Double.parseDouble(valuesItem.getOdd()));

							if (dummyLive.getOdd() > 1.3) {
								dummyLive.setFire(true);
								break;
							}
						}
					}
				}

			}

			System.out.println(dummyLive);
			liveMatchesCache.add(dummyLive); 
		}
		return null;
	}

}
