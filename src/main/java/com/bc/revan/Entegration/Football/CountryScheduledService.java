package com.bc.revan.Entegration.Football;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bc.revan.DataAccess.ICountryDal;
import com.bc.revan.Entegration.ICountryScheduledService;
import com.bc.revan.Entities.Country;
import com.bc.revan.Entities.Base.BaseResponse;

import jakarta.transaction.Transactional;

@Service
@Component
public class CountryScheduledService extends BaseRequest implements ICountryScheduledService {

	private ICountryDal countryDal;

	@Autowired
	public CountryScheduledService(RestTemplate restTemplate, ICountryDal countryDal) {
		this.restTemplate = restTemplate;
		this.countryDal = countryDal;
	}

	@Override
	@Transactional
	public BaseResponse<Country> getCountries() {
		HttpHeaders headers = new HttpHeaders();

		headers.add("x-rapidapi-host", user);
		headers.add("x-rapidapi-key", password);

		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<BaseResponse<Country>> exchange = restTemplate.exchange(apiUrl + "countries", HttpMethod.GET,
				entity, new ParameterizedTypeReference<BaseResponse<Country>>() {
				});
		for (Country item : exchange.getBody().getResponseList()) {

			Country value = countryDal.getByName(item.getName());
			if (value == null)
				countryDal.add(item);
			else if (!value.getName().equals(item.getName())) {
				countryDal.add(item);
			}
		}

		return exchange.getBody();
	}

}
