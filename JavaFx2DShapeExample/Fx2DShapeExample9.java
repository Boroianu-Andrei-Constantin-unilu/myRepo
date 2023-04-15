package JavaFx2DShapeExample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.stage.Stage;

public class Fx2DShapeExample9 extends Application
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage stage)
	{
		// Create the CubicCurves
		CubicCurve cubiccurve1 = new CubicCurve(0, 50, 20, 0, 50, 80, 50, 0);
		cubiccurve1.setFill(Color.TRANSPARENT);
		cubiccurve1.setStroke(Color.BLACK);
		CubicCurve cubiccurve2 = new CubicCurve(0, 50, 20, 0, 50, 80, 50, 0);
		cubiccurve2.setFill(Color.LIGHTGRAY);

		// Create the HBox
		HBox root = new HBox();
		// Add the Children to the HBox
		root.getChildren().addAll(cubiccurve1, cubiccurve2);
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
		stage.setTitle("A JavaFX CubicCurve Example");
		// Display the Stage
		stage.show();
	}
}
