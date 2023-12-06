package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import com.bc.revan.Entities.ForAndAgainstOfBiggest;
import com.bc.revan.Entities.GoalsOfBiggest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@MappedSuperclass
public class GoalsOfBiggestDto implements Serializable{

	
	List<ForAndAgainstOfBiggestDto> forAndAgainstOfBiggest;
}
