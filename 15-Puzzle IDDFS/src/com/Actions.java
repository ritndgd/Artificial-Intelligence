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

public class Actions {

	
	ArrayList<String> newState = new ArrayList<>();
	
	ArrayList<String> applyAction(ArrayList<String> initialState, int i){
		
		if(i == 0){
			newState = moveRight(initialState);                        // moveRight - Moves the blank right
			if(newState != null) {
	        	return newState;
	        }
		}
		if(i == 1){
			newState = moveDown(initialState);                          // moveDown - Moves the blank down.
			if(newState != null) {
	        	return newState;
	        }
		}
		if(i == 2){
			newState = moveLeft(initialState);                         // moveLeft - Moves the blank left.
			System.out.println("In Left "+newState);
	        if(newState != null) {
	        	return newState;
	        }
		}
		if(i == 3){
			newState = moveUp(initialState);							// moveUp - Moves the blank up.
			if(newState != null) {
	        	return newState;
	        }
		}
		
		return null;
	}
	ArrayList<String> moveRight(ArrayList<String> initialState){
		ArrayList<String> tempState = null;
		
		int index = initialState.indexOf("0");
		if(index != 3 && index != 7 && index != 11 && index != 15){
			System.out.println("In Right");
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
			System.out.println("In Left");
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
			System.out.println("In Up");
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
        	System.out.println("In Down");
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
