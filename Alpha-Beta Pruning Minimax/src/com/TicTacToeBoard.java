/*
 * TicTacToeBoard Class: The class has logic to calculate the minimax with and without alpha beta pruning depending on the user's choice. Along with the helper methods.
 * Consideration is that X is player 1 and O is player 2
 * Created: Ritwij Nadagouda 
 * Date: 10-09-2015
 * 
 */
package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeBoard {

	Scanner scan = new Scanner(System.in);
	List<PointOnBoard> availablePointsOnBoard;
	int[][] ticTacToeBoard = new int[3][3];
	int numberOfNodes = 0;
	List<PointsAndScores> childScores = new ArrayList<>();

	// The given string input from the user is plotted onto the board using this
	// fucntion
	void setBoard(ArrayList<String> initialState) {
		for (int i = 0; i < initialState.size(); i++) {
			if (initialState.get(i).equalsIgnoreCase("X")) {		// Xs are marked on the board
				switch (i) {
				case 0:
					makeMove(new PointOnBoard(0, 0), 1);

					break;
				case 1:
					makeMove(new PointOnBoard(0, 1), 1);

					break;
				case 2:
					makeMove(new PointOnBoard(0, 2), 1);

					break;
				case 3:
					makeMove(new PointOnBoard(1, 0), 1);

					break;
				case 4:
					makeMove(new PointOnBoard(1, 1), 1);

					break;
				case 5:
					makeMove(new PointOnBoard(1, 2), 1);

					break;
				case 6:
					makeMove(new PointOnBoard(2, 0), 1);

					break;
				case 7:
					makeMove(new PointOnBoard(2, 1), 1);

					break;
				case 8:
					makeMove(new PointOnBoard(2, 2), 1);

					break;

				}
			}
			if (initialState.get(i).equalsIgnoreCase("O")) {	// Os are marked on the board
				switch (i) {
				case 0:
					makeMove(new PointOnBoard(0, 0), 2);

					break;
				case 1:
					makeMove(new PointOnBoard(0, 1), 2);

					break;
				case 2:
					makeMove(new PointOnBoard(0, 2), 2);

					break;
				case 3:
					makeMove(new PointOnBoard(1, 0), 2);

					break;
				case 4:
					makeMove(new PointOnBoard(1, 1), 2);

					break;
				case 5:
					makeMove(new PointOnBoard(1, 2), 2);

					break;
				case 6:
					makeMove(new PointOnBoard(2, 0), 2);

					break;
				case 7:
					makeMove(new PointOnBoard(2, 1), 2);

					break;
				case 8:
					makeMove(new PointOnBoard(2, 2), 2);

					break;

				}
			}
		}
	}

	// Board is evaluated for selecting the optimal position or move depending
	// on the number of X and O
	public int boardEvaluation() {
		int score = 0;

		// Check the rows of the board
		for (int i = 0; i < 3; ++i) {
			int blank = 0;
			int X = 0;
			int O = 0;
			for (int j = 0; j < 3; ++j) {
				if (ticTacToeBoard[i][j] == 0) {
					blank++;
				} else if (ticTacToeBoard[i][j] == 1) {
					X++;
				} else {
					O++;
				}

			}
			score += scoreChange(X, O);
		}

		// Check the columns of the board
		for (int j = 0; j < 3; ++j) {
			int blank = 0;
			int X = 0;
			int O = 0;
			for (int i = 0; i < 3; ++i) {
				if (ticTacToeBoard[i][j] == 0) {
					blank++;
				} else if (ticTacToeBoard[i][j] == 1) {
					X++;
				} else {
					O++;
				}
			}
			score += scoreChange(X, O);
		}

		int blank = 0;
		int X = 0;
		int O = 0;

		// Check the first diagonal of the board
		for (int i = 0, j = 0; i < 3; ++i, ++j) {
			if (ticTacToeBoard[i][j] == 1) {
				X++;
			} else if (ticTacToeBoard[i][j] == 2) {
				O++;
			} else {
				blank++;
			}
		}

		score += scoreChange(X, O);

		blank = 0;
		X = 0;
		O = 0;

		// Check the second diagonal of the board
		for (int i = 2, j = 0; i > -1; --i, ++j) {
			if (ticTacToeBoard[i][j] == 1) {
				X++;
			} else if (ticTacToeBoard[i][j] == 2) {
				O++;
			} else {
				blank++;
			}
		}

		score += scoreChange(X, O);

		return score;
	}

	// Maintains the score change depending on the value of X and O in the board
	private int scoreChange(int X, int O) {
		int change;
		if (X == 3) {
			change = 100;
		} else if (X == 2 && O == 0) {
			change = 10;
		} else if (X == 1 && O == 0) {
			change = 1;
		} else if (O == 3) {
			change = -100;
		} else if (O == 2 && X == 0) {
			change = -10;
		} else if (O == 1 && X == 0) {
			change = -1;
		} else {
			change = 0;
		}
		return change;
	}

	// Set this to some value if you wish to have a certain limit on the septh
	// of the search
	int uptoDepth = -1;

	// Minmax with alpa beta pruning
	public int minimaxWithAlphaBetaPruning(int alpha, int beta, int depth, int turn) {

		if (beta <= alpha) {
			if (turn == 1)
				return Integer.MAX_VALUE;
			else
				return Integer.MIN_VALUE;
		}

		if (depth == uptoDepth || isGameOver())
			return boardEvaluation();

		List<PointOnBoard> pointsAvailableOnBoard = getPossibleStates();

		if (pointsAvailableOnBoard.isEmpty())
			return 0;

		if (depth == 0)
			childScores.clear();

		int maxValue = Integer.MIN_VALUE, minValue = Integer.MAX_VALUE;

		for (int i = 0; i < pointsAvailableOnBoard.size(); ++i) {

			PointOnBoard point = pointsAvailableOnBoard.get(i);

			int currentScore = 0;

			if (turn == 1) {

				makeMove(point, 1);
				numberOfNodes++;
				currentScore = minimaxWithAlphaBetaPruning(alpha, beta, depth + 1, 2);
				maxValue = Math.max(maxValue, currentScore);

				// Set alpha value
				alpha = Math.max(currentScore, alpha);

				if (depth == 0)
					childScores.add(new PointsAndScores(currentScore, point));

			} else if (turn == 2) {

				makeMove(point, 2);
				numberOfNodes++;
				currentScore = minimaxWithAlphaBetaPruning(alpha, beta, depth + 1, 1);
				minValue = Math.min(minValue, currentScore);

				// Set beta value
				beta = Math.min(currentScore, beta);

			}
			// reset board
			ticTacToeBoard[point.x][point.y] = 0;

			// Skip evaluating sibling states if pruning is done
			if (currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE)
				break;
		}
		return turn == 1 ? maxValue : minValue;
	}

	// Minimax without alpha beta pruning
	public int returnMinValue(List<Integer> list) {
		int min = Integer.MAX_VALUE;
		int index = -1;
		for (int i = 0; i < list.size(); ++i) {
			if (list.get(i) < min) {
				min = list.get(i);
				index = i;
			}
		}
		return list.get(index);
	}

	public int returnMaxValue(List<Integer> list) {
		int maxValue = Integer.MIN_VALUE;
		int index = -1;
		for (int i = 0; i < list.size(); ++i) {
			if (list.get(i) > maxValue) {
				maxValue = list.get(i);
				index = i;
			}
		}
		return list.get(index);
	}

	public int minimax(int depth, int turn) {

		if (isXWinner())
			return +1;
		if (isOWinner())
			return -1;

		List<PointOnBoard> pointsAvailable = getPossibleStates();
		if (pointsAvailable.isEmpty())
			return 0;

		List<Integer> scores = new ArrayList<>();

		for (int i = 0; i < pointsAvailable.size(); ++i) {
			PointOnBoard point = pointsAvailable.get(i);

			if (turn == 1) { // X's turn select the highest from below minimax()
								// call
				makeMove(point, 1);
				numberOfNodes++;
				int currentScore = minimax(depth + 1, 2);
				scores.add(currentScore);

				if (depth == 0)
					childScores.add(new PointsAndScores(currentScore, point));

			} else if (turn == 2) {// O's turn select the lowest from below
									// minimax() call
				makeMove(point, 2);
				numberOfNodes++;
				scores.add(minimax(depth + 1, 1));
			}
			ticTacToeBoard[point.x][point.y] = 0; // Reset this point
		}
		return turn == 1 ? returnMaxValue(scores) : returnMinValue(scores);
	}

	public boolean isGameOver() {
		// Game is over if X or O has won or there is draw between the players
		return (isXWinner() || isOWinner() || getPossibleStates().isEmpty());
	}

	public boolean isXWinner() {
		if ((ticTacToeBoard[0][0] == ticTacToeBoard[1][1] && ticTacToeBoard[0][0] == ticTacToeBoard[2][2]
				&& ticTacToeBoard[0][0] == 1)
				|| (ticTacToeBoard[0][2] == ticTacToeBoard[1][1] && ticTacToeBoard[0][2] == ticTacToeBoard[2][0]
						&& ticTacToeBoard[0][2] == 1)) {

			return true;
		}
		for (int i = 0; i < 3; ++i) {
			if (((ticTacToeBoard[i][0] == ticTacToeBoard[i][1] && ticTacToeBoard[i][0] == ticTacToeBoard[i][2]
					&& ticTacToeBoard[i][0] == 1)
					|| (ticTacToeBoard[0][i] == ticTacToeBoard[1][i] && ticTacToeBoard[0][i] == ticTacToeBoard[2][i]
							&& ticTacToeBoard[0][i] == 1))) {

				return true;
			}
		}
		return false;
	}

	public boolean isOWinner() {
		if ((ticTacToeBoard[0][0] == ticTacToeBoard[1][1] && ticTacToeBoard[0][0] == ticTacToeBoard[2][2]
				&& ticTacToeBoard[0][0] == 2)
				|| (ticTacToeBoard[0][2] == ticTacToeBoard[1][1] && ticTacToeBoard[0][2] == ticTacToeBoard[2][0]
						&& ticTacToeBoard[0][2] == 2)) {

			return true;
		}
		for (int i = 0; i < 3; ++i) {
			if ((ticTacToeBoard[i][0] == ticTacToeBoard[i][1] && ticTacToeBoard[i][0] == ticTacToeBoard[i][2]
					&& ticTacToeBoard[i][0] == 2)
					|| (ticTacToeBoard[0][i] == ticTacToeBoard[1][i] && ticTacToeBoard[0][i] == ticTacToeBoard[2][i]
							&& ticTacToeBoard[0][i] == 2)) {
				return true;
			}
		}

		return false;
	}

	// returns the available states for the current one
	public List<PointOnBoard> getPossibleStates() {
		availablePointsOnBoard = new ArrayList<>();
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				if (ticTacToeBoard[i][j] == 0) {
					availablePointsOnBoard.add(new PointOnBoard(i, j));
				}
			}
		}
		return availablePointsOnBoard;
	}

	public void makeMove(PointOnBoard point, int player) {
		ticTacToeBoard[point.x][point.y] = player; // player = 1 for X, 2 for O
	}

	public PointOnBoard getBestMove() {
		int MAX = -100000;
		int best = -1;

		for (int i = 0; i < childScores.size(); ++i) {
			if (MAX < childScores.get(i).score) {
				MAX = childScores.get(i).score;
				best = i;
			}
		}

		return childScores.get(best).point;
	}

	// displays the board
	public void displayBoard() {
		System.out.println();
		String[][] string = new String[3][3];
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				if (ticTacToeBoard[i][j] == 1) {
					string[i][j] = "|X|";
				}
				if (ticTacToeBoard[i][j] == 2) {
					string[i][j] = "|O|";
				}
				if (ticTacToeBoard[i][j] == 0) {
					string[i][j] = "|_|";
				}
			}
		}

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				System.out.print(string[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	int getOptimalSolution(PointOnBoard pointOnBoard) {
		if (pointOnBoard.x == 0 && pointOnBoard.y == 0) {
			return 1;
		}
		if (pointOnBoard.x == 0 && pointOnBoard.y == 1) {
			return 2;
		}
		if (pointOnBoard.x == 0 && pointOnBoard.y == 2) {
			return 3;
		}
		if (pointOnBoard.x == 1 && pointOnBoard.y == 0) {
			return 4;
		}
		if (pointOnBoard.x == 1 && pointOnBoard.y == 1) {
			return 5;
		}
		if (pointOnBoard.x == 1 && pointOnBoard.y == 2) {
			return 6;
		}
		if (pointOnBoard.x == 2 && pointOnBoard.y == 0) {
			return 7;
		}
		if (pointOnBoard.x == 2 && pointOnBoard.y == 1) {
			return 8;
		}
		if (pointOnBoard.x == 2 && pointOnBoard.y == 2) {
			return 9;
		}
		return 0;
	}
}