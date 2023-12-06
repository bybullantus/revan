package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import com.bc.revan.Entities.ScoreOfFixture;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
@Data
@MappedSuperclass
public class MainNodeOfScoreForFixtureDto implements Serializable{
	List<ScoreOfFixtureDto> scores;
}
