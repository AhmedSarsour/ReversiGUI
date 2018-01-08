/***********************************************************
* Eliad Arzuan
* 206482622
****************************************************/
/**
 * White.
 * Represent the white player- connected to the board and to the moves class.
 **/

#ifndef WHITE_H
#define WHITE_H


#include "Board.h"
#include "Moves.h"

class White{
private:
    //We use reference because we don't need to copy the whole objects.
    Board &board; //Reference to the board we work on.
    Moves &moves; //Reference to the moves factory that will get the moevs for it.

public:
    /**
     * The default constructor of our class.
     */
    White();
    /**
      * White.
      * The constructor of our class.
      * @param &b reference to the board.
      * @param &m reference to the moves factory.
      **/
    White(Board& b,Moves &m);
    /**
        * put
        * puts a white char on the board in the place x,y (Behind the scenes in x-1,y-1).
        * @param x - the x cordinate.
        * @param y- the y cordinate.
       **/
    void put(int x, int y);
    /**
      * playerMoves.
      * The function is const because it does not change the inner members.
      * @return array of swappManager- with it we can know which of move we can have. And which cause to which swapp.
      **/
    SwappManager * playerMoves() ;
    /**
      * getNumMoves.
      * The function is const because it does not change the inner members.
      * @return the number of moves the player can have.
    **/
    int getNumMoves() const;
};


#endif //WHITE_H
