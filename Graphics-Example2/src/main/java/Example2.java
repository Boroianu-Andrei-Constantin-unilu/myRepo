import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Example2 extends JFrame {

    public Example2() {
        super("Ellipse Filling Demo");

        setSize(800, 590);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        super.paint(getGraphics ());
    }

    void drawEllipse(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.fill (new Ellipse2D.Double(50, 50, 400, 200));

        GradientPaint redtowhite = new GradientPaint(250, 50, Color.RED, 250, 200, Color.WHITE);
        g2d.setPaint(redtowhite);
        g2d.fill (new Ellipse2D.Double(250, 50, 400, 200));

    }

    public void paint(Graphics g) {
        super.paint(g);
        drawEllipse(g);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new Example2().setVisible(true));
    }
}
