/*
 * IterativeDeepeningDFS - Iterative Deepening Depth First Search
 * Created by: Ritwij Nadagouda			Date: 09/25/2015
 * 
 * This program solves the standard 15-puzzle problem using Iterative Deepening Depth First Search Technique.
 * The input to the program is a string which represents the initial state of the 8-puzzle board.
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
import java.util.List;
import java.util.Map;


public class IterativeDeepeningDFS extends Actions{

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
	
	
	void lookForGoalState() {																	//The function checks for the goal state by implementing
		  																						//iterative deepening dfs by repeatedly calling depth limited
		  for(int depth = 0; depth < 1000; depth++) {											//search; as mentioned in text
			  System.out.println("Depth reached in Looking for goal = " +depth);
			  stateHistory.clear();
			  if(doDepthLimited(initialState, depth)){
				  return;
			  }
		  }		  
	  }
	

	boolean doDepthLimited(ArrayList<String> initialState, int depth){								//This function implements Depth Limited Search to search for 
																								//goal state 
		if(depth >= 0) {
			
			if(initialState.equals(goalState)){													//Checks if the state is goal state. If it is then prints the state
				System.out.println("Solution found");
				System.out.println(initialState.subList(0, 4).toString().replace("[", "").replace("]", "").replace(",", ""));
				System.out.println(initialState.subList(4, 8).toString().replace("[", "").replace("]", "").replace(",", ""));
				System.out.println(initialState.subList(8, 12).toString().replace("[", "").replace("]", "").replace(",", ""));
				System.out.println(initialState.subList(12, 16).toString().replace("[", "").replace("]", "").replace(",", "") +"\n");
				System.out.println("Number of Nodes Generated: "+numberOfNodes);
				return true;
			}
			
			for(int i=0; i<4; i++){
				ArrayList<String> newState = action.applyAction(initialState, i);				//Actions are applied on to the initial state of the board
				if(newState != null){
					if(!stateHistory.containsKey(newState)){
						numberOfNodes++;
						stateHistory.put(newState, initialState);								//DLS is called recursively
						boolean b = doDepthLimited(newState, depth-1);
						if(b){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	void printSolution(Map<ArrayList<String>, ArrayList<String>> stateHistory){					//Function prints the solution
		System.out.println("Number of Nodes Generated = "+numberOfNodes);
		for(ArrayList<String> key: stateHistory.keySet()){
			List<String> key1 = key;
			System.out.println(key1.subList(0, 4).toString().replace("[", "").replace("]", "").replace(",", ""));
			System.out.println(key1.subList(4, 8).toString().replace("[", "").replace("]", "").replace(",", ""));
			System.out.println(key1.subList(8, 12).toString().replace("[", "").replace("]", "").replace(",", ""));
			System.out.println(key1.subList(12, 16).toString().replace("[", "").replace("]", "").replace(",", "") +"\n");
		}	
	}
}
