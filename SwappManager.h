/***********************************************************
* Eliad Arzuan
* 206482622
****************************************************/
/**
 * Swapp manager.
 * In charge of converting point to the points he can swapp.
 * Any move can cause to some swapps from some directions so it can make it easier.
 * Point to swap -> array of pointers to the points he can swapp.
 */


#ifndef SWAPPMANAGER_H
#define SWAPPMANAGER_H


#include "Point.h"
#include "Board.h"

class SwappManager {
private:
    Board &board; //Referrence to board.
    Point &point; //Reference to point.
    Point ** swapps; //Array of array of points to swap.
    int connected;//The number of arrays of points to swapp.

public:
    /**
     * Regaular constructor in order to create array of swapp managers.
     */
    SwappManager();
    /**
     * Constructor of our class.
     * @param p a referrence to point that connected to move.
     * @param b a referrence of board we work on.
     */
    SwappManager(Point &p,Board &b);
    /**
     * Distructor of our class.
     */
    ~SwappManager();
    /**
     * getPoint.
     * const because does not change the inner member of the class.
     * @return the point of this swapp manager.
     */
    Point& getPoint() const ;
    /**
     * getSwapps.
     * Const because we just get it.
     * @param i the index we want to get the swap from.
     * @return the swapps at i position.
     */
    Point * getSwapps(int i) const ;
    /**
     * addSwap.
     * Adding a swapp to this swap manager.
     * @param p the array of points to swapp we ant to add.
     */
    void addSwap(Point *p); //Add swapps to this point.
    /**
     * swappAll
     * Swapp all the point caused by this move.
     */
    void swappALl(); //Swapp all those points at board b.
    /**
     * getBoard.
     * Const because we don't change the inner members.
     * @return referrence to the board of this swapp manager.
     */
    Board &getBoard() const;
    /**
     * setPoint.
     * Setting our point.
     * @param p the point we want to change to.
     */
    void setPoint(Point &p);
    /**
      * setPoint.
      * Setting our board.
      * @param b the board we want to change to.
      */
    void setBoard(Board &b);

};


#endif //SWAPPMANAGER_H
