package test;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import game.constant.Constain;
public class Test extends JPanel {
 
  Image image;
 
  public Test() {
    image = Toolkit.getDefaultToolkit().createImage("src/images/background/background.gif");
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null) {
      g.drawImage(image, 0, 0, 600, Constain.SCREEN_HEIGHT, this);
    }
  }
 
  public static void main(String[] args) {
    // SwingUtilities.invokeLater(new Runnable() {
 
      // @Override
      // public void run() {
        JFrame frame = new JFrame();
        frame.add(new Test());
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      // }
    // });
  }
}