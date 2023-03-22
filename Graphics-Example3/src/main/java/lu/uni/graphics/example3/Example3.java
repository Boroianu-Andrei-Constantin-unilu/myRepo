import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

class Wheel extends JPanel {

  private Image wheel;
  private boolean first = true;
  private int counter = 0;

  public Wheel() {
    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(321, 321));

    loadImage();

    Timer timer = new Timer();
    int INITIAL_DELAY = 100;
    int PERIOD_INTERVAL = 200;
    timer.scheduleAtFixedRate(new ScheduleTask(),
            INITIAL_DELAY, PERIOD_INTERVAL);
  }

  private void loadImage() {
    wheel = null;
    try {
      wheel = ImageIO.read(new File(getClass().getResource("/wheel.png").getFile()));
    } catch (IOException e) {
      
      System.err.println("Image not found: " + getClass().getResource("/wheel.png").toString());
      System.exit(1);
    }
  }


  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawWheel(g);
  }

  private void drawWheel(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    if (first) {
      g2d.drawImage(wheel, 0, 0, this);
      first = false;
    } else {
      AffineTransform t = new AffineTransform();
      t.rotate(counter * 20.0, 160, 160);
      counter++;
      g2d.drawImage(wheel, t, this);
    }
    Toolkit.getDefaultToolkit().sync();
  }

  private class ScheduleTask extends TimerTask {

    @Override
    public void run() {
      repaint();
    }
  }
}

public class Example3 extends JFrame {

  public Example3() {
      add(new Wheel());

      setResizable(false);
      pack();

      setTitle("Simple Animation Example");
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {

      EventQueue.invokeLater(() -> {
          JFrame ex = new Example3();
          ex.setVisible(true);
      });
  }
}
