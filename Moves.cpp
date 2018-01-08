
#include "Moves.h"
#include <cmath>
using namespace std;
//Reference to numwhites and numblacks because it is dinamic.
//Constructor.
Moves::Moves(Board &b) : board(b),blacks(b.getBlacks()), whites(b.getWhites()) {
    //Creating pointer of current white and black moves because we want to make it dinamic.
    this->numBlackMoves = new int[1];
    this->numWhiteMoves = new int[1];
    //Initializiong the black moves and white moves.
    *numBlackMoves = 0;
    *numWhiteMoves = 0;


}
//Return the number of current white chars.
int Moves::getNumWhites() const {
    return board.getNumberWhites();
}
//Return the number of current black chars.
int Moves::getNumBlacks() const {
    return board.getNumberBlacks();
}
//Return all the optional moves.
//The moves will shown
SwappManager * Moves::blackMoves() {
    SwappManager *  moves = new SwappManager[this->getNumWhites() * 8];//8 options betwenn the whites.
    int index = 0;
    for (int i = 0; i < getNumWhites(); i++) {
        Point pos = whites[i]; //The point where the current
        int x = pos.getX();
        int y = pos.getY();
        int current = index; //The current index in swappManager.
        bool found = false; //Found the point.

        //All the areas 1-8 for example slant from (1,1) ->x+1,y+1
        Point areas[] = {Point(-1, -1), Point(-1, 0), Point(-1, 1)
                , Point(0, -1), Point(0, 1), Point(1, -1), Point(1, 0), Point(1, 1)};
        //Moving on all the areas.
        for (int j = 0; j < 8; j++) {
            if (board.isValid(x + areas[j].getX(), y + areas[j].getY())) {
                if (board.get(x + areas[j].getX(), y + areas[j].getY()) == ' ') {
                    Point point(x + areas[j].getX(), y + areas[j].getY());
                    //Potential moves
                    Point *potential = scanForPlayer(x, y, -areas[j].getX(),
                                                     -areas[j].getY(), true);
                    if (potential != 0) { //If is not null
                        if (index != 0) {
                            for (int k = 0; k < index; k++) {
                                if (moves[k].getPoint().equal(point)) {
                                    current = k;
                                    found = true;
                                }
                            } //endfor
                        }
                        if (!found) { //Create new swapp manager.
                            //moves[current] is initialized so we just will set it
                            //Set the point and board because it is maybe with default constructor.
                            current = index;
                            moves[current].setPoint(point);
                            moves[current].setBoard(this->board);
                            index++;
                        }
                        //Adding the potential swap if is not null;e
                        moves[current].addSwap(potential);
                    }

                } //endif
            }//end for

        }
   } //end for
    *numBlackMoves = index; //Updating the current number of black moves.
    return moves;
}
//The white moves.
SwappManager* Moves::whiteMoves() {
    SwappManager *  moves = new SwappManager[this->getNumBlacks() * 8];//8 options betwenn the blacks.
    int index = 0;
    for (int i = 0; i < getNumBlacks(); i++) {
        Point pos = blacks[i]; //The point where the current
        int x = pos.getX();

        int y = pos.getY();
        int current = index; //The current index in swappManager.
        bool found = false; //Found the point.

        //All the areas 1-8 for example slant from (1,1) ->x+1,y+1
        Point areas[] = {Point(-1, -1), Point(-1, 0), Point(-1, 1)
                , Point(0, -1), Point(0, 1), Point(1, -1), Point(1, 0), Point(1, 1)};
        //Moving on the areas.
        for (int j = 0; j < 8; j++) {
            if (board.isValid(x + areas[j].getX(), y + areas[j].getY())) {
                if (board.get(x + areas[j].getX(), y + areas[j].getY()) == ' ') {
                    if (board.get(x + areas[j].getX(), y + areas[j].getY()) == ' ') {
                        Point point(x + areas[j].getX(), y + areas[j].getY());
                        int a = x + areas[j].getX();
                        int b =  y + areas[j].getY();
                        //Potential moves
                        Point *potential = scanForPlayer(x, y, -areas[j].getX(),
                                                         -areas[j].getY(), false);
                        //Check if not null.
                        if (potential != 0) {
                            if (index != 0) {
                                //Check if the point exist (for swapp from some directions).
                                for (int k = 0; k < index; k++) {
                                    if (moves[k].getPoint().equal(point)) {
                                        current = k;
                                        found = true;
                                        break;
                                    }
                                } //endfor
                            }
                            if (!found) { //Create new swapp manager.
                                //moves[current] is initialized so we just will set it
                                //Set the point and board because it is maybe with default constructor.
                                current = index;
                                moves[current].setPoint(point);
                                moves[current].setBoard(this->board);
                                index++;
                            }
                            //Adding the potential swap if is not null

                            moves[current].addSwap(potential);
                        }

                    } //endif
                }
            }//end for
        }

    } //end for
//Updating the number of moves.
    *numWhiteMoves = index;
    return moves;
}
//Scan for player at the same type.
    Point *Moves::scanForPlayer(int x, int y, int xAdd, int yAdd, bool isBlack) const {
        bool check = false;
        //Case of white.
        char player = 'O';
        char rival = 'X';
        if (isBlack) {
            player = 'X';
            rival = 'O';
        }
    //Max size to the array - all the board.
        int size = (int) fmax(this->board.margins().getX(), this->board.margins().getY());
        Point *toReverse = new Point[size]; //The points we want to reverse.
        int i = x;
        int j = y;
        int index = -1; //-1 to determine if there is point or not because the point is initialized to 0,0.
    if (board.isValid(i, j)) {
        while (board.get(i, j) == rival || board.get(i, j) == player) {
            if (board.get(i, j) == rival) {
                index++;
                toReverse[index] = Point(i, j);
            }
            if (board.get(i, j) == player && index != -1) {
                //Found the margin to reverse.
                return toReverse;
            }
            j += yAdd;
            i += xAdd;
        }
    }
        return 0;
    }



Moves::~Moves() {
    //Delete the pointers we made for the number of moves.
    delete this->numBlackMoves;
    delete this->numWhiteMoves;
}