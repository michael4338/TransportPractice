package com.tw.trains.app.model;

public class Town {
	
	private String name = null;

	public Town(){
	}
	
	public Town(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean equalsWithName(String name){
		return this.name.equalsIgnoreCase(name);
	}
	
	public String toString(){
		return name;
	}
	
}
