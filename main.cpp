#include <iostream>
#include "Board.h"
#include "Black.h"
#include "Game.h"
/***********************************************************
* Eliad Arzuan
* 206482622
****************************************************/
using namespace std;
int main() {

    Board *  board = new Board(8, 8);
    //The moves.
    Moves *  moves = new Moves(*board);
    //The game.


    Game game(*board, *moves);
    game.run();
    //Free the classes that contains pointers.
    delete board;
    delete moves;


	return 0;
}