package com.bc.revan.Entegration.Football;

import java.sql.Date;
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

import com.bc.revan.DataAccess.IBirthDal;
import com.bc.revan.DataAccess.ICareerOfCoachDal;
import com.bc.revan.DataAccess.ICoachsDal;
import com.bc.revan.DataAccess.ITeamDal;
import com.bc.revan.Entegration.ICoachScheduledService;
import com.bc.revan.Entities.Birth;
import com.bc.revan.Entities.CareerOfCoach;
import com.bc.revan.Entities.Coachs;
import com.bc.revan.Entities.Team;
import com.bc.revan.Entities.Base.BaseResponse;
import com.bc.revan.Entities.Dto.CoachScheduledDtos.CareerOfCoachScheduledDto;

import jakarta.transaction.Transactional;
import lombok.Data;

@Service
@Component
public class CoachScheduledService extends BaseRequest implements ICoachScheduledService {

	@Autowired
	ICoachsDal coachsDal;
	@Autowired
	ITeamDal teamDal;
	@Autowired
	IBirthDal birthDal;
	@Autowired
	ICareerOfCoachDal careerOfCoachDal;

	public CoachScheduledService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Transactional
	@Override
	public BaseResponse<Coachs> getCoach() { // throws InterruptedException, ExecutionException
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.add("x-rapidapi-host", user);
		headers.add("x-rapidapi-key", password);

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		long teamId=645; // 645

		//for (Team responseTeam : teamDal.getAll()) {
		    //teamId=responseTeam.getId();
			String url = apiUrl + "/coachs?team=" + teamId;
			ResponseEntity<BaseResponse<CoachData>> exchange = null;
			try {
				exchange = restTemplate.exchange(url, HttpMethod.GET, entity,
						new ParameterizedTypeReference<BaseResponse<CoachData>>() {
						});
			} catch (Exception e) {
				//continue;
			}

			System.out.println(exchange.getBody());
			for (CoachData responseItem : exchange.getBody().getResponseList()) {

				if (coachsDal.getById(responseItem.getId()) == null) {
					List<CareerOfCoach> careers = new ArrayList<CareerOfCoach>();
					for (CareerOfCoachScheduledDto responseCareer : responseItem.career) {
						Team team = null;
						System.out.println(responseCareer.getTeam().getId());
						if (responseCareer.getTeam().getId() == 0 || responseCareer.getTeam() == null) {
							List<Team> teamList = teamDal.getByName(responseCareer.getTeam().getName());
							if (teamList != null) {
								team = teamList.get(0);
								System.out.println(team.getId());
							} else {
								long maxId = teamDal.getMaxId() + 1;
								team = Team.builder().name(responseCareer.getTeam().getName()).id(maxId).build();
								team = teamDal.add(team);
							}

						} else {

							team = teamDal.getById(responseCareer.getTeam().getId());

						}

						CareerOfCoach career = CareerOfCoach.builder().team(team).start(responseCareer.getStart())
								.end(responseCareer.getEnd()).build();
						career = careerOfCoachDal.add(career);
						careers.add(career);

					}

					Birth birth = Birth.builder().date(responseItem.getBirth().getDate())
							.place(responseItem.getBirth().getPlace()).country(responseItem.getBirth().getCountry())
							.build();
					birth = birthDal.add(birth);

					Coachs coach = Coachs.builder().id(responseItem.getId()).name(responseItem.getName())
							.firstName(responseItem.getFirstname()).lastName(responseItem.getLastname()).birth(birth)
							.nationality(responseItem.getNationality()).height(responseItem.getHeight())
							.weight(responseItem.getWeight()).photo(responseItem.getPhoto())
							.team(teamDal.getById(responseItem.getTeam().getId())).careers(careers).build();
					coach = coachsDal.add(coach);
				}
			}
	//	}
		return null;
	}

	@Data
	private static class CoachData {

		long id;
		String name;
		String firstname;
		String lastname;
		String age;
		BirthData birth;
		String nationality;
		String height;
		String weight;
		String photo;
		TeamData team;
		List<CareerOfCoachScheduledDto> career;

		@Data
		private static class BirthData {

			Date date;
			String place;
			String country;

		}

		@Data
		private static class TeamData {

			long id;
			String name;
			String logo;

		}

	}

}
