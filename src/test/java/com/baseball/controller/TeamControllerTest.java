package com.baseball.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.jupiter.api.*;
import com.baseball.service.*;
import com.baseball.model.*;
import com.baseball.parseJSON.*;
import org.springframework.web.client.RestTemplate;

//JUnit tests on the TeamController class. Currently, all tests pass
public class TeamControllerTest {
	
	ParseJSON parser;
	BaseballAPIService api;
	TeamController test;
	RestTemplate template;
	Game game;
	Team teamOne;
	Team teamTwo;
	
	@BeforeEach
	public void setUp() {
		parser = new ParseJSON();
		teamOne = new Team("Detroit", "Tigers", "123");
		teamTwo = new Team("Chicago", "White Sox", "124");
		game = new Game(teamOne, teamTwo);
		parser.games = new Game[1];
		template = new RestTemplate();
		api = new BaseballAPIService(template);
		test = new TeamController(api);
		test.validAPIkey = true;
	}
	
	@After
	public void tearDown() {
		parser = null;
		teamOne = null;
		teamTwo = null;
		game = null;
		parser.games = null;
		template = null;
		api = null;
		test = null;
	}

	@Test
	public void testChangeDateSuccess() {
		assertEquals(test.changeDate("05022020"), "Date changed successfully. Current date: May 02 2020.");
		
	}
	
	@Test
	public void testChangeDateTooShort() {
		assertEquals(test.changeDate("5022020"), "5022020 is not a valid date entry. Please enter the date as MMDDYYYY.");
	}
	
	@Test
	public void testChangeDateTooLong() {
		assertEquals(test.changeDate("050220200"), "050220200 is not a valid date entry. Please enter the date as MMDDYYYY.");
	}
	
	@Test
	public void testChangeDateNonNumeric() {
		assertEquals(test.changeDate("0502202D"), "0502202D is not a valid date entry. Please enter the date as MMDDYYYY.");
	}
	
	@Test
	public void testChangeDateInvalidFeb29() {
		assertEquals(test.changeDate("02292021"), "02292021 is not a valid date entry. Please enter the date as MMDDYYYY.");
	}
	
	public void testChangeDateInvalidYear() {
		assertEquals(test.changeDate("05022026"), "Valid years are 2012 through 2024 inclusive. Please try again.");
	}

	@Test
	public void testGetGame() {
		assertEquals(test.getGame(1), "You can only use /scoreboard/gameID for games that have already been played. Please change the date to a date in the past and try again.");
	}
	
	@Test
	public void testAccessLevelSuccess() {
		assertEquals(test.setAccessLevel("production"), "Access level changed to \"production\"");
	}
	
	@Test
	public void testAccessLevelFailure() {
		assertEquals(test.setAccessLevel("test"), "test is an invalid access level. Valid values are \"trial\", \"production\", \"tracking\", and \"trial-tracking\"");
	}
}
