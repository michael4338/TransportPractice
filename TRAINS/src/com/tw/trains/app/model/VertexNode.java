package com.tw.trains.app.model;

public class VertexNode {
	private String name = null;
	private int pos = 0;
	private int cost = 0;
	
	public VertexNode(String name, int pos, int cost){
		setName(name);
		setPos(pos);
		setCost(cost);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public String toString(){
		return "("+name+","+String.valueOf(pos)+","+String.valueOf(cost)+")";
	}
}
