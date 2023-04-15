import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import javafx.scene.shape.Rectangle;

public class MovingRectangle {
    static public void moveRect(Rectangle r, Point2D diff) {
        double x = r.getX() + diff.getX();
        double y = r.getY() + diff.getY();
        //      System.out.println("Moving " + r.getId() + " to " + x + ", " + y + " diff: " + diff);
        setRect(r, x, y);/*from   w w  w .j av  a2s  .co  m*/
    }

    static public void moveRect(Rectangle r, Point2D diff,
            Point2D constraint) {
        double x = r.getX() + diff.getX();
        double y = r.getY() + diff.getY();

        if (constraint != null) {
            if (x < 0)
                x = 0;
            if (x > (constraint.getX() - r.getWidth()))
                x = constraint.getX() - r.getWidth();
            if (y < 0)
                y = 0;
            if (y > (constraint.getY() - r.getHeight()))
                y = constraint.getY() - r.getHeight();
        }
        //      System.out.println("Moving " + r.getId() + " to " + x + ", " + y + " diff: " + diff);
        setRect(r, x, y);
    }

    static public void moveRect(Rectangle r, double dx, double dy) {
        double x = r.getX();
        double y = r.getY();
        setRect(r, dx + x, dy + y);
    }

    static public void setRect(Rectangle r, Point2D a, Point2D b) {
        double x = Math.min(a.getX(), b.getX());
        double y = Math.min(a.getY(), b.getY());
        double width = Math.abs(a.getX() - b.getX());
        double height = Math.abs(a.getY() - b.getY());
        setRect(r, x, y, width, height);
    }

    static public void setRect(Rectangle target, Rectangle src) {
        setRect(target, src.getX(), src.getY(), src.getWidth(),
                src.getHeight());
    }

    static public void setRect(Rectangle target, Rectangle src,
            Rectangle constraint) {
        double x = src.getX();
        double y = src.getY();
        if (constraint != null) {
            if (x < 0)
                x = 0;
            if (x > (constraint.getX() - target.getWidth()))
                x = constraint.getX() - target.getWidth();
            if (y < 0)
                y = 0;
            if (y > (constraint.getY() - target.getHeight()))
                y = constraint.getY() - target.getHeight();
        }
    }

    static public void setRect(Rectangle r, double x, double y, double w,
            double h) {
        r.setX(x);
        r.setY(y);
        r.setWidth(w);
        r.setHeight(h);
    }

    static public void setRect(Region r, double x, double y, double w,
            double h) {
        r.setLayoutX(x);
        r.setLayoutY(y);
        r.prefWidth(w);
        r.prefHeight(h);
    }

    static public void setRect(StackPane r, Point2D a, Point2D b) {
        double x = Math.min(a.getX(), b.getX());
        double y = Math.min(a.getY(), b.getY());
        double width = Math.abs(a.getX() - b.getX());
        double height = Math.abs(a.getY() - b.getY());
        setRect(r, x, y, width, height);
    }

    static public void setRect(StackPane r, double x, double y, double w,
            double h) {
        //      r.localToParent(new Bounds(x,y,w,h));
        r.setLayoutX(x);
        r.setLayoutY(y);
        r.prefWidth(w);
        r.setMaxWidth(w);
        r.setMinWidth(w);
        r.prefHeight(h);
        r.setMaxHeight(h);
        r.setMinHeight(h);
    }

    static public void setRect(ImageView r, double x, double y, double w,
            double h) {
        r.setX(x);
        r.setY(y);
        r.setFitWidth(w);
        r.setFitHeight(h);
    }

    static public void setRect(Rectangle r, double x, double y) {
        r.setX(x);
        r.setY(y);
    }
}