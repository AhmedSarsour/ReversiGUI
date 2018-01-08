/* Moves.
 * This class creates moves to the player.
 * We will divide the sections to 8 parts where we can use moves.
 * Let x be a black or white (black for example).
 * If one of the numbers 1-8 isnt empty.
 * Otherwise we will check the section (row,column, slant) if the section has white members and than black we will
 * swap all the white into black - like the rules.
 *       j-1 j j+1
 *   i-1 1  2  3
 *   i   4  x  5
 *   i+1 6  7  8
 */

#ifndef MOVES_H
#define MOVES_H
/***********************************************************
* Eliad Arzuan
* 206482622
****************************************************/
#include "Board.h"
#include "SwappManager.h"

class Moves {
private:
    Board &board; //Referrence to board.
    Point * blacks; //Array of current black players.
    Point * whites; //Array of current white players.
    /**
     * getNumWhites.
     * @return the number of the current white chars on the board.
     */
    int getNumWhites() const;
    /**
   * getNUmBLacks.
   * @return the number of the current black chars on the board.
   */
    int getNumBlacks() const;
    /**
     * scanForPlayer.
     * As we mentioned at the top of there are 8 areas which a move can be.
     * By this function we will search for player at the same type of our player.
     * For example OXO - we will find O on the other side (we save the Xs).
     * We will move by x+xAdd y+yAdd until we got to empty place.
     * @param x x position.
     * @param y y position
     * @param xAdd how much adding to x.
     * @param yAdd how much adding to y.
     * @param isBlack true if we want to search for black. false for white.
     * @return array of points to swap until we got to the same player color.
     */
    Point * scanForPlayer(int x, int y, int xAdd, int yAdd, bool isBlack) const; //isBlack - true if  scan for

public:
    /**
     * Moves.
     * The constructor of our class.
     * @param b a referrence to board.
     */
    Moves(Board &b);
    /**
     * ~Moves
     * The destructor of our class.
     */
    ~Moves();//Destructor.
    /**
     * blackMoves
     * @return the SwappManager array to represent the black moves and which move cause to which swapp.
     */
    SwappManager * blackMoves(); //Array of arrays of points that contains moves of black.
    /**
     * whiteMoves
     * @return the SwappManager array to represent the white moves and which move cause to which swapp.
     */
    SwappManager* whiteMoves();
    //The current number of black moves.
    int *numBlackMoves; //Changed only after activating the function blackMoves!!!
    //The current number of white moves.
    int *numWhiteMoves; //Changed only after activating the function whiteMoves!!

};


#endif //MOVES_H