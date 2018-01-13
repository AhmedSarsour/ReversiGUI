package sample;
import javafx.scene.paint.Color;
import java.io.*;
import java.util.*;
// this import is needed for "exit".
import static java.lang.System.exit;

/**
 * SettingsReader.
 * This class is used to reading and writing to the settings file of our game.
 */
public class SettingsReader {
    private Scanner scanner;
    private String firstPlayer;
    private String secondPlayer;
    private int boardSize;
    private final String path = "src\\sample\\";
    /**
     * SettingsReader.
     * The constuctor of our class - reading the file and setting the variables.
     * @param fileName the name of the settings file.
     */
    public SettingsReader(String fileName) {
        try {
            scanner = new Scanner(new File(path + fileName));
            //Reading the file and setting the variables.
            readFile();
        } catch (Exception e) {
            System.out.println("could not find file");
            exit(-1);
        }
    }
    /**
     * readFile: it reads the file and knows what the first player is and sizez of the board.
     */
    private void readFile() {
        int i = 0;
        while (scanner.hasNext()) {
            //Reading every line in the file.
            if (i == 0) {
                //Reading the first player color.
                this.firstPlayer = scanner.next();
            } else if (i == 1) {
                //Reading the second player color.
                this.secondPlayer = scanner.next();
            } else if (i == 2) {
                //Reading the board size.
                this.boardSize = scanner.nextInt();
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
     * getFirstPlayerColor.
     * @return the color of the first player.
     */
    public Color getFirstPlayerColor() {
        return stringToColor(this.firstPlayer);
    }

    /**
     * getSecondPlayer.
     * @return the color of the second player.
     */
    public Color getSecondPlayerColor() {
        return stringToColor(this.secondPlayer);

    }

    /**
     * getFirstPlayer.
     * @return the first player string,
     */
    public String getFirstPlayer() {
        return this.firstPlayer;
    }

    /**
     * getSecondPlayer.
     * @return the second player string.
     */
    public String getSecondPlayer() {
        return this.secondPlayer;
    }

    /**
     * closeFile: closes the file menuInfo.txt.
     */
    public void closeTheFile() {
        scanner.close();
    }

    /**
     * stringToColor
     * @param color the string of the color
     * @return a color represented by this string.
     */
    private Color stringToColor(String color) {
        switch (color) {
            case "White":
                return Color.WHITE;
            case "Black":
                return Color.BLACK;

            case "Yellow":
                return Color.YELLOW;

            case "Green":
                return Color.GREEN;
            case "Pink":
                return Color.PINK;
            case "Aqua":
                return Color.AQUA;
            case "Orange":
                return Color.ORANGE;
            case "Gray":
                return Color.GRAY;
            case "Red":
                return Color.RED;

            case "Blue":
                return Color.BLUE;

            default: //Wrong color or wrong syntax
                return Color.BLACK;
        }
    }

    /**
     * clearFile
     * @param fileName the name of the file we want to clear.
     * Clears the file.
     */
    public void clearFile(String fileName) {
        try {
            //Clearing the file by writing on it empty char.
            PrintWriter p = new PrintWriter(path + fileName);
            p.write("");
            //Closing the file.
            p.close();
        } catch (Exception e) {
            System.out.println("unable to clear file\n");
            exit(-1);
        }
    }

    /**
     * writeToFile.
     * @param fileName the file we want to write on.
     * @param lstInfo list contains - {first player color, second player color, board size}
     * Writing to file those settings of lstInfo.
     */
    public void writeToFile(String fileName, List lstInfo) {
        try {
            //First clearing the file.
            clearFile(fileName);
            //Writing on the file by the list.
            Formatter f = new Formatter(path + fileName);
            f.format("%s\n%s\n%s",lstInfo.get(0),lstInfo.get(1), lstInfo.get(2));
            //Closing the file.
            f.close();
        } catch (Exception e) {
            System.out.println("Unable to write on file\n");
            exit(-1);
        }


    }
}
