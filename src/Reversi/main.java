package Reversi;

/**
 * Created by eliad1998 on 08/01/2018.
 */
public class main {
    public static void main(String[] args) {
        Board board = new ConsoleBoard(3, 3);
        //The moves.

        //The game.



        Game game = new ConsoleGame(board);
        game.run();
    }
}
