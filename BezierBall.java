import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BezierBall extends Application {
    private static final int WIDTH = 595;
    private static final int HEIGHT = 400;
    private static final List<Color> COLORS = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PURPLE, Color.ORANGE);
    private static final double CENTER_MOVE_RANGE = 50;
    private static final double BALL_SIZE = 10;

    private Random rand = new Random();
    private Pane canvas = new Pane();
    private Circle ball = new Circle(BALL_SIZE, getRandomColor());

    private PathTransition transition;
    private Path path;

    private int pointIndex = 0;
    private Point2D[] points = new Point2D[] {
            new Point2D(WIDTH/2, 0),
            new Point2D(WIDTH, HEIGHT/2),
            new Point2D(WIDTH/2, HEIGHT),
            new Point2D(0, HEIGHT/2)
    };

    @Override
    public void start(Stage stage) {
        canvas.getChildren().add(ball);

        moveBallToPoint(points[pointIndex]);

        Scene scene = new Scene(new Group(canvas), WIDTH, HEIGHT, Color.WHITE);
        stage.setScene(scene);
        stage.show();
    }

    private void moveBallToPoint(Point2D point) {
        Point2D center = getRandomCenter();

        if (path != null) {
            canvas.getChildren().remove(path);
        }

        path = new Path();
        path.getElements().add(new MoveTo(ball.getCenterX(), ball.getCenterY()));
        path.getElements().add(new QuadCurveTo(center.getX(), center.getY(), point.getX(), point.getY()));

        transition = new PathTransition(Duration.seconds(2), path, ball);
        transition.setOnFinished(event -> {
            pointIndex = (pointIndex + 1) % points.length;
            ball.setFill(getRandomColor());
            moveBallToPoint(points[pointIndex]);
        });
        transition.play();
    }

    private Point2D getRandomCenter() {
        double x = rand.nextDouble() * CENTER_MOVE_RANGE * 2 - CENTER_MOVE_RANGE;
        double y = rand.nextDouble() * CENTER_MOVE_RANGE * 2 - CENTER_MOVE_RANGE;
        return new Point2D(WIDTH/2 + x, HEIGHT/2 + y);
    }

    private Color getRandomColor() {
        return COLORS.get(rand.nextInt(COLORS.size()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
