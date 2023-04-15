import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BezierCurveExample extends Application {

    @Override
    public void start(Stage stage) {
        // Create a rectangle
        Rectangle rect = new Rectangle(50, 50, 50, 50);
        rect.setFill(Color.BLUE);

        // Create a Bezier curve
        CubicCurve curve = new CubicCurve(50, 50, 100, 200, 200, 200, 250, 50);
        curve.setFill(null);
        curve.setStroke(Color.BLACK);

        // Create a path transition
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(5));
        pathTransition.setPath(curve);
        pathTransition.setNode(rect);
        pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);

        // Set up the scene
        Group root = new Group();
        root.getChildren().addAll(curve, rect);
        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
        stage.show();

        // Start the path transition
        pathTransition.play();

        // Stop the rectangle when it hits a frame border
        pathTransition.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (rect.getBoundsInParent().getMaxX() >= scene.getWidth() ||
                    rect.getBoundsInParent().getMaxY() >= scene.getHeight()) {
                pathTransition.stop();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
