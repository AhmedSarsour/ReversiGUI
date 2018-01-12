package sample;
//these 2 imporst are needed for the files.
import javafx.scene.paint.Color;

import java.io.*;
import java.util.*;
// this import is needed for "exit".
import static java.lang.System.exit;
//this class is made for reading the menuInformation text file.
public class SettingsReader {
    private Scanner scanner;
    private String firstPlayer;
    private String secondPlayer;
    private int boardSize;

    /**
     * SettingsReader.
     * The constuctor of our class - reading the file and setting the variables.
     * @param fileName the name of the settings file.
     */
    public SettingsReader(String fileName) {
        try {
            scanner = new Scanner(new File("src\\sample\\" + fileName));
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
            if (i == 0) {
                this.firstPlayer = scanner.next();
            } else if (i == 1) {
                this.secondPlayer = scanner.next();
            } else if (i == 2) {
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

    public void clearFile(String fileName) {
        try {
            PrintWriter p = new PrintWriter("src\\sample\\" + fileName);
            p.write("");
            p.close();
        }catch (Exception e) {
            System.out.println("unable to clear file\n");
            exit(-1);
        }
    }
    public void writeToFile(String fileName, List s) {
        try {
            clearFile(fileName);
            Formatter f = new Formatter("src\\sample\\" + fileName);
            f.format("%s\n%s\n%s",s.get(0),s.get(1), s.get(2));
            f.close();
        }catch (Exception e) {
            System.out.println("Unable to write on file\n");
            exit(-1);
        }


    }
}
