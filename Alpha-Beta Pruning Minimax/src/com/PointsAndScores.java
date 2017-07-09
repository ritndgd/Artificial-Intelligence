/*
 * PointsAndScores Class: Maintains the scores of the particular point achieved in the minimax with alpha beta pruning
 * 
 * Created: Ritwij Nadagouda 
 * Date: 10-09-2015
 * 
 */
package com;

public class PointsAndScores {

    int score;
    PointOnBoard point;

    PointsAndScores(int score, PointOnBoard point) {
        this.score = score;
        this.point = point;
    }
}
