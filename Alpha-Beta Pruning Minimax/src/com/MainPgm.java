/*
 * MainPgm Class: Accepts the input from the user and calculates the optimal move for the entered state of TicTactoe game using minimax with alpha beta pruning.
 * 
 * Created: Ritwij Nadagouda 
 * Date: 10-09-2015
 * 
 */

package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class MainPgm {

	public static void main(String[] args) { 
		
		//Instance of TicTacToe
		TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();	
        Scanner scan = new Scanner(System.in);
        List<String> stateList;
        ArrayList<String> initialState;
        String state = null;
        //Accept the input from the user
        System.out.println("Enter the initial state of the board: "
        				 + "\nEx: b,b,b,X,b,b,b,O,b; \nwhere b represents the blank tile");
        state = scan.next();
        stateList = Arrays.asList(state.split(","));
        initialState = new ArrayList<>(stateList);
       
        System.out.println("\nEnter your choice of algorithm: "
        				 + "\n 1. Minimax with alpha beta pruning"
        				 + "\n 2. Minimax without alpha beta pruning");
        String choice = scan.next();
        //set the board to the string entered by the user
        ticTacToeBoard.setBoard(initialState);
        //display the state entered by the user
        System.out.println("\nInitial State of the board");
        ticTacToeBoard.displayBoard();
        //call the function minimax with pruning to calculate the best move for X
        if(choice.equals("1")){
        	System.out.println("Using MiniMax with alpha beta pruning");
        	ticTacToeBoard.minimaxWithAlphaBetaPruning(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 2); //X is player 1
        }else if(choice.equals("2")){
        	System.out.println("Using MiniMax without alpha beta pruning");
        	ticTacToeBoard.minimax(0, 1);	//X is player 1
        }else{
        	System.out.println("Make valid choice..");
        }
        //display the best move of the X        
        ticTacToeBoard.makeMove(ticTacToeBoard.getBestMove(), 2); 
        System.out.println("\nBoard with optimal move marked");
        ticTacToeBoard.displayBoard();
        //print the optimal solution of the board
        System.out.println("The Optimal Move is " +ticTacToeBoard.getOptimalSolution(ticTacToeBoard.getBestMove()));
        System.out.println("Number Of Nodes = " + ticTacToeBoard.numberOfNodes);
        
    }
}
