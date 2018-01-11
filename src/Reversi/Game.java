package Reversi;

import java.util.Scanner;

public abstract class Game {
    protected Board board; //Reference to the board.
    protected Moves moves; //Reference to the moves factory.
    protected Player black; //Black player.
    protected Player white; //White player.

    /**
     * Game.
     * The constructor of our class.
     * @param b a referrence to the board.
     */
    public Game(Board b) {
        this.board = b;
        this.moves = new Moves(board);
        this.black = new Player(b,moves, 'X');
        this.white = new Player(b, moves , 'O');
    }

        /**
         * searchPoint.
         * @param swappManager an array of moves.
         * @param size the size of the array.
         * @param p point we want to search in the array of swapp manager (the point connected to each swappmanager[i]).
         * @return the point index in the array.
         */
    protected int searchPoint(SwappManager [] swappManager, int size, Point p) {
        for (int i = 0; i < size; i++) {
            //-1 to update to the real board.
            if (swappManager[i].getPoint().equals(new Point(p.getX() - 1, p.getY() - 1))) {
                return i;
            }
        }
        return -1; //Case of didn't found.
    }


    /**
     * run.
     * Running the game.
     */
    public abstract void run();
}
