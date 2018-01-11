package Reversi;

/**
 * BlackTurn.
 * This class holds a reference to boolean in order to pass it into argument to click event.
 */
public class BlackTurn {
    //True - this is the first player turn, False- this is the second player turn.
    private boolean blackTurn;

    /**
     * BlackTurn.
     * The constructor of our class - sets blackTurn to true.
     * The first turn will be of the black so we will set it to true.
     */
    public BlackTurn() {
        this.blackTurn = true;
    }

    /**
     * passTurn.
     * Pass the turn to the other player by doing not.
     */
    public void passTurn() {
        this.blackTurn = !blackTurn;
    }

    /**
     * getTurn.
     * @return the current player turn.
     */
    public boolean getTurn() {
        return this.blackTurn;
    }
}
