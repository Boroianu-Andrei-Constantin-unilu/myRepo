import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class AnimationUI extends Application {  


    @Override  
    public void start(Stage stage) throws Exception 
    { 
    
    Rectangle newrect = new Rectangle(120,120,100,100);     
    newrect.setStroke(Color.BLUE);  
    newrect.setFill(Color.RED);  
    newrect.setStrokeWidth(20);  
          
    Path newpath = new Path();  
    newpath.getElements().add (new MoveTo (120f, 20f));  
    newpath.getElements().add (new CubicCurveTo (200f, 200f, 450f, 320f, 560f, 40f));  
          
        PathTransition pathTransition = new PathTransition();  
         
        pathTransition.setDuration(Duration.millis(1000));  
          
        pathTransition.setNode(newrect);  
          
        pathTransition.setPath(newpath);  
          
        pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);  
          
        pathTransition.setCycleCount(10);  
          
        pathTransition.setAutoReverse(true);  
      
        pathTransition.play();  
          
     
        Group root = new Group();  
        root.getChildren().addAll(newrect);  
         Scene scene = new Scene(root,700,450);  
         stage.setScene(scene);  
        stage.setTitle("JavaFx Path Transition example");  
         stage.show();  
     }
      
    public static void main(String[] args) {  
        launch(args);  
    }  
}