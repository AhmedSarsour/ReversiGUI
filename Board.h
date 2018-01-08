/***********************************************************
* Eliad Arzuan
* 206482622
****************************************************/

#ifndef Board_h

#define Board_h

#include "Point.h"
/**
* Board.
* Represents a board of the game reversy.
* Has rows and cols member to change the game variations.
* Remember that we always convert the functions to board cordinate from (1,1) to (8,8)
**/
class Board {
public:
    /**
        * Board.
        * Default constructor.
     **/
    Board();
    /**
    * Board.
    * The constructor of our class Board.
    * @param rows - the number of the rows of the board.
    * @param cols - the number of the columns of the board.
    **/
    Board(int rows, int cols);
    /**
    * ~Board.
    * The destructor of our class Board.
    * Free the memory allocated to the matrix.
    **/
    ~Board();
    /**
    * show function.
    * Showing the board by the format of the game we requested to do.
    * The function is const because it does not change the inner board structure.
    **/
    void show() const;
    /**
      * put
      * puts a player char on the board in the place x,y (Behind the scenes in x-1,y-1).
      * @param x - the x cordinate.
      * @param y- the y cordinate.
      * @param c - the char we want to put.
      * @return true if succesfully put, false otherwise.
     **/
    bool put(int x, int y, char c);
    /**
        * getBlacks.
        * The function is const because we don't change the inner members.
        * @return pointer as array of the black players in the game.
       **/
    Point * getBlacks() const;
    /**
      * getWhites.
      * The function is const because we don't change the inner members.
      * @return pointer as array of the white players in the game.
     **/
    Point * getWhites() const;
    /**
    * upsideDown.
    * The function is const because we don't change the inner members.
    * Swapp black to white or white to black in the x, y position.
    * @return true if succeed, false if didn't find the point.
   **/
    bool upsideDown(int x, int y);
    /**
    * getNumberBlacks.
     * The function is const because we don't change the inner members.
    * @return the number of black chars at the board.
     **/
    int getNumberBlacks() const;
    /**
    * getNumberWhites.
    * The function is const because we don't change the inner members.
    * @return the white of black chars at the board.
*   */
    int getNumberWhites() const;
    /**
   * get.
   * The function is const because we don't change the inner members.
   * @param x the x cordinate.
   * @param y the y cordinate.
   * @return the char at x,y position on the board.
*   */
    char get(int x, int y) const;
    /**
    * margins.
    * The function is const because we don't change the inner members.
    * @return point which x is the rows number of the board and y is the columns number of the board.
    **/
    Point margins() const;
    /**
* isValid.
 * The function is const because we don't change the inner members.
 * @param x the x cordinate.
 * @param y the y cordinate.
* @return true if board[x,y] contains char, false otherwise.
**/
    bool isValid(int x, int y) const;
private:
    //Blacks and whites
    Point * blacks ; //List of the blacks' position in the game.
    Point * whites ; //List of the whites' position in the gam
    int *numBlacks; //Number of the black char - pointer to make it update between the different classes.
    int *numWhites;//Number of the white char - pointer to make it update between the different classes.
    int rows; //The rows' number of the board.
    int cols; //The columns' number of the board.
    char** board; //the board itself.

    /**
     * removeBlack.
     * Remove black char from the board in x,y position.
     * @param x the x cordinate.
     * @param y the y cordinate.
    **/
    void removeBlack(int x, int y);//Remove black player's square from the board.
    /**
     * removeWhite.
     * Remove white char from the board in x,y position.
     * @param x the x cordinate.
     * @param y the y cordinate.
    **/
    void removeWhite(int x, int y);//Remove white player's square from the board.

};
#endif