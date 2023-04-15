import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.batik.parser.PathParser;

public class SVGPathSlider extends Application {

    private final static double WIDTH = 1488;
    private final static double HEIGHT = 840;

    private final static int SLIDER_MIN = 0;
    private final static int SLIDER_MAX = 100;

    private final static double ANIMATION_DURATION = 500.0d;

    private double _initX;
    private double _initY;
    private Point2D _dragAnchor;

    private PathTransition _pathTransition;
    private Circle _circle;

    private List<Point2D> _pathPointList;

    private int _actIndex;

    private int _sliderIndex = 0;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        // Parse the SVG Path with Apache Batik and create a Path
        PathParser parser = new PathParser();
        JavaFXPathElementHandler handler = new JavaFXPathElementHandler("track");
        parser.setPathHandler(handler);

        // SVG Path
        parser.parse("M1411.789,59.381c-32.315,6.982-60.416,21.861-85.014,43.752c-19.922,17.731-35.061,38.869-45.943,63.058\n" +
"       c-5.333,11.853-9.46,24.204-12.018,36.946c-1.175,5.859-2.027,11.836-2.615,17.849c-0.784,8.039-1.604,16.047-1.405,24.125\n" +
"       c0.544,22.257,4.783,43.825,12.943,64.524c3.956,10.034,8.61,19.806,13.214,29.573c10.041,21.306,20.268,42.525,30.387,63.795\n" +
"       c5.109,10.741,10.271,21.46,15.19,32.288c7.075,15.575,14.958,30.839,19.51,47.438c3.979,14.508,6.568,29.219,7.169,44.322\n" +
"       c0.331,8.324,0.435,16.646-0.246,24.884c-1.532,18.565-5.443,36.725-12.43,54.038c-8.359,20.721-19.532,39.866-34.275,56.79\n" +
"       c-11.257,12.923-24.016,24.286-38.271,33.754c-15.338,10.186-31.729,18.501-49.484,23.784c-9.592,2.853-19.303,5.104-29.216,6.816\n" +
"       c-15.327,2.646-30.695,2.349-46.054,2.007c-9.38-0.208-18.705-2.263-28.096-2.838c-6.393-0.393-12.754-1.123-19.09-1.821\n" +
"       c-8.197-0.902-16.423-1.712-24.613-2.781c-5.534-0.722-11.162-1.225-16.739-1.823c-5.861-0.631-11.759-1.022-17.578-2.167\n" +
"       c-2.655-0.522-5.449-0.437-8.188-0.75c-8.846-1.013-17.698-1.924-26.519-3.171c-5.554-0.786-11.189-0.973-16.764-1.633\n" +
"       c-7.814-0.928-15.595-2.138-23.412-3.036c-2.847-0.327-5.725-0.423-8.588-0.699c-8.938-0.864-17.943-1.748-26.899-1.554\n" +
"       c-15.911,0.347-31.625,2.248-47.251,5.876c-15.535,3.606-31.159,6.96-46.725,10.469c-14.081,3.174-28.128,6.604-42.203,9.777\n" +
"       c-16.611,3.743-33.229,7.521-49.856,11.208c-9.739,2.16-19.507,4.166-29.301,5.996c-5.747,1.073-11.674,1.388-17.537,1.594\n" +
"       c-7.402,0.261-14.82,0.113-22.231,0.052c-15.293-0.127-30.188-2.85-44.857-6.989c-21.598-6.095-40.763-16.693-58.57-30.253\n" +
"       c-27.463-20.914-55.199-41.478-83.072-61.846c-15.905-11.621-34.119-18.604-53.016-23.727c-9.574-2.596-19.33-4.402-29.226-5.603\n" +
"       c-11.292-1.37-22.576-2.289-33.947-2.248c-11.527,0.041-22.741,2.374-33.923,4.73c-11.105,2.342-22.015,5.769-33.198,7.525\n" +
"       c-9.26,1.455-18.411,3.436-27.664,4.864");

        Path path = handler.getPath();
        root.getChildren().add(path);

        // Moving image
        _circle = new Circle(20);
        _circle.setFill(Color.RED);

        root.getChildren().add(_circle);

        // Path Transition
        _pathTransition = new PathTransition();
        _pathTransition.setDuration(Duration.seconds(ANIMATION_DURATION));
        _pathTransition.setPath(path);
        _pathTransition.setNode(_circle);
        _pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        _pathTransition.setCycleCount(1);
        _pathTransition.playFromStart();
        _pathTransition.pause();
        _pathTransition.jumpTo("end");

        // Save the circle positions on the path
        _pathPointList = new ArrayList<>();
        savePositions();


        // Mouse presssed handler
        _circle.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                 // Store initial position
                _initX = _circle.getTranslateX();
                _initY = _circle.getTranslateY();
                _dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());                
            }
        });


        // Mouse dragged handler
        _circle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                double dragX = me.getSceneX() - _dragAnchor.getX();
                double dragY = me.getSceneY() - _dragAnchor.getY();

                // Calculate new position of the circle
                double newXPosition = _initX + dragX;
                double newYPosition = _initY + dragY;

                // Get the nearest index (= second) of the animation
                _actIndex = getAnimationIndex(newXPosition, newYPosition);

                // Show animation at the given second
                _pathTransition.jumpTo(Duration.seconds(_actIndex));                                

                // Get slider index
                _sliderIndex = remap(_actIndex, ANIMATION_DURATION, 0, SLIDER_MIN, SLIDER_MAX);
                System.out.println(_sliderIndex);
            }
        });

        primaryStage.setTitle("JavaFX SVG Path Slider");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }    


    /**
     * Save the position of the circle for every second of the animation in
     * a list.
     */
    private void savePositions() {

        if (_pathPointList == null)
            return;

        for (int i=0; i<=(int)ANIMATION_DURATION; i++) {
            _pathTransition.jumpTo(Duration.seconds(i));

            _pathPointList.add(new Point2D(_circle.getTranslateX(), _circle.getTranslateY()));
        }

    }

    /**
     * Returns the index 
     * @param mousePosX
     * @param mousePosY
     * @return 
     */
    private int getAnimationIndex(double mousePosX, double mousePosY) {

        int nearestIndex = 0;

        int i = 0;
        double dx;
        double dy;
        double old_dist = Double.MAX_VALUE;
        double act_dist;

        for (Point2D pathPos : _pathPointList) {

            // Get distance between mouse position and saved position on path
            // with pythagoras
            dx = mousePosX - pathPos.getX();
            dy = mousePosY - pathPos.getY();
            act_dist = Math.sqrt(dx * dx + dy * dy);

            if (act_dist < old_dist) {
                old_dist = act_dist;
                nearestIndex = i;
            }

            i++;            
        }

        return nearestIndex;
    }

    /**
     * Remaps the given value from one to anoter range
     * 
     * @param value
     * @param from1
     * @param to1
     * @param from2
     * @param to2
     * @return 
     */
    private int remap (int value, double from1, double to1, double from2, double to2) {
        double tmp = (value - from1) / (to1 - from1) * (to2 - from2) + from2;        
        return (int)tmp;
    }
}