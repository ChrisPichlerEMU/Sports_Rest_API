package com.baseball.parseJSON;

import com.baseball.model.*;
import com.jayway.jsonpath.JsonPath;

//This class will parse the JSON String returned by the Sportradar API into human-readable format
public class ParseJSON {	
	
	//games is a Game array that will hold each Game object from the current set date
	public static Game[] games;
	
	public ParseJSON() {}
	
	//This method initializes the games array to the current set date if the date is in the past. If it returns false, there were no games played on the current date
	public boolean initializeGameArrayPast(String str) {
		String pathStr = "$.league.games.length()";
		
		int numOfGames;
		
		try {
			numOfGames = JsonPath.read(str, pathStr);
		}catch(com.jayway.jsonpath.PathNotFoundException ex) {
			return false;
		}
		
		games = new Game[numOfGames];
				
		for(int i = 0; i < numOfGames; i++) {
			Game game = new Game();
			Team teamOne = new Team();
			Team teamTwo = new Team();
			teamOne.setCity(JsonPath.read(str, "$.league.games[" + (i + "") + "].game.home.market"));
			teamTwo.setCity(JsonPath.read(str, "$.league.games[" + (i + "") + "].game.away.market"));
			teamOne.setMascot(JsonPath.read(str, "$.league.games[" + (i + "") + "].game.home.name"));
			teamTwo.setMascot(JsonPath.read(str, "$.league.games[" + (i + "") + "].game.away.name"));
			teamOne.setId(JsonPath.read(str, "$.league.games[" + (i + "") + "].game.home_team"));
			teamTwo.setId(JsonPath.read(str, "$.league.games[" + (i + "") + "].game.away_team"));
			teamOne.setRuns(JsonPath.read(str, "$.league.games[" + (i + "") + "].game.home.runs"));
			teamTwo.setRuns(JsonPath.read(str, "$.league.games[" + (i + "") + "].game.away.runs"));
			teamOne.setWins(JsonPath.read(str, "$.league.games[" + (i + "") + "].game.home.win"));
			teamTwo.setWins(JsonPath.read(str, "$.league.games[" + (i + "") + "].game.away.win"));
			teamOne.setLosses(JsonPath.read(str, "$.league.games[" + (i + "") + "].game.home.loss"));
			teamTwo.setLosses(JsonPath.read(str, "$.league.games[" + (i + "") + "].game.away.loss"));				
			game.setTeamOne(teamOne);
			game.setTeamTwo(teamTwo);
			game.setBallpark(JsonPath.read(str, "$.league.games[" + (i + "") + "].game.venue.name"));
			String winningPitcher = (JsonPath.read(str, "$.league.games[" + (i + "") + "].game.pitching.win.first_name")) + " " + (JsonPath.read(str, "$.league.games[" + (i + "") + "].game.pitching.win.last_name"));
			game.setWinningPitcher(winningPitcher);
			String losingPitcher = (JsonPath.read(str, "$.league.games[" + (i + "") + "].game.pitching.loss.first_name")) + " " + (JsonPath.read(str, "$.league.games[" + (i + "") + "].game.pitching.loss.last_name"));
			game.setLosingPitcher(losingPitcher);
			game.setWpWins((JsonPath.read(str, "$.league.games[" + (i + "") + "].game.pitching.win.win")));
			game.setWpLosses((JsonPath.read(str, "$.league.games[" + (i + "") + "].game.pitching.win.loss")));
			game.setLpWins((JsonPath.read(str, "$.league.games[" + (i + "") + "].game.pitching.loss.win")));
			game.setLpLosses((JsonPath.read(str, "$.league.games[" + (i + "") + "].game.pitching.loss.loss")));
			
			try {
			game.setAttendance(JsonPath.read(str, "$.league.games[" + (i + "") + "].game.attendance"));
			game.setCapacity((JsonPath.read(str, "$.league.games[" + (i + "") + "].game.venue.capacity")));
			}catch(com.jayway.jsonpath.PathNotFoundException ex) {
			}
			
			try {
				String savingPitcher = (JsonPath.read(str, "$.league.games[" + (i + "") + "].game.pitching.save.first_name")) + " " + (JsonPath.read(str, "$.league.games[" + (i + "") + "].game.pitching.save.last_name"));
				game.setSavingPitcher(savingPitcher);
				game.setSaves((JsonPath.read(str, "$.league.games[" + (i + "") + "].game.pitching.save.save")));
			}catch(com.jayway.jsonpath.PathNotFoundException ex) {
			}
			
			games[i] = game;
		}
		
		return true;
	}
	
	//This will initialize the games array based on the current set date's games if the current set date is today or in the future
	public boolean initializeGameArrayFuture(String str) {
		String pathStr = "$.league.games.length()";

		int numOfGames;
		
		try {
			numOfGames = JsonPath.read(str, pathStr);
		}catch(com.jayway.jsonpath.PathNotFoundException ex) {
			return false;
		}
		
		games = new Game[numOfGames];
				
		for(int i = 0; i < numOfGames; i++) {
			Game game = new Game();
			Team teamOne = new Team();
			Team teamTwo = new Team();
			teamOne.setCity(JsonPath.read(str, "$.league.games[" + (i + "") + "].game.home.market"));
			teamTwo.setCity(JsonPath.read(str, "$.league.games[" + (i + "") + "].game.away.market"));
			teamOne.setMascot(JsonPath.read(str, "$.league.games[" + (i + "") + "].game.home.name"));
			teamTwo.setMascot(JsonPath.read(str, "$.league.games[" + (i + "") + "].game.away.name"));
			game.setTeamOne(teamOne);
			game.setTeamTwo(teamTwo);
			games[i] = game;
		}
		
		return true;
	}
	
	//Getter for the games array
	public Game[] getGames() {
		return games;
	}
	
	//Returns the games played on the current date in human-readable format. isPast determines if the current date is in the past or not
	public String parseSchedule(boolean isPast) {
		String answer = "";
		
		if(isPast == true) {
			for(int i = 0; i < games.length; i++) {
				answer += ((i + 1) + "") + ": ";
				Team home = games[i].getTeamOne();
				Team road = games[i].getTeamTwo();
				answer += road.getCity() + " " + road.getMascot() + " at " + home.getCity() + " " + home.getMascot() + "\n";
			}
		}
		else {
			for(int i = 0; i < games.length; i++) {
				Team home = games[i].getTeamOne();
				Team road = games[i].getTeamTwo();
				answer += road.getCity() + " " + road.getMascot() + " at " + home.getCity() + " " + home.getMascot() + "\n";
			}
		}
		
		return answer;
	}
	
	//Returns a specific game from the Games array
	public String parseGames(int num){	
		if(num >= games.length) {
			return "The number you have entered does not correspond to a game on this date. Please go back to /scoreboard and pick a number from those options.";
		}
		
		return games[num].toString();
	}
}
