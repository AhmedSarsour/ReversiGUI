package Reversi;

/**
 * Created by eliad1998 on 08/01/2018.
 */
public class main {
    public static void main(String[] args) {
        int rows, cols;
        ReadFile r = new ReadFile();
        r.openFile();
        r.readFile();
        rows = r.getBoardSize();
        cols = rows;
        r.closeTheFile();
        Board board = new ConsoleBoard(rows, cols);
        //The moves.
        Moves moves = new Moves(board);
        //The game.

        Game game = new Game(board, moves);
        game.run();
    }
}
