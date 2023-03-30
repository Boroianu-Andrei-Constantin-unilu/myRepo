import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Example1 extends JFrame {

    public Example1() {
        super("Lines Drawing Demo");

        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        super.paint(getGraphics ());
    }

    void drawLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawLine(120, 50, 360, 50);

        g2d.setColor(Color.RED);
        Line2D x = new Line2D.Double(59.2d, 99.8d, 419.1d, 99.8d);
        g2d.draw(x);

        g2d.setColor(Color.GREEN);
        g2d.setStroke(new BasicStroke(14f));
        g2d.draw(new Line2D.Float(21.50f, 132.50f, 459.50f, 132.50f));

        float[] dashingPattern = {10f, 10f, 1f, 10f};
        Stroke stroke = new BasicStroke(4f, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_MITER, 1.0f, dashingPattern, 0.2f);
        g2d.setColor(Color.BLUE);
        g2d.setStroke(stroke);
        g2d.drawLine(150, 250, 350, 250);
    }

    public void paint(Graphics g) {
        super.paint(g);
        drawLines(g);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Example1().setVisible(true));
    }
}