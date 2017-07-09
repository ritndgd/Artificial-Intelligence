package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();							
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Choose one option");
		System.out.println("1. ID Astar with heuristics 1\n"
						+ "2. ID Astar with heuristics 2");
		int choice = scan.nextInt();
		if(choice == 1){
			IDAstarHeuristicOne id = new IDAstarHeuristicOne();
			if(id.lookForGoalState() != null){
				System.out.println("Solution found");
				System.out.println("Number of Nodes Generated = "+id.numberOfNodes);
			}
		}
		else if(choice == 2){
			IDAstarHeuristicTwo id = new IDAstarHeuristicTwo();
			if(id.lookForGoalState() != null){
				System.out.println("Solution found");
				System.out.println("Number of Nodes Generated = "+id.numberOfNodes);
			}
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
