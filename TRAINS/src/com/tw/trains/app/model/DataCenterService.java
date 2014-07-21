package com.tw.trains.app.model;


public class DataCenterService {
	
	private static DataCenterService instance = null;
	
	private DataCenterCoreGraph datacoreGraph = new DataCenterCoreGraph();
	private DataCenterStore dataStore = new DataCenterStore();
	
	//if data change, updated is set to true, meaning that the graph should be regenerated
	private boolean updated = true; 
		
	//singleton 
	private DataCenterService(){
	}
	
	public static synchronized DataCenterService getInstance(){
		if(instance == null){
			instance = new DataCenterService();
		}
		return instance;	
	}
	
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	

	/*
	 * As a central service center, provide all the interfaces from model layer to view layer
	 * dispatch data modification operations to data store class
	 * dispatch searching operations to graph class
	 */
	public boolean addTrack(String beginName, String endName, int distance){
		updated = true;
		return dataStore.addTrack(beginName, endName, distance);
	}
	
	public int getDistance(String[] cmd){
		generateDataCoreGraph();
		return datacoreGraph.getDistance(cmd);
	}
	
	public int getTripsNumWithMaximumStops(String beginTown, String endTown, int maximumStops){
		generateDataCoreGraph();
		return datacoreGraph.getTripsNumWithMaximumStops(beginTown, endTown, maximumStops);
	}
	
	public int getTripsNumWithExactStops(String beginTown, String endTown, int exactStops){
		generateDataCoreGraph();
		return datacoreGraph.getTripsNumWithExactStops(beginTown, endTown, exactStops);
	}
	
	public int getTripsNumWithMaximumDistance(String beginTown, String endTown, int maximumDistance){
		generateDataCoreGraph();
		return datacoreGraph.getTripsNumWithMaximumDistance(beginTown, endTown, maximumDistance);
	}
	
	public int getShortestTrip(String beginTown, String endTown){
		generateDataCoreGraph();
		return datacoreGraph.getShortestTrip(beginTown, endTown);
	}
	
	
	
	


	private void generateDataCoreGraph(){
		if(updated){
			datacoreGraph.generateGraph(dataStore.getTownsList(), dataStore.getTracksList());
			updated = false;
		}
	}
	
	
	/*
	 * For unit testing
	 */
	public void clearAllData(){
		dataStore = new DataCenterStore();
		datacoreGraph = new DataCenterCoreGraph();
		updated = true; 
	}
	
	
}
