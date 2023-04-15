package JavaFx2DShapeExample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class Fx2DShapeExample4 extends Application
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage stage)
	{
		// Create the ellipses
		Ellipse ellipse1 = new Ellipse(50, 30);
		ellipse1.setFill(Color.LIGHTGRAY);
		Ellipse ellipse2 = new Ellipse(60, 30);
		ellipse2.setFill(Color.YELLOW);
		ellipse2.setStroke(Color.BLACK);
		ellipse2.setStrokeWidth(2.0);
		Ellipse ellipse3 = new Ellipse(30, 30);
		ellipse3.setFill(Color.YELLOW);
		ellipse3.setStroke(Color.BLACK);
		ellipse3.setStrokeWidth(2.0);

		// Create the HBox
		HBox root = new HBox();
		// Add the children to the HBox
		root.getChildren().addAll(ellipse1, ellipse2, ellipse3);
		// Set Spacing of the HBox
		root.setSpacing(10);

		// Set the Style of the HBox
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
		stage.setTitle("A JavaFX Ellipses Example");
		// Display the Stage
		stage.show();
	}
}
