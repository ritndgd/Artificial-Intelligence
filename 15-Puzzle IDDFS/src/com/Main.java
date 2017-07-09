/*
 * Main - This is the main class which calls in to find the solution to the problem
 * Created by: Ritwij Nadagouda			Date: 09/25/2015
 */

package com;

public class Main {
	
	public static void main(String[] args) {
		System.gc();
		System.out.println("Total Memory before = " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
		long startTime = System.currentTimeMillis();
				
		IterativeDeepeningDFS iterativeDeepeningDFS = new IterativeDeepeningDFS();	//Instance of class IterativeDeepening
		iterativeDeepeningDFS.lookForGoalState();									//Call for function lookupfor goal state
		System.out.println("Number of Nodes Generated =  "+iterativeDeepeningDFS.numberOfNodes);
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Toal Running Time:"+ totalTime + " MiliSeconds");
		System.gc();
		Runtime.getRuntime().freeMemory();
		Runtime.getRuntime().totalMemory();
		System.out.println("Total Memory after = " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
	
	} 
}
