package com.hr.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name = "round_master")
public class Round {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roundId;

	private String roundName;

	@ManyToMany()
	private List<department> dep;

	public Round() {
		super();
	}

	public Round(int roundId, String roundName) {
		super();
		this.roundId = roundId;
		this.roundName = roundName;
	}

	public int getRoundId() {
		return roundId;
	}

	public void setRoundId(int roundId) {
		this.roundId = roundId;
	}

	public String getRoundName() {
		return roundName;
	}

	public void setRoundName(String roundName) {
		this.roundName = roundName;
	}

	@Override
	public String toString() {
		return "Round [roundId=" + roundId + ", roundName=" + roundName + "]";
	}

}
