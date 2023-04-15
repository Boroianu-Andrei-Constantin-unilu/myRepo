import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BezierCurveExample2 extends Application {

    @Override
    public void start(Stage stage) {
        // Create a rectangle
        Rectangle rect = new Rectangle(50, 50, 50, 50);
        rect.setFill(Color.BLUE);

        // Create the first Bezier curve
        CubicCurve curve1 = new CubicCurve(50, 50, 100, 200, 200, 200, 250, 50);
        curve1.setFill(null);
        curve1.setStroke(Color.BLACK);

        // Create the second Bezier curve
        CubicCurve curve2 = new CubicCurve(250, 50, 300, 200, 400, 200, 450, 50);
        curve2.setFill(null);
        curve2.setStroke(Color.BLACK);

        // Create the path transition for the first curve
        PathTransition pathTransition1 = new PathTransition();
        pathTransition1.setDuration(Duration.seconds(5));
        pathTransition1.setPath(curve1);
        pathTransition1.setNode(rect);
        pathTransition1.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition1.setCycleCount(1);

        // Create the path transition for the second curve
        PathTransition pathTransition2 = new PathTransition();
        pathTransition2.setDuration(Duration.seconds(5));
        pathTransition2.setPath(curve2);
        pathTransition2.setNode(rect);
        pathTransition2.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition2.setCycleCount(1);

        // Set up the scene
        Group root = new Group();
        root.getChildren().addAll(curve1, curve2, rect);
        Scene scene = new Scene(root, 500, 250);
        stage.setScene(scene);
        stage.show();

        // Start the first path transition
        pathTransition1.play();

        // Stop the rectangle when it hits a frame border and start the second path transition
        pathTransition1.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (rect.getBoundsInParent().getMaxX() >= scene.getWidth()) {
                pathTransition1.stop();
                pathTransition2.play();
            }
        });

        // Stop the rectangle when it reaches the end of the second curve
        pathTransition2.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(Duration.seconds(5))) {
                pathTransition2.stop();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
