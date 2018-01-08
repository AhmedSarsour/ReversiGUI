/***********************************************************
* Eliad Arzuan
* 206482622
****************************************************/
/**
* Board.
* Represents a board of the game reversy.
* Has rows and cols member to change the game variations.
**/
#include <iostream>
#include "Point.h"
#include "Board.h";
using namespace std;
//Default constructor.
Board::Board() {}
//Regular constructor.
Board::Board(int rows, int cols): rows(rows), cols(cols) {
    //Create array of the black's player parable.
    //Has rows*cols places because it is limit.
    this->blacks = new Point[rows * cols];
    //The current number of black's chars.
    //Pointer because when we move the board as a reference we want it to update.
    this->numBlacks = new int[1];
    *numBlacks = 0;
    //Create array of the white's player parable.
    //Has rows*cols places because it is limit.
    this->whites = new Point[this->rows * this->cols];
    //The current number of black's chars.
    //Pointer because when we move the board as a reference we want it to update.
    this->numWhites = new int[1];
    *numWhites = 0;
    //Creating dinamic matrix- a little help from stack overflow
    this->board = new char *[rows];
    for (int i = 0; i < rows; i++) {
        //Each row is a dinamic array.
        this->board[i] = new char[cols];
    }
    //Put the players by moving on the matirx.
    for (int i = 0; i < this->rows; i++) {
        for (int j = 0; j < this->cols; j++) {
            //The default character is only space.
            this->board[i][j] = ' ';
        }
    }
//Putting the start characters.
    put(4,4,'O');
    put(4,5,'X');
    put(5,4,'X');
    put(5,5,'O');
}

//Destructor.
Board::~Board() {
    for (int i = 0; i < rows; i++) {
        //Free the array in the matrix.
        delete this->board[i];
    }
    //Remove the board itself.
    delete this->board;

    //Remove the pointers for numWhites.
    delete this->numWhites;
    delete this->numBlacks;
    delete []whites;
    delete []blacks;
 }

//Show the board
void Board::show() const {
    //First showing | 1 | 2 | ....
    cout << " |";
    //Iterates the number of columns.
    for (int i = 1; i <= cols; i++) {
        cout << " " << i << " |";
    }
    cout << endl;
    //Showing the matrix itself
    for (int i = 0; i <= rows; i++) {
        for (int k = 0; k <= 4 * cols + 1; k++) {
            cout << "-";
        }
        cout << endl;
        //Showing the content itself of the matrix.
        if (i < rows) {
            cout << i + 1 << "|";
            for (int j = 0; j < cols; j++) {
                cout << " " << board[i][j] << " |";
            }
            cout << endl;
        }
    }

}
//Check if point is on the board.
bool Board::isValid(int x, int y) const {
    //Out of board margins.
    //Rows margins.
    if (x < 0 || x >= this-> rows) {
        return false;
    }
    //Columns margins
    if (y < 0 || y >= this-> cols) {
        return false;
    }
    return true;
}
//Put char on the board.
bool Board::put(int x, int y, char c) {
//It is not a valid point.

    if(x > 8 || y >8 || x <= 0 || y <= 0) {
        return false;
    }

//Make it comfort for the user - so we will make the x and y easy for the user.
    int newX = x - 1;
    int newY = y - 1;
//Black character.
    if (c == 'X') {
        //Updating the current black.
        this->blacks[*this->numBlacks] = Point(newX, newY);


        *numBlacks = *numBlacks + 1;
    }
//Put white character
    if (c == 'O') {
        //Updating the white array and number.
        this->whites[*this->numWhites] = Point(newX, newY);
        *numWhites = *numWhites + 1;
    }
//Put the character.
    //We take the place and reduce one because index starts with 1.
    this->board[newX][newY] = c;
}
//Remove black player from the game.
void Board::removeBlack(int x, int y) {
    //Initializing new point just for using in a function.
    Point p =  Point();
    //Delete the current black from the array of the blacks.
    p.deleteFromArray(this->blacks, *this->numBlacks, Point(x, y));
    *numBlacks = *numBlacks - 1; //Reduce the number of blacks.
}
//Remove white player from the game.
void Board::removeWhite(int x, int y) {
    //Initializing new point just for using in a function.
    Point p =  Point();
    //Delete the current white from the array of the blacks.
    p.deleteFromArray(this->whites, *this->numWhites, Point(x, y));
    *numWhites = *numWhites - 1; //Reduce the number of whites.
}
//Upside down black or white player
bool Board::upsideDown(int x, int y) {
    //Not valid point.
    if(x > 8 || y >8 || x <= 0 || y <= 0) {
        return false;
    }
    //Switch black to white
    if (board[x-1][y-1] ==  'X') {
        put(x, y, 'O'); //Put white instead of black
        removeBlack(x - 1, y - 1); //Remove the upsided black from the array of blacks.
        return true;
    }

    //Switch white to black
    if (board[x-1][y-1] == 'O') {
        put(x, y, 'X'); //Put black instead of white..
        removeWhite(x - 1, y - 1); //Remove the upsided white from the array of whites.
        return true;
    }
    return false; //Wrong position- its not black or white
}
//Return the array of blacks' character position.
Point* Board::getBlacks() const {
    return this->blacks;
}
//Return the array of whites' character position.
Point* Board::getWhites() const {
    return this->whites;
}
//Reuturn the current parrels of black on the board.
int Board::getNumberBlacks() const {
    return *numBlacks;
}
//Return the current number of white parrels on the board.
int Board::getNumberWhites() const {
    return *numWhites;
}
//Return the board character on (x,y).
char Board::get(int x, int y) const {
    if (isValid(x,y)) {
        return this->board[x][y];
    }
    return  ' ';
}
//Return point (rows,cols).
Point Board::margins() const {
    return Point(this->rows, this->cols);
}