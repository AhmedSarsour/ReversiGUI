/***********************************************************
* Eliad Arzuan
* 206482622
****************************************************/
/*
 * Point.
 * Represent a point with x and y integers.
 * In this game it represent index on the board.
 */

#ifndef POINT_H
#define POINT_H

#include <iostream>
using namespace std;
class Point {
private:
    int x, y; //Int because represents point on the board.

public:
    /**
     * Default constructor of our class.
     */
    Point();
    /**
    * The constructor of our class.
    * @param x the x cordinate.
    * @param y the y cordinate.
    */
    Point(int x, int y);
    /**
     * getX
     * Const because it does not change the members.
     * @return the x value.
     */
    int getX() const;
    /**
       * getY
       * Const because it does not change the members.
       * @return the y value.
       */
    int getY() const;
    /**
     * equal.
     * Checks is the point values equal to this point values
     * @param p the point we want to check.
     * @return true if the values equal, false otherwise.
     */
    bool equal(Point p);
    /**
     * deleteFromArray.
     * Delete a point from array.
     * @param arr the array of points.
     * @param numPoints the number of points in the array.
     * @param p the points we want to remove.
     */
    void deleteFromArray(Point *arr, int numPoints, Point p);
    /**
     * Override the operator << like toString.
     * @param out - an ostream -like cout
     * @param frac the point we want to print.
     * @return a way to print the point
     */
    friend ostream &operator <<(ostream &out, const Point &frac);
    /**
    * Destructor of our class.
    */
    ~Point();

};

#endif //POINT_H
