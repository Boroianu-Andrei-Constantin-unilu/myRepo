import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class Gui extends Application{

    Stage Window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Window = primaryStage;
        Window.setTitle("Gui Tester");

        Group root = new Group();
        Rectangle box = new Rectangle(0, 0, 50,50);
        box.setFill(Color.GREEN);
        KeyValue x = new KeyValue(box.xProperty(), 900);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(3000), x);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        root.getChildren().add(box);

        GridPane grid = new GridPane();
        grid.setVgap(8);

        root.getChildren().add(grid);

        Button newGame = new Button("START NEW GAME");

        GridPane.setConstraints(newGame, 1, 1);

        Button continueGame = new Button("CONTINUE");
        GridPane.setConstraints(continueGame, 1, 2);

        grid.getChildren().addAll(newGame, continueGame);

        Scene scene = new Scene(root, 1000, 1000);
        scene.getStylesheets().add("tester.css");
        Window.setScene(scene);
        Window.show();
        Window.setWidth(Window.getWidth() + 0.1);
    }
}