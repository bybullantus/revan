package com.bc.revan.Entegration.Football;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bc.revan.DataAccess.IAllHomeAwayOfStandingDetailDal;
import com.bc.revan.DataAccess.IGoalsOfAllHomeAwayDal;
import com.bc.revan.DataAccess.ILeagueDal;
import com.bc.revan.DataAccess.ISeasonDal;
import com.bc.revan.DataAccess.IStandingDal;
import com.bc.revan.DataAccess.IStandingDetailDal;
import com.bc.revan.DataAccess.IStandingMainNodeDal;
import com.bc.revan.DataAccess.ITeamDal;
import com.bc.revan.Entegration.IStandingScheduledService;
import com.bc.revan.Entities.AllHomeAwayOfStandingDetail;
import com.bc.revan.Entities.GoalsOfAllHomeAway;
import com.bc.revan.Entities.League;
import com.bc.revan.Entities.Standing;
import com.bc.revan.Entities.StandingDetail;
import com.bc.revan.Entities.StandingMainNode;
import com.bc.revan.Entities.Base.BaseResponse;
import com.bc.revan.Entities.Dto.StandingScheduledDtos.LeagueForStandingsScheduledDto;
import com.bc.revan.Entities.Dto.StandingScheduledDtos.StandingForStandingsScheduledDto;
import com.bc.revan.Entities.Enums.EnumForAllHomeAway;

import jakarta.transaction.Transactional;
import lombok.Data;

@Service
@Component
public class StandingScheduledService extends BaseRequest implements IStandingScheduledService {

	@Autowired
	private IStandingDal standingDal;

	@Autowired
	private IStandingDetailDal standingDetailDal;

	@Autowired
	private IAllHomeAwayOfStandingDetailDal allHomeAwayOfStandingDetailDal;

	@Autowired
	private IGoalsOfAllHomeAwayDal goalsOfAllHomeAwayDal;

	@Autowired
	private ITeamDal teamDal;

	@Autowired
	private ILeagueDal leagueDal;

	@Autowired
	private ISeasonDal seasonDal;

	@Autowired
	private IStandingMainNodeDal standingMainNodeDal;

