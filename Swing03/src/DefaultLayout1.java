import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class DefaultLayout1 extends JFrame {
  public DefaultLayout1() {
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

    // 1. Непосредственно в contentPane
    content.add(first);
    content.add(second);
    content.add(third);
    content.add(fourth);
    content.add(fifth);

  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        DefaultLayout1 example = new DefaultLayout1();
        example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        example.pack();
        example.setVisible(true);
      }
    });
  }
}
