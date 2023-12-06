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

import com.bc.revan.Business.IInjuriesService;
import com.bc.revan.Entities.Dto.InjuriesDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@RequestMapping("api")
@Tag(name = "Injuries")
@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
public class InjuriesController {
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	IInjuriesService injuriesManager;

	@GetMapping("/injuries")
	public ResponseEntity<List<InjuriesDto>> get() throws ContextedException, ExecutionException, InterruptedException {

		List<InjuriesDto> returnValue = modelMapper.map(injuriesManager.getAll().get(),
				new TypeToken<List<InjuriesDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);
	}

	@GetMapping("/injuries/{fixture}")
	public ResponseEntity<List<InjuriesDto>> evets(@PathVariable long fixture)
			throws ContextedException, ExecutionException, InterruptedException {

		List<InjuriesDto> returnValue = modelMapper.map(injuriesManager.getByFixture(fixture).get(),
				new TypeToken<List<InjuriesDto>>() {
				}.getType());
		return new ResponseEntity(returnValue, HttpStatus.OK);

	}

}
