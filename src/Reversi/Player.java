package Reversi;

/**
 * Created by eliad1998 on 08/01/2018.
 */
public class Player {
    private Board board; //Reference to the board we work on.
    private Moves moves; //Reference to the moves factory that will get the moevs for it.
    private char playerId;
    /**
     * Black.
     * The constructor of our class.
     * @param &b reference to the board.
     * @param &m reference to the moves factory.
     **/
    public Player(Board b, Moves m, char playerId){
        this.board = b;
        this.moves = m;
        this.playerId = playerId;
    }
    /**
     * put
     * puts a black char on the board in the place x,y (Behind the scenes in x-1,y-1).
     * @param x - the x cordinate.
     * @param y- the y cordinate.
     **/
    public void put(int x, int y){
        this.board.put(x, y, playerId); //Use the put function with the player's char.
    }
    /**
     * playerMoves.
     * The function is const because it does not change the inner members.
     * @return array of swappManager- with it we can know which of move we can have. And which cause to which swapp.
     **/
    public SwappManager[] playerMoves(){
        if (playerId == 'X') {
            return moves.blackMoves();
        } else if (playerId == 'O') {
            return moves.whiteMoves();
        }
        return null; //Null in case of wrong player id.
    }
    /**
     * getNumMoves.
     * The function is const because it does not change the inner members.
     * @return the number of moves the player can have.
     **/
    int getNumMoves() {
        if (playerId == 'X') {
            moves.blackMoves(); //Do it to get the number of the black moves.
            return moves.getNumBlackMoves();
        } else if (playerId == 'O') {
            moves.whiteMoves(); //Do it to get the number of the black moves.
            return moves.getNumWhiteMoves();
        }
        return -1; //-1 in cases of wrong player id.
    }
}
