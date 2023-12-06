package com.bc.revan.RestApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
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

import com.bc.revan.Business.ICountryService;
import com.bc.revan.Entities.Country;
import com.bc.revan.Entities.Dto.CountryDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController()
@RequestMapping("api")
@Tag(name = "Country")
@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
public class CountryController {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ICountryService countryManager;

	@GetMapping("/country")
	
	public ResponseEntity<List<CountryDto>> get() throws ContextedException, ExecutionException, InterruptedException {

		List<CountryDto> returnValue = modelMapper.map(countryManager.getAll().get(),
				new TypeToken<List<CountryDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);
	}

	@GetMapping("/country/getbyCode/{code}")
	public ResponseEntity<List<CountryDto>> getbyCode(@PathVariable String code)
			throws InterruptedException, ExecutionException {

		List<CountryDto> returnValue = new ArrayList<CountryDto>();

		for (Country country : countryManager.getByCode(code).get())
			returnValue.add(modelMapper.map(country, CountryDto.class));
		return new ResponseEntity(returnValue, HttpStatus.OK);
	}

	@GetMapping("/country/getbyId/{id}")
	public ResponseEntity<CountryDto> getbyId(@PathVariable long id) throws InterruptedException, ExecutionException {
		CountryDto cntry = modelMapper.map(countryManager.getById(id).get(), CountryDto.class);
		return new ResponseEntity(cntry, HttpStatus.OK);
	}

	@GetMapping("/country/getbyName/{name}")
	public CompletableFuture<ResponseEntity<CountryDto>> getbyName(@PathVariable String name)
			throws InterruptedException, ExecutionException {
		CountryDto cntry = modelMapper.map(countryManager.getByName(name).get(), CountryDto.class);
		return CompletableFuture.completedFuture(new ResponseEntity(cntry, HttpStatus.OK));
	}

}
