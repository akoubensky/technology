import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


@SuppressWarnings("serial")
public class BorderLayoutEx extends JFrame {
  public BorderLayoutEx() {
    super("Border layout");
    
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout(5, 5));
    
    Border border = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.RED, 2),
        BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JLabel labelNorth = new JLabel("Север");
    JLabel labelSouth = new JLabel("Юг");
    JLabel labelEast = new JLabel("Восток");
    JLabel labelWest = new JLabel("Запад");
    JLabel labelCenter = new JLabel("Центр");
    
    labelNorth.setBorder(border);
    labelSouth.setBorder(border);
    labelEast.setBorder(border);
    labelWest.setBorder(border);
    labelCenter.setBorder(border);
    
    panel.add(labelNorth, BorderLayout.NORTH);
    panel.add(labelSouth, BorderLayout.SOUTH);
    panel.add(labelEast, BorderLayout.EAST);
    panel.add(labelWest, BorderLayout.WEST);
    panel.add(labelCenter, BorderLayout.CENTER);
    
    getContentPane().add(panel);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        BorderLayoutEx example = new BorderLayoutEx();
        example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        example.pack();
        example.setVisible(true);
      }
    });
  }
}
