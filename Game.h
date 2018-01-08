/***********************************************************
* Eliad Arzuan
* 206482622
****************************************************/
/**
 * Game.
 * This class run the game - has black and white player and uses referrence of board and moves.
 **/

#ifndef GAME_H
#define GAME_H


#include "Board.h"
#include "Moves.h"
#include "Black.h"
#include "White.h"
#include <string>
class Game {
private:
    Board &board; //Reference to the board.
    Moves &moves; //Reference to the moves factory.
    Black black; //Black player.
    White white; //White player.
    /**
     * inputPoint.
     * Gets 2 numbers from the user and check if its a valid move.
     * @param swappManager the swap manager array connected to the point because we want to swapp the points
     *                     that matches to it.
     * @param size the size of the swap manager
     * @return the point that the user put.
     */
    Point inputPoint(SwappManager * swappManager, int size);
    /**
     * searchPoint.
     * @param swappManager an array of moves.
     * @param size the size of the array.
     * @param p point we want to search in the array of swapp manager (the point connected to each swappmanager[i]).
     * @return the point index in the array.
     */
    int searchPoint(SwappManager * swappManager, int size, Point p);
    /**
     * printPoints.
     * Print the current moves.
     * @param swappManager moves array.
     * @param num the number of moves.
     */
    void printPoints (SwappManager * swappManager, int num);
public:
    /**
     * Game.
     * The constructor of our class.
     * @param b a referrence to the board.
     * @param m a referrence to the moves.
     */
    Game(Board &b, Moves &m);
    /**
     * The destructor of our class.
     */
    ~Game();
    /**
     * run.
     * Running the game.
     */
    void run();
};


#endif //EX2_GAME_H
