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

import com.bc.revan.DataAccess.IFixtureDal;
import com.bc.revan.DataAccess.IStatisticsDetailsForFixtureDal;
import com.bc.revan.DataAccess.IStatisticsForFixtureDal;
import com.bc.revan.DataAccess.ITeamDal;
import com.bc.revan.Entegration.IStatisticsForFixtureScheduledService;
import com.bc.revan.Entities.Fixture;
import com.bc.revan.Entities.StatisticsDetailsForFixture;
import com.bc.revan.Entities.StatisticsForFixture;
import com.bc.revan.Entities.Team;
import com.bc.revan.Entities.Base.BaseResponse;
import com.bc.revan.Entities.Dto.StatisticsForFixtureScheduledDtos.StatisticsForFixtureScheduledDto;

import jakarta.transaction.Transactional;
import lombok.Data;

@Service
@Component
public class StatisticsForFixtureScheduledService extends BaseRequest implements IStatisticsForFixtureScheduledService {

	@Autowired
	IStatisticsForFixtureDal statisticForFixtureDal;

	@Autowired
	IStatisticsDetailsForFixtureDal statisticsDetailsForFixtureDal;

	@Autowired
	ITeamDal teamDal;

	@Autowired
	IFixtureDal fixtureDal;

	public StatisticsForFixtureScheduledService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Transactional
	@Override
	public BaseResponse<StatisticForFixtureData> getStatisticsForFixture()
			throws InterruptedException, ExecutionException {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.add("x-rapidapi-host", user);
		headers.add("x-rapidapi-key", password);

		//long fixtureId = 592872;
		long fixtureId;
		
		for (Fixture responseFixture : fixtureDal.getAll()) {

			fixtureId=responseFixture.getId();
			
			if (statisticForFixtureDal.getByFixture(fixtureId)==null) {
				HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

				String url = apiUrl + "/fixtures/statistics?fixture=" + fixtureId;

				ResponseEntity<BaseResponse<StatisticForFixtureData>> exchange = restTemplate.exchange(url, HttpMethod.GET,
						entity, new ParameterizedTypeReference<BaseResponse<StatisticForFixtureData>>() {
						});
				System.out.println(exchange.getBody());
				for (StatisticForFixtureData responseItem : exchange.getBody().getResponseList()) {

					Team team = teamDal.getById(responseItem.getTeam().getId());

					List<StatisticsDetailsForFixture> detailList = new ArrayList<StatisticsDetailsForFixture>();

					for (StatisticsForFixtureScheduledDto responseDetails : responseItem.getStatistics()) {

						StatisticsDetailsForFixture detail = StatisticsDetailsForFixture.builder()
								.type(responseDetails.getType()).value(responseDetails.getValue()).build();
						detail = statisticsDetailsForFixtureDal.add(detail);
						detailList.add(detail);
					}

					StatisticsForFixture mainNode = StatisticsForFixture.builder().team(team)
							.statisticsDetailsForFixture(detailList).fixture(responseFixture).build();
					mainNode = statisticForFixtureDal.add(mainNode);

				}

				
			}
			
		}
		return null;
	}

	@Data
	private static class StatisticForFixtureData {
		TeamData team;

		ArrayList<StatisticsForFixtureScheduledDto> statistics;

		@Data
		private static class TeamData {

			long id;
			String name;
			String logo;

		}

	}

}
