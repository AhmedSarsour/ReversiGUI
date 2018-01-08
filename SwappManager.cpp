/***********************************************************
* Eliad Arzuan
* 206482622
****************************************************/

#include "SwappManager.h"
#include <cmath>
#include <iostream>
using namespace std;
SwappManager::SwappManager() :board(*new Board()),point(*new Point()) {
}
SwappManager::SwappManager(Point &p, Board &b):point(p), board(b) {
    this->connected = 0;
    //You can put points until 8*number of black o whites.
    int size = 8 * (int)fmax(b.getNumberWhites(), b.getNumberBlacks());
        this->swapps = new Point *[size];

        //Anny point can be until all the board swapps.
        for (int i = 0; i < size; i++)
            this->swapps[i] = new Point[b.margins().getY() * b.margins().getX()];

    }

//Adding swap.
void SwappManager::addSwap(Point *p) {
    int sizeP = 0; //The size of current swapps.
    while (board.get(p[sizeP].getX(), p[sizeP].getY()) != ' ' &&
           !p[sizeP].equal(Point(0, 0))) {
        sizeP++;
    }

    Point func;//Point to use functions.
    for (int i = 0; i < connected; i++) {
        int j=0;
        //Points on the section.
        while (board.get(this->swapps[i][j].getX(), this->swapps[i][j].getY()) != ' ' &&
                !this->swapps[i][j].equal(Point(0, 0))) {
            for (int k = 0; k < sizeP; k++ ) {
                //Delete duplicate swaps
                if (this->swapps[i][j].equal(p[k])) {
                    func.deleteFromArray(p, sizeP, p[k]);
                }
            }
            j++;
        }
    }
    //Dupliceate swaps
    this->swapps[this->connected] = p; //Connect those swapps to the point.
    this->connected++;
}
Point & SwappManager::getPoint() const  {
    return this->point;
}

Point* SwappManager::getSwapps(int i) const {
    return this->swapps[i]; //Return the swap in i case.
}

void SwappManager::swappALl() {
    bool used = false;//If its (0,0) and it is not the end of the array.
    for (int i = 0; i < connected; i++) {
        int j=0;
        Point p = this->swapps[i][j];
            while (board.get(p.getX(), p.getY()) != ' ' && !this->swapps[i][j].equal(Point(0, 0))) {
                Point p = this->swapps[i][j];
                //Doing a swapp.
                board.upsideDown(p.getX() + 1, p.getY() + 1);
                j++; //Incrase j value.
            }
        }
    }
//Return reference to the board.
Board & SwappManager::getBoard() const {
    return this->board;
}
//Setting the point.
void SwappManager::setPoint(Point &p) {
    this->point = p;
}
//Setting the board.
void SwappManager::setBoard(Board &b) {
    this->board = b;
    //We are doing all the instalation because the variables are depends on the board.
    this->connected = 0;
    //You can put points until 8*number of black o whites.
    int size = 8 * (int)fmax(b.getNumberWhites(), b.getNumberBlacks());
    this->swapps = new Point *[size];

    //Anny point can be until all the board swapps.
    for (int i = 0; i < size; i++)
        this->swapps[i] = new Point[b.margins().getY() * b.margins().getX()];


}
SwappManager::~SwappManager() {

   delete [] swapps;
}