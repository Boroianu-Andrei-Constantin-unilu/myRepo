package Square;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application implements EventHandler<ActionEvent> {
    private static int cellId=-1;
    private static Cell[] cells = new Cell[100];
    private static BorderPane gamePane;
    private static Pane cellsPane;
    private static HBox buttonsPane;
    private static Pane statsPane;
    private static Pane setupsPane;
    private static HBox bottomPane;

    private static Square[][] field;

    Button createCellButton;
    Button deleteCellsButton;

    Label cellsCountLabel;
    Label setupsLabel;

    static int  gameWidth= 800;
    static int gameHeight=595;

    static int fieldSize = 20;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello World");

        gamePane = new BorderPane();

        buttonsPane = new HBox(5);
        statsPane = new Pane();
        cellsPane = makeGrid(fieldSize);
        setupsPane = new Pane();
        bottomPane = new HBox(5);

        cellsPane.setStyle("-fx-background-color:  #333333; -fx-border-color: black");
        buttonsPane.setStyle("-fx-background-color:  #333333; -fx-border-color: black");
        statsPane.setStyle("-fx-background-color:  #333333; -fx-border-color: black");
        setupsPane.setStyle("-fx-background-color:  #333333; -fx-border-color: black");
        bottomPane.setStyle("-fx-background-color:  #333333; -fx-border-color: black");

        cellsPane.setMaxWidth(400);
        statsPane.setMaxWidth((gameWidth-400)/2);
        setupsPane.setMaxWidth((gameWidth-400)/2);

        createCellButton = new Button();
        deleteCellsButton = new Button();

        createCellButton.setText("Create a cell");
        deleteCellsButton.setText("Delete cells");

        createCellButton.setOnAction(this);
        deleteCellsButton.setOnAction(this);

        buttonsPane.setSpacing(10);
        buttonsPane.setPadding(new Insets(10, 10, 10, 10));
        buttonsPane.getChildren().add(createCellButton);
        buttonsPane.getChildren().add(deleteCellsButton);
        // buttonsPane.setPrefHeight(100);

        gamePane.setTop(buttonsPane);
        gamePane.setCenter(cellsPane);
        gamePane.setBottom(bottomPane);

        final Rectangle r = new Rectangle(10, 10, 50, 25);


        cellsCountLabel = new Label("Cells Count: " + (cellId + 1));
        // cellsCountLabel.setStyle("-fx-background-color: #e6e6e6;");
        cellsCountLabel.setTextFill(Color.web("#e6e6e6"));


        statsPane.getChildren().add(cellsCountLabel);
        gamePane.setLeft(statsPane);

        setupsLabel = new Label("Setups Label");
        setupsLabel.setTextFill(Color.web("#e6e6e6"));

        setupsPane.getChildren().add(setupsLabel);
        gamePane.setRight(setupsPane);

        gamePane.setMargin(statsPane, new Insets(0, 0, 0, 0));
        gamePane.setMargin(cellsPane, new Insets(0,0,0,0));
        gamePane.setMargin(setupsPane, new Insets(0,0,0,0));

        statsPane.setPrefWidth((gameWidth-400)/2);
        setupsPane.setPrefWidth((gameWidth-400)/2);
        cellsCountLabel.setPrefWidth((gameWidth-400)/2);

        cellsCountLabel.setWrapText(true);


        Scene scene = new Scene(gamePane, gameWidth, gameHeight);
        primaryStage.setScene(scene);

        createCell(0,0);
        // moveCell(0,0, 0, 6);

        final long startNanoTime = System.nanoTime();

        new AnimationTimer()  {
            public void handle(long currentNanoTime)  {
                //double t_nano = (currentNanoTime - startNanoTime) / 1000_000_000_000L;

                int t = (int) Math.round((currentNanoTime - startNanoTime) / (double) 1000_000_000L);

                int x1 = t;
                int y1 = 0;

                int x2;
                if (t>0)  {
                    x2 = x1 + 1;
                }
                else  {
                    x2=x1+1;
                }

                //double y = 10;

                System.out.println("t: " + t +", x1: " + x1 + ", x2: "+x2);

                if (x2 < fieldSize) {
                    moveCell(x1, y1, x2, y1);
                }
                else  {
                    stop();
                }

            }
        }.start();


        primaryStage.show();


    }

    @Override
    public void handle(ActionEvent event)  {
        if (event.getSource()==createCellButton)  {
            createCell();
        }
        else if (event.getSource() == deleteCellsButton) {
            deleteCells();
            cellsCountLabel.setText("Cells Count: " + (cellId + 1));
            System.out.println("Cells deleted");
        }
        else  {
            System.out.println("Unknown button");
        }
    }

    private boolean moveCell(int x1, int y1, int x2, int y2)  {
        if (field[x1][y1].getStatus())  {
            if (!(field[x2][y2].getStatus()))  {
                field[x2][y2].setCell(field[x1][y1].getCell());
                field[x2][y2].setStatus(true);

                field[x1][y1].setCell(null);
                field[x1][y1].setStatus(false);

                System.out.println("Moved cell from (" + x1 + ", " + y1 + "to (" + x2 + ", )" + y2);
                return true;
            }
        }

        System.out.println("couldn't move a cell from (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + ")");
        return false;
    }

    private void createCell()  {
        cells[++cellId] = new Cell(cellId, fieldSize);

        Random rand = new Random();

        field[cells[cellId].getAddressX()][cells[cellId].getAddressY()].setStatus(true);
        field[cells[cellId].getAddressX()][cells[cellId].getAddressY()].setCell(cells[cellId]);
        cellsCountLabel.setText("Cells Count: " + (cellId + 1));
        System.out.println("Cell created: " + cells[cellId].getAge() + ", "
                + cells[cellId].getColor());
    }

    private void createCell(int x, int y)  {
        cells[++cellId] = new Cell(cellId, fieldSize);

        field[x][y].setStatus(true);
        field[x][y].setCell(cells[cellId]);

        cellsCountLabel.setText("Cells Count: " + (cellId + 1));
        System.out.println("Cell created: " + cells[cellId].getAge() + ", "
                + cells[cellId].getColor());
    }

    private void deleteCells()  {
        Cell cell;

        for (int i=0; i<field.length; i++) {
            for (Square square: field[i]) {
                square.setStatus(false);
                square.setCell(null);
            }
        }

        cellId = -1;
        cells = new Cell[100];
    }

    public static Pane makeGrid(int n) {
        double side = fieldSize;

        Pane p = new Pane();


        final Square[][] squares = new Square[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                squares[i][j] = new Square();
                squares[i][j].getModel().setX(i * side);
                squares[i][j].getModel().setY(j * side);
                squares[i][j].getModel().setWidth(side);
                squares[i][j].getModel().setHeight(side);

                p.getChildren().add(squares[i][j].getModel());


            }
        }

        field = squares;

        return p;
    }
}