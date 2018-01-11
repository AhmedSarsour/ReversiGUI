package Reversi;

import java.util.Scanner;

/**
 * Created by eliad1998 on 11/01/2018.
 */
public class ConsoleGame extends Game {
    public ConsoleGame(Board board) {
        super(board);
    }

    /**
     * printPoints.
     * Print the current moves.
     * @param swappManager moves array.
     * @param num the number of moves.
     */
    private void printPoints (SwappManager [] swappManager, int num) {

        for (int i = 0; i < num; i++) {
            //The point before update (at the pointer shown as (3,3) will show (4,4)
            Point beforeUpdate = swappManager[i].getPoint();
            //Update the point to make it convenience.
            Point updated = new Point(beforeUpdate.getX() + 1, beforeUpdate.getY() + 1);
            System.out.print(updated); //Print the point.
            if (i != num - 1) {
                System.out.print(",");
            }
        }
        System.out.println();

    }


    /**
     * inputPoint.
     * Gets 2 numbers from the user and check if its a valid move.
     * @param swappManager the swap manager array connected to the point because we want to swapp the points
     *                     that matches to it.
     * @param size the size of the swap manager
     * @return the point that the user put.
     */
    private Point inputPoint(SwappManager [] swappManager, int size) {
        int x = 0, y = 0;
        boolean isOk = false; //Checks if the input is ok.
        Point p;
        while (!isOk) {
            System.out.println("Please enter your move row, col:");
            Scanner s = new Scanner(System.in);
//            //cin >> x; //Input one number.
            x = s.nextInt();
//            isOk = !cin.fail(); //Checks if a number that is not double.

//            cin >> y; //Input second number.
            y = s.nextInt();
            p = new Point(x,y);
            //Check if the point is a possible move.
            isOk = true;
            if (isOk) { //The input is ok.
                p = new Point(x, y);
                //Check if the point is a possible move.
                isOk &= searchPoint(swappManager, size, p) != -1;
                //Wrong move.
                if (!isOk) {
                    System.out.println("Move does not exist.");
                }
            } else { //Didnt entered the numbers properly.
                System.out.println("Enter 2 numbers please.");
            }
            System.out.println();
        }
        return new Point(x, y);
    }
    @Override
    public void run() {
    //Getting the number of black and white moves.
        int numBlackMoves = black.getNumMoves();
        int numWhiteMoves = white.getNumMoves();
        //Bool to check which player the move is.
        boolean blackTurn = true;
        //Stops when 2 players dont have more moves.
        while (numBlackMoves != 0 && numWhiteMoves != 0) {
            //Showing the board.
            board.show();
            //The black turn.
            if (blackTurn) {
                System.out.println("X: It's your move.");
                //The moves of the black.
                SwappManager []blackMoves = black.playerMoves();

                numBlackMoves = black.getNumMoves();
                //Has more moves
                if (numBlackMoves > 0) {
                    System.out.print("Your possible moves:");
                    //Printing the moves.
                    printPoints(blackMoves, numBlackMoves);
                    //Turn to put the move.
                    System.out.println();
                    Point p = inputPoint(blackMoves, numBlackMoves);
                    //Put a parrel and swap the match white parrels.
                    black.put(p.getX(), p.getY());
                    blackMoves[searchPoint(blackMoves, numBlackMoves, p)].swappAll();
                } else { //No possible moves.
                    System.out.println("No possible moves. Play passes back to the other player. Press any key to continue.");
                    //Enter to continue.
                    // cin.clear();
                    // std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

                }
                blackTurn = false; //Move to the white turn
            } else { //White turn
                System.out.println("O: It's your move.");
                //The moves of the white player.
                SwappManager []whiteMoves = white.playerMoves();
                //Number of the moves.
                numWhiteMoves = white.getNumMoves();
                if (numWhiteMoves > 0) { //Has more moves.
                    System.out.print("Your possible moves:");
                    //Print the moves.
                    printPoints(whiteMoves, numWhiteMoves);
                    //Turn to put the move.
                    System.out.println();
                    Point p = inputPoint(whiteMoves, numWhiteMoves);
                    //Put the white char and swap black characters.
                    white.put(p.getX(), p.getY());
                    whiteMoves[searchPoint(whiteMoves, numWhiteMoves, p)].swappAll();

                    blackTurn = true;
                } else { //No possible moves.
                    System.out.println("No possible moves. Play passes back to the other player. Press any key to continue.");
                }
            }

        } //endWhile.

        //End game
        if (board.getNumberBlacks() >= board.getNumberWhites()) { //Black wins.
            System.out.println("End of the game.  The winner is X ");
        } else { //White wins.
            System.out.println("End of the game.  The winner is O ");
        }

    }
}
