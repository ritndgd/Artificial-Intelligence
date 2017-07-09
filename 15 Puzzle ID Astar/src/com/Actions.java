/*
 * Actions - Performs actions on the States of the board left, right, up, down
 * Created by: Ritwij Nadagouda			Date: 09/25/2015
 * 
 * The class has a method applyAction which accpets the arraylist and an integer value i as parameter and then based on the value of i
 * performs the action and then returns the new state if found else returns null
 * 
 */

package com;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Actions {

	ConcurrentLinkedQueue<ArrayList<String>> possibleActions = new ConcurrentLinkedQueue<>();
	ArrayList<String> newState = new ArrayList<>();
	
	ConcurrentLinkedQueue<ArrayList<String>> applyAction(ArrayList<String> initialState){
		
		newState = moveDown(initialState);
		if(newState != null){
			possibleActions.add(newState);
		}
		
		newState = moveUp(initialState);
		if(newState != null){
			possibleActions.add(newState);
		}
		
		newState = moveLeft(initialState);
		if(newState != null){
			possibleActions.add(newState);
		}
		
		newState = moveRight(initialState);
		if(newState != null){
			possibleActions.add(newState);
		}
		
		return possibleActions;
	}
	ArrayList<String> moveRight(ArrayList<String> initialState){
		ArrayList<String> tempState = null;
		
		int index = initialState.indexOf("0");
		if(index != 3 && index != 7 && index != 11 && index != 15){
			
			tempState = new ArrayList<>();
			tempState.addAll(initialState.subList(0, index));
			tempState.add(initialState.get(index+1));
			tempState.add("0");
			tempState.addAll(initialState.subList(index+2, initialState.size()));
		   	
        }
		return tempState;
	}
	ArrayList<String> moveLeft(ArrayList<String> initialState){
		ArrayList<String> tempState = null;
		
		int index = initialState.indexOf("0");
		if(index != 0 && index != 4 && index != 8 && index != 12){
			
			tempState = new ArrayList<>();
			tempState.addAll(initialState.subList(0, index-1));
			tempState.add("0");
			tempState.add(initialState.get(index-1));
			tempState.addAll(initialState.subList(index+1, initialState.size()));
			
		}
		
        return tempState;
	}
	ArrayList<String> moveUp(ArrayList<String> iniStateState){
		ArrayList<String> tempState = null;
		int index = iniStateState.indexOf("0");
		if(index > 3){
			
			tempState = new ArrayList<>();
			tempState.addAll(iniStateState.subList(0, index-4));
        	tempState.add("0");
    		tempState.addAll(iniStateState.subList(index-3, index));
    		tempState.add(iniStateState.get(index-4));
    		tempState.addAll(iniStateState.subList(index+1, iniStateState.size()));

        }
        return tempState;
	}
	ArrayList<String> moveDown(ArrayList<String> initialState){
		ArrayList<String> tempState = null;
		int index = initialState.indexOf("0");
        if(index < 12){
        	
        	tempState = new ArrayList<>();
        	tempState.addAll(initialState.subList(0, index));
        	tempState.addAll(initialState.subList(index+4, index+5));
        	tempState.addAll(initialState.subList(index+1, index+4));
        	tempState.add("0");
        	tempState.addAll(initialState.subList(index+5, initialState.size()));
			        	
        }
        return tempState;
	}
}
