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

	boolean result = false;
	
	
	ArrayList<String> applyAction(ArrayList<String> initialState, int i){
		ArrayList<String> newState;
		
		int index = initialState.indexOf("0");
		newState = initialState;
		if(i == 0){
			
			if(index != 3 && index != 7 && index != 11 && index != 15){
				
				newState = new ArrayList<>();
				newState.addAll(initialState.subList(0, index));
				newState.add(initialState.get(index+1));
				newState.add("0");
				newState.addAll(initialState.subList(index+2, initialState.size()));
			   	
	        }
		}
		else if(i == 1){
			
			if(index < 12){
			
				newState = new ArrayList<>();
	        	newState.addAll(initialState.subList(0, index));
	        	newState.addAll(initialState.subList(index+4, index+5));
	        	newState.addAll(initialState.subList(index+1, index+4));
	        	newState.add("0");
	        	newState.addAll(initialState.subList(index+5, initialState.size()));
				        	
	        }
			
		}
		else if(i == 2){
			
			if(index != 0 && index != 4 && index != 8 && index != 12){
				
				newState = new ArrayList<>();
				newState.addAll(initialState.subList(0, index-1));
				newState.add("0");
				newState.add(initialState.get(index-1));
				newState.addAll(initialState.subList(index+1, initialState.size()));
				
			}
		}
		else if(i == 3){
			
			if(index > 3){
				
				newState = new ArrayList<>();
				newState.addAll(initialState.subList(0, index-4));
	        	newState.add("0");
	    		newState.addAll(initialState.subList(index-3, index));
	    		newState.add(initialState.get(index-4));
	    		newState.addAll(initialState.subList(index+1, initialState.size()));

	        }
		}
		
		return newState;
	}
}
