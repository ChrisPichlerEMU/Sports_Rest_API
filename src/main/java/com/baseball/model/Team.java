package com.baseball.model;

//This class defines a Team object. There are 2 Team objects per 1 Game object
public class Team {
	private String city;
	private String mascot;
	private String id;
	private int runs;
	private int wins;
	private int losses;
	
	//Constructors
	public Team() {}
	
	public Team(String city, String mascot, String id) {
		this.city = city;
		this.mascot = mascot;
		this.id = id;
	}
	
	public Team(String city, String mascot, String id, int runs, int wins, int losses) {
		this.city = city;
		this.mascot = mascot;
		this.id = id;
		this.runs = runs;
		this.wins = wins;
		this.losses = losses;
	}
	
	//Getters and setters
	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public String getCity() {
		return city;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setMascot(String mascot) {
		this.mascot = mascot;
	}

	public String getMascot() {
		return mascot;
	}
	
	//The toString() method returns the city plus the mascot of the Team object
	@Override
	public String toString() {
		return city +  " " + mascot;
	}
	
	//The equals method determines if 2 Team objects are equal. This is true if the city, mascot, and ID of the Team objects are all equal
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(obj.getClass() != this.getClass()) {
			return false;
		}
		
		final Team team = (Team) obj;
		
		if(team.getCity().equals(this.getCity()) && team.getMascot().equals(this.getMascot()) && team.getId().equals(this.getId())){
			return true;
		}
		
		return false;
	}
}
