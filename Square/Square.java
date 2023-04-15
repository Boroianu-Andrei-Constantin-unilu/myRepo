package Square;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Square  {
    private Rectangle model;
    private boolean status;
    private static Color busyColor = Color.GREEN;
    private static Color defaultColor = Color.SILVER;

    private Cell cell;

    public Square()  {
        model = new Rectangle();
        model.setFill(defaultColor);
        model.setStroke(Color.BLACK);

        cell = null;
        status = false;

        model.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent me){
                model.setFill(Color.RED);
            }
        });
    }

    public Square(Rectangle rec, boolean busy)  {
        model = rec;
        status = busy;
    }

    public Rectangle getModel()  {
        return model;
    }

    public boolean getStatus()  {
        return status;
    }

    public Cell getCell()  {
        return cell;
    }

    public void setStatus(boolean busy)  {
        status = busy;

        if (status == true) {
            model.setFill(busyColor);
        }
        else  {
            model.setFill(defaultColor);
        }
    }

    public void setModel(Rectangle rec)  {
        model = rec;
    }

    public void setCell(Cell aCell)  {
        cell = aCell;
    }
}
