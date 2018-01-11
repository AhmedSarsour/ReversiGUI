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
public class ReversiBoard extends GridPane {
    //Our reversi controller in order to use it on GuiBoard.
    private ReversiBoard reversiController = this;
    //The board contains reversi.
    private Board board;
    //Member of point we clicked on the board.
    private Point clicked;
    //GuiBoard
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
            int cellHeight = height / this.rows;
            int cellWidth = width / this.cols;
            //Moving on the board items.
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    Rectangle rectangle;
                    rectangle = new Rectangle(cellWidth, cellHeight,
                            Color.web("#B93A32"));
                    reversiController.add(rectangle, i, j);
                    if (this.get(i, j) == Board.firstPlayerChar()) {
                        Circle circle = new Circle(cellWidth, cellHeight, 0.5 * cellHeight, Color.BLACK);
                        GridPane.setConstraints(circle, i, j);
                        reversiController.getChildren().add(circle);
                    }

                    if (this.get(i, j) == Board.secondPlayerChar()) {
                        Circle circle = new Circle(cellWidth, cellHeight, 0.5 * cellHeight, Color.WHITE);
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
