package Reversi;
/*
 *      student 1: ahmed sarsour. 315397059
 *      student 2: Eliad Arzuan 206482622
 */
public abstract class Board {
    protected int rows;   //The board rows.
    protected int cols;   //The board cols
    private Point blacks[];//The board black places.
    private Point whites[];//The board white places.
    private int numBlacks;//The number of current black players.
    private int numWhites;//The number of current white players.
    protected char[][]board;
    /**
     * Board.
     * The constructor of our class Board.
     * @param rows - the number of the rows of the board.
     * @param cols - the number of the columns of the board.
     **/
    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        //Create array of the black's player parable.
        //Has rows*cols places because it is limit.
        this.blacks = new Point[rows * cols];
        //The current number of black's chars.
        //Pointer because when we move the board as a reference we want it to update.
        this.numBlacks = 0;
        //Create array of the white's player parable.
        //Has rows*cols places because it is limit.
        this.whites = new Point[this.rows * this.cols];
        //The current number of black's chars.
        //Pointer because when we move the board as a reference we want it to update.
        this.numWhites = 0;
        //Creating dinamic matrix- a little help from stack overflow
        this.board = new char [rows][cols];
        //Put the players by moving on the matirx.
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                //The default character is only space.
                this.board[i][j] = ' ';
            }
        }
    //Putting the start characters.
        put(this.margins().getX() / 2,this.margins().getY() / 2,'O');
        put(this.margins().getX() / 2,this.margins().getY() / 2 + 1,'X');
        put(this.margins().getX() / 2 + 1,this.margins().getY() / 2,'X');
        put(this.margins().getX() / 2 + 1,this.margins().getY() / 2 + 1,'O');

//        put(4, 3, 'X');
//        put(4, 4, 'X');
//        put(4, 5, 'X');
//
//
//        put(5, 3, 'O');
//        put(5, 4, 'O');
//        put(5, 5, 'O');




    }
    /**
     * show function.
     * Showing the board by the format of the game we requested to do.
     * The function is const because it does not change the inner board structure.
     **/
    public abstract void show();
    /**
     * isValid.
     * The function is const because we don't change the inner members.
     * @param x the x cordinate.
     * @param y the y cordinate.
     * @return true if board[x,y] contains char, false otherwise.
     **/
    public boolean isValid(int x, int y){
        //Out of board margins.
        //Rows margins.
        if (x < 0 || x >= this. rows) {
            return false;
        }
        //Columns margins
        if (y < 0 || y >= this. cols) {
            return false;
        }
        return true;
    }
    /**
     * put
     * puts a player char on the board in the place x,y (Behind the scenes in x-1,y-1).
     * @param x - the x cordinate.
     * @param y- the y cordinate.
     * @param c - the char we want to put.
     * @return true if succesfully put, false otherwise.
     **/
    public boolean put(int x, int y, char c) {
    //It is not a valid point.

        if(x > this.margins().getX()|| y >this.margins().getY() || x <= 0 || y <= 0) {
            return false;
        }

    //Make it comfort for the user - so we will make the x and y easy for the user.
        int newX = x - 1;
        int newY = y - 1;
    //Black character.
        if (c == 'X') {
            //Updating the current black.
            this.blacks[this.numBlacks] = new Point(newX, newY);


        this.numBlacks = this.numBlacks + 1;
        }
    //Put white character
        if (c == 'O') {
            //Updating the white array and number.
        this.whites[this.numWhites] = new Point(newX, newY);
        this.numWhites = this.numWhites + 1;
        }
    //Put the character.
        //We take the place and reduce one because index starts with 1.
        this.board[newX][newY] = c;
        return true;
    }
    /**
     * removeBlack.
     * Remove black char from the board in x,y position.
     * @param x the x cordinate.
     * @param y the y cordinate.
     **/
    private void removeBlack(int x, int y) {
        //Initializing new point just for using in a function.
        //Delete the current black from the array of the blacks.
        Point.deleteFromArray(this.blacks,this.numBlacks, new Point(x, y));
        this.numBlacks = this.numBlacks - 1; //Reduce the number of blacks.
    }
    /**
     * removeWhite.
     * Remove white char from the board in x,y position.
     * @param x the x cordinate.
     * @param y the y cordinate.
     **/
    private void removeWhite(int x, int y) {
        //Initializing new point just for using in a function.
        //Delete the current white from the array of the blacks.
        Point.deleteFromArray(this.whites, this.numWhites, new Point(x, y));
        this.numWhites = this.numWhites - 1; //Reduce the number of whites.
    }
    /**
     * upsideDown.
     * The function is const because we don't change the inner members.
     * Swapp black to white or white to black in the x, y position.
     * @return true if succeed, false if didn't find the point.
     **/
    boolean upsideDown(int x, int y) {
        //Not valid point.
        if(x > this.margins().getX()|| y >this.margins().getY() || x <= 0 || y <= 0) {
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
    /**
     * getBlacks.
     * The function is const because we don't change the inner members.
     * @return pointer as array of the black players in the game.
     **/
    Point[] getBlacks(){
        return this.blacks;
    }
    /**
     * getWhites.
     * The function is const because we don't change the inner members.
     * @return pointer as array of the white players in the game.
     **/    public Point[] getWhites(){
        return this.whites;
    }
    /**
     * getNumberBlacks.
     * The function is const because we don't change the inner members.
     * @return the number of black chars at the board.
     **/    public int getNumberBlacks() {
        return this.numBlacks;
    }
    /**
     * getNumberWhites.
     * The function is const because we don't change the inner members.
     * @return the white of black chars at the board.
     *
     */
    public int getNumberWhites() {
        return this.numWhites;
    }
    /**
     * get.
     * The function is const because we don't change the inner members.
     * @param x the x cordinate.
     * @param y the y cordinate.
     * @return the char at x,y position on the board.
     *   */
    public char get(int x, int y) {
        if (isValid(x,y)) {
            return this.board[x][y];
        }
        return  ' ';
    }
    /**
     * margins.
     * The function is const because we don't change the inner members.
     * @return point which x is the rows number of the board and y is the columns number of the board.
     **/
    public Point margins() {
        return new Point(this.rows, this.cols);
    }

    public static char firstPlayerChar() {
        return 'X';
    }
    public static char secondPlayerChar() {
        return 'O';
    }

    public static char emptyChar() {
        return ' ';
    }

}
