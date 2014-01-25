import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class DefaultLayout3 extends JFrame {
  public DefaultLayout3() {
    super("Default layout");
    Container content = getContentPane();

    Border border = BorderFactory.createCompoundBorder(BorderFactory
        .createLineBorder(Color.RED, 2), BorderFactory.createEmptyBorder(10,
        10, 10, 10));

    JLabel first = new JLabel("First");
    JLabel second = new JLabel("Second");
    JLabel third = new JLabel("Third");
    JLabel fourth = new JLabel("Fourth");
    JLabel fifth = new JLabel("Fifth");

    first.setBorder(border);
    second.setBorder(border);
    third.setBorder(border);
    fourth.setBorder(border);
    fifth.setBorder(border);

    // 3. В панель
    JPanel panel = new JPanel();
    panel.add(first);
    panel.add(second);
    panel.add(third);
    panel.add(fourth);
    panel.add(fifth);
    content.add(panel);

  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        DefaultLayout3 example = new DefaultLayout3();
        example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        example.pack();
        example.setVisible(true);
      }
    });
  }
}
