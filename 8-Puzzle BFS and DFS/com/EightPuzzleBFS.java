/*
 * EightPuzzleBFS - Breadth First Search
 * Created by: Ritwij Nadagouda			Date: 09/18/2015
 * 
 * This program solves the standard 8-puzzle problem using Breadth First Search Technique.
 * The input to the program is a string which represents the initial state of the 8-puzzle board.
 * And then the Breadth First Search runs on the string to achieve the goal state.
 * 
 * The string input format is: 724506831 - where 0 represents the blank tile in puzzle.
 * The goal state which the program or the search should achieve is 012345678
 * 
 */

package com;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

class EightPuzzleBFS {

    Queue<String> nodeQueue = new LinkedList<String>();    									// Use of Queue Implemented using LinkedList for Storing All the Nodes in BFS.
    Map<String,Integer> depthOfState = new HashMap<String, Integer>(); 						// HashMap is implemented to avoid duplicate nodes and depth is maintained
    Map<String,String> nodeHistory = new HashMap<String,String>(); 							// To maintain the History of the state - Parent Node
    
    public static void main(String args[]){

    	Scanner inputState = new Scanner(System.in);
    	
    	String regex = "[0-8]+";															// Regular expression to check for string validity: It should only contain numbers between 0-8
    	String str = null;
    	
        System.out.println("Enter the initial state of the puzzle [Numbers 0-8]: "); 
        str = inputState.next();        													// The input string for the board i.e 0-8; 0 represents the blank state.
        
        if((str.matches(regex) && str.length() == 9)){
	        EightPuzzleBFS eigthPuzzleBFS = new EightPuzzleBFS();              								// Instance of the class EightPuzzleBFS
	        eigthPuzzleBFS.addToQueue(str, null);                                                   		// Add the initial state into the queue; new state being null initially
	
	        while(!eigthPuzzleBFS.nodeQueue.isEmpty()){
	        	
	            String currentState = eigthPuzzleBFS.nodeQueue.remove();
	            eigthPuzzleBFS.moveLeft(currentState);														// moveLeft - Moves the blank left.
	            eigthPuzzleBFS.moveUp(currentState);                                       					// moveUp - Moves the blank up.
	            eigthPuzzleBFS.moveRight(currentState);														// moveRight - Moves the blank right
	            eigthPuzzleBFS.moveDown(currentState);                                     					// moveDown - Moves the blank down.
	                                   							
	        }
        }    
        
	    System.out.println("Can't find solution- BFS ran out of memory!");
    }

    //Add method to add the new string to the Map and Queue
    void addToQueue(String newState, String previousState){
        if(!depthOfState.containsKey(newState)){
            int newValue = previousState == null ? 0 : depthOfState.get(previousState) + 1;
            depthOfState.put(newState, newValue);
            nodeQueue.add(newState);
            nodeHistory.put(newState, previousState);
        }
    }

    /*
     * The following helper methods takes string as current state of the board and operations to move the blank space are carried out if any movements are 
     * possible. If the blank space is moved then, the new configuration will be a new string with change in blank space, which is then added to the
     * HashMap and Stack. This new state is checked for solution i.e if it is the goal state. If so, the program terminates.
     */
    
    void moveUp(String currentState){
        int a = currentState.indexOf("0");
        if(a>2){
            String nextState = currentState.substring(0,a-3)+"0"+currentState.substring(a-2,a)+currentState.charAt(a-3)+currentState.substring(a+1);
           
            checkForGoalState(currentState, nextState);
        }
    }

    void moveDown(String presentState){
        int a = presentState.indexOf("0");
        if(a<6){
            String nextState = presentState.substring(0,a)+presentState.substring(a+3,a+4)+presentState.substring(a+1,a+3)+"0"+presentState.substring(a+4);
            
            checkForGoalState(presentState, nextState);
        }
    }
    void moveLeft(String presentState){
        int a = presentState.indexOf("0");
        if(a!=0 && a!=3 && a!=6){
            String nextState = presentState.substring(0,a-1)+"0"+presentState.charAt(a-1)+presentState.substring(a+1);
         
            checkForGoalState(presentState, nextState);
        }
    }
    void moveRight(String presentState){
        int a = presentState.indexOf("0");
        if(a!=2 && a!=5 && a!=8){
            String nextState = presentState.substring(0,a)+presentState.charAt(a+1)+"0"+presentState.substring(a+2);
            
            checkForGoalState(presentState, nextState);
        }
    }

    private void checkForGoalState(String previousState, String presentState) {										//Checks if the input string is goal string: if it is the prints the trace and solution, if not then adds the string to the stack
    	
        addToQueue(presentState, previousState);																	// If goal state not reached add to stack
        
        if(presentState.equals("012345678")) {
        	
        	System.out.println(" Goal state is reached at: " + depthOfState.get(presentState));
            String tracePath = presentState;
            
            while (tracePath != null) {																				//Print the path or trace from initial to goal state
                System.out.println(tracePath + " at " + depthOfState.get(tracePath));
                tracePath = nodeHistory.get(tracePath);
            }
            
            System.exit(0);
        }
    }

}