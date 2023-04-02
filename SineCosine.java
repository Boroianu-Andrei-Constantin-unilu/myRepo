import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SineCosine extends Application 
{

@Override
public void start(Stage primaryStage) {
    Pane p = new Pane();

    CubicCurve cubic = new CubicCurve();
    cubic.setStartX(0.0f);
    cubic.setStartY(100.0f);
    cubic.setControlX1(50.0f);
    cubic.setControlY1(0.0f);
    cubic.setControlX2(150.0f);
    cubic.setControlY2(200.0f);
    cubic.setEndX(200.0f);
    cubic.setEndY(100.0f);

    Circle orbitingDot = new Circle(100, 50, 5);
    orbitingDot.setStyle("-fx-stroke: red; -fx-fill: red");

   // Add nodes to the pane
   p.getChildren().addAll(cubic, orbitingDot);

   // Create the path transition
   PathTransition pt = new PathTransition(Duration.millis(4000), cubic, orbitingDot);
   pt.setInterpolator(Interpolator.LINEAR);
   pt.setOrientation(PathTransition.OrientationType.NONE);
   pt.setCycleCount(Timeline.INDEFINITE);
   pt.setAutoReverse(false);
   pt.play();

   p.setOnMousePressed(e -> pt.pause());
   p.setOnMouseReleased(e -> pt.play());

   primaryStage.setTitle(" ");
   primaryStage.setScene(new Scene(p, 600, 175));
   primaryStage.show();
}
public static void main(String[] args) 
{
   launch(args);
}
}