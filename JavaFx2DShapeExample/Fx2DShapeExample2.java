package JavaFx2DShapeExample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Fx2DShapeExample2 extends Application
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage stage)
	{
		// Create the Rectangles
		Rectangle rectangle1 = new Rectangle(100, 50, Color.LIGHTGRAY);
		Rectangle rectangle2 = new Rectangle(120, 20, 100, 50);
		rectangle2.setFill(Color.WHITE);
		rectangle2.setStroke(Color.BLACK);
		rectangle2.setArcWidth(10);
		rectangle2.setArcHeight(10);

		// Create the Pane
		Pane root = new Pane();
		// Add the Children to the Pane
		root.getChildren().addAll(rectangle1, rectangle2);

		// Create the Scene
		Scene scene = new Scene(root);
		// Add the Scene to the Stage
		stage.setScene(scene);
		// Set the Title of the Stage
		stage.setTitle("A JavaFX Rectangle Example");
		// Display the Stage
		stage.show();
	}
}
