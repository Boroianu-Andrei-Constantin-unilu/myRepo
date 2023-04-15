import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BezierCurveAnimation extends Application {

    private static final int WINDOW_WIDTH = 595;
    private static final int WINDOW_HEIGHT = 400;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // create a rectangle to animate
        Rectangle rect = new Rectangle(20, 20, 50, 50);
        rect.setFill(Color.BLUE);

        // create a group to hold the rectangle and the bezier curve
        Group root = new Group(rect);

        // create a bezier curve path that ends at the frame border
        Path path1 = new Path();
        path1.getElements().add(new MoveTo(50, 100));
        path1.getElements().add(new CubicCurveTo(200, 10, 400, 10, 550, 100));

        // create a path transition for the rectangle along the first bezier curve
        PathTransition pathTransition1 = new PathTransition();
        pathTransition1.setDuration(Duration.seconds(4));
        pathTransition1.setPath(path1);
        pathTransition1.setNode(rect);

        // create a bezier curve path in the opposite direction that also ends at the frame border
        Path path2 = new Path();
        path2.getElements().add(new MoveTo(550, 300));
        path2.getElements().add(new CubicCurveTo(400, 390, 200, 390, 50, 300));

        // create a path transition for the rectangle along the second bezier curve
        PathTransition pathTransition2 = new PathTransition();
        pathTransition2.setDuration(Duration.seconds(4));
        pathTransition2.setPath(path2);
        pathTransition2.setNode(rect);

        // set up event handler for when the first path transition ends
        pathTransition1.setOnFinished(event -> {
            // reverse direction of second path transition
            pathTransition2.setRate(-1);
            // start second path transition
            pathTransition2.play();
        });

        // add the group to a scene
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        // show the stage
        primaryStage.setTitle("Bezier Curve Animation");
        primaryStage.setScene(scene);
        primaryStage.show();

        // start the first path transition
        pathTransition1.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