	@Autowired
	public StandingScheduledService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Transactional
	@Override
	public BaseResponse<StandingData> getStanding() throws InterruptedException, ExecutionException {
		standingMainNodeDal.deleteAllStandingMain();
		goalsOfAllHomeAwayDal.deleteAllGoalsOfAllHomeAway();
		allHomeAwayOfStandingDetailDal.deleteAllHomeAwayOfStandingDetail();
		standingDetailDal.deleteAllStandingDetail();
		standingDal.deleteAllStanding();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.add("x-rapidapi-host", user);
		headers.add("x-rapidapi-key", password);

		ResponseEntity<BaseResponse<StandingData>> exchange = null;
		List<Long> leagues = new ArrayList<Long>();
		leagues.add((long) 203);
		leagues.add((long) 88);
		leagues.add((long) 144);
		leagues.add((long) 135);
		leagues.add((long) 61);
		leagues.add((long) 39);
		leagues.add((long) 140);
		leagues.add((long) 78);

		
		for (Long leagueValue : leagues) {

			int seasonValue = 2023;

			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			StandingMainNode dummy = standingMainNodeDal.getBySeasonAndLeagueId(seasonValue,
					leagueValue);
			if (dummy != null) {
			
				return null;
			}

			String url = apiUrl + "standings?league=" + leagueValue + "&season=" + seasonValue;

			exchange = restTemplate.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<BaseResponse<StandingData>>() {
					});

			System.out.println(exchange.getBody().getResponseList());
			for (StandingData responseItem : exchange.getBody().getResponseList()) {

				List<Standing> standingList = new ArrayList<Standing>();

				for (List<StandingForStandingsScheduledDto> mainList : responseItem.getLeague().getStandingMainNode()) {

					List<StandingDetail> standingDetailList = new ArrayList<StandingDetail>();

					for (StandingForStandingsScheduledDto standingItem : mainList) {

						GoalsOfAllHomeAway goalsOfAll = GoalsOfAllHomeAway.builder()
								.forofgoals(standingItem.getAll().getGoals().getForOfGols())
								.against(standingItem.getAll().getGoals().getAgainst()).build();
						goalsOfAll = goalsOfAllHomeAwayDal.add(goalsOfAll);

						GoalsOfAllHomeAway goalsOfHome = GoalsOfAllHomeAway.builder()
								.forofgoals(standingItem.getHome().getGoals().getForOfGols())
								.against(standingItem.getHome().getGoals().getAgainst()).build();
						goalsOfHome = goalsOfAllHomeAwayDal.add(goalsOfHome);

						GoalsOfAllHomeAway goalsOfAway = GoalsOfAllHomeAway.builder()
								.forofgoals(standingItem.getAway().getGoals().getForOfGols())
								.against(standingItem.getAway().getGoals().getAgainst()).build();
						goalsOfAway = goalsOfAllHomeAwayDal.add(goalsOfAway);

						AllHomeAwayOfStandingDetail allOfStandingDetail = AllHomeAwayOfStandingDetail.builder()
								.played(standingItem.getAll().getPlayed()).win(standingItem.getAll().getWin())
								.draw(standingItem.getAll().getDraw()).lose(standingItem.getAll().getLose())
								.goalsOfAllHomeAway(goalsOfAll).allHomeAway(EnumForAllHomeAway.All).build();
						allOfStandingDetail = allHomeAwayOfStandingDetailDal.add(allOfStandingDetail);

						AllHomeAwayOfStandingDetail homeOfStandingDetail = AllHomeAwayOfStandingDetail.builder()
								.played(standingItem.getHome().getPlayed()).win(standingItem.getHome().getWin())
								.draw(standingItem.getHome().getDraw()).lose(standingItem.getHome().getLose())
								.goalsOfAllHomeAway(goalsOfHome).allHomeAway(EnumForAllHomeAway.Home).build();
						homeOfStandingDetail = allHomeAwayOfStandingDetailDal.add(homeOfStandingDetail);

						AllHomeAwayOfStandingDetail awayOfStandingDetail = AllHomeAwayOfStandingDetail.builder()
								.played(standingItem.getAway().getPlayed()).win(standingItem.getAway().getWin())
								.draw(standingItem.getAway().getDraw()).lose(standingItem.getAway().getLose())
								.goalsOfAllHomeAway(goalsOfAway).allHomeAway(EnumForAllHomeAway.Away).build();
						awayOfStandingDetail = allHomeAwayOfStandingDetailDal.add(awayOfStandingDetail);

						List<AllHomeAwayOfStandingDetail> allHomeAwayOfStandingDetailList = new ArrayList<AllHomeAwayOfStandingDetail>();
						allHomeAwayOfStandingDetailList.add(allOfStandingDetail);
						allHomeAwayOfStandingDetailList.add(homeOfStandingDetail);
						allHomeAwayOfStandingDetailList.add(awayOfStandingDetail);

						StandingDetail standingDetail = StandingDetail.builder()
								.allHomeAwayOfStandingDetail(allHomeAwayOfStandingDetailList)
								.rank(standingItem.getRank()).team(teamDal.getById(standingItem.getTeam().getId()))
								.points(standingItem.getPoints()).goalsDiff(standingItem.getGoalsDiff())
								.group(standingItem.getGroup()).form(standingItem.getForm())
								.status(standingItem.getStatus()).description(standingItem.getDescription())
								.update(standingItem.getUpdate()).build();
						standingDetail = standingDetailDal.add(standingDetail);
						standingDetailList.add(standingDetail);

					}
					Standing standing = Standing.builder().StandingDetail(standingDetailList).build();
					standing = standingDal.add(standing);
					standingList.add(standing);

				}
				League leagueOfStanding = leagueDal.getById(responseItem.getLeague().getId());

				StandingMainNode standingMainNode = StandingMainNode.builder().Standing(standingList)
						.league(leagueOfStanding)
						.season(seasonDal.getByLeagueAndYear(leagueOfStanding, responseItem.getLeague().getSeason()))
						.build();
				standingMainNode = standingMainNodeDal.add(standingMainNode);
			}
		}
		return exchange.getBody();
	}

	@Data
	private static class StandingData {

		private LeagueForStandingsScheduledDto league;

	}

}
