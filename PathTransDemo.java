import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PathTransDemo extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Rectangle invisibleBorder = new Rectangle(600, 600);
        invisibleBorder.setFill(Color.TRANSPARENT);
        invisibleBorder.setLayoutX(-100);
        invisibleBorder.setLayoutY(-100);

        Rectangle rect = new Rectangle (0, 0, 100, 100);

        Path path = new Path();
        path.getElements().add(new MoveTo(0, 0));
        path.getElements().add(new LineTo(400, 0));
        path.getElements().add(new LineTo(400, 400));
        path.getElements().add(new LineTo(0, 400));
        path.getElements().add(new LineTo(0, 0));

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(5000));
        pathTransition.setNode(rect);
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setAutoReverse(true);

        Button play = new Button("Play");
        play.setLayoutX(200);
        play.setLayoutY(200);
        play.setOnMouseClicked(event -> pathTransition.play());

        Group container = new Group(invisibleBorder, rect, path, play);

        StackPane root = new StackPane(container);
        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}