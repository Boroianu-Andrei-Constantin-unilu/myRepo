package JavaFx2DShapeExample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;

public class Fx2DShapeExample8 extends Application
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage stage)
	{
		// Create the QuadCurves
		QuadCurve quadcurce1 = new QuadCurve(0, 100, 20, 0, 150, 100);
		quadcurce1.setFill(Color.TRANSPARENT);
		quadcurce1.setStroke(Color.BLACK);
		QuadCurve quadcurce2 = new QuadCurve(0, 100, 20, 0, 150, 100);
		quadcurce2.setFill(Color.LIGHTGRAY);

		// Create the HBox
		HBox root = new HBox();
		// Add the children to the HBox
		root.getChildren().addAll(quadcurce1, quadcurce2);
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
		stage.setTitle("A JavaFX QuadCurve Example");
		// Display the Stage
		stage.show();
	}
}
