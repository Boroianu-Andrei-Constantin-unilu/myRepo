package JavaFx2DShapeExample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Fx2DShapeExample3 extends Application
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage stage)
	{
		// Create the Circles
		Circle circle1 = new Circle(0, 0, 40);
		circle1.setFill(Color.LIGHTGRAY);
		Circle circle2 = new Circle(10, 10, 40, Color.YELLOW);
		circle2.setStroke(Color.BLACK);
		circle2.setStrokeWidth(2.0);

		// Create the HBox
		HBox root = new HBox();
		// Add the children to the HBox
		root.getChildren().addAll(circle1, circle2);

		// Set Spacing of the HBox
		root.setSpacing(10);
		// Set Style for the HBox
		root.setStyle
		(
			"-fx-padding: 10;" +
			"-fx-border-style: solid inside;" +
			"-fx-border-width: 2;" +
			"-fx-border-insets: 5;" +
			"-fx-border-radius: 5;" +
			"-fx-border-color: blue;"
		);

		// Create the Scene
		Scene scene = new Scene(root);
		// Add the Scene to the Stage
		stage.setScene(scene);
		// Set the Title of the Stage
		stage.setTitle("A JavaFX Circle Example");
		// Display the Stage
		stage.show();
	}
}
