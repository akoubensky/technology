import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class FlowLayoutEx extends JFrame {
  public FlowLayoutEx() {
    super("Flow layout");
    setPreferredSize(new Dimension(600, 300));

    Container content = getContentPane();
    JPanel panel = new JPanel();
    FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 5);
    //FlowLayout layout = new FlowLayout(FlowLayout.TRAILING, 15, 2);
    //FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 0, 0);
    layout.setAlignOnBaseline(true);
    panel.setLayout(layout);

    for (int i = 0; i < 25; ++i) {
      JLabel label = new JLabel("Label " + (i+1));
      Border border = BorderFactory.createCompoundBorder(
          BorderFactory.createLineBorder(Color.RED, 2),
          BorderFactory.createEmptyBorder(30-i, 10, 1, 10));
      label.setBorder(border);
      panel.add(label);
    }
    content.add(panel);

  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        FlowLayoutEx example = new FlowLayoutEx();
        example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        example.pack();
        example.setVisible(true);
      }
    });
  }
}
