package com.baseball.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.jupiter.api.*;

//JUnit tests on the Game class. Currently, all tests pass
public class GameTest {
	
	private Game gameOne;
	private Game gameNoSave;
	private Game gameSave;
	private Game fullConstructor;
	private Team teamOne;
	private Team teamTwo;
	
	@BeforeEach
	public void setUp() {
		gameOne = new Game();
		teamOne = new Team("Detroit", "Tigers", "123");
		teamTwo = new Team("Chicago", "White Sox", "124", 5, 3, 25);
		gameNoSave = new Game(teamOne, teamTwo, 5000, "Comerica Park", 5, 2, "Nolan Ryan", "Cy Young");
		gameSave = new Game(teamOne, teamTwo, 5000, "Comerica Park", 5, 2, "Nolan Ryan", "Cy Young", "Todd Jones");
		fullConstructor = new Game(teamOne, teamTwo, 3000, 6000, "Coors Field", "Max Scherzer", "Rick Porcello", "Alex Lange", 5, 2, 3, 4, 50);
	}
	
	@After
	public void tearDown() {
		gameOne = null;
		teamOne = null;
		teamTwo = null;
		gameNoSave = null;
		gameSave = null;
		fullConstructor = null;
	}

	@Test
	public void testGetWinningPitcher() {
		assertEquals(gameNoSave.getWinningPitcher(), "Nolan Ryan");
	}

	@Test
	public void testSetWinningPitcher() {
		gameOne.setWinningPitcher("Justin Verlander");
		assertEquals(gameOne.getWinningPitcher(), "Justin Verlander");
	}

	@Test
	public void testGetLosingPitcher() {
		assertEquals(gameSave.getLosingPitcher(), "Cy Young");
	}

	@Test
	public void testSetLosingPitcher() {
		gameOne.setLosingPitcher("Tarik Skubal");
		assertEquals(gameOne.getLosingPitcher(), "Tarik Skubal");
	}

	@Test
	public void testGetSavingPitcher() {
		assertEquals(gameSave.getSavingPitcher(), "Todd Jones");
	}
	
	@Test
	public void testGetSavingPitcherNoSave() {
		assertEquals(gameNoSave.getSavingPitcher(), null);
	}

	@Test
	public void testSetSavingPitcher() {
		gameOne.setSavingPitcher("Francisco Rodriguez");
		assertEquals(gameOne.getSavingPitcher(), "Francisco Rodriguez");
	}

	@Test
	public void testGetTeamOne() {
		assertEquals(fullConstructor.getTeamOne(), teamOne);
	}

	@Test
	public void testSetTeamOne() {
		gameOne.setTeamOne(teamTwo);
		assertEquals(gameOne.getTeamOne(), teamTwo);
	}

	@Test
	public void testGetTeamTwo() {
		assertEquals(fullConstructor.getTeamTwo(), teamTwo);
	}

	@Test
	public void testSetTeamTwo() {
		gameOne.setTeamTwo(teamOne);
		assertEquals(gameOne.getTeamTwo(), teamOne);
	}

	@Test
	public void testGetAttendance() {
		assertEquals(gameSave.getAttendance(), 5000);
	}

	@Test
	public void testSetAttendance() {
		gameOne.setAttendance(400);
		assertEquals(gameOne.getAttendance(), 400);
	}

	@Test
	public void testGetBallpark() {
		assertEquals(gameSave.getBallpark(), "Comerica Park");
	}
	
	@Test
	public void testSetBallpark() {
		gameOne.setBallpark("Fifth Third Field");
		assertEquals(gameOne.getBallpark(), "Fifth Third Field");
	}

	@Test
	public void testGetCapacity() {
		assertEquals(fullConstructor.getCapacity(), 6000);
	}

	@Test
	public void testSetCapacity() {
		fullConstructor.setCapacity(7000);
		assertEquals(fullConstructor.getCapacity(), 7000);
	}
	
	@Test
	public void testGetWpWins() {
		assertEquals(fullConstructor.getWpWins(), 5);
	}

	@Test
	public void testSetWpWins() {
		gameOne.setWpWins(7);
		assertEquals(gameOne.getWpWins(), 7);
	}

	@Test
	public void testGetWpLosses() {
		assertEquals(fullConstructor.getWpLosses(), 2);
	}

	@Test
	public void testSetWpLosses() {
		gameOne.setWpLosses(10);
		assertEquals(gameOne.getWpLosses(), 10);
	}

	@Test
	public void testGetLpWins() {
		assertEquals(fullConstructor.getLpWins(), 3);
	}

	@Test
	public void testSetLpWins() {
		gameOne.setLpWins(15);
		assertEquals(gameOne.getLpWins(), 15);
	}

	@Test
	public void testGetLpLosses() {
		assertEquals(fullConstructor.getLpLosses(), 4);
	}

	@Test
	public void testSetLpLosses() {
		gameOne.setLpLosses(11);
		assertEquals(gameOne.getLpLosses(), 11);
	}

	@Test
	public void testGetSaves() {
		assertEquals(fullConstructor.getSaves(), 50);
	}
	
	@Test
	public void testGetSavesNoSave() {
		assertEquals(gameNoSave.getSaves(), 0);
	}

	@Test
	public void testSetSaves() {
		gameOne.setSaves(15);
		assertEquals(gameOne.getSaves(), 15);
	}

	@Test
	public void testToString() {
		teamOne.setRuns(5);
		teamTwo.setRuns(4);
		teamOne.setWins(10);
		teamOne.setLosses(0);
		teamTwo.setWins(9);
		teamTwo.setLosses(1);
		fullConstructor.setAttendance(3000);
		fullConstructor.setCapacity(6000);
		assertEquals(fullConstructor.toString(), "Detroit Tigers (10-0) def. Chicago White Sox (9-1) 5 - 4.\nWinning Pitcher: Max Scherzer (5-2)\nLosing Pitcher: Rick Porcello (3-4)\nSaving Pitcher: Alex Lange (50)\nAttendance: 3,000/6,000 (50.0%)\nBallpark: Coors Field\n");
	}
}
