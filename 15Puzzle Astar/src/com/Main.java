package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.gc();
		Runtime.getRuntime().totalMemory();
		Runtime.getRuntime().freeMemory();
		long startTime = System.currentTimeMillis();
		
		Scanner scan = new Scanner(System.in);
		String initialString = "6,2,10,3,1,4,7,11,8,5,9,15,12,13,14,0"; 							//initial state of the board where 0 represents the blank space
		List<String> initial = Arrays.asList(initialString.split(","));								// The given Strings are split at "," and the stored
		ArrayList<String> initialState = new ArrayList<String>(initial);							//Then the list is converted to ArrayList for easy manipulation
		System.out.println("Choose one option");
		System.out.println("1. Astar with heuristics 1\n"
						+ "2. Astar with heuristics 2");
		int choice = scan.nextInt();
		if(choice == 1){
			System.out.println("Solving using Heuristics 1..");
			AstarHeuristicOne astar = new AstarHeuristicOne();
			astar.lookForGoal(initialState);
		}
		else if(choice == 2){
			System.out.println("Solving using Heuristics 2..");
			AstarHeuristicTwo astarHeuristicTwo = new AstarHeuristicTwo();
			astarHeuristicTwo.lookForGoal(initialState);
		}else{
			System.out.println("Enter correct choice..");
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime+ " MiliSeconds");
		System.gc();
		Runtime.getRuntime().freeMemory();
		Runtime.getRuntime().totalMemory();
		System.out.println("Total Memory after = " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
	}
	
}
