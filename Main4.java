import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;
import java.awt.*;

import static java.awt.Color.RED;

public class Main4 extends Application {

    private Button btn = new Button();
    private Pane canvas = new Pane();
    private Rectangle box = new Rectangle(5, 10, 20, 30);
    private Timeline timeline=new Timeline();
    //int a=0;
    private boolean isMoving = false;

    @Override
    public void start(Stage stage) {
        box.setFill(Color.rgb(255,0,0));
        box.relocate(100, 550);
        canvas.getChildren().addAll(box, btn);

        Scene scene = new Scene(canvas, 800, 600);


        stage.setTitle("Moving Rectangle");
        stage.setScene(scene);
        stage.show();

        Bounds bounds = canvas.getBoundsInLocal();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(10),
                        new KeyValue(box.layoutXProperty(), bounds.getMaxX() - box.getWidth())));
        timeline.setCycleCount(Animation.INDEFINITE);

        btn.setOnAction(event -> handle());
    }

    private void handle() {
        if(timeline.getStatus()== Animation.Status.RUNNING) {
            //a=0;
            //timeline.getKeyFrames().add(
            //        new KeyFrame(Duration.seconds(10), new KeyValue(box.layoutXProperty() , box.getX()))
            //);
            //timeline.setCycleCount(Animation.INDEFINITE);
            //timeline.play();
            //isMoving = false;
            timeline.stop();

        }
        else {
            //a=1;
            //isMoving = true;
            timeline.play();
        }
    }

    public static void main(String[] args) { launch(args); }
}