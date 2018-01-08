/***********************************************************
* Eliad Arzuan
* 206482622
****************************************************/
#include "Point.h"
using namespace std;
//Default constructor.
Point ::Point(){}
//Constructor.
Point::Point(int x, int y) : x(x), y(y) {}
int Point::getX() const {
    return this->x;
}

int Point::getY() const {
    return this->y;
}

bool Point::equal(Point p) {
    return this->x == p.getX() && this->y == p.getY();
}

//Delete p from the array
 void Point::deleteFromArray(Point * arr, int numPoints, Point p) {
    int index = 0;

    for (int i = 0; i < numPoints; i++) {
        if (arr[i].equal(p)) { //Found p
            index = i;
            break;
        }
    }
    //Moving the point one place ahead from the point we want to remove.
    for (int i = index;i < numPoints; i++) {
        arr[i] = arr[i+1];
    }

}

ostream &operator <<(ostream &out, const Point &p)
{
    out << "(" << p.getX() << "," << p.getY() << ")";
    return out;
}
//Destructor.
Point::~Point() {
}
