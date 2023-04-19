import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;
public class AnimationPath extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Random random = new Random();
    Group group = new Group();
    Rectangle object = new Rectangle(10, 10, 10, 10);
    Circle circle = new Circle(random.nextInt(595), random.nextInt(595), 5);
    QuadCurve curve1 = new QuadCurve(10,10, random.nextInt(595), random.nextInt(595), 0, random.nextInt(595));
    QuadCurve curve2 = new QuadCurve(curve1.getEndX(), curve1.getEndY(), random.nextInt(595), random.nextInt(595), random.nextInt(595), 595);
    QuadCurve curve3 = new QuadCurve(curve2.getEndX(), curve2.getEndY(), random.nextInt(595), random.nextInt(595), 595, random.nextInt(595));
    QuadCurve curve4 = new QuadCurve(curve3.getEndX(), curve3.getEndY(), random.nextInt(595), random.nextInt(595), random.nextInt(595), 0);
    QuadCurve curve5 = new QuadCurve(curve4.getEndX(), curve4.getEndY(), random.nextInt(595), random.nextInt(595), 0, random.nextInt(595));
    QuadCurve curve6 = new QuadCurve(curve1.getEndX(), curve1.getEndY(), random.nextInt(595), random.nextInt(595), random.nextInt(595), 595);
    object.setFill(Color.DARKRED);
    group.getChildren().addAll(circle, object);

    Scene scene = new Scene(group, 600, 600, Color.GREEN);
    stage.setScene(scene);
    stage.setTitle("JavaFX 2 Animations");
    stage.show();

    object.getLayoutBounds();

    PathTransition pathTransition1 = new PathTransition();
    pathTransition1.setDuration(Duration.seconds(2));
    pathTransition1.setPath(curve1);
    pathTransition1.setNode(object);
    pathTransition1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
    pathTransition1.setCycleCount(1);
    pathTransition1.play();

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
                
      if (object.getLayoutX() > scene.getWidth() || object.getLayoutY() > scene.getHeight() ||
          object.getLayoutX() < scene.getWidth() || object.getLayoutY() < scene.getHeight()) {
                
          circle.relocate(random.nextInt(595), random.nextInt(595));
          object.setFill(Color.color (Math.random(), Math.random(), Math.random()));

          PathTransition pathTransition2 = new PathTransition();
          pathTransition2.setDuration(Duration.seconds(2));
          pathTransition2.setPath(curve2);
          pathTransition2.setNode(object);
          pathTransition2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
          pathTransition2.setCycleCount(1);
          pathTransition2.play();

          Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event2) {

            if (object.getLayoutX() > scene.getWidth() || object.getLayoutY() > scene.getHeight() ||
                object.getLayoutX() < scene.getWidth() || object.getLayoutY() < scene.getHeight()) {
                
                circle.relocate(random.nextInt(595), random.nextInt(595));
                object.setFill(Color.color (Math.random(), Math.random(), Math.random()));

                PathTransition pathTransition3 = new PathTransition();
                pathTransition3.setDuration(Duration.seconds(2));
                pathTransition3.setPath(curve3);
                pathTransition3.setNode(object);
                pathTransition3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition3.setCycleCount(1);
                pathTransition3.play();

                Timeline timeline3 = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {

                  @Override
                  public void handle(ActionEvent event3) {

                  if (object.getLayoutX() > scene.getWidth() || object.getLayoutY() > scene.getHeight() ||
                      object.getLayoutX() < scene.getWidth() || object.getLayoutY() < scene.getHeight()) {

                  circle.relocate(random.nextInt(595), random.nextInt(595));
                  object.setFill(Color.color (Math.random(), Math.random(), Math.random()));

                  PathTransition pathTransition4 = new PathTransition();
                  pathTransition4.setDuration(Duration.seconds(2));
                  pathTransition4.setPath(curve4);
                  pathTransition4.setNode(object);
                  pathTransition4.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                  pathTransition4.setCycleCount(1);
                  pathTransition4.play();

                  Timeline timeline4 = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event4) {

                    if (object.getLayoutX() > scene.getWidth() || object.getLayoutY() > scene.getHeight() ||
                        object.getLayoutX() < scene.getWidth() || object.getLayoutY() < scene.getHeight()) {

                    circle.relocate(random.nextInt(595), random.nextInt(595));
                    object.setFill(Color.color (Math.random(), Math.random(), Math.random()));

                    PathTransition pathTransition5 = new PathTransition();
                    pathTransition5.setDuration(Duration.seconds(2));
                    pathTransition5.setPath(curve5);
                    pathTransition5.setNode(object);
                    pathTransition5.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                    pathTransition5.setCycleCount(1);
                    pathTransition5.play();

                    Timeline timeline5 = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {

                      @Override
                      public void handle(ActionEvent event5) {

                      if (object.getLayoutX() > scene.getWidth() || object.getLayoutY() > scene.getHeight() ||
                          object.getLayoutX() < scene.getWidth() || object.getLayoutY() < scene.getHeight()) {

                      circle.relocate(random.nextInt(595), random.nextInt(595));
                      object.setFill(Color.color (Math.random(), Math.random(), Math.random()));
                
                      PathTransition pathTransition6 = new PathTransition();
                      pathTransition6.setDuration(Duration.seconds(2));
                      pathTransition6.setPath(curve6);
                      pathTransition6.setNode(object);
                      pathTransition6.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                      pathTransition6.setCycleCount(1);
                      }
                    throw new UnsupportedOperationException("Unimplemented method 'handle'");
                  }
                }));
              timeline5.setCycleCount(1);
              }
              throw new UnsupportedOperationException("Unimplemented method 'handle'");
              }
            }));
            timeline4.setCycleCount(1);
            timeline4.play();
            }
            throw new UnsupportedOperationException("Unimplemented method 'handle'");
            }
        }));
        timeline3.setCycleCount(1);
        timeline3.play();
        }
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
        }
        }));
        timeline2.setCycleCount(1);
        timeline2.play();
        }
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
        }
        }));
        timeline.setCycleCount(1);
        timeline.play();
        }

  public static void main( String[] arguments) {
    Application.launch(arguments);
  }
}