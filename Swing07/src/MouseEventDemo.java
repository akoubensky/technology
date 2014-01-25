import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;


@SuppressWarnings("serial")
/**
 * ����� ������������ ����� ���������� ���� ��� ����������� ������� �� ����.
 * ���� ��� ������ ������������ ������� ����� � ������ �� ��� ������� ����,
 * � ����� ������� ������ ����.
 */
public class MouseEventDemo extends JPanel {
  // �������, � ������� �������������� �����, ������ � ������� �� ������ ����.
  BlankArea blankArea;
  // ������� ������ ��������� �� �������.
  JTextArea textArea;
  // ����������� ����� (�������� ���������).
  static final String NEWLINE = System.getProperty("line.separator");

  /**
   * �������� �������� �������, ��������� ���� ����������.
   * @param args - �� ������������.
   */
  public static void main(String[] args) {
    /* ���������� ��������� Look and Feel */
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    // ���� ��������� � ������������� ������.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }
  
  /**
   * �������� � ����� �������� ���� ����������.
   */
  private static void createAndShowGUI() {
    // ������� ���� ����������.
    JFrame frame = new JFrame("MouseEventDemo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // ������� ���������� ���� � ����������� ��� ����.
    frame.setContentPane(new MouseEventDemo());
    
    // �������� � ����� ����.
    frame.pack();
    frame.setVisible(true);
  }
 
  /**
   * �������� �������� ����������� ����.
   */
  public MouseEventDemo() {
    // ������ ������������ ��������� ���������� � ���� �������.
    super(new GridLayout(0,1));
    
    // ������ �������������, � ������� �������������� ������� �� ����.
    blankArea = new BlankArea(Color.YELLOW);
    add(blankArea);
    
    // ��������� �������, � ������� ��������� ��������� � ������������ ��������.
    textArea = new JTextArea();
    textArea.setFont(textArea.getFont().deriveFont(Font.PLAIN, 12));
    textArea.setEditable(false);
    // ��������� ������� ������ ���� "��������������", ������� ��� �����������
    // � ���������� ����������� ��� ����� ������ � �������� ���������.
    JScrollPane scrollPane = new JScrollPane(textArea);
    add(scrollPane);
    
    // ������������ ������� �� ���� ��� � ���������� ��������� ��� ����� �������,
    // ��� � �� ���� ���� ���������� (������, � ������� ��� �����).
    MouseListener ml = new MyMouseListener(textArea);
    blankArea.addMouseListener(ml);
    addMouseListener(ml);
    
    // ���������� ���������������� ������ � ������� ��� ������� ������.
    setPreferredSize(new Dimension(450, 450));
    setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
  }
  
  /**
   * "������" ��� ������� �� ����. ������� ��������������, �
   * ��������� � ��� ��������� � ��������� �������, ������������
   * ������������ "�������".
   */
  private static class MyMouseListener extends MouseAdapter {
    // ��������� ������� ��� ������ ��������� � ��������.
    final JTextArea textArea;
    
    /**
     * �����������.
     * @param ta - ��������� ������� ��� ������ ���������.
     */
    public MyMouseListener(JTextArea ta) { textArea = ta; }
    
    /**
     * ������� ��� ������ ���������.
     * @param eventDescription - ����� ��������� � �������.
     * @param e - �������.
     */
    void eventOutput(String eventDescription, MouseEvent e) {
      textArea.append("������� \"" + eventDescription + "\" ��������� � "
          + e.getComponent().getClass().getName() + "." + " Bubbles: " + NEWLINE);
      // ������������ ������� � ����� �������, ����� ������ ��������� ���������.
      textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    // ������� �� ������� ����� �������� ������� ������ ������� �������.
    public void mouseEntered(MouseEvent e) {
      eventOutput("���� �����", e);
    }
    
    // ������� �� ������� ������ �������� ������� �� ������� �������.
    public void mouseExited(MouseEvent e) {
      eventOutput("���� ��������", e);
    }
    
    // ������� �� ������� ������� ������ ���� ������ ������� �������.
    public void mouseClicked(MouseEvent e) {
      eventOutput("������ ���� � " + e.getButton() + " ������ "
          + e.getClickCount() + " ���", e);
    }
  }
}
