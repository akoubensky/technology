import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class Borders extends JFrame {
  public Borders() {
    super("Borders");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setContentPane(createContent());
    pack();
    setVisible(true);
  }

  private JComponent createContent() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    
//    Border emptyBorder20 = BorderFactory.createEmptyBorder(20, 20, 20, 20);
//    panel.setBorder(emptyBorder20);
//    panel.setPreferredSize(new Dimension(200, 250));

    JLabel label1 = new JLabel("Первая надпись");
    JLabel label2 = new JLabel("Вторая надпись");
    JLabel label3 = new JLabel("Третья надпись");
    JLabel label4 = new JLabel("Четвертая надпись");
    JLabel label5 = new JLabel("Пятая надпись");
    JLabel label6 = new JLabel("Шестая надпись");

    Border emptyBorder = BorderFactory.createEmptyBorder(5, 10, 15, 20);
    Border lineBorder = BorderFactory.createLineBorder(Color.RED, 2);
    Border raisedBorder = BorderFactory.createRaisedBevelBorder();
    Border loweredBorder = BorderFactory.createLoweredBevelBorder();
    TitledBorder titledBorder = BorderFactory.createTitledBorder(
        lineBorder, "заголовок", TitledBorder.CENTER, TitledBorder.TOP);
    CompoundBorder compBorder = BorderFactory.createCompoundBorder(
        titledBorder, BorderFactory.createCompoundBorder(
            raisedBorder, loweredBorder));

//    label1.setBorder(emptyBorder);
//    label2.setBorder(lineBorder);
//    label3.setBorder(raisedBorder);
//    label4.setBorder(loweredBorder);
//    label5.setBorder(titledBorder);
//    label6.setBorder(compBorder);
//
//    label3.setFont(new Font("Courier New", Font.ITALIC, 16));
//    label4.setOpaque(true);
//    label4.setBackground(Color.BLUE);
//    label4.setForeground(Color.YELLOW);
    
    panel.add(label1);
    panel.add(label2);
    panel.add(label3);
    panel.add(label4);
    panel.add(label5);
    panel.add(label6);

    return panel;
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new Borders();
      }
    });
  }
}
