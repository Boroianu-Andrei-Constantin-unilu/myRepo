import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BezierCurveAnimation2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a rectangle
        Rectangle rect = new Rectangle(20, 20, 50, 50);
        rect.setFill(Color.RED);

        // Define the first Bezier curve
        double startX = 20;
        double startY = 20;
        double endX = 280;
        double endY = 300;
        double controlX1 = 100;
        double controlY1 = 0;
        double controlX2 = 180;
        double controlY2 = 320;

        // Create the path for the first Bezier curve
        Path path = new Path();
        path.getElements().add(new MoveTo(startX, startY));
        path.getElements().add(new CubicCurveTo(controlX1, controlY1, controlX2, controlY2, endX, endY));

        // Create a PathTransition for the first Bezier curve
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(5));
        pathTransition.setNode(rect);
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);

        // Define the second Bezier curve
        double startX2 = endX;
        double startY2 = endY;
        double endX2 = startX;
        double endY2 = startY;
        double controlX12 = controlX2;
        double controlY12 = controlY2;
        double controlX22 = controlX1;
        double controlY22 = controlY1;

        // Create the path for the second Bezier curve
        Path path2 = new Path();
        path2.getElements().add(new MoveTo(startX2, startY2));
        path2.getElements().add(new CubicCurveTo(controlX12, controlY12, controlX22, controlY22, endX2, endY2));

        // Create a PathTransition for the second Bezier curve
        PathTransition pathTransition2 = new PathTransition();
        pathTransition2.setDuration(Duration.seconds(5));
        pathTransition2.setNode(rect);
        pathTransition2.setPath(path2);
        pathTransition2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition2.setCycleCount(1);

        // Set the animation for the rectangle
        pathTransition.setOnFinished(event -> pathTransition2.play());
        pathTransition2.setOnFinished(event -> pathTransition.play());

        // Create a group and add the rectangle to it
        Group root = new Group();
        root.getChildren().add(rect);

        // Create a scene and add the group to it
        Scene scene = new Scene(root, 300, 400);

        // Set the scene to the stage and show it
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start the animation
        pathTransition.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
