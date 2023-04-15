import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BezierCurveExample3 extends Application {

    private boolean isGoingRight = true;

    @Override
    public void start(Stage stage) {
        // Create a rectangle
        Rectangle rect = new Rectangle(50, 50, 50, 50);
        rect.setFill(Color.BLUE);

        // Create two Bezier curves
        CubicCurve curve1 = new CubicCurve(50, 50, 100, 200, 200, 200, 250, 50);
        curve1.setFill(null);
        curve1.setStroke(Color.BLACK);

        CubicCurve curve2 = new CubicCurve(250, 50, 300, 200, 400, 200, 450, 50);
        curve2.setFill(null);
        curve2.setStroke(Color.RED);

        // Create a path transition for the first curve
        PathTransition pathTransition1 = new PathTransition();
        pathTransition1.setDuration(Duration.seconds(5));
        pathTransition1.setPath(curve1);
        pathTransition1.setNode(rect);
        pathTransition1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition1.setCycleCount(1);

        // Create a path transition for the second curve
        PathTransition pathTransition2 = new PathTransition();
        pathTransition2.setDuration(Duration.seconds(5));
        pathTransition2.setPath(curve2);
        pathTransition2.setNode(rect);
        pathTransition2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition2.setCycleCount(1);

        // Set up the scene
        Group root = new Group();
        root.getChildren().addAll(curve1, curve2, rect);
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.show();

        // Start the first path transition
        pathTransition1.play();

        // When the rectangle hits the border of the scene, switch to the second path transition
        pathTransition1.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (isGoingRight && (rect.getBoundsInParent().getMaxX() >= scene.getWidth() || rect.getBoundsInParent().getMaxY() >= scene.getHeight())) {
                pathTransition1.stop();
                pathTransition2.play();
                isGoingRight = false;
            } else if (!isGoingRight && (rect.getBoundsInParent().getMinX() <= 0 || rect.getBoundsInParent().getMinY() <= 0)) {
                pathTransition1.play();
                pathTransition2.stop();
                isGoingRight = true;
            }
        });

        // When the second path transition finishes, loop back to the first one
        pathTransition2.setOnFinished(event -> {
            pathTransition1.play();
            pathTransition2.stop();
            isGoingRight = true;
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
