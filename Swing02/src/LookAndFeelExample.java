/*
 * ������������� ����������� Look & Feel.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;



/**
 * ��� ��������� ������������� ����������� �������������
 * ������������ Look & Feel.
 */
public class LookAndFeelExample {
  // �������� ���� ����������.
  static JFrame frame;

  // ���������� ����
  @SuppressWarnings("serial")
  static private class MyPanel extends JPanel {
    // ����������� �����-������������� Look & Feel
    static final String metal= "Metal";
    static final String metalClassName = "javax.swing.plaf.metal.MetalLookAndFeel";

    // ����������� �����-������������� Look & Feel
    static final String nimbus= "Nimbus";
    static final String nimbusClassName = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

    // Java-�������� - �������� ������������ ������� Motif.
    static final String motif = "Motif";
    static final String motifClassName = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";

    // ���� "��� � Windows"
    static final String windows = "Windows";
    static final String windowsClassName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

    final JPanel upper;
    final JButton button;
    final JLabel label;
    final JRadioButton metalButton, nimbusButton, motifButton, windowsButton;
    
    int number = 0;
    final String labelFormat = "����� ������� ������: %d";

    public MyPanel() {
      button = new JButton("Hello, world");
      button.setMnemonic('h');
      
      upper = new JPanel();
      label = new JLabel(String.format(labelFormat, number));

      // ��� �����-������ - ��� ������������ Look & Feel
      metalButton = new JRadioButton(metal);
      metalButton.setMnemonic('o'); 
      metalButton.setActionCommand(metalClassName);
      System.out.println(metalButton.getActionCommand());
  
      nimbusButton = new JRadioButton(nimbus);
      nimbusButton.setMnemonic('n'); 
      nimbusButton.setActionCommand(nimbusClassName);
      System.out.println(nimbusButton.getActionCommand());
  
      motifButton = new JRadioButton(motif);
      motifButton.setMnemonic('m'); 
      motifButton.setActionCommand(motifClassName);
      System.out.println(motifButton.getActionCommand());
  
      windowsButton = new JRadioButton(windows);
      windowsButton.setMnemonic('w'); 
      windowsButton.setActionCommand(windowsClassName);
      System.out.println(windowsButton.getActionCommand());
  
      // �������� �����-������ � ������.
      ButtonGroup group = new ButtonGroup();
      group.add(metalButton);
      group.add(nimbusButton);
      group.add(motifButton);
      group.add(windowsButton);
  
      // ������� ������� �� ������������ ������.
      RadioListener myListener = new RadioListener();
      metalButton.addActionListener(myListener);
      nimbusButton.addActionListener(myListener);
      motifButton.addActionListener(myListener);
      windowsButton.addActionListener(myListener);

      // ������� ������� �� ������� "������� ������"      
      button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
          label.setText(String.format(labelFormat, ++number));
        }
      });

      // ��������� ������
      upper.add(button);
      upper.add(label);
      add(upper);
      add(metalButton);
      add(nimbusButton);
      add(motifButton);
      add(windowsButton);
    }


    /** ���� ����� ���������� ��������� ��������� ��� �������
     * �����-������. 
     */
    class RadioListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        // ��� ������� Look & Feel ��������� ������ � �������� �� ActionCommand
        String lnfName = e.getActionCommand();

        try {
          // �������� ���������� �������� Look & Feel
          UIManager.setLookAndFeel(lnfName);
          // ��������� ������������ ��������� � ����
          SwingUtilities.updateComponentTreeUI(frame.getContentPane());
          // ��������� ��������� (layout).
          frame.pack();
        } catch (Exception exc) {
          // �� ������� ���������� ����� Look & Feel, ��������� ������
          JRadioButton button = (JRadioButton)e.getSource();
          button.setEnabled(false);
          updateState();
          System.err.println("Could not load LookAndFeel: " + lnfName);
        }
      }
    }

    // ������������� ������� �����-������ � ������������
    // � ������������� Look & Feel.
    public void updateState() {
      String lnfName = UIManager.getLookAndFeel().getClass().getName();
      if (lnfName.indexOf(metal) >= 0) {
        metalButton.setSelected(true);
      } else if (lnfName.indexOf(nimbus) >= 0) {
        nimbusButton.setSelected(true);
      } else if (lnfName.indexOf(windows) >= 0) {
        windowsButton.setSelected(true);
      } else if (lnfName.indexOf(motif) >= 0) {
        motifButton.setSelected(true);
      } else {
        System.err.println("����������� Look & Feel: " + lnfName);
      }
    }
  }

  /**
   * �������� �������, ����������� ��� ����������
   */
  public static void main(String s[]) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
          e.printStackTrace();
        }
        MyPanel panel = new MyPanel();
        
        frame = new JFrame("Look & Feel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        
        panel.updateState();
      }
    });
  }
}
