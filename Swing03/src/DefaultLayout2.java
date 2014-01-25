import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class DefaultLayout2 extends JFrame {
  public DefaultLayout2() {
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

    // 2. Ќепосредственно в contentPane, размеща€ по сторонам света.
    content.add(first, BorderLayout.NORTH);
    content.add(second, BorderLayout.SOUTH);
    content.add(third, BorderLayout.WEST);
    content.add(fourth, BorderLayout.EAST);
    content.add(fifth, BorderLayout.CENTER);

  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        DefaultLayout2 example = new DefaultLayout2();
        example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        example.pack();
        example.setVisible(true);
      }
    });
  }
}
