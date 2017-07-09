/*
 * PointOnBoard Class: Maintains the position on the board like point(x, y) where x is the row number and y is the column number
 * 
 * Created: Ritwij Nadagouda 
 * Date: 10-09-2015
 * 
 */
package com;

public class PointOnBoard {

    int x, y;

    public PointOnBoard(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
