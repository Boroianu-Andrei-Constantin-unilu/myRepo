import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.shape.LineTo; 
import javafx.scene.shape.MoveTo; 
import javafx.scene.shape.Path; 
import javafx.stage.Stage;  

public class LineToExample extends Application { 
   @Override 
   public void start(Stage stage) {
      //Creating a Path object 
      Path path = new Path();  
      
      //Moving to the starting point 
      MoveTo moveTo = new MoveTo(); 
      moveTo.setX(500.0f); 
      moveTo.setY(150.0f); 
         
      //Instantiating the LineTo class  
      LineTo lineTo = new LineTo(); 
         
      //Setting the Properties of the line element 
      lineTo.setX(100.0f); 
      lineTo.setY(150.0f);      
       
      //Adding the path elements to Observable list of the Path class   
      path.getElements().add(moveTo); 
      path.getElements().add(lineTo); 
         
      //Creating a Group object  
      Group root = new Group(path); 
         
      //Creating a scene object 
      Scene scene = new Scene(root, 595, 300);  
      
      //Setting title to the Stage 
      stage.setTitle("Drawing a Line"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   }
   public static void main(String args[]){ 
      launch(args); 
   } 
} 