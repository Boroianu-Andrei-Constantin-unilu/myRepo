import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class MoveObject extends Application {
    @Override
    public void start(Stage primaryStage) {  //Our main method
        BallPane BallPane = new BallPane();

        HBox ButtonHolder = new HBox(110); // Create an Hbox named "ButtonHolder"
        Button LeftBtn = new Button("Left"); // Create a button that move the ball left
        Button RightBtn = new Button("Right"); // Create a button that move the ball Right
        Button UpBtn = new Button("Up"); // Create a button that move the ball Up
        Button DownBtn = new Button("Down"); // Create a button that move the ball Down
        ButtonHolder.getChildren().addAll(LeftBtn, RightBtn, UpBtn, DownBtn); // Contain all the button to the `ButtonHolder` Hbox

        // Create actions for the buttons
        LeftBtn.setOnAction(e -> BallPane.MoveLeft());
        RightBtn.setOnAction(e -> BallPane.MoveRight());
        UpBtn.setOnAction(e -> BallPane.MoveUp());
        DownBtn.setOnAction(e -> BallPane.MoveDown());

        BorderPane pane = new BorderPane();  // Create a BorderPane
        pane.setCenter(BallPane);  // Set the BallPane to pane
        pane.setBottom(ButtonHolder); // Set the ButtonHolder to pane
        pane.setPadding(new Insets(0, 0, 0, 0)); // Apply necessary paddings
        BorderPane.setAlignment(ButtonHolder, Pos.CENTER); // Align the ButtonHolder

        Scene scene = new Scene(pane, 500, 500); // Create a scene
        primaryStage.setTitle("Move a Ball"); // Provide an application title "Move a Ball"
        primaryStage.setScene(scene);  // Set the scene to stage
        primaryStage.show(); // Visualizing the stage.
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

class BallPane extends Pane { //  Create a Pane for pane
    private double radius = 20; // Define the ball redius
    private double width = 500; // Define the ball width
    private double height = 500; // Define the ball height
    Circle Ball = new Circle(width/2, height/2, radius); // Calcuate the circle

    BallPane() { // Set ball properties
        Ball.setFill(Color.GREEN);
        Ball.setStroke(Color.BLACK);
        getChildren().add(Ball);
    }

    public void MoveLeft() { // Method for moving the ball left
        if (Ball.getRadius() < Ball.getCenterX()) {
            Ball.setCenterX(Ball.getCenterX() - 10);
        }
    }

    public void MoveRight() { // Method for moving the ball Right
        if (Ball.getCenterX() < width - Ball.getRadius()) {
            Ball.setCenterX(Ball.getCenterX() + 10);
        }
    }

    public void MoveUp() { // Method for moving the ball Up
        if (Ball.getRadius() < Ball.getCenterY()) {
            Ball.setCenterY(Ball.getCenterY() - 10);
        }
    }

    public void MoveDown() { // Method for moving the ball Down
        if (Ball.getCenterY() < height - Ball.getRadius()) {
            Ball.setCenterY(Ball.getCenterY() + 10);
        }
    }
}