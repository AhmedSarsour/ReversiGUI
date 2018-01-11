package Reversi;

/**
 * Created by eliad1998 on 11/01/2018.
 */
public class BlackTurn {
    private boolean blackTurn;
    public BlackTurn() {
        this.blackTurn = true;
    }

    public void moveTurn() {
        this.blackTurn = !blackTurn;
    }

    public boolean getTurn() {
        return this.blackTurn;
    }
}
