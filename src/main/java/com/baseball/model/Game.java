package com.baseball.model;

//This class represents a specific MLB game as a Game object
public class Game {
	private Team teamOne;
	private Team teamTwo;
	private int attendance = 0;
	private int capacity;
	private String ballpark;
	private String winningPitcher;
	private String losingPitcher;
	private String savingPitcher;
	private String prettyAttendance;
	private String prettyCapacity;
	private int wpWins;
	private int wpLosses;
	private int lpWins;
	private int lpLosses;
	private int saves = 0;

	//Constructors
	public Game() {}
	
	public Game(Team teamOne, Team teamTwo) {
		this.teamOne = teamOne;
		this.teamTwo = teamTwo;
	}
	
	//Nobody got the save in this game
	public Game(Team teamOne, Team teamTwo, int attendance, String ballpark,
			int teamOneRuns, int teamTwoRuns, String winner, String loser) {
		this.teamOne = teamOne;
		this.teamTwo = teamTwo;
		this.attendance = attendance;
		this.ballpark = ballpark;
		this.winningPitcher = winner;
		this.losingPitcher = loser;
	}
	
	//A pitcher recorded a save in this game
	public Game(Team teamOne, Team teamTwo, int attendance, String ballpark,
			int teamOneRuns, int teamTwoRuns, String winner, String loser, String saver) {
		this.teamOne = teamOne;
		this.teamTwo = teamTwo;
		this.attendance = attendance;
		this.ballpark = ballpark;
		this.winningPitcher = winner;
		this.losingPitcher = loser;
		this.savingPitcher = saver;
	}

	public Game(Team teamOne, Team teamTwo, int attendance, int capacity, String ballpark, String winningPitcher,
			String losingPitcher, String savingPitcher, int wpWins,
			int wpLosses, int lpWins, int lpLosses, int saves) {
		this.teamOne = teamOne;
		this.teamTwo = teamTwo;
		this.attendance = attendance;
		this.capacity = capacity;
		this.ballpark = ballpark;
		this.winningPitcher = winningPitcher;
		this.losingPitcher = losingPitcher;
		this.savingPitcher = savingPitcher;
		this.wpWins = wpWins;
		this.wpLosses = wpLosses;
		this.lpWins = lpWins;
		this.lpLosses = lpLosses;
		this.saves = saves;
	}

	//Getters and setters
	public String getWinningPitcher() {
		return winningPitcher;
	}

	public void setWinningPitcher(String winningPitcher) {
		this.winningPitcher = winningPitcher;
	}

	public String getLosingPitcher() {
		return losingPitcher;
	}

	public void setLosingPitcher(String losingPitcher) {
		this.losingPitcher = losingPitcher;
	}

	public String getSavingPitcher() {
		return savingPitcher;
	}

	public void setSavingPitcher(String savingPitcher) {
		this.savingPitcher = savingPitcher;
	}

	public Team getTeamOne() {
		return teamOne;
	}

	public void setTeamOne(Team teamOne) {
		this.teamOne = teamOne;
	}

	public Team getTeamTwo() {
		return teamTwo;
	}

	public void setTeamTwo(Team teamTwo) {
		this.teamTwo = teamTwo;
	}

	public int getAttendance() {
		return attendance;
	}

	public void setAttendance(int attendance) {
		this.attendance = attendance;
		String firstThreeDigits = (attendance % 1000) + "";
		String lastDigits = (attendance) / 1000 + "";
		if(firstThreeDigits.length() == 1) {
			firstThreeDigits = "00" + firstThreeDigits;
		}
		else if(firstThreeDigits.length() == 2) {
			firstThreeDigits = "0" + firstThreeDigits;
		}
		this.prettyAttendance = lastDigits + "," + firstThreeDigits;
	}

	public String getBallpark() {
		return ballpark;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
		
		String firstThreeDigits = (capacity % 1000) + "";
		String lastDigits = (capacity / 1000) + "";
		if(firstThreeDigits.length() == 1) {
			firstThreeDigits = "00" + firstThreeDigits;
		}
		else if(firstThreeDigits.length() == 2) {
			firstThreeDigits = "0" + firstThreeDigits;
		}
		
		this.prettyCapacity = lastDigits + "," + firstThreeDigits;
	}
	
	public int getWpWins() {
		return wpWins;
	}

	public void setWpWins(int wpWins) {
		this.wpWins = wpWins;
	}

	public int getWpLosses() {
		return wpLosses;
	}

	public void setWpLosses(int wpLosses) {
		this.wpLosses = wpLosses;
	}

	public int getLpWins() {
		return lpWins;
	}

	public void setLpWins(int lpWins) {
		this.lpWins = lpWins;
	}

	public int getLpLosses() {
		return lpLosses;
	}

	public void setLpLosses(int lpLosses) {
		this.lpLosses = lpLosses;
	}

	public int getSaves() {
		return saves;
	}

