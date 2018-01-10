package sample;
//these 2 imporst are needed for the files.
import java.io.*;
import java.util.*;
// this import is needed for "exit".
import static java.lang.System.exit;
//this class is made for reading the menuInformation text file.
public class ReadFile {
    private Scanner x;
    private String firstPlayer;
    private String secondPlayer;
    private int boardSize;
    /**
     * openFile: it opens the file menuInfo.txt.
     */
    public void openFile() {
        try {
            x = new Scanner(new File("src\\Reversi\\menuInfo.txt"));
        } catch (Exception e) {
            System.out.println("could not find file");
            exit(-1);
        }
    }

    /**
     * readFile: it reads the file and knows what the first player is and sizez of the board.
     */
    public void readFile() {
        int i = 0;
        while (x.hasNext()) {
            String a = x.next();
            String b = x.next();
            String c = x.next();
            if (i == 0) {
                 this.firstPlayer = x.next();
            } else if (i == 1) {
                this.secondPlayer = x.next();
            } else if (i == 2) {
                this.boardSize = x.nextInt();
            }
            i++;
        }
    }

    /**
     * getColSize: it gets the size from the menuInfo file.
     * @return the board's cols' size.
     */
    public int getBoardSize() {
        return this.boardSize;
    }

    /**
     * closeFile: closes the file menuInfo.txt.
     */
    public void closeTheFile() {
        x.close();
    }
}
