package Reversi;

/*
 * Point.cpp
 *      student 1: ahmed sarsour. 315397059
 *      student 2: Eliad Arzuan 206482622
 */
/*
 * Point.
 * Represent a point with x and y integers.
 * In this game it represent index on the board.
 */

public class Point {
    private int x, y; //Int because represents point on the board.

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getX
     * Const because it does not change the members.
     *
     * @return the x value.
     */
    public int getX() {
        return this.x;
    }

    /**
     * getY
     * Const because it does not change the members.
     *
     * @return the y value.
     */
    public int getY() {
        return this.y;
    }

    /**
     * equal.
     * Checks is the point values equal to this point values
     *
     * @param p the point we want to check.
     * @return true if the values equal, false otherwise.
     */
    @Override
    public boolean equals(Object p) {
        Point other = (Point)p;
        if (this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        return false;
    }

    /**
     * deleteFromArray.
     * Delete a point from array.
     *
     * @param arr       the array of points.
     * @param numPoints the number of points in the array.
     * @param p         the points we want to remove.
     */
    public static void deleteFromArray(Point[] arr, int numPoints, Point p) {
        int index = 0;

        for (int i = 0; i < numPoints; i++) {
            if (arr[i].equals(p)) { //Found p
                index = i;
                break;
            }
        }
        //Moving the point one place ahead from the point we want to remove.
        for (int i = index;i < numPoints; i++) {
            arr[i] = arr[i+1];
        }
    }

    /**
     * @return a way to print the point
     */
    @Override
    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ")";
    }
}

