import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

public class Exercise5 extends JFrame {

    public Exercise5() {
        super("Solution Exercise 5");

        setSize(650, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        super.paint(getGraphics());
    }

    void readImage(Graphics g) {
        Graphics2D gd2 = (Graphics2D) g;
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(getClass().getResource("/forest.jpg").getFile()));
        } catch (IOException e) {
            System.err.println("Image not found at " + getClass().getResource("forest.jpg").toString());
            System.exit(1);
        }
        gd2.drawImage (img, 0, 0, null);

        int rows = img.getHeight ();
        float[] mask = new float[] {
                -2.0f, -1.0f, -2.0f,
                -1.0f, 12.0f, -1.0f,
                -2.0f, -1.0f, -2.0f
        };
        Kernel kernel = new Kernel(3, 3, mask);
        ConvolveOp op = new ConvolveOp(kernel);
        gd2.drawImage(img, op, 0, rows+10);
    }

    public void paint(Graphics g) {
        super.paint(g);
        readImage (g);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Exercise5().setVisible(true);
            }
        });
    }
}
