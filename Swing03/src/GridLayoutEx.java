import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


@SuppressWarnings("serial")
public class GridLayoutEx extends JFrame {
  public GridLayoutEx() {
    super("Grid Layout");

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(0, 2, 10, 8));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    panel.add(new JLabel("First name:", JLabel.TRAILING));
    panel.add(new JTextField(10));
    panel.add(new JLabel("Last name:", JLabel.TRAILING));
    panel.add(new JTextField(10));
    panel.add(new JLabel("Position:", JLabel.TRAILING));
    panel.add(new JTextField(10));
    panel.add(new JLabel("Compensation:", JLabel.TRAILING));
    panel.add(new JTextField(10));
    
    JPanel buttons = new JPanel();
    FlowLayout buttonsLayout = (FlowLayout)buttons.getLayout();
    buttonsLayout.setAlignment(FlowLayout.RIGHT);
    buttonsLayout.setHgap(5);
    buttons.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 8));
    buttons.add(new JButton("OK"));
    buttons.add(new JButton("Cancel"));
    
    getContentPane().add(panel, BorderLayout.NORTH);
    getContentPane().add(buttons, BorderLayout.SOUTH);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        GridLayoutEx example = new GridLayoutEx();
        example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        example.pack();
        example.setVisible(true);
      }
    });
  }

}
