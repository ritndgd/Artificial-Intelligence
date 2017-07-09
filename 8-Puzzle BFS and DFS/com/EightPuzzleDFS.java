/*
 * EightPuzzleDFS - Depth First Search
 * Created by: Ritwij Nadagouda			Date: 09/18/2015
 * 
 * This program solves the standard 8-puzzle problem using Depth First Search Technique.
 * The input to the program is a string which represents the initial state of the 8-puzzle board.
 * And then the Depth First Search runs on the string to achieve the goal state.
 * 
 * The string input format is: 724506831 - where 0 represents the blank tile in puzzle.
 * The goal state which the program or the search should achieve is 012345678
 * 
 */

package com;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

class EightPuzzleDFS {

    Stack<String> nodeStack = new Stack<String>();    									// Using Stack to maintain all the nodes used in DFS
    Map<String,Integer> depthOfState = new HashMap<String, Integer>(); 					// HashMap is implemented to avoid duplicate nodes and depth is maintained
    Map<String,String> nodeHistory = new HashMap<String, String>(); 					// To maintain the History of the state - Parent Node
    
    public static void main(String args[]){

    	Scanner inputState = new Scanner(System.in);
    	
    	String regex = "[0-8]+";														// Regular expression to check for string validity: It should only contain numbers between 0-8
    	String str = null;
    	
        System.out.println("Enter the initial state of the puzzle [Numbers 0-8]: "); 
        str = inputState.next();        												// The input string for the board i.e 0-8; 0 represents the blank state.
               
        if((str.matches(regex) && str.length() == 9)){									// Checks if the entered string is valid
        	
	        EightPuzzleDFS eightPuzzleDFS = new EightPuzzleDFS();              			// Instance of the class EightPuzzleDFS
	        eightPuzzleDFS.add(str, null);                                             	// Push the initial state into the stack; new state being null initially
        
	        while(!eightPuzzleDFS.nodeStack.isEmpty()){
	        	
	            boolean result = false;
	            
	        	String presentState = eightPuzzleDFS.nodeStack.pop();
	        	
	            result = eightPuzzleDFS.moveLeft(presentState);                         // moveLeft - Moves the blank left.
	            if(result) {
	            	continue;
	            }
	            
	            result = eightPuzzleDFS.moveUp(presentState);							// moveUp - Moves the blank up.
	            if(result) {
	            	continue;
	            }
	            
	            result = eightPuzzleDFS.moveRight(presentState);                        // moveRight - Moves the blank right
	            if(result) {
	            	continue;
	            }
	            
	            result = eightPuzzleDFS.moveDown(presentState);                          // moveDown - Moves the blank down.
	            if(result) {
	            	continue;
	            }
	    
	            String parent = eightPuzzleDFS.nodeHistory.get(presentState);			// If no further branching or child nodes are possible the parent node is
	            																		// added to the stack and the loop continues.
	            if (parent != null) {
	                eightPuzzleDFS.nodeStack.add(parent);
	            }
	        }
	        
        System.out.println("Can't find solution- DFS ran out of memory!");
        
        }else{
        	System.out.println("Enter valid state for puzzle; Numbers between 0 - 8.");
        }
    }

    //Add method to add the new string to the Map and Queue
    boolean add(String newState, String previousState){
    	
    	boolean result = false;
    	
        if(!depthOfState.containsKey(newState)){
        	
        	int newValue = previousState == null ? 0 : depthOfState.get(previousState) + 1;
            depthOfState.put(newState, newValue);
            nodeStack.add(newState);
            nodeHistory.put(newState, previousState);
            result = true;
        }
       return result; 
    }


    /*
     * The following helper methods takes string as current state of the board and operations to move the blank space are carried out if any movements are 
     * possible. If the blank space is moved then, the new configuration will be a new string with change in blank space, which is then added to the
     * HashMap and Stack. This new state is checked for solution i.e if it is the goal state. If so, the program terminates.
     */
     
    boolean moveUp(String presentState){
    	boolean result = false;
        int index = presentState.indexOf("0");
        if(index>2){
            String nextState = presentState.substring(0,index-3)+"0"+presentState.substring(index-2,index)+presentState.charAt(index-3)+presentState.substring(index+1);
            result = checkForGoalState(presentState, nextState);
        }
        return result;
    }

    boolean moveDown(String presentState){
    	boolean result = false;
        int index = presentState.indexOf("0");
        if(index<6){
            String nextState = presentState.substring(0,index)+presentState.substring(index+3,index+4)+presentState.substring(index+1,index+3)+"0"+presentState.substring(index+4);
            result = checkForGoalState(presentState, nextState);
        }
        return result;
    }
    boolean moveLeft(String presentState){
    	boolean result = false;
        int index = presentState.indexOf("0");
        if(index!=0 && index!=3 && index!=6){
            String nextState = presentState.substring(0,index-1)+"0"+presentState.charAt(index-1)+presentState.substring(index+1);
            result = checkForGoalState(presentState, nextState);
        }
        return result;
    }
    boolean moveRight(String presentState){
    	boolean result = false;
        int index = presentState.indexOf("0");
        if(index!=2 && index!=5 && index!=8){
            String nextState = presentState.substring(0,index)+presentState.charAt(index+1)+"0"+presentState.substring(index+2);
            result = checkForGoalState(presentState, nextState); 
        }
        return result;
    }

    private boolean checkForGoalState(String previousState, String presentState) {							//Checks if the input string is goal string: if it is the prints the trace and solution, if not then adds the string to the stack
        boolean result = false;
        
        result = add(presentState, previousState);															// If goal state not reached add to stack
        
        if(presentState.equals("012345678")) {
        	
            System.out.println(" Goal state is reached at: " + depthOfState.get(presentState));
            
            String traceStates = presentState;
            
            while (traceStates != null) {																	//Print the path or trace from initial to goal state
            	
                System.out.println(traceStates + " at " + depthOfState.get(traceStates));
                traceStates = nodeHistory.get(traceStates);
            }
            System.exit(0);
        }
        
        return result;
    }

}