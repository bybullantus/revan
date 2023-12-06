package com.bc.revan.Entities;

import java.util.List;

import com.bc.revan.Entities.Base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "standingdetails",indexes = {
		   @Index(columnList = "id", name = "standingdetails_id_hidx"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StandingDetail extends BaseEntity{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "standingdetails", sequenceName = "standingdetails.gen", initialValue = 1, allocationSize = 1)
	@Setter(AccessLevel.NONE)
	long id;
	
	@JsonProperty("rank")
	@Column(name = "rank")
	int rank;
	
	@ManyToOne
	@JoinColumn(name = "teamid")
	Team team;
	
	@JsonProperty("points")
	@Column(name = "points")
	int points;
	
	@JsonProperty("goalsDiff")
	@Column(name = "goalsDiff")
	int goalsDiff;
	
	@JsonProperty("group")
	@Column(name = "groupofstandingdetail", length = 200)
	String group;
	
	@JsonProperty("form")
	@Column(name = "form", length = 100)
	String form;
	
	@JsonProperty("status")
	@Column(name = "status", length = 50)
	String status;
	
	@JsonProperty("description")
	@Column(name = "description", length = 200)
	String description;
	
	@JsonProperty("update")
	@Column(name = "update", length = 200)
	String update;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "standingdetailid")
	List<AllHomeAwayOfStandingDetail> allHomeAwayOfStandingDetail;
}
