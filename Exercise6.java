import javafx.animation.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import javafx.util.*;

public class Exercise6 extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(10, 20);
        
        Group group = new Group(circle, rectangle);
        
        PathTransition transition = new PathTransition();
        transition.setNode(group);
        transition.setDuration(Duration.seconds(3));
        transition.setPath(new Line(0, 0, 500, 500));
        transition.play();
        
        Scene scene = new Scene(group, 800,800);
        stage.setScene(scene);
        stage.show();
    }
}
