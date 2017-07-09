package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class AstarHeuristicOne {
	
	String goalString = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15";								//goal state of the board
	List<String> goal = Arrays.asList(goalString.split(","));								
	ArrayList<String> goalState = new ArrayList<String>(goal);
	Actions action = new Actions();
	int numberOfNodes;
	
	void lookForGoal(ArrayList<String> initialState){
		
		Node node = new Node();
		node.setState(initialState);
		node.setDepth(0);
		node.setParent(null);
		node.setHeuristics(estimateMisplacedTiles(node.getState()));
		
		if((node.getState().equals(goalState))){
			System.out.println("Found");
			printSolution(node);
			return;
		}
		
		Comparator<Node> comparator = new HeuristicsComparator();
		PriorityQueue<Node> nodeQueue = new PriorityQueue<Node>(10, comparator);
		
		nodeQueue.add(node);
		Set<ArrayList<String>> explored_nodes = new HashSet<ArrayList<String>>();
		while(!nodeQueue.isEmpty()){
			Node currentNode = nodeQueue.remove();
			explored_nodes.add(currentNode.getState());
			
			for(int i=0; i<4; i++){
			
				ArrayList<String> newState = action.applyAction(currentNode.getState(), i);
				numberOfNodes++;
				Node newNodeState = new Node();
				newNodeState.setState(newState);
				newNodeState.setParent(currentNode);
				newNodeState.setDepth(currentNode.getDepth()+1);
				newNodeState.setHeuristics(currentNode.getDepth() + estimateMisplacedTiles(node.getState()));
				
				if(!explored_nodes.contains(newState)){
					if(newNodeState.getState().equals(goalState)){
						System.out.println("Solution found at Depth = " + newNodeState.getDepth());
						printSolution(newNodeState);
						return;
					}else{
						nodeQueue.add(newNodeState);
					}
				}
			}
		}
	}
	
	public int estimateMisplacedTiles(ArrayList<String> currentState){
    	int counter = 0;
    	
    	for(int i = 0; i<goalState.size(); i++){
    		if(!(goalState.get(i).equals(currentState.get(i)))){
    			counter++;
    		}
    	}
		return counter;
    	
    }
	
	void printSolution(Node currentState){
		System.out.println("Number of Nodes Generated = " + numberOfNodes);
		Stack<Node> traceStack = new Stack<Node>();
		  while(currentState != null) {
			  traceStack.push(currentState);
			  currentState = currentState.getParent();
		  }
		  while(!traceStack.isEmpty()) {
			  currentState = traceStack.pop();
			  ArrayList<String> state = currentState.getState();
			  System.out.println("---Board---");
			  System.out.println(state.subList(0, 4).toString().replace("[", "").replace(",", " ").replace("]", ""));
			  System.out.println(state.subList(4, 8).toString().replace("[", "").replace(",", " ").replace("]", ""));
			  System.out.println(state.subList(8, 12).toString().replace("[", "").replace(",", " ").replace("]", ""));
			  System.out.println(state.subList(12, 16).toString().replace("[", "").replace(",", "").replace("]", ""));
			  System.out.println("-------------\n");
			  
		  }
	}
	
}
