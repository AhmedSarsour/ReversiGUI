package Reversi;

import Reversi.Board;
import Reversi.Game;
import sample.AlertBox;
import sample.ReversiBoard;

/**
 * GuiGame.
 * The game manager of our gui
 */
public class GuiGame extends Game {
    //The reversi board in order to use the gui.
    private ReversiBoard reversiBoard;
    //A class holds boolean in order to pass it into the mouse clicked event.
    private BlackTurn turn = new BlackTurn();

    /**
     * GuiGame.
     * Our constructor doing the same as Game.
     * @param b the board we want to pass.
     */
    public GuiGame(Board b) {
        super(b);
    }

    /**
     * setReveersiBoard.
     * Setting the member of reversi board we have.
     * @param rb a reversi board.
     */
    public void setReversiBoard(ReversiBoard rb) {
        this.reversiBoard = rb;
    }

    /**
     * checkClicked.
     * Checks if the move we want to do is ok.
     * @param swappManager a swappmanager.
     * @param size the size of the swappmanager.
     * @param p our move we want to check.
     * @return true - ok, false - otherwise.
     */
    private boolean checkClicked(SwappManager [] swappManager, int size, Point p) {
        return searchPoint(swappManager, size, p) != -1;
    }

    private void putPlayer(SwappManager [] moves, int numMoves, char player, Point clicked) {
        Point putPoint = new Point(clicked.getX() + 1, clicked.getY() + 1);
        boolean isOk = checkClicked(moves, numMoves, putPoint);
        AlertBox.display("title", "Just clicked " + clicked.toString());
        if (isOk) {
            // In case of wrong point to put it won't put it
            board.put(putPoint.getX(), putPoint.getY(), player);
            moves[searchPoint(moves, numMoves, putPoint)].swappAll();
            //Showing the board after the swapping.
            board.show();
            turn.moveTurn();
        } else {
            AlertBox.display("Error", "You can't do this move!");
        }
    }

    /**
     * run.
     * Running the game now with the gui.
     */
    public void run() {
        board.show();
        reversiBoard.setOnMouseClicked(e -> {
            int numBlackMoves = black.getNumMoves();
            int numWhiteMoves = white.getNumMoves();
            boolean blackTurn = turn.getTurn();

            if (numBlackMoves != 0 && numWhiteMoves != 0) {
                System.out.println(blackTurn);
                //Getting the input point
                Point clicked = reversiBoard.getClicked();
                if (blackTurn) {
                    //Put in the game Player 1 :its your move on a label.
                    //###################REMEMBER DOING IT###############
                    SwappManager[] blackMoves = black.playerMoves();
                    numBlackMoves = black.getNumMoves();
                    //Has more moves
                    if (numBlackMoves > 0) { // Found black moves
                        putPlayer(blackMoves, numBlackMoves, 'X', clicked);
                    } else { //No possible moves.
                        System.out.println("No possible moves. Play passes back to the other player. Press any key to continue.");
                        turn.moveTurn();
                    }
                } else { //White turn
                    //Put in the game Player 1 :its your move on a label.
                    //###################REMEMBER DOING IT###############
                    SwappManager[] whiteMoves = white.playerMoves();

                    numWhiteMoves = white.getNumMoves();
                    if (numWhiteMoves > 0) {
                        System.out.println("How i got here?");
                        putPlayer(whiteMoves, numWhiteMoves, 'O', clicked);
                    }
                    else { //No possible moves.
                        System.out.println("No possible moves. Play passes back to the other player. Press any key to continue.");
                        turn.moveTurn();
                    }
                }

                reversiBoard.resetClicked();
            }

        });

       }
    }
