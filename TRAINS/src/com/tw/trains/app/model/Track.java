package com.tw.trains.app.model;

public class Track {
	private Town beginTown = null;
	private Town endTown = null;
	private int distance = 0;
	
	public Track(Town beginTown, Town endTown, int distance){
		setBeginTown(beginTown);
		setEndTown(endTown);
		setDistance(distance);
	}

	public Town getBeginTown() {
		return beginTown;
	}
	public void setBeginTown(Town beginTown) {
		this.beginTown = beginTown;
	}
	public Town getEndTown() {
		return endTown;
	}
	public void setEndTown(Town endTown) {
		this.endTown = endTown;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public boolean equalsWithName(String beginName, String endName){
		return equalsWithBeginName(beginName) && equalsWithEndName(endName);
	}
	
	public boolean equalsWithBeginName(String name){
		return beginTown.equalsWithName(name);
	}
	
	public boolean equalsWithEndName(String name){
		return endTown.equalsWithName(name);
	}
	
	public String toString(){
		String beginStr = "["+beginTown.toString()+"]";
		String endStr = "["+endTown.toString()+"]";
		String distStr = "("+String.valueOf(distance)+")";
		
		return beginStr+"->"+endStr+distStr;
	}
	
}