	public void setSaves(int saves) {
		this.saves = saves;
	}

	public void setBallpark(String ballpark) {
		this.ballpark = ballpark;
	}
	
	//The toString() method returns a String that explains everything that happened in the current game
	@Override
	public String toString() {
		//Detroit Tigers (1-0)
		String firstTeamPlusRecord = teamOne.getCity() + " " + teamOne.getMascot() + " (" + teamOne.getWins() + "-" + teamOne.getLosses() + ")";
		String secondTeamPlusRecord = teamTwo.getCity() + " " + teamTwo.getMascot() + " (" + teamTwo.getWins() + "-" + teamTwo.getLosses() + ") ";
		
		//5 - 0
		String scoreFirstWins = teamOne.getRuns() + " - " + teamTwo.getRuns();
		String scoreSecondWins = teamTwo.getRuns() + " - " + teamOne.getRuns();
		
		//Justin Verlander (1-0)
		String winningPitcherPlusRecord = ".\nWinning Pitcher: " + winningPitcher + " (" + wpWins + "-" + wpLosses + ")";
		String losingPitcherPlusRecord = "\nLosing Pitcher: " + losingPitcher + " (" + lpWins + "-" + lpLosses + ")";
		
		String attendancePercentage = String.format("%.1f", ((double)attendance/(double)capacity) * 100);
		
		//Attendance: 5,000/10,000 (50%)
		String attendanceStr = "\nAttendance: " + prettyAttendance + "/" + prettyCapacity + " (" + attendancePercentage + "%)";
		
		//Ballpark: Comerica Park
		String ballparkStr = "\nBallpark: " + ballpark + "\n";
		
		String noAttendanceReported = "\nAttendance has not yet been reported for this game. Capacity = " + prettyCapacity;
		
		boolean attendanceReported = attendance > 0 ? true : false;
		boolean saveRecorded = saves > 0 ? true: false;
		boolean teamOneWins = teamOne.getRuns() - teamTwo.getRuns() > 0 ? true : false;
		boolean teamTwoWins = !teamOneWins;
		
		if(teamOneWins && saveRecorded && attendanceReported) {
			String savingPitcherPlusSaves = "\nSaving Pitcher: " + savingPitcher + " (" + saves + ")";
			return firstTeamPlusRecord + " def. " + secondTeamPlusRecord + scoreFirstWins + winningPitcherPlusRecord + losingPitcherPlusRecord + savingPitcherPlusSaves + attendanceStr + ballparkStr;
		}
		else if(teamOneWins && saveRecorded && !attendanceReported) {
			String savingPitcherPlusSaves = "\nSaving Pitcher: " + savingPitcher + " (" + saves + ")";
			return firstTeamPlusRecord + " def. " + secondTeamPlusRecord + scoreFirstWins + winningPitcherPlusRecord + losingPitcherPlusRecord + savingPitcherPlusSaves + noAttendanceReported + ballparkStr;
		}
		else if(teamOneWins && !saveRecorded && attendanceReported){
			return firstTeamPlusRecord + " def. " + secondTeamPlusRecord + scoreFirstWins + winningPitcherPlusRecord + losingPitcherPlusRecord + attendanceStr + ballparkStr;

		}
		
		else if(teamOneWins && !saveRecorded && !attendanceReported) {
			return firstTeamPlusRecord + " def. " + secondTeamPlusRecord + scoreFirstWins + winningPitcherPlusRecord + losingPitcherPlusRecord + noAttendanceReported + ballparkStr;
		}
		
		else if(teamTwoWins && saveRecorded && attendanceReported) {
			String savingPitcherPlusSaves = "\nSaving Pitcher: " + savingPitcher + " (" + saves + ")";
			return secondTeamPlusRecord + " def. " + firstTeamPlusRecord + scoreSecondWins + winningPitcherPlusRecord + losingPitcherPlusRecord + savingPitcherPlusSaves + attendanceStr + ballparkStr;
		}	
		else if(teamTwoWins && saveRecorded && !attendanceReported) {
			String savingPitcherPlusSaves = "\nSaving Pitcher: " + savingPitcher + " (" + saves + ")";
			return secondTeamPlusRecord + " def. " + firstTeamPlusRecord + scoreSecondWins + winningPitcherPlusRecord + losingPitcherPlusRecord + savingPitcherPlusSaves + noAttendanceReported + ballparkStr;
		}
		else if(teamTwoWins && !saveRecorded && attendanceReported){
			return secondTeamPlusRecord + " def. " + firstTeamPlusRecord + scoreSecondWins + winningPitcherPlusRecord + losingPitcherPlusRecord + attendanceStr + ballparkStr;

		}
		//teamTwo wins, no save recorded, no attendance reported
		else {
			return secondTeamPlusRecord + " def. " + firstTeamPlusRecord + scoreSecondWins + winningPitcherPlusRecord + losingPitcherPlusRecord + noAttendanceReported + ballparkStr;

		}
	}
}
