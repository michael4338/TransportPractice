package com.tw.trains.app.model;

import java.util.ArrayList;
import java.util.Iterator;

public class DataCenterStore {
	private ArrayList<Town> townsList = new ArrayList<Town>();
	private ArrayList<Track> tracksList = new ArrayList<Track>();

	
	public ArrayList<Town> getTownsList() {
		return townsList;
	}

	public void setTownsList(ArrayList<Town> townsList) {
		this.townsList = townsList;
	}

	public ArrayList<Track> getTracksList() {
		return tracksList;
	}

	public void setTracksList(ArrayList<Track> tracksList) {
		this.tracksList = tracksList;
	}
	
	public boolean addTown(String name){
		if(townExisted(name))
			return false;
		return townsList.add(new Town(name));
	}
	
	public boolean addTrack(String beginName, String endName, int distance){		
		if(trackExisted(beginName, endName)) 
			return false;
		
		addTown(beginName);
		addTown(endName);
		Track track = new Track(new Town(beginName), new Town(endName), distance);
		
		return tracksList.add(track);
	}
	
	public void clearData(){
		townsList.clear();
		tracksList.clear();
	}
	
	
	
	
	
	private boolean townExisted(String name){
		Iterator<Town> listIterator = townsList.iterator();
		while(listIterator.hasNext()){
			Town town = listIterator.next();
			if(town.equalsWithName(name)) 
				return true;
		}
		return false;
	}
	
	private boolean trackExisted(String beginName, String endName){
		Iterator<Track> listIterator = tracksList.iterator();
		while(listIterator.hasNext()){
			Track track = listIterator.next();
			if(track.equalsWithName(beginName, endName)) 
				return true;
		}
		return false;
	}
	
}
