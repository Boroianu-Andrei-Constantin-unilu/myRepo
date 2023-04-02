import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Hello World");
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/java/sample.fxml"));
        loader.setClassLoader(Main.class.getClassLoader());
        Parent root = loader.load();
        stage.setScene(new Scene(root, 300, 300));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
