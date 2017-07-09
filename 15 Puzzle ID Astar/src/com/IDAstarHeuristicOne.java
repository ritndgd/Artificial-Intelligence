/*
 * IterativeDeepeningAstar - Iterative Deepening Astar
 * Created by: Ritwij Nadagouda			Date: 10/2/2015
 * 
 * This program solves the standard 15-puzzle problem using IterativeDeepeningAstar - Iterative Deepening Astar Technique.
 * The input to the program is a string which represents the initial state of the 15-puzzle board.
 * And then the IDDFS runs on the string to achieve the goal state.
 * 
 * The string input format is: {1,0,2,4,5,7,3,8,9,6,11,12,13,10,14,15} - where 0 represents the blank tile in puzzle.
 * The goal state which the program or the search should achieve is {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0}
 * 
 */

package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;


public class IDAstarHeuristicOne{

	Set<ArrayList<String>> exploredStates = new HashSet<>();
	Map<ArrayList<String>, ArrayList<String>> stateHistory = new HashMap<>();
	Map<ArrayList<String>, Integer> levelDepth = new HashMap<>();
	int numberOfNodes = 0;
	Actions action = new Actions();
	
	String initialString = "2,6,10,3,1,4,7,11,8,5,9,15,12,13,14,0"; 							//initial state of the board where 0 represents the blank space
	String goalString = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15";								//goal state of the board 
	
	List<String> initial = Arrays.asList(initialString.split(","));								// The given Strings are split at "," and the stored
	List<String> goal = Arrays.asList(goalString.split(","));									// to List.
	
	
	ArrayList<String> initialState = new ArrayList<String>(initial);							//Then the list is converted to ArrayList for easy manipulation
	ArrayList<String> goalState = new ArrayList<String>(goal);
	
	
	Node lookForGoalState() {																	
		  																						
		Node initialNode = new Node();
		initialNode.setState(initialState);
		initialNode.setDepth(0);
		initialNode.setParent(null);
		initialNode.setHeuristics(estimateMisplacedTiles(initialNode.getState()));
		Node solution = null;
		int currentCostBound = estimateMisplacedTiles(initialNode.getState());
		while(solution == null){
			
			stateHistory.put(initialNode.getState(), null);
			solution = doDepthLimited(initialNode, currentCostBound);
		}
		
		return solution;
	 }
	

	Node doDepthLimited(Node initialNode, int currentCostBound){								

			if(initialNode.getState().equals(goalState)){													//Checks if the state is goal state. If it is then prints the state
				printSolution(initialNode);
				return initialNode;
			}
			ConcurrentLinkedQueue<ArrayList<String>> possibleStates = new ConcurrentLinkedQueue<ArrayList<String>>();
			possibleStates = action.applyAction(initialNode.getState());
			
			for(ArrayList<String> newState: possibleStates){			//Actions are applied on to the initial state of the board
				
				int cost = initialNode.getDepth()+1;
					if((cost + estimateMisplacedTiles(newState)) <= currentCostBound){
						if(!stateHistory.containsKey(newState)){
							Node newNode = new Node();
							newNode.setState(newState);
							newNode.setDepth(initialNode.getDepth()+1);
							newNode.setHeuristics(estimateMisplacedTiles(newNode.getState()));
							stateHistory.put(newNode.getState(), initialNode.getState());
							numberOfNodes++;
							Node possibleSolution = doDepthLimited(newNode, currentCostBound);
							if(possibleSolution != null){
								printSolution(newNode);
								return possibleSolution;
							}
						}
					}
				}	
			
			return null;
	}
	
	void printSolution(Node currentState){
		
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
	public int estimateMisplacedTiles(ArrayList<String> currentState){
    	int counter = 0;
    	
    	for(int i = 0; i<goalState.size(); i++){
    		if(!(goalState.get(i).equals(currentState.get(i)))){
    			counter++;
    		}
    	}
		return counter;
    	
    }
}
