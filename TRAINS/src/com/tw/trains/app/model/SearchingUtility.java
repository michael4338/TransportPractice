package com.tw.trains.app.model;

import java.util.ArrayList;

public class SearchingUtility {
	public final static int NO_LIMATION = -1;
	
	//the limitations according to which we control recursive searching progress 
	private int maximumStops = NO_LIMATION;     //stop recursion if stops number is over this value
	private int exactStops = NO_LIMATION;       //record new generated trip if current stops number matches this value
	private int maximumDistance = NO_LIMATION;  //stop recursion if accumulated distance is over this value
	private boolean keepMoving = true;          //whether to keep searching after reaching the end town 
	private boolean duplicateStop = true;       //whether allow duplicate town in the trip
			
	//working list to hold the town name during processing
	private ArrayList<String> workingList = new ArrayList<String>();  
	
	//working list to hold the distance between towns during processing
	private ArrayList<Integer> workingDistanceList = new ArrayList<Integer>(); 
	
	//the final results
	private ArrayList<ArrayList<String>> searchingResultsList = new ArrayList<ArrayList<String>>();
	
	public SearchingUtility(int maximumStops, int exactStops, int maximumDistance, boolean keepMoving, boolean duplicateStop){
		this.maximumStops = maximumStops;
		this.exactStops = exactStops;
		this.maximumDistance = maximumDistance;
		this.keepMoving = keepMoving;
		this.duplicateStop = duplicateStop;
	}
	
	public void setWorkingListItem(String value, int pos){
		if(pos < 0 || pos > workingList.size()) return ;
		else if(pos == workingList.size()) workingList.add(value);
		else workingList.set(pos, value);
	}
	
	public void setWorkingDistanceListItem(int value, int pos){
		if(pos < 0 || pos > workingDistanceList.size()) return ;
		else if(pos == workingDistanceList.size()) workingDistanceList.add(value);
		else workingDistanceList.set(pos, value);		
	}
	
	public void addWorkingListToResult(int endIndex){
		searchingResultsList.add(cloneWorkingList(endIndex));
	}
	
	/*
	 * control the recursive progress by evaluate all the limitations
	 */
	public boolean evaluateLimitation(int stops){
		boolean result = true;
		result &= maximumStops == NO_LIMATION ? true : stops<=maximumStops;
		result &= exactStops == NO_LIMATION ? true : stops<=exactStops;
		result &= maximumDistance == NO_LIMATION ? true: getDistanceFromWorkingList(stops) < maximumDistance; 
		
		return result;
	}
	
	public boolean evaluateKeepMoving(){
		return keepMoving;
	}
	
	public boolean evaluateDuplicateStop(String curStop, int curPos, boolean completed){
		return duplicateStop ? true : !duplicateStopExisted(curStop, curPos-1, completed);
	}
	
	public boolean evaluateExactStops(int stops){
		return  exactStops == NO_LIMATION ? true : stops==exactStops;
	}
	
	public String toString(){
		String finalStr = "";
		for(int i=0; i<searchingResultsList.size(); i++)
			finalStr += searchingResultsList.get(i).toString()+"\n";
		
		return finalStr;
	}
	
	public int getTripsNum(){
		return searchingResultsList.size();
	}
	
	public ArrayList<ArrayList<String>> getAllTrips(){
		return searchingResultsList;
	}
	
	
	
	
	
	private boolean duplicateStopExisted(String curStop, int curPos, boolean completed){
		if(curPos < 0 || curPos > workingList.size()) return false;
		else if(completed && curStop.equalsIgnoreCase(workingList.get(0))) return false;
		for(int i=0; i<curPos && i<workingList.size(); i++)
			if(workingList.get(i).equalsIgnoreCase(curStop))
				return true;
			
		return false;
	}
	
	private int getDistanceFromWorkingList(int endIndex){
		int result = 0;
		for(int i=0; i<workingDistanceList.size() && i<endIndex; i++)
			result += workingDistanceList.get(i);
		return result;
	}
	
	private ArrayList<String> cloneWorkingList(int endIndex){
		ArrayList<String> tmpList = new ArrayList<String>();
		for(int i=0; i<workingList.size() && i<=endIndex; i++)
			tmpList.add(workingList.get(i));
		
		return tmpList;
	}
	
	
	
}
