package sample;

import Reversi.Board;
import Reversi.Point;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

/**
 * ReversiBoard.
 * This is a GridPane contains our board. We will use our board class and merge it with gui.
 */
public class ReversiBoard extends GridPane {
    //Our reversi controller in order to use it on GuiBoard.
    private ReversiBoard reversiController = this;
    //The board contains reversi.
    private Board board;
    //Member of point we clicked on the board.
    private Point clicked;

    /**
     * GuiBoard.
     * A board that can be shown on gui.
     */
    private class GuiBoard extends Board {
        /**
         * The constructor of our class.
         * The constructor is private because inner class.
         * @param rows the number of rows
         * @param cols the number of cols
         */
        private GuiBoard(int rows, int cols) {
            super(rows,cols);
        }
        /**
         * show.
         * Showing the board now on gui.
         */
        public void show(){
            //Clearing the gui.
            reversiController.getChildren().clear();
            //Height and weight by our gui size.
            int height = (int)reversiController.getPrefHeight();
            int width = (int)reversiController.getPrefWidth();
            //Match height and weight to the current state of the screen.
            int cellHeight = height / this.rows;
            int cellWidth = width / this.cols;
            //Reading from file in order to get the color.
            SettingsReader settingsReader = new SettingsReader("menuInfo.txt");
            //The colors of the players
            Color firstColor = settingsReader.getFirstPlayerColor();
            Color secondColor = settingsReader.getSecondPlayerColor();
            //Closing the file we just opened
            settingsReader.closeTheFile();
            //Moving on the board items.
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    //Creating rectangle for each cell on the board.
                    Rectangle rectangle  = new Rectangle(cellWidth, cellHeight,
                            Color.web("#B93A32"));
                    //Adding the rectangee to the controller.
                    reversiController.add(rectangle, i, j);
                    //First player
                    if (this.get(i, j) == Board.firstPlayerChar()) {
                        //Creating circle with the first player color.
                        Circle circle = new Circle(cellWidth, cellHeight, 0.5 * cellHeight, firstColor);
                        GridPane.setConstraints(circle, i, j);
                        reversiController.getChildren().add(circle);
                    }
                    //Second player
                    if (this.get(i, j) == Board.secondPlayerChar()) {
                        //Creating circle with the second player color.
                        Circle circle = new Circle(cellWidth, cellHeight, 0.5 * cellHeight, secondColor);
                        GridPane.setConstraints(circle, i, j);
                        reversiController.getChildren().add(circle);
                    }
                    rectangle.setStroke(Color.BLACK);
                    //We meed to create final integers to pass the value in to lambda exmpression
                    final int rowIndex = i;
                    final int colIndex = j;
                    //When we are clicking on the rectangle it should recognize
                    rectangle.setOnMouseClicked(e -> {
                        clicked = new Point(rowIndex, colIndex);
                    });
                }
            }
        }
    }

    /**
     * ReversiBoard.
     * The constructor of our class.
     * @param rows the rows size of the board.
     * @param cols the columns size of the board.
     */
    public ReversiBoard(int rows, int cols) {
        this.clicked = new Point(-1, -1);
        this.board = new GuiBoard(rows, cols);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiBoard.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            //Loading the board
            fxmlLoader.load();
        }
        catch (IOException exception){
            throw new RuntimeException(exception);
        }
    }

    /**
     * getClicked.
     * @return the point clicked.
     */
    public Point getClicked() {
        return this.clicked;
    }

    /**
     * resetClicked.
     * Reset the clicked point to -1,-1 in order to do not touch the board when clicking forbidden area.
     */
    public void resetClicked() {
        this.clicked = new Point(-1, -1);
    }

    /**
     * getBoard.
     * @return our game board.
     */
    public Board getBoard() {
        return this.board;
    }
    /**
     * draw()
     * Drawing our reversi board.
     */
    public void draw() {
        this.board.show();
    }
}
