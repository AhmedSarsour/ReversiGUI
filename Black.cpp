/***********************************************************
* Eliad Arzuan
* 206482622
****************************************************/
// Black - the full comments on the .h file.
//

#include "Black.h"
//Put values on the board and moves by the references.
Black::Black(Board &b, Moves &m) :board(b), moves(m) {}
//Put the black player's char on the board.
void Black::put(int x, int y) {
    this->board.put(x, y, 'X'); //Use the put function with the player's char.
}

//Return the number of the moves the black player has.
int Black::getNumMoves() const  {
    moves.blackMoves(); //Do it to get the number of the black moves.
    return *moves.numBlackMoves;
}
//Return the moves that the black player has.
SwappManager* Black::playerMoves() const {
    return moves.blackMoves();
}


Black::~Black() {

}


