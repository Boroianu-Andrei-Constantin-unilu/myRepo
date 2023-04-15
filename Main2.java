import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main2 extends Application{
    Path onePath = new Path();
    Point2D anchorPt;/*  w w w . j ava2  s  . c  om*/
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Working with the Scene Graph");
        
        final Group root = new Group();  
        root.getChildren().add(onePath);
        
        final Scene scene = new Scene(root, 300, 250);
        scene.setFill(Color.WHITE);
        
        final Circle sphere = new Circle();
        sphere.setCenterX(100);
        sphere.setCenterY(100);
        sphere.setRadius(20);
        sphere.setFill(Color.BLACK);

        root.getChildren().add(sphere);
       
        final PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setCycleCount(1);
        pathTransition.setNode(sphere);
        pathTransition.setPath(onePath);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        pathTransition.onFinishedProperty().set((EventHandler<ActionEvent>) (ActionEvent event) -> {
            onePath.getElements().clear();
        });

        scene.onMousePressedProperty().set((EventHandler<MouseEvent>) (MouseEvent event) -> {
            onePath.getElements().clear();
            anchorPt = new Point2D(event.getX(), event.getY());
            onePath.setStrokeWidth(3);
            onePath.setStroke(Color.BLACK);
            onePath.getElements().add(new MoveTo(anchorPt.getX(), anchorPt.getY()));
        });
        
        scene.onMouseDraggedProperty().set((EventHandler<MouseEvent>) (MouseEvent event) -> {
            onePath.getElements().add(new LineTo(event.getX(), event.getY()));
        });
        
        scene.onMouseReleasedProperty().set((EventHandler<MouseEvent>) (MouseEvent event) -> {
            onePath.setStrokeWidth(0);
            if (onePath.getElements().size() > 1) {
                pathTransition.stop();
                pathTransition.playFromStart();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}