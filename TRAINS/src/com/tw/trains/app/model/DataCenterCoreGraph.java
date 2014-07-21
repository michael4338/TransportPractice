package com.tw.trains.app.model;


import java.util.ArrayList;
import java.util.Collections;

public class DataCenterCoreGraph {
	
	private static final int INFINITE_BIG = -1;
	private static final int INVALID_VERTEX_POS = -1;
	
	private ArrayList<ArrayList<VertexNode>> vertexTable = new ArrayList<ArrayList<VertexNode>>();
	SearchingUtility util = null;
	
	public DataCenterCoreGraph(){
	}
	
	/*
	 * Generate Graph data structure for various of searching algorithm 
	 */
	public void generateGraph(ArrayList<Town> townsList, ArrayList<Track> tracksList){
		addVetexs(townsList);
		addWedges(tracksList);
	}
	
	/*
	 * provide searching operations related methods for data service to call
	 */
	public int getDistance(String[] cmd){
		int distance = 0;
		
		int startPos = getVertexPos(cmd[0]);
		if(startPos == INVALID_VERTEX_POS) return -1;
		
		for(int i=1; i<cmd.length; i++){
			int currPos = getVertexPos(cmd[i]);
			if(currPos == INVALID_VERTEX_POS) return -1;
			VertexNode vertexNode = getTragetNeightbor(startPos, currPos);
			if(vertexNode == null) return -1;
			distance += vertexNode.getCost();
			
			startPos = currPos;
		}
		
		return distance;
	}
	
	public int getTripsNumWithMaximumStops(String beginTown, String endTown, int maximumStops){
		util = new SearchingUtility(maximumStops, 
				SearchingUtility.NO_LIMATION, 
				SearchingUtility.NO_LIMATION,
				true,
				true);
		return callDFS(beginTown, endTown);
	}
	
	public int getTripsNumWithExactStops(String beginTown, String endTown, int exactStops){
		util = new SearchingUtility(SearchingUtility.NO_LIMATION, 
				exactStops, 
				SearchingUtility.NO_LIMATION,
				true,
				true);
		
		return callDFS(beginTown, endTown);
	}
	
	public int getTripsNumWithMaximumDistance(String beginTown, String endTown, int maximumDistance){
		util = new SearchingUtility(SearchingUtility.NO_LIMATION, 
				SearchingUtility.NO_LIMATION, 
				maximumDistance,
				true,
				true);
		return callDFS(beginTown, endTown);
	}
	
	public int getTripsNumWithNoDuplicate(String beginTown, String endTown){
		util = new SearchingUtility(SearchingUtility.NO_LIMATION, 
				SearchingUtility.NO_LIMATION, 
				SearchingUtility.NO_LIMATION,
				false,
				false);
		return callDFS(beginTown, endTown);
	}
	
	public int getShortestTrip(String beginTown, String endTown){
		getTripsNumWithNoDuplicate(beginTown, endTown);
		
		ArrayList<ArrayList<String>> allTrips = util.getAllTrips();
		ArrayList<Integer> distanceList = new ArrayList<Integer>();
		
		for(int i=0; i<allTrips.size(); i++)
			distanceList.add(getDistance(listToArray(allTrips.get(i))));
		Collections.sort(distanceList);
		
		if(distanceList.size() > 0)
			return distanceList.get(0);
		
		return -1;
	}
	
	public String toString(){
		String finalStr = "";
		for(int i=0; i<vertexTable.size(); i++)
			finalStr += vertexTable.get(i).toString()+"\n";
		
		return finalStr;
	}
	
	
	
	
	
		
	private void addVetexs(ArrayList<Town> townsList){
		VertexNode vertexNode = null;
		
		for(int i=0; i<townsList.size(); i++){
			ArrayList<VertexNode> newList = new ArrayList<VertexNode>();
			Town town = townsList.get(i);
			vertexNode = new VertexNode(town.getName(), i, INFINITE_BIG);
			newList.add(vertexNode);
			vertexTable.add(newList);
		}
	}
	
