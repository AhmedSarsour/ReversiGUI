package Reversi;

/**
 * Swapp manager.
 * In charge of converting point to the points he can swapp.
 * Any move can cause to some swapps from some directions so it can make it easier.
 * Point to swap -> array of pointers to the points he can swapp.
 */
public class SwappManager {
    private Board board; //Referrence to board.
    private Point point; //Reference to point.
    private Point [][] swapps; //Array of array of points to swap.
    private int connected;//The number of arrays of points to swapp.
    /**
     * Constructor of our class.
     * @param p a referrence to point that connected to move.
     * @param b a referrence of board we work on.
     */
    public SwappManager(Point p,Board b) {
        this.connected = 0;
        //You can put points until 8*number of black o whites.
        int size = b.margins().getX() * Math.max(b.getNumberWhites(), b.getNumberBlacks());
        this.swapps = new Point [size][b.margins().getY() * b.margins().getX()];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < b.margins().getY() * b.margins().getX(); j++) {
                this.swapps[i][j] = new Point (0,0);
            }
        }
        this.point = p;
        this.board = b;

    }
    /**
     * getPoint.
     * const because does not change the inner member of the class.
     * @return the point of this swapp manager.
     */
    public Point getPoint() {
        return this.point;
    }
    /**
     * getSwapps.
     * Const because we just get it.
     * @param i the index we want to get the swap from.
     * @return the swapps at i position.
     */
    public Point [] getSwapps(int i) {
        return this.swapps[i]; //Return the swap in i case.
    }
    /**
     * addSwap.
     * Adding a swapp to this swap manager.
     * @param p the array of points to swapp we ant to add.
     */
    public void addSwap(Point p[]) {
        int sizeP = 0; //The size of current swapps.
        while (board.get(p[sizeP].getX(), p[sizeP].getY()) != ' ' &&
                !p[sizeP].equals(new Point(0, 0))) {
            sizeP++;
        }
        for (int i = 0; i < connected; i++) {
            int j=0;
            //Points on the section.
            while (board.get(this.swapps[i][j].getX(), this.swapps[i][j].getY()) != ' ' &&
                    !this.swapps[i][j].equals(new Point(0, 0))) {
                for (int k = 0; k < sizeP; k++ ) {
                    //Delete duplicate swaps
                    if (this.swapps[i][j].equals(p[k])) {
                        Point.deleteFromArray(p, sizeP, p[k]);
                    }
                }
                j++;
            }
        }
        //Dupliceate swaps
        this.swapps[this.connected] = p; //Connect those swapps to the point.
        this.connected++;
    }

    //Add swapps to this point.
    /**
     * swappAll
     * Swapp all the point caused by this move.
     */
    public void swappAll() { //Swapp all those points at board b.
        boolean used = false;//If its (0,0) and it is not the end of the array.
        for (int i = 0; i < connected; i++) {
            int j=0;
            Point p = this.swapps[i][j];
            while (board.get(p.getX(), p.getY()) != ' ' && !this.swapps[i][j].equals(new Point(0, 0))) {
                p = this.swapps[i][j];
                //Doing a swapp.
                board.upsideDown(p.getX() + 1, p.getY() + 1);
                j++; //Incrase j value.
            }
        }
    }
    /**
     * getBoard.
     * Const because we don't change the inner members.
     * @return referrence to the board of this swapp manager.
     */
    public Board getBoard() {
        return this.board;
    }
    /**
     * setPoint.
     * Setting our point.
     * @param p the point we want to change to.
     */
    public void setPoint(Point p) {
        this.point = p;
    }

    /**
     * setPoint.
     * Setting our board.
     * @param b the board we want to change to.
     */
    public void setBoard(Board b) {
        this.board = b;
        //We are doing all the instalation because the variables are depends on the board.
        this.connected = 0;
        //You can put points until 8*number of black o whites.
        int size = b.margins().getX() * Math.max(b.getNumberWhites(), b.getNumberBlacks());
        this.swapps = new Point [size][b.margins().getY() * b.margins().getX()];
    }

}
