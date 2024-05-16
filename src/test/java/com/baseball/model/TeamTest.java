package com.baseball.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.jupiter.api.*;

//JUnit tests on the Team class. Currently, all tests pass
public class TeamTest {
	
	Team testGet;
	Team testSet;
	Team testFullConstructor;

	@BeforeEach
	public void setUp() {
		testGet = new Team("Chicago", "White Sox", "124");
		testSet = new Team();
		testFullConstructor = new Team("Detroit", "Tigers", "123", 5, 160, 0);
	}
	
	@After
	public void tearDown() {
		testGet = null;
		testSet = null;
		testFullConstructor = null;
	}

	@Test
	public void testGetCity() {
		assertEquals(testGet.getCity(), "Chicago");
	}
	
	@Test
	public void testSetCity() {
		testSet.setCity("Boston");
		assertEquals(testSet.getCity(), "Boston");
	}
	
	@Test
	public void testGetMascot() {
		assertEquals(testGet.getMascot(), "White Sox");
	}
	
	@Test
	public void testSetMascot() {
		testSet.setMascot("Red Sox");
		assertEquals(testSet.getMascot(), "Red Sox");
	}

	@Test
	public void testGetId() {
		assertEquals(testGet.getId(), "124");
	}

	@Test
	public void testSetId() {
		testSet.setId("111");
		assertEquals(testSet.getId(), "111");
	}
	
	@Test
	public void testGetRuns() {
		assertEquals(testFullConstructor.getRuns(), 5);
	}

	@Test
	public void testSetRuns() {
		testSet.setRuns(100);
		assertEquals(testSet.getRuns(), 100);
	}
	
	@Test
	public void testGetWins() {
		assertEquals(testFullConstructor.getWins(), 160);
	}

	@Test
	public void testSetWins() {
		testSet.setWins(3);
		assertEquals(testSet.getWins(), 3);
	}

	@Test
	public void testGetLosses() {
		assertEquals(testFullConstructor.getLosses(), 0);
	}

	@Test
	public void testSetLosses() {
		testSet.setLosses(5);
		assertEquals(testSet.getLosses(), 5);
	}

	@Test
	public void testToString() {
		assertEquals(testGet.toString(), "Chicago White Sox");
	}

	@Test
	public void testToString1() {
		assertEquals(testFullConstructor.toString(), "Detroit Tigers");
	}
	
	@Test
	public void testEquals() {
		testSet.setCity("Chicago");
		testSet.setMascot("White Sox");
		testSet.setId("124");
		testSet.equals(testFullConstructor);
	}

}
