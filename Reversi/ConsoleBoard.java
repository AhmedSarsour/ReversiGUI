package Reversi;

/**
 * Created by eliad1998 on 08/01/2018.
 */
public class ConsoleBoard extends Board {
    public ConsoleBoard(int rows, int cols) {
        super(rows,cols);
    }
    public void show(){
        //First showing | 1 | 2 | ....
        System.out.print(" |");
        //Iterates the number of columns.
        for (int i = 1; i <= cols; i++) {
            System.out.print(" " + i + " |");
        }
        System.out.println("");
        //Showing the matrix itself
        for (int i = 0; i <= rows; i++) {
            for (int k = 0; k <= 4 * cols + 1; k++) {
                System.out.print("-");
            }
            System.out.println("");
            //Showing the content itself of the matrix.
            if (i < rows) {
                System.out.print(i + 1 + "|");
                for (int j = 0; j < cols; j++) {
                    System.out.print(" " + board[i][j] + " |");
                }
                System.out.println();
            }
        }
    }
}
