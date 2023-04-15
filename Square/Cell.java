package Square;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

class Cell  {
    private int age;
    private Color color;
    private Rectangle model;
    private int addressX;
    private int addressY;

    public Cell(int id, int fieldSize)  {
        age = id;

        Random rand = new Random();

        int lowerBound = 0;
        int upperBound = fieldSize - 1;

        addressX = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
        addressY = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;

        color = Color.YELLOW;
        model = new Rectangle(addressX, addressY, 50, 25);
        model.setFill(color);
    }

    public int getAddressX()  {
        return addressX;
    }

    public int getAddressY()  {
        return addressY;
    }

    public Rectangle getModel()  {
        return model;
    }

    public int getAge()  {
        return age;
    }

    public Color getColor()  {
        return color;
    }
}