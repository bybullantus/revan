package com.bc.revan.Entities.Dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class CoachsDto implements Serializable{
    String name;
    String firstName;
    String lastName;
    BirthDto birth;
    String nationality;
    String height;
	String weight;
	String photo;
	TeamForInjuriesDto team;
	List<CareerOfCoachDto> careers;
	

    private int calculateAgeFromBirthDate(BirthDto birth) {
    	if (birth != null && birth.getDate() != null) {
            String dateString = birth.getDate().toString(); 
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
            try {
                LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter); 
                LocalDate birthDate = dateTime.toLocalDate(); 
                LocalDate currentDate = LocalDate.now();

                Period period = Period.between(birthDate, currentDate);
                return period.getYears();
            } catch (DateTimeParseException e) {
                
                e.printStackTrace(); 
                return 0; 
            }
        } else {
            return 0;
        }
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public BirthDto getBirth() {
		return birth;
	}

	public void setBirth(BirthDto birth) {
		this.birth = birth;
	}

	public int getAge() {
		if(this.birth != null) {
			
			return calculateAgeFromBirthDate(this.birth);
		}
		return 0;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public TeamForInjuriesDto getTeam() {
		return team;
	}

	public void setTeam(TeamForInjuriesDto team) {
		this.team = team;
	}

	public List<CareerOfCoachDto> getCareers() {
		return careers;
	}

	public void setCareers(List<CareerOfCoachDto> careers) {
		this.careers = careers;
	}
}
