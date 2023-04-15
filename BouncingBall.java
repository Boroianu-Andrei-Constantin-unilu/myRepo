import java.io.Closeable;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BouncingBall extends Application{
	
    @Override
    public void start(Stage stage) {
    	
        Random random = new Random();
        Group group = new Group();
    	Pane canvas = new Pane();
        Path path = new Path();
    	Scene scene = new Scene(canvas, 300, 300, Color.GREEN);
    	Rectangle object = new Rectangle(0, 0, 20, 20);
        Circle circle = new Circle(random.nextInt(300), random.nextInt(300), 5, Color.YELLOW);
        Rectangle rect2 = new Rectangle(0, 0, 20, 20);
        QuadCurve curve = new QuadCurve(0, 0, random.nextInt(300), random.nextInt(300), 0, random.nextInt(300));
        QuadCurveTo curveTo = new QuadCurveTo(random.nextInt(300), random.nextInt(300), 0, random.nextInt(300));
        
        canvas.getChildren().add(object);
        canvas.getChildren().add(circle);
        canvas.getChildren().add(curve);
        
        stage.setTitle("Animated Ball");
        stage.setScene(scene);
        stage.show();

        path.getElements().add(new MoveTo(0, 0));
        path.getElements().add(curveTo);

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(50));
        pathTransition.setPath(curve);
        pathTransition.setNode(rect2);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);

        group.getChildren().add(curve);
        group.getChildren().add(rect2);
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {

        	double dx = 5; //Step on x or velocity
        	double dy = 3; //Step on y
        	
            @Override
            public void handle(ActionEvent t) {
            	//move the ball

            	object.setLayoutX(object.getLayoutX() + dx);
            	object.setLayoutY(object.getLayoutY() + dy);
                circle.setCenterX(5);
                circle.setCenterY(5);

                Bounds bounds = canvas.getBoundsInLocal();
                Bounds bounds2 = object.getBoundsInLocal();

                pathTransition.play();
                
                //If the ball reaches the left or right border make the step negative
                if(object.getLayoutX() <= (bounds.getMinX() + bounds2.getMinX()) || 
                        object.getLayoutX() >= (bounds.getMaxX() - bounds2.getMaxX()) ){

                	dx = -dx;
                    circle.relocate(random.nextInt(275), random.nextInt(275));
                    curve.setStartX(0);
                    curve.setStartY(random.nextInt(300));
                    curve.setControlX(random.nextInt(300));
                    curve.setControlY(random.nextInt(300));
                    curve.setEndX(random.nextInt(300));
                    curve.setEndY(0);
                }

                //If the ball reaches the bottom or top border make the step negative
                if((object.getLayoutY() >= (bounds.getMaxY() - bounds2.getMaxY())) || 
                        (object.getLayoutY() <= (bounds.getMinY() + bounds2.getMinY()))){

                	dy = -dy;
                    circle.relocate(random.nextInt(275), random.nextInt(275));
                    curve.setStartX(random.nextInt(300));
                    curve.setStartY(0);
                    curve.setControlX(random.nextInt(300));
                    curve.setControlY(random.nextInt(300));
                    curve.setEndX(300);
                    curve.setEndY(random.nextInt(300));

                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
    public static void main(String[] args) {
        launch();
    }
}