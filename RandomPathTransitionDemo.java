import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class RandomPathTransitionDemo extends Application {
    PathTransition pathTransition;
    Path path;

    SecureRandom random = new SecureRandom();

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 595, 595);
        stage.setScene(scene);
        stage.setTitle("Random Path Transistion");
        stage.show();

        Pane pane = new Pane();
        pane.setPadding(new Insets(10));
        pane.setStyle("-fx-border-width:1px;-fx-border-color:black;-fx-background-color:white;");
        VBox.setVgrow(pane, Priority.ALWAYS);

        Button generateBtn = new Button("Generate circles");
        Button animationBtn = new Button("Start Animation");
        animationBtn.setDisable(true);

        HBox buttons = new HBox(generateBtn, animationBtn);
        buttons.setSpacing(15);
        root.getChildren().addAll(buttons, new Label("Click generate button as many times as you want !!"),pane);

        final Rectangle rectPath = new Rectangle(0, 0, 20, 20);
        rectPath.setArcHeight(10);
        rectPath.setArcWidth(10);
        rectPath.setFill(Color.ORANGE);

        path = new Path();
        path.setStroke(Color.LIGHTGREEN);
        path.setStrokeWidth(2);

        generateBtn.setOnAction(e -> {
            animationBtn.setDisable(false);
            if (pathTransition != null) {
                pathTransition.stop();
            }
            pane.getChildren().clear();
            path.getElements().clear();

            int width = (int) pane.getWidth() - 20;
            int height = (int) pane.getHeight() - 20;
            List<Circle> dots = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                double x = random.nextInt(width); // Get a random value of x within the pane width
                double y = random.nextInt(height);// Get a random value of y within the pane height

                // If required include your logic to see if this point is not within the range of other points.

                // Create a circle with this random point
                Circle dot = new Circle(x, y, 5, Color.RED);
                dots.add(dot);

                // Also inlcude a path element for this random point.
                path.getElements().add(i == 0 ? new MoveTo(x, y) : new LineTo(x, y));
            }

            // Add all nodes in the pane one after another to have a nice visual.
            pane.getChildren().add(path);
            pane.getChildren().addAll(dots);
            pane.getChildren().add(rectPath);

            // Move the rectangle to the start point of the path.
            rectPath.setTranslateX(dots.get(0).getCenterX() - 10); // 10 :: half of rectangle width
            rectPath.setTranslateY(dots.get(0).getCenterY() - 10); // 10 :: half of rectangle height

        });

        animationBtn.setOnAction(e -> {
            pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.millis(4000));
            pathTransition.setPath(path);
            pathTransition.setNode(rectPath);
            pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition.setAutoReverse(false);
            pathTransition.play();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}