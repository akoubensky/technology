import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class BoxLayoutEx extends JFrame {
  public BoxLayoutEx() {
    super("Box layout");

    Container content = getContentPane();
    
    JPanel buttons = new JPanel();
    BoxLayout layoutH = new BoxLayout(buttons, BoxLayout.LINE_AXIS);
    buttons.setLayout(layoutH);
    buttons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
    buttons.add(Box.createHorizontalGlue());
    buttons.add(new JButton("OK"));
    buttons.add(Box.createHorizontalStrut(5));
    buttons.add(new JButton("Cancel"));

    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    BoxLayout layoutV = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
    panel.setLayout(layoutV);
    panel.add(new JLabel("Поле для ввода текста:"));
    JTextArea textarea = new JTextArea(5, 20);
    textarea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    panel.add(textarea);
    
    content.add(panel, BorderLayout.CENTER);
    content.add(buttons, BorderLayout.SOUTH);

  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        BoxLayoutEx example = new BoxLayoutEx();
        example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        example.pack();
        example.setVisible(true);
      }
    });
  }
}
