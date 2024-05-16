package com.baseball.parseJSON;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.jupiter.api.*;
import com.baseball.model.*;

//JUnit tests on the ParseJSON class. Currently, all tests pass
public class ParseJSONTest {
	

	String random;
	ParseJSON parser;
	Game game;
	Team teamOne;
	Team teamTwo;
	
	@BeforeEach
	public void setUp() {
		teamOne = new Team("Detroit", "Tigers", "123");
		teamTwo = new Team("Chicago", "White Sox", "124");
		game = new Game(teamOne, teamTwo, 20000, 40000, "Comerica Park", "Justin Verlander", "Cy Young", "Todd Jones", 5, 2, 3, 4, 50);
		random = "Hello world!";
		parser = new ParseJSON();
		ParseJSON.games = new Game[1];
	}
	
	@After
	public void tearDown() {
		teamOne = null;
		teamTwo = null;
		game = null;
		random = null;
		parser = null;
		ParseJSON.games = null;
	}

	@Test
	public void testInitializeGameArrayPastFail() {
		assertFalse(parser.initializeGameArrayPast(random));
	}
	
	@Test 
	public void testInitializeGameArrayFutureFail() {
		assertFalse(parser.initializeGameArrayFuture(random));
	}

	@Test
	public void testGetGames() {
		ParseJSON.games[0] = game;
		assertEquals(ParseJSON.games[0].getTeamOne(), teamOne);
	}
}
