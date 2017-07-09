package com;

import java.util.ArrayList;

public class Node {

	private ArrayList<String> state = new ArrayList<>();
	private Node parent;
	private int depth = 0;
	private int heuristics = 0;
	
	public int getHeuristics() {
		return heuristics;
	}
	public void setHeuristics(int heuristics) {
		this.heuristics = heuristics;
	}
	public ArrayList<String> getState() {
		return state;
	}
	public void setState(ArrayList<String> state) {
		this.state = state;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	
}
