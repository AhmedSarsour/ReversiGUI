package Reversi;
import javafx.scene.control.Label;
import sample.AlertBox;
import sample.ReversiBoard;

/**
 * GuiGame.
 * The game manager of our gui
 * We are still using names like black and white in spite of we changed the game to many colors.
 * We are doing it just for comfort.
 */
public class GuiGame extends Game {
    //The reversi board in order to use the gui.
    private ReversiBoard reversiBoard;
    //A class holds boolean in order to pass it into the mouse clicked event.
    private BlackTurn turn = new BlackTurn();
    private Label lblCurrent = null;
    private Label lblBlack = null;
    private Label lblWhite = null;

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
     * setLabels.
     * Setting the labels of the score.
     * @param lblBlack the label of the first player's points
     * @param lblWhite the label of the second player's points.
     * @param lblCurrent the label which contains the current turn.
     */
    public void setLabels(Label lblBlack, Label lblWhite, Label lblCurrent) {
        this.lblCurrent = lblCurrent;
        this.lblBlack = lblBlack;
        this.lblWhite = lblWhite;
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

    /**
     * putPlayer.
     * Put a player point on the board if we can and continue by the game logic.
     * @param moves the moves of the current player.
     * @param numMoves the number of the moves.
     * @param player the player char (X or O)
     * @param clicked the point the player has clicked on.
     */
    private void putPlayer(SwappManager [] moves, int numMoves, char player, Point clicked) {
        //The point to put on the board.
        Point putPoint = new Point(clicked.getX() + 1, clicked.getY() + 1);
        //Check if we can put this point - current move.
        boolean isOk = checkClicked(moves, numMoves, putPoint);
        if (isOk) {
            //Put the point on the board.
            board.put(putPoint.getX(), putPoint.getY(), player);
            //Swapping the board by the point we put.
            moves[searchPoint(moves, numMoves, putPoint)].swappAll();
            //Showing the board after the swapping.
            board.show();
            //Passing the turn to the next player.
            turn.passTurn();
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

            //Continue if there are moves for both
            if (numBlackMoves != 0 || numWhiteMoves != 0) {
                //Getting the input point
                Point clicked = reversiBoard.getClicked();
                //If clicked area outside the board do not do anything.
                if (clicked.equals(new Point(-1,-1))) {
                    return;
                }
                if (blackTurn) {
                    lblCurrent.setText("Current player: Black");
                    //Put in the game Player 1 :its your move on a label.
                    //###################REMEMBER DOING IT###############
                    SwappManager[] blackMoves = black.playerMoves();
                    numBlackMoves = black.getNumMoves();
                    //Has more moves
                    if (numBlackMoves > 0) { // Found black moves
                        putPlayer(blackMoves, numBlackMoves, 'X', clicked);
                    } else { //No possible moves.
                        AlertBox.display("Message ","No possible moves." +
                                " Play passes back to the other player");
                        turn.passTurn();
                    }
                } else { //White turn
                    //Put in the game Player 1 :its your move on a label.
                    //###################REMEMBER DOING IT###############
                    SwappManager[] whiteMoves = white.playerMoves();

                    numWhiteMoves = white.getNumMoves();
                    if (numWhiteMoves > 0) {
                        putPlayer(whiteMoves, numWhiteMoves, 'O', clicked);
                    }
                    else { //No possible moves.
                        AlertBox.display("Message",
                                "No possible moves. Play passes back to the other player.");
                        turn.passTurn();
                    }
                }
                //Setting the text of labels.
                if (turn.getTurn()) {
                    //First player turn.
                    lblCurrent.setText("Current player: Black");
                } else {
                    //Second player turn.
                    lblCurrent.setText("Current player: White");
                }
                //Showing the current scores of the players.
                lblBlack.setText("Black Player score: " + board.getNumberBlacks());
                lblWhite.setText("White Player score: " + board.getNumberWhites());
                //Reset the point we clicked on to (-1,-1) until clicking another area on the board.
                reversiBoard.resetClicked();


                // End of the game - there are no possible moves for both players.
                if (black.getNumMoves() == 0 && white.getNumMoves() == 0) {
                    int numBlacks = board.getNumberBlacks();
                    int numWhites = board.getNumberWhites();
                    // More blacks than whites - first player wins.
                    if (numBlacks > numWhites) {
                        AlertBox.display("End of game", "The winner is the player1!");
                    } else if (numBlacks < numWhites) { //More whites than black - second player wins.
                        AlertBox.display("End of game", "The winner is the player2!");
                    } else { //A draw - same points
                        AlertBox.display("End of game", "This is a draw!");

                    }
                }
            }




        });
       }
    }