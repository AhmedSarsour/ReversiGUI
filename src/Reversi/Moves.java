package Reversi;

/**
 * Created by eliad1998 on 08/01/2018.
 */
public class Moves {
    private Board board; //Referrence to board.
    private Point blacks[]; //Array of current black players.
    private Point whites[]; //Array of current white players.
    private int numBlackMoves;
    private int numWhiteMoves; //Changed only after activating the function whiteMoves!!
    /**
     * Moves.
     * The constructor of our class.
     * @param b a referrence to board.
     */
    public Moves(Board b) {
        this.board = b;
        //Creating pointer of current white and black moves because we want to make it dinamic.
        this.numBlackMoves = 0;
        this.numWhiteMoves = 0;
        this.blacks = board.getBlacks();
        this.whites = board.getWhites();
    }

    /**
     * getNumWhites.
     * @return the number of the current white chars on the board.
     */
    private int getNumWhites() {
        return board.getNumberWhites();
    }

    /**
     * getNUmBLacks.
     * @return the number of the current black chars on the board.
     */
    private  int getNumBlacks() {
        return board.getNumberBlacks();
    }
    /**
     * scanForPlayer.
     * As we mentioned at the top of there are 8 areas which a move can be.
     * By this function we will search for player at the same type of our player.
     * For example OXO - we will find O on the other side (we save the Xs).
     * We will move by x+xAdd y+yAdd until we got to empty place.
     * @param x x position.
     * @param y y position
     * @param xAdd how much adding to x.
     * @param yAdd how much adding to y.
     * @param isBlack true if we want to search for black. false for white.
     * @return array of points to swap until we got to the same player color.
     */
    private Point[] scanForPlayer(int x, int y, int xAdd, int yAdd, boolean isBlack){
        boolean check = false;
        //Case of white.
        char player = 'O';
        char rival = 'X';
        if (isBlack) {
            player = 'X';
            rival = 'O';
        }
        //Max size to the array - all the board.
        int size = Math.max(this.board.margins().getX(), this.board.margins().getY());
        Point []toReverse = new Point[size]; //The points we want to reverse.

        for (int i = 0; i < size; i++) {
            toReverse[i] = new Point(0,0);
        }
        int i = x;
        int j = y;
        int index = -1; //-1 to determine if there is point or not because the point is initialized to 0,0.
        if (board.isValid(i, j)) {
            while (board.get(i, j) == rival || board.get(i, j) == player) {
                if (board.get(i, j) == rival) {
                    index++;
                    toReverse[index] = new Point(i, j);
                }
                if (board.get(i, j) == player && index != -1) {
                    //Found the margin to reverse.
                    return toReverse;
                }
                j += yAdd;
                i += xAdd;
            }
        }
        return null;//Null in case of empty.
    }
    /**
     * blackMoves
     * @return the SwappManager array to represent the black moves and which move cause to which swapp.
     */
    SwappManager [] blackMoves() {//Array of arrays of points that contains moves of black.
        SwappManager []  moves = new SwappManager[this.getNumWhites() * 8];//8 options between the whites.
        int index = 0;
        for (int i = 0; i < getNumWhites(); i++) {
            Point pos = this.whites[i]; //The point where the current
            int x = pos.getX();
            int y = pos.getY();
            int current = index; //The current index in swappManager.
            boolean found = false; //Found the point.

            //All the areas 1-8 for example slant from (1,1) ->x+1,y+1
            Point areas[] = {new Point(-1, -1), new Point(-1, 0), new Point(-1, 1)
                    , new Point(0, -1), new Point(0, 1), new Point(1, -1), new Point(1, 0), new
                    Point(1, 1)};
            //Moving on all the areas.
            for (int j = 0; j < 8; j++) {
                if (board.isValid(x + areas[j].getX(), y + areas[j].getY())) {
                    if (board.get(x + areas[j].getX(), y + areas[j].getY()) == ' ') {


                        Point point = new Point(x + areas[j].getX(), y + areas[j].getY());
                        //Potential moves
                        Point []potential = scanForPlayer(x, y, -areas[j].getX(),
                                -areas[j].getY(), true);
                        if (potential != null) { //If is not null
                            //Reset found.
                            found = false;
                            if (index != 0) {
                                //Search for moves.
                                for (int k = 0; k < index; k++) {
                                    if (moves[k].getPoint().equals(point)) {
                                        current = k;
                                        found = true;
                                    }
                                } //endfor
                            }
                            if (!found) { //Create new swapp manager.
                                //moves[current] is initialized so we just will set it
                                //Set the point and board because it is maybe with default constructor.
                                current = index;
                                moves[current] = new SwappManager(point, this.board);
                                index++;
                            }
                            //Adding the potential swap if is not null;e
                            moves[current].addSwap(potential);
                        }

                    } //endif
                }//end for
            }
        } //end for
        this.numBlackMoves = index; //Updating the current number of black moves.
        return moves;
    }
    /**
     * whiteMoves
     * @return the SwappManager array to represent the white moves and which move cause to which swapp.
     */
    public SwappManager[] whiteMoves(){
        SwappManager []  moves = new SwappManager[this.getNumBlacks() * 8];//8 options betwenn the blacks.
        int index = 0;
        for (int i = 0; i < getNumBlacks(); i++) {
            Point pos = blacks[i]; //The point where the current
            int x = pos.getX();

            int y = pos.getY();
            int current = index; //The current index in swappManager.
            boolean found = false; //Found the point.

            //All the areas 1-8 for example slant from (1,1) ->x+1,y+1
            Point areas[] = {new Point(-1, -1), new Point(-1, 0), new Point(-1, 1)
                    , new Point(0, -1), new Point(0, 1), new Point(1, -1), new Point(1, 0), new
                    Point(1, 1)};
            //Moving on the areas.
            for (int j = 0; j < 8; j++) {
                if (board.isValid(x + areas[j].getX(), y + areas[j].getY())) {
                    if (board.get(x + areas[j].getX(), y + areas[j].getY()) == ' ') {
                        if (board.get(x + areas[j].getX(), y + areas[j].getY()) == ' ') {
                            Point point = new Point(x + areas[j].getX(), y + areas[j].getY());
                            int a = x + areas[j].getX();
                            int b =  y + areas[j].getY();
                            //Potential moves
                            Point[]potential = scanForPlayer(x, y, -areas[j].getX(),
                                    -areas[j].getY(), false);
                            //Check if not null.
                            if (potential != null) {
                                //Reset found.
                                found = false;
                                if (index != 0) {
                                    //Check if the point exist (for swapp from some directions).
                                    for (int k = 0; k < index; k++) {
                                        if (moves[k].getPoint().equals(point)) {
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
                                    moves[current] = new SwappManager(point, this.board);
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
        this.numWhiteMoves = index;
        return moves;
    }

    public int getNumBlackMoves() {
        return this.numBlackMoves;
    }

    public int getNumWhiteMoves() {
        return this.numWhiteMoves;
    }
}