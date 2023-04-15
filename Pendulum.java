import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Pendulum extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        // For the ball
        Arc pathArc = new Arc(150, 30, 150, 150, -30, -120);
        Circle circle = new Circle(0, 0, 10); 

        // For the hand
        Arc pathArc2 = new Arc(150, 30, 75, 75, -30, -120);
        Rectangle string = new Rectangle(0, 0, 2, 150);

        pane.getChildren().add(circle);
        pane.getChildren().add(string);

        // For the ball's animation
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(2000));
        pt.setPath(pathArc);
        pt.setNode(circle);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setAutoReverse(true);
        pt.play();

        // For the hand's animation
        PathTransition pt2 = new PathTransition(); // for the string connecting the pendulum ball
        pt2.setDuration(Duration.millis(2000));
        pt2.setPath(pathArc2);
        pt2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt2.setNode(string);
        pt2.setCycleCount(Timeline.INDEFINITE);
        pt2.setAutoReverse(true);
        pt2.play();

        Scene scene = new Scene(pane, 300, 220);
        primaryStage.setTitle("Pendulum");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}