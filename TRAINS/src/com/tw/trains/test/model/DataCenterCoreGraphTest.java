package com.tw.trains.test.model;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tw.trains.app.model.DataCenterCoreGraph;
import com.tw.trains.app.model.Town;
import com.tw.trains.app.model.Track;

public class DataCenterCoreGraphTest {
	
	private static DataCenterCoreGraph datacoreGraph = null; 
	private static ArrayList<Town> townsList = null;
	private static ArrayList<Track> tracksList = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		datacoreGraph = new DataCenterCoreGraph();
		townsList = new ArrayList<Town>();
		tracksList = new ArrayList<Track>();
		
		Town t1 = new Town("A");
		Town t2 = new Town("B");
		Town t3 = new Town("C");
		Town t4 = new Town("D");
		Town t5 = new Town("E");
		
		townsList.add(t1);
		townsList.add(t2);
		townsList.add(t3);
		townsList.add(t4);
		townsList.add(t5);

		tracksList.add(new Track(t1, t2, 5));
		tracksList.add(new Track(t1, t4, 5));
		tracksList.add(new Track(t1, t5, 7));
		tracksList.add(new Track(t2, t3, 4));
		tracksList.add(new Track(t3, t4, 8));
		tracksList.add(new Track(t3, t5, 2));
		tracksList.add(new Track(t4, t3, 8));
		tracksList.add(new Track(t4, t5, 6));
		tracksList.add(new Track(t5, t2, 3));
		
		datacoreGraph.generateGraph(townsList, tracksList);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		datacoreGraph = null;
		townsList = null;
		tracksList = null;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGenerateGraph(){
		
		String graphToStr = datacoreGraph.toString();
		String expectedStr = "";
		expectedStr += "[(A,0,-1), (B,1,5), (D,3,5), (E,4,7)]\n";
		expectedStr += "[(B,1,-1), (C,2,4)]\n";
		expectedStr += "[(C,2,-1), (D,3,8), (E,4,2)]\n";
		expectedStr += "[(D,3,-1), (C,2,8), (E,4,6)]\n";
		expectedStr += "[(E,4,-1), (B,1,3)]\n";
		
		assertTrue(graphToStr.equals(expectedStr));
	}
	
	@Test
	public void testGetDistance1(){
		String cmd = "A-B-C";
		int distance = datacoreGraph.getDistance(cmd.split("-"));
		assertEquals(distance, 9);
	}
	
	@Test
	public void testGetDistance2(){	
		String cmd = "A-D";
		int distance = datacoreGraph.getDistance(cmd.split("-"));
		assertEquals(distance, 5);
	}
	
	@Test
	public void testGetDistance3(){
		String cmd = "A-D-C";
		int distance = datacoreGraph.getDistance(cmd.split("-"));
		assertEquals(distance, 13);
	}
	
	@Test
	public void testGetDistance4(){
		String cmd = "A-E-B-C-D";
		int distance = datacoreGraph.getDistance(cmd.split("-"));
		assertEquals(distance, 22);
	}
	
	@Test
	public void testGetDistance5(){
		String cmd = "A-E-D";
		int distance = datacoreGraph.getDistance(cmd.split("-"));
		assertEquals(distance, -1);
	}
	
	@Test
	public void testGetTripsNum1(){
		assertEquals(datacoreGraph.getTripsNumWithNoDuplicate("A", "C"), 4);
	}
	
	@Test
	public void testGetTripsNum2(){	
		assertEquals(datacoreGraph.getTripsNumWithNoDuplicate("A", "D"), 3);
	}
	
	@Test
	public void testGetTripsNum3(){
		assertEquals(datacoreGraph.getTripsNumWithNoDuplicate("B", "B"), 2);
	}
	
	@Test
	public void testGetTripsNum4(){
		assertEquals(datacoreGraph.getTripsNumWithNoDuplicate("D", "A"), 0);
	}
	
	@Test
	public void testGetTripsNum5(){
		assertEquals(datacoreGraph.getTripsNumWithNoDuplicate("D", "E"), 2);
	}
	
	@Test
	public void testGetTripsNum6(){
		assertEquals(datacoreGraph.getTripsNumWithNoDuplicate("C", "E"), 2);
	}
	
	@Test
	public void testGetTripsNum7(){
		assertEquals(datacoreGraph.getTripsNumWithNoDuplicate("B", "E"), 2);
	}
	
	@Test
	public void testGetTripsNum8(){
		assertEquals(datacoreGraph.getTripsNumWithNoDuplicate("D", "E"), 2);
	}
	
	@Test
	public void testGetTripsNum9(){
		assertEquals(datacoreGraph.getTripsNumWithNoDuplicate("D", "D"), 2);
	}
	
	@Test
	public void testGetTripsNum10(){
		assertEquals(datacoreGraph.getTripsNumWithNoDuplicate("E", "A"), 0);
	}
	
	@Test
	public void testGetTripsNumWithMaximumStops1(){
		assertEquals(datacoreGraph.getTripsNumWithMaximumStops("C", "C", 3), 2);
	}
	
	@Test
	public void testGetTripsNumWithMaximumStops2(){	
		assertEquals(datacoreGraph.getTripsNumWithMaximumStops("B", "B", 4), 2);
	}
	
	@Test
	public void testGetTripsNumWithExactStops1(){
		assertEquals(datacoreGraph.getTripsNumWithExactStops("A", "C", 4), 3);
	}
	
	@Test
	public void testGetTripsNumWithMaximumDistance1(){
		assertEquals(datacoreGraph.getTripsNumWithMaximumDistance("C", "C", 30), 7);
	}	
	
	@Test
	public void testGetShortestTrip1(){
		assertEquals(datacoreGraph.getShortestTrip("A", "C"), 9);
	}
	
	@Test
	public void testGetShortestTrip2(){	
		assertEquals(datacoreGraph.getShortestTrip("B", "B"), 9);
	}
	
	@Test
	public void testGetShortestTrip3(){
		assertEquals(datacoreGraph.getShortestTrip("E", "A"), -1);
	}
	
}
