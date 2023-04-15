import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.animation.PathTransition.OrientationType;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {

  @Override
  public void start(Stage stage) {
    // Disque bleu
    Circle circle = new Circle(25);
    circle.setFill(Color.BLUE);

    // Courbe
    CubicCurve cubicCurve = new CubicCurve();

    // Propriétés de la courbe
    // Commence à 0,0
    cubicCurve.setStartX(0);
    cubicCurve.setStartY(0);

    // Points de contrôle 400,40 et 175,250
    cubicCurve.setControlX1(400);
    cubicCurve.setControlY1(40);

    cubicCurve.setControlX2(175);
    cubicCurve.setControlY2(250);

    // Fin de la courbe 640,480
    cubicCurve.setEndX(640);
    cubicCurve.setEndY(480);

    // Courbe jaune épaisseur 5
    cubicCurve.setFill(Color.TRANSPARENT);
    cubicCurve.setStrokeWidth(5);
    cubicCurve.setStroke(Color.YELLOW);

    // Mouvement courbe
    CubicCurveTo cubic = new CubicCurveTo();

    // Reprend les propriétés de la courbe pour l'animation
    cubic.setControlX1(cubicCurve.getControlX1());
    cubic.setControlY1(cubicCurve.getControlY1());
    cubic.setControlX2(cubicCurve.getControlX2());
    cubic.setControlY2(cubicCurve.getControlY2());
    cubic.setX(cubicCurve.getEndX());
    cubic.setY(cubicCurve.getEndY());

    // Le chemin pour l'animation
    Path path = new Path();
    // A partir de 0,0 et la forme de la courbe
    path.getElements().add(new MoveTo(0, 0));
    path.getElements().add(cubic);

    //L'animation transition
    PathTransition pathTransition = new PathTransition();
    // 3 secondes
    pathTransition.setDuration(Duration.millis(3000));
    // Le cercle
    pathTransition.setNode(circle);
    // Le chemin
    pathTransition.setPath(path);
    // Animation infinie
    pathTransition.setCycleCount(Transition.INDEFINITE);
    // Revient en arrière automatiquement
    pathTransition.setAutoReverse(true);

    pathTransition.play();

    var scene = new Scene(new Group(cubicCurve, circle), 640, 480);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }

}