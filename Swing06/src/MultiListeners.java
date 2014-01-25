import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


@SuppressWarnings("serial")
public class MultiListeners extends JFrame {
  final JPanel panel = new JPanel();
  final JButton button1 = new JButton("Action");
  final JButton button2 = new JButton("Processing");
  final JTextArea textArea = new JTextArea(25, 80);
  
  public MultiListeners() {
    super("Listening events...");
    getContentPane().add(createWidgets());
  }
  
  public Component createWidgets() {
    JPanel mainPanel = new JPanel();
    
    textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
    mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    mainPanel.add(textArea);
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    panel.add(button1);
    panel.add(Box.createVerticalStrut(10));
    panel.add(button2);
    mainPanel.add(panel);
    
    button1.addActionListener(new Listener1());
    button2.addActionListener(new Listener1());
    button2.addActionListener(new Listener2());
    return mainPanel;
  }
  
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        MultiListeners frame = new MultiListeners();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
      }
    });
  }
  
  private class Listener1 implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      textArea.append(e.getActionCommand() + " has generated " + 
          e.getClass().getSimpleName() + " registered by " + 
          getClass().getSimpleName() + "\n");
    }
  }
  
  private class Listener2 implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      textArea.append(e.getActionCommand() + " has generated " + 
          e.getClass().getSimpleName() + " registered by " + 
          getClass().getSimpleName() + "\n");
    }
  }
}
