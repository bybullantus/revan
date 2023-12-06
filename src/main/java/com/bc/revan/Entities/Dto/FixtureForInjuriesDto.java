package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class FixtureForInjuriesDto  implements Serializable {
	long id;
	String timezone;
	String date;
	long timestamp;

}
