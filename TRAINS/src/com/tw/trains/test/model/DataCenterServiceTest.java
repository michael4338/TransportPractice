package com.tw.trains.test.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tw.trains.app.model.DataCenterService;

public class DataCenterServiceTest {

	private static DataCenterService dataCenterService = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dataCenterService = DataCenterService.getInstance();
		dataCenterService.clearAllData();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dataCenterService.clearAllData();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddTrack1() {
		boolean result = true;
		
		result &= dataCenterService.addTrack("b", "a", 1);
		result &= dataCenterService.addTrack("i", "e", 9);
		result &= dataCenterService.addTrack("c", "b", 4);
		result &= dataCenterService.addTrack("c", "e", 6);
		result &= dataCenterService.addTrack("d", "c", 5);
		result &= dataCenterService.addTrack("a", "c", 3);
		result &= dataCenterService.addTrack("a", "f", 2);
		result &= dataCenterService.addTrack("b", "g", 1);
		result &= dataCenterService.addTrack("e", "f", 4);
		result &= dataCenterService.addTrack("f", "d", 6);
		result &= dataCenterService.addTrack("g", "f", 5);
		result &= dataCenterService.addTrack("h", "e", 2);
		result &= dataCenterService.addTrack("i", "d", 7);
		result &= dataCenterService.addTrack("a", "h", 3);
		result &= dataCenterService.addTrack("d", "i", 8);
		result &= dataCenterService.addTrack("d", "g", 3);
		
		assertTrue(result);
	}
	
	@Test
	public void testAddTrack2() {
		assertFalse(dataCenterService.addTrack("e", "f", 2));
	}
	

	@Test
	public void testGetDistance1() {
		int distance = 22;
		String route = "a-c-e-f-d-g";
		assertEquals(distance, dataCenterService.getDistance(route.split("-")));
	}
	
	@Test
	public void testGetDistance2() {
		int distance = -1;
		String route = "a-a";
		assertEquals(distance, dataCenterService.getDistance(route.split("-")));
	}
	
	@Test
	public void testGetDistance3() {
		int distance = 29;
		String route = "d-i-d-g-f-d";
		assertEquals(distance, dataCenterService.getDistance(route.split("-")));
	}

	@Test
	public void testGetTripsNumWithMaximumStops1() {
		int maxStops = 5;
		String beginTown = "a";
		String endTown = "e";
		
		int tripsNum = 6;
		assertEquals(tripsNum, dataCenterService.getTripsNumWithMaximumStops(beginTown, endTown, maxStops));
	}
	
	@Test
	public void testGetTripsNumWithMaximumStops2() {
		int maxStops = 10;
		String beginTown = "b";
		String endTown = "d";
		
		int tripsNum = 96;
		assertEquals(tripsNum, dataCenterService.getTripsNumWithMaximumStops(beginTown, endTown, maxStops));
	}
	
	@Test
	public void testGetTripsNumWithMaximumStops3() {
		int maxStops = 20;
		String beginTown = "c";
		String endTown = "f";
		
		int tripsNum = 20686;
		assertEquals(tripsNum, dataCenterService.getTripsNumWithMaximumStops(beginTown, endTown, maxStops));
	}
	
	@Test
	public void testGetTripsNumWithExactStops1() {
		int exactStops = 0;
		String beginTown = "d";
		String endTown = "d";
		
		int tripsNum = 0;
		assertEquals(tripsNum, dataCenterService.getTripsNumWithExactStops(beginTown, endTown, exactStops));
	}

	@Test
	public void testGetTripsNumWithExactStops2() {
		int exactStops = 5;
		String beginTown = "d";
		String endTown = "d";
		
		int tripsNum = 4;
		assertEquals(tripsNum, dataCenterService.getTripsNumWithExactStops(beginTown, endTown, exactStops));
	}

	@Test
	public void testGetTripsNumWithMaximumDistance1() {
		int maxDistance = 10;
		String beginTown = "a";
		String endTown = "a";
		
		int tripsNum = 1;
		assertEquals(tripsNum, dataCenterService.getTripsNumWithMaximumDistance(beginTown, endTown, maxDistance));
	}
	
	@Test
	public void testGetTripsNumWithMaximumDistance2() {
		int maxDistance = 30;
		String beginTown = "a";
		String endTown = "a";
		
		int tripsNum = 9;
		assertEquals(tripsNum, dataCenterService.getTripsNumWithMaximumDistance(beginTown, endTown, maxDistance));
	}
	
	@Test
	public void testGetTripsNumWithMaximumDistance3() {
		int maxDistance = 50;
		String beginTown = "a";
		String endTown = "a";
		
		int tripsNum = 83;
		assertEquals(tripsNum, dataCenterService.getTripsNumWithMaximumDistance(beginTown, endTown, maxDistance));
	}

	@Test
	public void testGetShortestTrip1() {
		String beginTown = "a";
		String endTown = "a";
		
		int distance = 8;
		assertEquals(distance, dataCenterService.getShortestTrip(beginTown, endTown));
	}
	
	@Test
	public void testGetShortestTrip2() {
		String beginTown = "f";
		String endTown = "a";
		
		int distance = 16;
		assertEquals(distance, dataCenterService.getShortestTrip(beginTown, endTown));
	}
	
	@Test
	public void testGetShortestTrip3() {
		String beginTown = "h";
		String endTown = "g";
		
		int distance = 15;
		assertEquals(distance, dataCenterService.getShortestTrip(beginTown, endTown));
	}

}
