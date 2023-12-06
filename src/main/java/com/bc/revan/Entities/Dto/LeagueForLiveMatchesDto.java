package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public class LeagueForLiveMatchesDto implements Serializable {
	long id;
	int season; //yıl olarak geliyor
}
