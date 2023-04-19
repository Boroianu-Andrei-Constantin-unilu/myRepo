package com.graphics.exercise6.boroianuandrei.exercise6;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

public class Exercise6 extends Application{

    @Override
    public void start(Stage stage) throws Exception {

    Random random = new Random();
    Group group = new Group();
    Rectangle rect = new Rectangle(10, 10, 10, 10);
    Circle circle = new Circle(random.nextInt(595), random.nextInt(595), 5);
    QuadCurve curve1 = new QuadCurve(10, 10, random.nextInt(595), random.nextInt(595), 0, random.nextInt(595));
    QuadCurve curve2 = new QuadCurve(curve1.getEndX(), curve1.getEndY(), random.nextInt(595), random.nextInt(595), random.nextInt(595), 595);
    QuadCurve curve3 = new QuadCurve(curve2.getEndX(), curve2.getEndY(), random.nextInt(595), random.nextInt(595), 595, random.nextInt(595));
    QuadCurve curve4 = new QuadCurve(curve3.getEndX(), curve3.getEndY(), random.nextInt(595), random.nextInt(595), random.nextInt(595), 0);
    QuadCurve curve5 = new QuadCurve(curve4.getEndX(), curve4.getEndY(), random.nextInt(595), random.nextInt(595), 0, random.nextInt(595));
    QuadCurve curve6 = new QuadCurve(curve1.getEndX(), curve1.getEndY(), random.nextInt(595), random.nextInt(595), random.nextInt(595), 595);
    rect.setFill(Color.DARKRED);
    group.getChildren().addAll(circle, rect);

    Scene scene = new Scene(group, 600, 600, Color.GREEN);
    stage.setScene(scene);
    stage.setTitle("Crazy Biliard Game");
    stage.show();

    rect.getLayoutBounds();

    PathTransition path1 = new PathTransition();
    path1.setDuration(Duration.seconds(2));
    path1.setPath(curve1);
    path1.setNode(rect);
    path1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
    path1.setCycleCount(1);
    path1.play();

    Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
        @Override
                        public void handle(ActionEvent event1) {

                        if (rect.getLayoutX() > scene.getWidth() || rect.getLayoutX() > scene.getHeight() ||
                        rect.getLayoutX() < scene.getWidth() || rect.getLayoutX() < scene.getHeight()) {

                        circle.relocate(random.nextInt(595), random.nextInt(595));
                        rect.setFill(Color.color (Math.random(), Math.random(), Math.random()));

                        PathTransition path2 = new PathTransition();
                        path2.setDuration(Duration.seconds(2));
                        path2.setPath(curve2);
                        path2.setNode(rect);
                        path2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                        path2.setCycleCount(1);
                        path2.play();

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
            @Override
                        public void handle(ActionEvent event2) {

                        if (rect.getLayoutX() > scene.getWidth() || rect.getLayoutX() > scene.getHeight() ||
                        rect.getLayoutX() < scene.getWidth() || rect.getLayoutX() < scene.getHeight()) {

                        circle.relocate(random.nextInt(595), random.nextInt(595));
                        rect.setFill(Color.color (Math.random(), Math.random(), Math.random()));

                        PathTransition path3 = new PathTransition();
                        path3.setDuration(Duration.seconds(2));
                        path3.setPath(curve3);
                        path3.setNode(rect);
                        path3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                        path3.setCycleCount(1);
                        path3.play();

            Timeline timeline3 = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
                @Override
                        public void handle(ActionEvent event3) {

                        if (rect.getLayoutX() > scene.getWidth() || rect.getLayoutX() > scene.getHeight() ||
                        rect.getLayoutX() < scene.getWidth() || rect.getLayoutX() < scene.getHeight()) {

                        circle.relocate(random.nextInt(595), random.nextInt(595));
                        rect.setFill(Color.color (Math.random(), Math.random(), Math.random()));

                        PathTransition path4 = new PathTransition();
                        path4.setDuration(Duration.seconds(2));
                        path4.setPath(curve4);
                        path4.setNode(rect);
                        path4.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                        path4.setCycleCount(1);
                        path4.play();

                Timeline timeline4 = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
                    @Override
                        public void handle(ActionEvent event4) {

                        if (rect.getLayoutX() > scene.getWidth() || rect.getLayoutX() > scene.getHeight() ||
                        rect.getLayoutX() < scene.getWidth() || rect.getLayoutX() < scene.getHeight()) {

                        circle.relocate(random.nextInt(595), random.nextInt(595));
                        rect.setFill(Color.color (Math.random(), Math.random(), Math.random()));

                        PathTransition path5 = new PathTransition();
                        path5.setDuration(Duration.seconds(2));
                        path5.setPath(curve5);
                        path5.setNode(rect);
                        path5.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                        path5.setCycleCount(1);
                        path5.play();

                    Timeline timeline5 = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event5) {

                        if (rect.getLayoutX() > scene.getWidth() || rect.getLayoutX() > scene.getHeight() ||
                        rect.getLayoutX() < scene.getWidth() || rect.getLayoutX() < scene.getHeight()) {

                        circle.relocate(random.nextInt(595), random.nextInt(595));
                        rect.setFill(Color.color (Math.random(), Math.random(), Math.random()));

                        PathTransition path6 = new PathTransition();
                        path6.setDuration(Duration.seconds(2));
                        path6.setPath(curve6);
                        path6.setNode(rect);
                        path6.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                        path6.setCycleCount(1);
                    }
                    throw new UnsupportedOperationException("Uninplemented method 'handle'");
                    }
    }));
    timeline5.setCycleCount(1);
    }
    throw new UnsupportedOperationException("Uninplemented method 'handle'");
    }
    }));
    timeline4.setCycleCount(1);
    timeline4.play();
    }
    throw new UnsupportedOperationException("Uninplemented method 'handle'");
    }
    }));
    timeline3.setCycleCount(1);
    timeline3.play();
    }
    throw new UnsupportedOperationException("Uninplemented method 'handle'");
    }
    }));
    timeline2.setCycleCount(1);
    timeline2.play();
    }
    throw new UnsupportedOperationException("Uninplemented method 'handle'");
    }
    }));
    timeline1.setCycleCount(1);
    timeline1.play();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}