	private void addWedges(ArrayList<Track> tracksList){
		VertexNode vertexNode = null;
		
		for (int i = 0; i < vertexTable.size(); i++) {
			String townName = getVertexName(i);
			
			for (int j = 0; j < tracksList.size(); j++) {
				Track track = tracksList.get(j);
				if (track.equalsWithBeginName(townName)) {
					int endPos = getVertexPos(track.getEndTown().getName());
					if (endPos != INVALID_VERTEX_POS) {
						vertexNode = new VertexNode(track.getEndTown().getName(), endPos, track.getDistance());
						vertexTable.get(i).add(vertexNode);
					}
				}
			}
		}
	}
	
	private int getVertexPos(String vertexName){
		for (int i = 0; i < vertexTable.size(); i++)
			if(getVertexName(i).equalsIgnoreCase(vertexName))
				return i;
		return INVALID_VERTEX_POS;
	}
	
	private String getVertexName(int vertexPos){
		return vertexTable.get(vertexPos).get(0).getName();
	}
	
	private VertexNode getFirstNeighbor(int vertex){
		if(vertexPosValid(vertex))
			return vertexTable.get(vertex).get(1);
		return null;
	}
	
	private VertexNode getNextNeighbor(int startVertex, int targetVertex){
		if(!vertexPosValid(startVertex))
			return null;
		
		ArrayList<VertexNode> vertexList = vertexTable.get(startVertex);
		for(int i=0; i<vertexList.size(); i++)
			if(vertexList.get(i).getPos() == targetVertex && vertexList.size() > (i+1))
				return vertexList.get(i+1);
		
		return null;
	}
	
	private VertexNode getTragetNeightbor(int startVertex, int targetVertex){
		if(!vertexPosValid(startVertex))
			return null;
		
		ArrayList<VertexNode> vertexList = vertexTable.get(startVertex);
		for(int i=0; i<vertexList.size(); i++)
			if(vertexList.get(i).getPos() == targetVertex)
				return vertexList.get(i);
		
		return null;
	}
	
	private boolean vertexPosValid(int vertex){
		if(vertex != INVALID_VERTEX_POS && vertex < vertexTable.size())
			if(vertexTable.get(vertex).size() > 1)
				return true;
		return false;
	}
	
	private String[] listToArray(ArrayList<String> stringList){
		String[] strArray = new String[stringList.size()];
		
		for(int i=0; i<stringList.size(); i++)
			strArray[i] = stringList.get(i);
		
		return strArray;
	}
	
	/*
	 * call Deep First Searching algorithm to travel the graph
	 */
	private int callDFS(String beginTown, String endTown){
		deepFirstSearch(getVertexPos(beginTown), getVertexPos(endTown), 0);
		return util.getTripsNum();				
	}
	
	
	/*
	 * curPos is current town, it is initially the beginning town, and will change during recursion
	 * endPos is the target town, it does not change during recursion
	 * stop is the current deep of recursion, which also presents the current number of towns in the trip
	 */
	private void deepFirstSearch(int curPos, int endPos, int stop){	
		
		String curStop = getVertexName(curPos);
		
		/*
		 * if do not meet the limitation or do not allow duplicate towns in trip, 
		 * quit this time of recursion
		 */
		if(!util.evaluateLimitation(stop) || 
				!util.evaluateDuplicateStop(curStop, stop, curPos==endPos)) return;
		
		util.setWorkingListItem(getVertexName(curPos), stop);
		
		if(curPos==endPos && stop != 0) {
			//we get a new trip, if meets the limitation of exact stops number
			if(util.evaluateExactStops(stop)) util.addWorkingListToResult(stop);
			//recursion will keep on after reaches the target town if it's set as keep moving
			if(!util.evaluateKeepMoving()) return ;
		}
		
		VertexNode nextVertexNode = getFirstNeighbor(curPos);
		
		/*
		 * get all neighbors of current town and do recursion
		 */
		while(nextVertexNode != null){
			util.setWorkingDistanceListItem(nextVertexNode.getCost(), stop);
			deepFirstSearch(nextVertexNode.getPos(), endPos, stop+1);
			nextVertexNode = getNextNeighbor(curPos, nextVertexNode.getPos());
		}
	}
	
}








