#include "White.h"

White::White(Board &b, Moves &m) :board(b), moves(m) {}

void White::put(int x, int y) {
    this->board.put(x, y, 'O');

    SwappManager * whiteMoves = playerMoves();


}



int White::getNumMoves() const {
    moves.whiteMoves(); //Do it to get the number of the white moves.
    return *moves.numWhiteMoves;
}

SwappManager* White::playerMoves()  {
    return moves.whiteMoves();
}



