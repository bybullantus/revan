package com.bc.revan.Entegration.Football;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

import com.bc.revan.DataAccess.IFixtureDal;
import com.bc.revan.DataAccess.IFixtureMainNodeDal;
import com.bc.revan.DataAccess.IWeeklyMatchDal;
import com.bc.revan.Entegration.ICheckingWeeklyMatchesScheduledService;
import com.bc.revan.Entities.WeeklyMatch;
import com.bc.revan.Entities.Base.BaseResponse;
import com.bc.revan.Entities.Dto.FixtureMainNodeDto;
import com.bc.revan.Entities.Dto.WeeklyMatchDto;
import com.bc.revan.Entities.Dto.FixtureScheduledDtos.FixtureForScheduledDto;
import com.bc.revan.Entities.Dto.FixtureScheduledDtos.ScoreOfFixtureForScheduledDto;
import com.bc.revan.Entities.Dto.FixtureScheduledDtos.TeamsOfFixtureForScheduledDto;
import com.bc.revan.Entities.Enums.EnumForBetType;
import com.bc.revan.Entities.Enums.EnumForStatus;

import jakarta.transaction.Transactional;
import lombok.Data;

@Service
@Component
public class CheckingWeeklyMatchesScheduledService extends BaseRequest
		implements ICheckingWeeklyMatchesScheduledService {

	@Autowired
	IFixtureMainNodeDal fixtureMainNodeDal;

	@Autowired
	IFixtureDal fixtureDal;

	@Autowired
	IWeeklyMatchDal weeklyMatchDal;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	public CheckingWeeklyMatchesScheduledService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	@Transactional
	public BaseResponse<FixtureData> getCheckingWeeklyMatches() throws InterruptedException, ExecutionException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.add("x-rapidapi-host", user);
		headers.add("x-rapidapi-key", password);

		ResponseEntity<BaseResponse<FixtureData>> exchange = null;

		List<WeeklyMatch> weeklyMatches = weeklyMatchDal.getByStatus(EnumForStatus.NULL);
		for (WeeklyMatch weeklyMatch : weeklyMatches) {
			System.out.println(modelMapper.map(weeklyMatch, WeeklyMatchDto.class));
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			String url = apiUrl + "fixtures?id=" + weeklyMatch.getFixtureMainNode().getFixture().getId();

			exchange = restTemplate.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<BaseResponse<FixtureData>>() {
					});
			for (FixtureData responseItem : exchange.getBody().getResponseList()) {

				System.out.println(weeklyMatch.getBetType());
				if (responseItem.getFixture().getStatus().getShortOfStatus().equals("FT")) {
					if (weeklyMatch.getBetType().equals(EnumForBetType.HOMEOVER05)
							&& responseItem.getGoals().getHome() == 0) {

						weeklyMatch.setStatus(EnumForStatus.FALSE);
						weeklyMatch.getFixtureMainNode().getGoals().setHome(responseItem.getGoals().getHome());
						weeklyMatch.getFixtureMainNode().getGoals().setAway(responseItem.getGoals().getAway());

						weeklyMatchDal.update(weeklyMatch);

					} else if (weeklyMatch.getBetType().equals(EnumForBetType.AWAYOVER05)
							&& responseItem.getGoals().getAway() == 0) {
						weeklyMatch.setStatus(EnumForStatus.FALSE);
						weeklyMatch.getFixtureMainNode().getGoals().setHome(responseItem.getGoals().getHome());
						weeklyMatch.getFixtureMainNode().getGoals().setAway(responseItem.getGoals().getAway());
						weeklyMatchDal.update(weeklyMatch);
					} else if (weeklyMatch.getBetType().equals(EnumForBetType.HOMEOVER05)
							&& responseItem.getGoals().getHome() > 0) {

						weeklyMatch.setStatus(EnumForStatus.TRUE);
						weeklyMatch.getFixtureMainNode().getGoals().setHome(responseItem.getGoals().getHome());
						weeklyMatch.getFixtureMainNode().getGoals().setAway(responseItem.getGoals().getAway());

						weeklyMatchDal.update(weeklyMatch);

					} else if (weeklyMatch.getBetType().equals(EnumForBetType.AWAYOVER05)
							&& responseItem.getGoals().getAway() > 0) {
						weeklyMatch.setStatus(EnumForStatus.TRUE);
						weeklyMatch.getFixtureMainNode().getGoals().setHome(responseItem.getGoals().getHome());
						weeklyMatch.getFixtureMainNode().getGoals().setAway(responseItem.getGoals().getAway());

						weeklyMatchDal.update(weeklyMatch);
					}
				}

			}

		}
		return null;
	}

	@Data
	private static class FixtureData {

		FixtureForScheduledDto fixture;

		LeagueOfFixtureData league;

		TeamsOfFixtureForScheduledDto teams;

		GoalsOfFixtureData goals;

		ScoreOfFixtureForScheduledDto score;

		@Data
		public static class LeagueOfFixtureData {

			long id;
			String name;
			String country;
			String logo;
			String flag;
			int season;
			String round;
		}

		@Data
		public static class GoalsOfFixtureData {

			int home;
			int away;
		}

	}

}
