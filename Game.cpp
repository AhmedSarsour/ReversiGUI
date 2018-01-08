/***********************************************************
* Eliad Arzuan
* 206482622
****************************************************/

#include "Game.h"
#include <limits>

using namespace std;
//Constructor of our class.
Game::Game(Board &b, Moves &m) :board(b), moves(m), black(Black(b, m)), white(White(b, m)){
}
//Running the gma.e
void Game::run() {
    //Getting the number of black and white moves.
    int numBlackMoves = black.getNumMoves();
    int numWhiteMoves = white.getNumMoves();
    //Bool to check which player the move is.
    bool blackTurn = true;
    //Stops when 2 players dont have more moves.
    while (numBlackMoves != 0 && numWhiteMoves != 0) {
        //Showing the board.
        board.show();
        //The black turn.
        if (blackTurn) {
            cout << "X: It's your move." << endl;
            //The moves of the black.
            SwappManager *blackMoves = black.playerMoves();

            numBlackMoves = black.getNumMoves();
            //Has more moves
            if (numBlackMoves > 0) {
                cout << "Your possible moves:";
                //Printing the moves.
                printPoints(blackMoves, numBlackMoves);
                //Turn to put the move.
                cout << endl;
                Point p = inputPoint(blackMoves, numBlackMoves);
                //Put a parrel and swap the match white parrels.
                black.put(p.getX(), p.getY());
                blackMoves[searchPoint(blackMoves, numBlackMoves, p)].swappALl();
            } else { //No possible moves.
                cout << "No possible moves. Play passes back to the other player. Press any key to continue."<<endl;
                //Enter to continue.
                cin.clear();
                std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

            }
            blackTurn = false; //Move to the white turn
            //Free the black moves.

            delete [] blackMoves;
        } else { //White turn
            cout << "O: It's your move." << endl;
            //The moves of the white player.
            SwappManager *whiteMoves = white.playerMoves();
            //Number of the moves.
            numWhiteMoves = white.getNumMoves();
            if (numWhiteMoves > 0) { //Has more moves.
                cout << "Your possible moves:";
                //Print the moves.
                printPoints(whiteMoves, numWhiteMoves);
                //Turn to put the move.
                cout << endl;
                Point p = inputPoint(whiteMoves, numWhiteMoves);
                //Put the white char and swap black characters.
                white.put(p.getX(), p.getY());
                whiteMoves[searchPoint(whiteMoves, numWhiteMoves, p)].swappALl();

                blackTurn = true;
                //Free the white moves.
                delete [] whiteMoves;
            } else { //No possible moves.
                cout << "No possible moves. Play passes back to the other player. Press any key to continue."<<endl;
                cin.clear();
                std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
            }
        }

    } //endWhile.

    //End game
    if (board.getNumberBlacks() >= board.getNumberWhites()) { //Black wins.
        cout << "End of the game.  The winner is X " << endl;
    } else { //White wins.
        cout << "End of the game.  The winner is O " << endl;
    }

}
//Print moves.
void Game::printPoints(SwappManager *swappManager, int num) {
    for (int i = 0; i < num; i++) {
        //The point before update (at the pointer shown as (3,3) will show (4,4)
        Point beforeUpdate = swappManager[i].getPoint();
        //Update the point to make it convenience.
        Point updated(beforeUpdate.getX() + 1, beforeUpdate.getY() + 1);
        cout << updated; //Print the point.
        if (i != num - 1) {
            cout << ",";
        }
    }
    cout <<endl;
}
//Gets a point from the user.
Point Game::inputPoint(SwappManager * swappManager, int size) {
    int x, y;
    bool isOk = false; //Checks if the input is ok.
    Point p;
    while (!isOk) {
        cout << "Please enter your move row, col:";
        cin >> x; //Input one number.

        isOk = !cin.fail(); //Checks if a number that is not double.

        cin >> y; //Input second number.
        isOk &= !cin.fail(); //Checks if a number that is not double.
        if (isOk) { //The input is ok.
            p = Point(x, y);
            //Check if the point is a possible move.
            isOk &= searchPoint(swappManager, size, p) != -1;
            //Wrong move.
            if (!isOk) {
                cout << "Move does not exist."<<endl;
            }
        } else { //Didnt entered the numbers properly.
            cout << "Enter 2 numbers please." << endl;
        }
        cout <<endl;

        //From the piazza in order to prevent infinity loop.
        cin.clear();
        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
    }

    return Point(x, y);
}
//Search if the point is connected to move.
int Game::searchPoint(SwappManager *swappManager,int size, Point p) {
    for (int i = 0; i < size; i++) {
        //-1 to update to the real board.
        if (swappManager[i].getPoint().equal(Point(p.getX() - 1, p.getY() - 1))) {
            return i;
        }
    }

    return -1; //Case of didn't found.


}
//Destructor.
Game::~Game() {

}