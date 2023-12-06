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

import com.bc.revan.DataAccess.IPlayerDal;
import com.bc.revan.DataAccess.ISquadDal;
import com.bc.revan.DataAccess.ITeamDal;
import com.bc.revan.Entegration.ISquadsForPlayersScheduledService;
import com.bc.revan.Entities.Player;
import com.bc.revan.Entities.Squad;
import com.bc.revan.Entities.Team;
import com.bc.revan.Entities.Base.BaseResponse;
import com.bc.revan.Entities.Dto.SquadForPlayerScheduledDtos.PlayersOfSquadScheduledDto;

import jakarta.transaction.Transactional;
import lombok.Data;

@Service
@Component
public class SquadsForPlayersScheduledService extends BaseRequest implements ISquadsForPlayersScheduledService {

	@Autowired
	ITeamDal teamDal;

	@Autowired
	ISquadDal squadDal;

	@Autowired
	IPlayerDal playerDal;

	public SquadsForPlayersScheduledService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Transactional
	@Override
	public BaseResponse getSquads() throws InterruptedException, ExecutionException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.add("x-rapidapi-host", user);
		headers.add("x-rapidapi-key", password);

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		long teamId;

		for (Team responseTeam : teamDal.getByCountry("Spain")) {
		 //Team responseTeam = teamDal.getById(645); 
		 
			teamId = responseTeam.getId();

			Squad controlValue = squadDal.getByTeam(teamId);
			if (controlValue != null)
				 continue;
			

			String url = apiUrl + "/players/squads?team=" + teamId;
			ResponseEntity<BaseResponse<SquadsData>> exchange = restTemplate.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<BaseResponse<SquadsData>>() {
					});
			System.out.println(exchange.getBody());
		
			
			
			for (SquadsData responseItem : exchange.getBody().getResponseList()) {

				Team team = teamDal.getById(teamId);
				List<Player> players = new ArrayList<>();
				Player player = new Player();
				
				
				
				
				
				for (PlayersOfSquadScheduledDto responsePlayer : responseItem.getPlayers()) {
					
					 boolean playerExists = players.stream().anyMatch(checkPlayer -> checkPlayer.getId() == responsePlayer.getId());
				        if (playerExists) {
				            continue;
				        }
				   /* //-------------------   Aynı futbolcuyu iki kere gönderiyor bazı kadrolarda onu engellemek için yaptım.
				     int check=0;
					 System.out.println(responsePlayer.getId() +  " " + responsePlayer.getName());
					 
					 
					 for (Player checkPlayer : players) {
						if(checkPlayer.getId()==responsePlayer.getId()) {
							check++;
							break;
						}
					}
					 if(check>0) {
						 check=0;
						 continue;
					 
					 }
					*/  //----------------------------------
					 player = playerDal.getById(responsePlayer.getId());
					

					if (player != null) {
						player = Player.builder().id(responsePlayer.getId()).name(responsePlayer.getName())
								.number(responsePlayer.getNumber()).position(responsePlayer.getPosition())
								.photo(responsePlayer.getPhoto()).build();
						player = playerDal.update(player);
					}
					else {
						player = Player.builder().id(responsePlayer.getId()).name(responsePlayer.getName())
								.number(responsePlayer.getNumber()).position(responsePlayer.getPosition())
								.photo(responsePlayer.getPhoto()).build();
						player = playerDal.add(player);
					}

					players.add(player);
					

				}

				Squad squad = Squad.builder().team(team).players(players).build();
				squad = squadDal.add(squad);

			}

		}

		return null;
	}

	@Data
	private static class SquadsData {

		TeamData team;
		List<PlayersOfSquadScheduledDto> players;

		@Data
		private static class TeamData {

			long id;
			String name;
			String logo;

		}

	}

}
